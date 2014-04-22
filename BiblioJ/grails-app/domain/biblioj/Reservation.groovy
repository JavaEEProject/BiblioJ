package biblioj

class Reservation {

    int code
    Date dateReservation = new Date()
    // relation many-to-many
    static hasMany = [livres: Livre]
    static fetchMode = [livres: 'eager']
    
    static constraints = {
		code (min:0, max:999999, unique:true)
		dateReservation validator: {it >= (new Date() - 1)}
    }
    
    String toString(){
	"[$code] $dateReservation"
    }
}
