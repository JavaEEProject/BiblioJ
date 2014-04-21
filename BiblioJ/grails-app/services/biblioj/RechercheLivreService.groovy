package biblioj

class RechercheLivreService {

	def rechercheLivre(Map params, Reservation reservation) {
		def titreRecherche  = params.champRechercheLivreParTitre
		def auteurRecherche = params.champRechercheLivreParAuteur
		def typeDocumentRecherche = params.champRechercheLivreParTypeDocument

		int offsetResultat = Integer.valueOf(params.offset)
		if (!params.offset) {
			offsetResultat = 0  // 5
		}

		int maxResultat = Integer.valueOf(params.max)
		if (!params.max) {
			maxResultat = 0 // 5
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
				createAlias ("type", "typeDoc")
				ilike("typeDocument.intitule", "%$typeRecherche")
			}
		}
		[listLivre: livres, nbLivre: livres.totalCount, reservation : reservation]
	}
}
