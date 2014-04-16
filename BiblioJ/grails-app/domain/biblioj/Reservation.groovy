package biblioj

class Reservation {

	int code;
	Date dateReservation;

	static constraints = {
	}

	//static belongsTo = User
	static hasMany = [livres: Livre]
	
	String toString(){
		"[$code] $dateReservation"
	}
}
