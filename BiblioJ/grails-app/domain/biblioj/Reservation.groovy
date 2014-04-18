package biblioj

class Reservation {

	int code;
	Date dateReservation;

	static constraints = {
		code (min:0, max:999999, unique:true)
		//TOTO contrainte qui suit ne fonctionne pas, checker pk
		//dateReservation : validator: {return (it >= new Date())}
	}

	// relation many-to-many
	static hasMany = [livres: Livre]
	
	String toString(){
		"[$code] $dateReservation"
	}
}
