package biblioj

class Livre {
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles
	// relation many-to-many
	static hasMany = [auteurs: Auteur, reservations: Reservation]
	static belongsTo = [Auteur]
	// Relation many-to-one
	TypeDocument typeDocument
	
	static constraints = {
		titre(size:2..60, blank:false, nullable:false)
		nombreExemplaires(min:1, max:9999)
		//nombreExemplairesDisponibles validator: {val, obj -> if(obj.nombreExemplaires < val) ['error']}
		nombreExemplairesDisponibles validator: {val, obj -> obj.nombreExemplaires >= val}
	}
	
	String toString(){
		titre
	}
}
