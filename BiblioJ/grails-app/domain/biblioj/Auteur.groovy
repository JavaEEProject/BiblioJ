package biblioj

class Auteur {

<<<<<<< HEAD
	String nom;
	String prenom;

	static constraints = {
	}

	static hasMany = [livres: Livre]
	
	String toString(){
		nom + ' ' + prenom
	}
=======
    String nom;
    String prenom;

    static constraints = {
    }

    String toString(){
	nom + ' ' + prenom
    }
>>>>>>> e91c7d03b3d3aab3b0eff5e473e1fde6abf99261
}
