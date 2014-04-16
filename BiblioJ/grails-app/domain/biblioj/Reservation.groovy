package biblioj

class Reservation {
	
	int code;
	Date dateReservation;
	
    static constraints = {
    
	}
	
	String toString(){
		"[$code] $dateReservation"
	}
}
