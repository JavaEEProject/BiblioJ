package biblioj

class Livre {
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles

	static constraints = {
	}
	
	static hasMany = [auteurLivres: AuteurLivre]

	String toString(){
		titre
	}
}
