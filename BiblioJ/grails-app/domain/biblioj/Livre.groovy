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
		// TODO contrainte sur nombreExemplairesDisponibles : doit forcement etre inférieur ou egale à nombreExemplaires
		//nombreExemplairesDisponibles(min:1, validate : {return (it <= Livre.nombreExemplaires)}
	}
	
	// relation many-to-many
	static belongsTo  =   [auteurs: Auteur, reservations: Reservation]
	static hasMany = [auteurs: Auteur, reservations: Reservation]

	String toString(){
		titre
	}
}
