package biblioj

import org.springframework.dao.DataIntegrityViolationException

class LivreController {
	
	// ici l'utilisateur pourra rechercher des livres et sera redirig� vers une page de reservation s'il clique dessu
	// entre autre vers la view Reservation qui sera g�r� par ReservationController

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

	def rechercher() {
		//TODO bouton qui appelle apr�s que l'utilisateur 
		// ait rempli les champs ad�quat (� �diter dans la view list.gsp ou creer une nouvelle vue)
		// les m�thodes de recherche d�clar� dans les services sous le nom de RechercheLivre.groovy
		// rappel : le controleur ne fait aucun traitement sur les donn�es, 
		// il g�re juste l'actualisation de l'affichage (le render)
		
		 
	}
	
    def list(Integer max) {
		RechercheLivreService livreResultatList = new RechercheLivreService()
		params.max = Math.min(max ?: 5, 100)
		livreResultatList.rechercheLivre(params)
        /*params.max = Math.min(max ?: 5, 100)
        [livreInstanceList: Livre.list(params), livreInstanceTotal: Livre.count()]*/
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

        flash.message = message(code: 'default.created.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
        redirect(action: "show", id: livreInstance.id)
    }

    def show(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        [livreInstance: livreInstance]
    }
}
