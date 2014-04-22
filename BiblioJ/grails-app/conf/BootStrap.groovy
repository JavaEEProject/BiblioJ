import biblioj.Auteur
import biblioj.ChargementDonneesDepuisCSV;
import biblioj.Livre
import biblioj.Reservation
import biblioj.TypeDocument
import liquibase.util.csv.opencsv.CSVReader

class BootStrap {

	def init = { servletContext ->
		println "yoy"
		ChargementDonneesDepuisCSV parser = new ChargementDonneesDepuisCSV()
		parser.loadCSVToBD("corpus.csv")

		/*
		 def auteur2 = new Auteur(nom: "Guzzetta", prenom: "Marc").save()
		 def auteur1 = new Auteur(nom: "Dax", prenom: "Marc").save()
		 def auteur2 = new Auteur(nom: "Guzzetta", prenom: "Marc").save()
		 def auteur3 = new Auteur(nom: "Levy", prenom: "Marc").save()
		 def auteur4 = new Auteur(nom: "Einstein", prenom: "Albert").save()
		 def typeDocument1 = new TypeDocument(intitule: "Article de presse").save()
		 def typeDocument2 = new TypeDocument(intitule: "Manga").save()
		 def typeDocument3 = new TypeDocument(intitule: "Roman").save()
		 def typeDocument4 = new TypeDocument(intitule: "BD").save()
		 def defaultReservation = new Reservation(code: 0, dateReservation: new Date()).save()
		 def livre = new Livre(titre: "La chute du faucon bleu", nombreExemplaires:11, nombreExemplairesDisponibles: 11, typeDocument: typeDocument1).addMesAuteurs(auteur1).addMesAuteurs(auteur2).addReservations(defaultReservation).save()
		*/
		println "Nombre de livre chargé dans la base = " + Livre.count()
		println "Nombre d'auteur chargé dans la base = " + Auteur.count()
		println "Nombre de type doc chargé dans la base = " + TypeDocument.count()
	}

	def destroy = {
	}
}
