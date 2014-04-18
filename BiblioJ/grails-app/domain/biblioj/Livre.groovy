package biblioj

class Livre {
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles

	// Relation many-to-one
	TypeDocument typeDocument
	
	static constraints = {
	}
	
	// relation many-to-many
	static belongsTo  =   [auteurs: Auteur, reservations: Reservation]
	static hasMany = [auteurs: Auteur, reservations: Reservation]

	String toString(){
		titre
	}
}
