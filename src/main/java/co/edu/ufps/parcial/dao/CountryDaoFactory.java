package co.edu.ufps.parcial.dao;

public class CountryDaoFactory {
	public static CountryDao getCountryDao(String type) {
		switch(type) {
		case "mysql":
			return new CountryDaoMySQL();
		
		case "postgresql":
			return new CountryDaoPostgreSQL();
			
		default:
			return new CountryDaoPostgreSQL();
		}
	}
}
