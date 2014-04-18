package biblioj

class Auteur {

	String nom;
	String prenom;

	static constraints = {
	}

	// relation many-to-many
	static hasMany = [livres: Livre]

	String toString(){
		nom + ' ' + prenom
	}
}
