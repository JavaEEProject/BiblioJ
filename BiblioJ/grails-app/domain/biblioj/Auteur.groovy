package biblioj

class Auteur {

	String nom
	String prenom
	// relation many-to-many
	static hasMany = [livres: Livre]
	
	static constraints = {
		nom(size: 2..30, blank:false, nullable:false)
		prenom(size: 2..30, blank:false, nullable:false)
	}

	

	String toString(){
		nom + ' ' + prenom
	}
}
