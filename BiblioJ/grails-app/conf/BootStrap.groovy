import biblioj.Auteur
import biblioj.Reservation
import biblioj.TypeDocument

class BootStrap {

    def init = { servletContext ->
		def auteur1 = new Auteur(nom: "Dax", prenom: "Marc").save()
		def auteur2 = new Auteur(nom: "Guzzetta", prenom: "Marc").save()
		def auteur3 = new Auteur(nom: "Levy", prenom: "Marc").save()
		def auteur4 = new Auteur(nom: "Einstein", prenom: "Albert").save()
		
		def typeDocument1 = new TypeDocument(intitule: "Article de presse").save()
		def typeDocument2 = new TypeDocument(intitule: "Manga").save()
		def typeDocument3 = new TypeDocument(intitule: "Roman").save()
		def typeDocument4 = new TypeDocument(intitule: "BD").save()
		
		// Est requis pour pouvoir simuler la relation many-to-many entre livre et reservation 
		// dans le cas ou il n'existe pas de reservation, on ne peut pas creer de livre (ce qui est un peu bête)
		// du coup on déclare une reservation par defaut qui est reconnu à l'affichage par code 0 et qui affiche "aucune"
		def defaultReservation = new Reservation(code: 0).save()
		
    }
    def destroy = {
    }
}
