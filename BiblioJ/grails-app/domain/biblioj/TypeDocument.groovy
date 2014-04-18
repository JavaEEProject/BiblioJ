package biblioj

class TypeDocument {

	String intitule;

	static constraints = {
		intitule(size:2..50, blank:false, nullable:false)
	}

	static hasmany = [livres: Livre]
	
	String toString(){
		intitule
	}
}
