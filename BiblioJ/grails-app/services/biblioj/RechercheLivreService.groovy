package biblioj

class RechercheLivreService {

	def rechercheLivre(Map params) {
		def titreRecherche  = params.champRechercheLivreParTitre
		def auteurRecherche = params.champRechercheLivreParAuteur
		def typeDocumentRecherche = params.champRechercheLivreParTypeDocument

		def offsetResultat = 5
		def maxResultat = 5
		def criteria = Livre.createCriteria()
		def livres = criteria.list(offset: offsetResultat, max: maxResultat) {
			if(titreRecherche) {
				ilike("titre", '%' + titreRecherche + '%')
			}

			if(auteurRecherche) {
				mesAuteurs {
					or {
						ilike("nom", '%' + auteurRecherche + '%')
						ilike("prenom", '%' + auteurRecherche + '%')
					}
				}
			}

			if(typeDocumentRecherche) {
				createAlias ("type", "typeDoc")
				ilike("typeDocument.intitule", "%$typeRecherche")
			}
		}
		[listLivre: livres, nbLivre: livres.totalCount]
	}
}
