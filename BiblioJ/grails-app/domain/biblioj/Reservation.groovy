package biblioj

class Reservation {

<<<<<<< HEAD
	int code;
	Date dateReservation;

	static constraints = {
	}

	//static belongsTo = User
	static hasMany = [livres: Livre]
	
	String toString(){
		"[$code] $dateReservation"
	}
=======
    int code;
    Date dateReservation;

    static constraints = {
    }

    String toString(){
	"[$code] $dateReservation"
    }
>>>>>>> e91c7d03b3d3aab3b0eff5e473e1fde6abf99261
}
