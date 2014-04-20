package biblioj

class Auteur {

	String nom
	String prenom
	// relation many-to-many
	static hasMany = [livresEcrit: Livre]
	
	static constraints = {
		nom(size: 1..30, blank:false, nullable:false)
		prenom(size: 1..30, blank:false, nullable:false)
	}


	String toString(){
		nom + ' ' + prenom
	}
}
