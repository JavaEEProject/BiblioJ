package biblioj

class TypeDocument {

	String intitule
	//Relation one-to-many
	static hasmany = [livres: Livre]

	static constraints = {
		intitule(size:2..50, blank:false, nullable:false)
	}

	String toString(){
		intitule
	}
}
