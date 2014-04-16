package biblioj

class Auteur {

	String nom;
	String prenom;

	static constraints = {
	}

	static hasMany = [auteurLivres: AuteurLivre, livres: Livre]

	String toString(){
		nom + ' ' + prenom
	}
}
