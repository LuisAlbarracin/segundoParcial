package co.edu.ufps.parcial.dao;

import java.sql.SQLException;
import java.util.List;

import co.edu.ufps.parcial.modelo.Country;



public interface CountryDao {
	public void insert(Country country) throws SQLException;
	public Country select(String id);
	public List<Country> selectAll();
	public void delete(String id) throws SQLException;
	public void update(Country country) throws SQLException;
	
}
