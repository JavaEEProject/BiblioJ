package biblioj

class Auteur {

	String nom;
	String prenom;

	static constraints = {
	}

	static hasMany = [livres: Livre]
	
	String toString(){
		nom + ' ' + prenom
	}
}
