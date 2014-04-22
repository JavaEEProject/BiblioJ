package biblioj

import org.springframework.dao.DataIntegrityViolationException

class LivreController {

	// ici l'utilisateur pourra rechercher des livres et sera redirig� vers une page de reservation s'il clique dessu
	// entre autre vers la view Reservation qui sera g�r� par ReservationController

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 5, 1000)
		if(params?.champRechercheLivreParTypeDocument == null && params?.champRechercheLivreParAuteur == null && params?.champRechercheLivreParTitre == null)	{
			
			return [livreInstanceList: Livre.list(params), livreInstanceTotal: Livre.count()]
		} else {
			RechercheLivreService livreResultatList = new RechercheLivreService()
			return livreResultatList.rechercheLivre(params)
		}
	}

	def create() {
		[livreInstance: new Livre(params)]
	}

	def save() {
		def livreInstance = new Livre(params)
		if (!livreInstance.save(flush: true)) {
			render(view: "create", model: [livreInstance: livreInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'livre.label', default: 'Livre'),
			livreInstance.id
		])
		redirect(action: "show", id: livreInstance.id)
	}

	def show(Long id) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
			return
		}

		[livreInstance: livreInstance]
	}
}
