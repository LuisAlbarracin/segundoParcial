package co.edu.ufps.parcial.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.ufps.parcial.modelo.Country;
import co.edu.ufps.parcial.util.ConexionPostgreSQL;

public class CountryDaoPostgreSQL implements CountryDao {
	
	private ConexionPostgreSQL conexion;

	private static final String INSERT_COUNTRY_SQL = "INSERT INTO country (id,name) VALUES (?, ?);";
	private static final String DELETE_COUNTRY_SQL = "DELETE FROM country WHERE id = ?;";
	private static final String UPDATE_COUNTRY_SQL = "UPDATE country SET name = ? WHERE id = ?;";
	private static final String SELECT_COUNTRY_BY_ID = "SELECT * FROM country WHERE id = ?;";
	private static final String SELECT_ALL_COUNTRY_SQL = "SELECT * FROM country;";

	public CountryDaoPostgreSQL() {
		this.conexion = ConexionPostgreSQL.getConexion();
	}
	
	@Override
	public void insert(Country country) throws SQLException {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_COUNTRY_SQL);
			preparedStatement.setString(1, country.getId());
			preparedStatement.setString(2, country.getName());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Country select(String id) {
		// TODO Auto-generated method stub
		Country country = null;
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_COUNTRY_BY_ID);
			preparedStatement.setString(1,id);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String name = rs.getString("name");
				
				country = new Country(id, name);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return country;
	}

	@Override
	public List<Country> selectAll() {
		// TODO Auto-generated method stub
		List<Country> countries = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_COUNTRY_SQL);
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				
				countries.add(new Country(id, name));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return countries;
	}

	@Override
	public void delete(String id) throws SQLException {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_COUNTRY_SQL);
			preparedStatement.setString(1, id);

			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Country country) throws SQLException {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_COUNTRY_SQL);
			preparedStatement.setString(1, country.getName());
			preparedStatement.setString(2, country.getId());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
