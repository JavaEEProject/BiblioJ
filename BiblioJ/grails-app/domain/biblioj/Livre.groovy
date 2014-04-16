package biblioj

class Livre {
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles

	static constraints = {
	}
	
	static hasMany = [auteurs: Auteur, reservations: Reservation]

	String toString(){
		titre
	}
}
