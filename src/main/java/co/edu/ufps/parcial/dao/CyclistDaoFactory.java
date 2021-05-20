package co.edu.ufps.parcial.dao;



public class CyclistDaoFactory {
	public static CyclistDao getCyclistDao(String type) {
		switch(type) {
		case "mysql":
			return new CyclistDaoMySQL();
		
		case "postgresql":
			return new CyclistDaoPostgreSQL();
			
		default:
			return new CyclistDaoPostgreSQL();
		}
	}
}
