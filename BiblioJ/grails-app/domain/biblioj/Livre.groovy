package biblioj

class Livre {
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles

	// Relation many-to-one
	TypeDocument typeDocument
	
	static constraints = {
		titre(size:2..60, blank:false, nullable:false)
		nombreExemplaires(min:1, max:9999)
		//nombreExemplairesDisponibles validator: {val, obj -> if(obj.nombreExemplaires < val) ['error']}
		nombreExemplairesDisponibles validator: {val, obj -> obj.nombreExemplaires >= val}
	}
	
	// relation many-to-many
	static belongsTo  = [auteurs: Auteur, reservations: Reservation]
	static hasMany = [auteurs: Auteur, reservations: Reservation]

	String toString(){
		titre
	}
}
