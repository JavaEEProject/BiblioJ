package biblioj

import liquibase.util.csv.opencsv.CSVReader

class ChargementDonneesDepuisCSV {

	def parser(String TEST_FILE_NAME){

		List<String[]> rows = new CSVReader(new InputStreamReader(getClass().classLoader.getResourceAsStream(TEST_FILE_NAME))).readAll()

		//same finders as in the Groovy version
		/*def elementsOver200Mass = rows.findAll {it[ATOMIC_MASS].toDouble() > 200}
		def elementsBetween10And20 = rows.findAll { row ->
			double mass = castToDouble(row[ATOMIC_MASS])
			mass <= 20 && mass >= 10
		}*/
	}
}
