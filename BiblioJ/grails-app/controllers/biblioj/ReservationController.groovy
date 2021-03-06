package biblioj

import org.springframework.dao.DataIntegrityViolationException

class ReservationController {

	// il n'y aura ici un espace de recherche de reservation via l'id de la reservation du client
	// il entrera son id de reservation et pourra la consulter si elle existe

	// Apr�s la redirection de la view livre controller par LivreController
	// il pourra ajouter � sa reservation le livre qu'il avait choisis.
	// si il poss�de d�j� un reservation, il pr�cise son id de reservation dans un champs pr�vu pour
	// sinon, une nouvelle reservation est cr�� au moment de valider sa reservation

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	static int codeReservation = 0
	def nextCodeReservation(){
		codeReservation++
	}
	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[reservationInstanceList: Reservation.list(params), reservationInstanceTotal: Reservation.count()]
	}

	def create() {
		if(session['user'] != null && session['user']?.size() > 0){
			Reservation reservationI = new Reservation(code: nextCodeReservation(), dateReservation: params?.dateReservation)
			println session['user']
			session['msgErreur'] = false
			
			List<Livre> livres = session['user']		
			livres.each{
				if(it.nombreExemplairesDisponibles > 0 ) {
					it.nombreExemplairesDisponibles --
					reservationI.addToLivres(Livre.findWhere(titre: it.titre))
				} else {
					// on affiche un message d'erreur, tout n'a pas pu etre reserve
					session['msgErreur'] = true
				}	
			}
			return [reservationInstance: reservationI]
		}else{
			redirect(action: "list", controller : "livre")
		}
	}

	def save() {
		def reservationInstance = new Reservation(params)
		if (!reservationInstance.save(flush: true)) {
			render(view: "create", model: [reservationInstance: reservationInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'reservation.label', default: 'Reservation'),
			reservationInstance.id
		])
		redirect(action: "show", id: reservationInstance.id)
	}

	def show(Long id) {
		def reservationInstance = Reservation.get(id)
		if (!reservationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'reservation.label', default: 'Reservation'),
				id
			])
			redirect(action: "list")
			return
		}

		[reservationInstance: reservationInstance]
	}

	def edit(Long id) {
		def reservationInstance = Reservation.get(id)
		if (!reservationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'reservation.label', default: 'Reservation'),
				id
			])
			redirect(action: "list")
			return
		}

		[reservationInstance: reservationInstance]
	}

	def update(Long id, Long version) {
		def reservationInstance = Reservation.get(id)
		if (!reservationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'reservation.label', default: 'Reservation'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (reservationInstance.version > version) {
				reservationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'reservation.label', default: 'Reservation')] as Object[],
						"Another user has updated this Reservation while you were editing")
				render(view: "edit", model: [reservationInstance: reservationInstance])
				return
			}
		}

		reservationInstance.properties = params

		if (!reservationInstance.save(flush: true)) {
			render(view: "edit", model: [reservationInstance: reservationInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'reservation.label', default: 'Reservation'),
			reservationInstance.id
		])
		redirect(action: "show", id: reservationInstance.id)
	}

	def delete(Long id) {
		def reservationInstance = Reservation.get(id)
		if (!reservationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'reservation.label', default: 'Reservation'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			reservationInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'reservation.label', default: 'Reservation'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'reservation.label', default: 'Reservation'),
				id
			])
			redirect(action: "show", id: id)
		}
	}

	def addLivre(Long id) {
		Livre livreInstance = Livre.get(id)

		if(!session['user']){
			session['user'] = []
		}
		session['user'] << livreInstance
		redirect(action: "list", controller : "livre")
	}

	def removeLivre(Long id) {
		int index = id
		def livreInstanceList = session['user']
		Livre livreInstance = livreInstanceList[index]
		println "index" +  index
		println "size = " + session['user'].size()
		
		if(session['user'].size() > 1) session['user'].remove(index-1)
		else session.removeAttribute('user')
		redirect(action: "list", controller : "livre")
	}

	
	def saveLivre() {
		def reservationInstance = new Reservation(params)
		if (!reservationInstance.save(flush: true)) {
			render(view: "create", model: [reservationInstance: reservationInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'reservation.label', default: 'Reservation'),
			reservationInstance.id
		])
		redirect(action: "show", id: reservationInstance.id)
	}
}
