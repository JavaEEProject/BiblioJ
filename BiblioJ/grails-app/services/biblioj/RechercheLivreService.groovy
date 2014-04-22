package biblioj

class RechercheLivreService {

	def rechercheLivre(Map params) {
		def titreRecherche  = params.champRechercheLivreParTitre
		def auteurRecherche = params.champRechercheLivreParAuteur
		def typeDocumentRecherche = params.champRechercheLivreParTypeDocument

		int offsetResultat = 0
		int maxResultat = 0
		
		if (params.offset) {
			offsetResultat = Integer.valueOf(params.offset)
		}
		if (params.max) {
			maxResultat = Integer.valueOf(params.max)
		}
		
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
				createAlias ("typeDocument", "typeDoc")
				ilike("typeDoc.intitule", "%$typeDocumentRecherche")
			}
		}
		[livreInstanceList: livres, livreInstanceTotal: livres.totalCount]
	}
}
