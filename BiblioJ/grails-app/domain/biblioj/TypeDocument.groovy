package biblioj

class TypeDocument {

	String intitule;

	static constraints = {
	}

	static hasmany = [livres: Livre]
	
	String toString(){
		intitule
	}
}
