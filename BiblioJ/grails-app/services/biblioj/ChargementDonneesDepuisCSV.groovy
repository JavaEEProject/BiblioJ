package biblioj

import liquibase.util.csv.opencsv.CSVReader

class ChargementDonneesDepuisCSV {

	private static final int RANG = 0
	private static final int TYPE_DOC = 1
	private static final int NB_RECHERCHES = 2
	private static final int TITRE = 3
	private static final int AUTEUR = 4
	private static final int NB_LIVRE_DISPONIBLE_PAR_DEFAUT = 10
	private static final String INFO_ABSENTE = "NC"
	
	def loadCSVToBD(String fileName){
		if(new File(fileName).exists()) {
			def auteurTmp
			def livreTmp
			def typeDocTmp
			boolean premiereLignePasse = false
			int i = 0;
			new File(fileName).splitEachLine(";") { fields ->
				if(!premiereLignePasse){
					// on saute la premiere ligne du fichier csv
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
				}catch(Exception e){
					i++
				}
			}
			println "Nombre d'erreur de chargement : " + i
		} else {
			println "Attention ! Aucun corpus csv n'a été  trouvé dans le repertoire courant " + System.getProperty("user.dir" )
		}
	}
}
