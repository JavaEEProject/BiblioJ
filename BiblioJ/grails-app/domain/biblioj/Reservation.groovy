package biblioj

class Reservation {

	int code;
	Date dateReservation;

	static constraints = {
	}

	// relation many-to-many
	static hasMany = [livres: Livre]
	
	String toString(){
		"[$code] $dateReservation"
	}
}
