package biblioj

class Livre {
<<<<<<< HEAD
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles

	static constraints = {
	}
	
	static hasMany = [auteurs: Auteur, reservations: Reservation]

	String toString(){
		titre
	}
=======
    String titre
    int nombreExemplaires
    int nombreExemplairesDisponibles

    static constraints = {
    }

    String toString(){
	titre
    }
>>>>>>> e91c7d03b3d3aab3b0eff5e473e1fde6abf99261
}
