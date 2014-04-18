package biblioj

class Auteur {

	String nom;
	String prenom;

	static constraints = {
		nom(size: 2..30, blank:false, nullable:false)
		prenom(size: 2..30, blank:false, nullable:false)
	}

	// relation many-to-many
	static hasMany = [livres: Livre]

	String toString(){
		nom + ' ' + prenom
	}
}
