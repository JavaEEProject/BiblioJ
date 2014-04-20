import biblioj.Auteur
import biblioj.ChargementDonneesDepuisCSV;
import biblioj.Livre
import biblioj.Reservation
import biblioj.TypeDocument
import liquibase.util.csv.opencsv.CSVReader

class BootStrap {

	private static final int RANG = 0
	private static final int TYPE_DOC = 1
	private static final int NB_RECHERCHES = 2
	private static final int TITRE = 3
	private static final int AUTEUR = 4
	private static final int NB_LIVRE_DISPONIBLE_PAR_DEFAUT = 10
	private static final String INFO_ABSENTE = "NC"

	def init = { servletContext ->

		if(new File("corpus.csv").exists()) {

			/*List<String[]> rows = new CSVReader(new FileReader("corpus.csv")).readAll()
			 rows.each {
			 println "coucou"
			 println it[Type_de_document]
			 }*/
			def auteurTmp
			def livreTmp
			def typeDocTmp
			boolean premiereLignePasse = false
			int i = 0;
			new File("corpus.csv").splitEachLine(";") { fields ->
				if(!premiereLignePasse){ // on saute la premiere ligne du fichier csv
					premiereLignePasse = true
					return
				}

				String auteurStr  = fields[AUTEUR]
				String typeDocStr = fields[TYPE_DOC]
				String nbRechercheStr = fields[NB_RECHERCHES]
				String titreStr   = fields[TITRE]

				String nom = INFO_ABSENTE
				String prenom = INFO_ABSENTE

				if(auteurStr == null || auteurStr == ""){
					auteurStr = INFO_ABSENTE
				} else {
					if(auteurStr.contains(", "))
						(nom, prenom) = auteurStr.tokenize(", ")
					else {
						nom = auteurStr
						prenom = INFO_ABSENTE
					}
				}
				if(typeDocStr == null || typeDocStr == ""){
					typeDocStr = INFO_ABSENTE
				}
				if(nbRechercheStr == null || nbRechercheStr == ""){
					nbRechercheStr = INFO_ABSENTE
				}
				if(titreStr == null || titreStr == ""){
					titreStr = INFO_ABSENTE
				}

				if(Auteur.findWhere(nom : nom, prenom: prenom) == null){
					auteurTmp = new Auteur(nom: nom, prenom: prenom).save()
				}
				if(TypeDocument.findWhere(intitule : typeDocStr) == null){
					typeDocTmp = new TypeDocument(intitule: typeDocStr).save()
				}
				livreTmp = new Livre(titre: titreStr, nombreExemplaires: NB_LIVRE_DISPONIBLE_PAR_DEFAUT, nombreExemplairesDisponibles: NB_LIVRE_DISPONIBLE_PAR_DEFAUT, typeDocument: TypeDocument.findWhere(intitule : typeDocStr)).save()

				try{
					livreTmp.addToMesAuteurs(Auteur.findWhere(nom : nom, prenom: prenom))
					auteurTmp.addToLivresEcrit(Livre.findWhere(titre: titreStr, typeDocument: TypeDocument.findWhere(intitule : typeDocStr)))
					//typeDocTmp.addToLivresAssocies(livreTmp)
				}catch(Exception e){
					i++
				}
				
			}
			println "nombre d'erreur = " + i
		} else {
			println "Attention ! Aucun corpus csv n'a été  trouvé dans le repertoire courant " + System.getProperty("user.dir" )
		}

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
		 // Est requis pour pouvoir simuler la relation many-to-many entre livre et reservation
		 // dans le cas ou il n'existe pas de reservation, on ne peut pas creer de livre (ce qui est un peu b�te)
		 // du coup on d�clare une reservation par defaut qui est reconnu � l'affichage par code 0 et qui affiche "aucune"
		 //def defaultReservation = new Reservation(code: 0, dateReservation: new Date()).save()
		 // TODO Ne fonctionne pas encore, modifier les relations
		 //def livre = new Livre(titre: "La chute du faucon bleu", nombreExemplaires:11, nombreExemplairesDisponibles: 11, typeDocument: typeDocument1).addAuteur(auteur1).addAuteur(auteur2).addReservation(defaultReservation).save()
		 */
		println "Nombre de livre chargé dans la base = " + Livre.count()
		println "Nombre d'auteur chargé dans la base = " + Auteur.count()
		println "Nombre de type doc chargé dans la base = " + TypeDocument.count()
	}

	def destroy = {
	}
}
