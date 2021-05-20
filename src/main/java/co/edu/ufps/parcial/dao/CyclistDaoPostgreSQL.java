package co.edu.ufps.parcial.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.ufps.parcial.modelo.Country;
import co.edu.ufps.parcial.modelo.Cyclist;
import co.edu.ufps.parcial.modelo.Team;
import co.edu.ufps.parcial.util.ConexionPostgreSQL;

public class CyclistDaoPostgreSQL implements CyclistDao {
	
	private ConexionPostgreSQL conexion;

	private static final String INSERT_CYCLIST_SQL = "INSERT INTO cyclist (name,email,birthday, country, team) VALUES (?, ?, ?, ?, ?);";
	private static final String DELETE_CYCLIST_SQL = "DELETE FROM cyclist WHERE id = ?;";
	private static final String UPDATE_CYCLIST_SQL = "UPDATE cyclist SET name = ?, email = ?, birthday = ?, country = ?, team = ? WHERE id = ?;";
	private static final String SELECT_CYCLIST_BY_ID = "SELECT * FROM cyclist WHERE id = ?;";
	private static final String SELECT_ALL_CYCLIST_SQL = "SELECT * FROM cyclist;";

	
	public CyclistDaoPostgreSQL() {
		this.conexion = ConexionPostgreSQL.getConexion();
	}
	
	@Override
	public void insert(Cyclist cyclist) throws SQLException {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_CYCLIST_SQL);
			preparedStatement.setString(1, cyclist.getName());
			preparedStatement.setString(2, cyclist.getEmail());
			preparedStatement.setDate(3, (Date) cyclist.getBirthdate());
			preparedStatement.setString(4, cyclist.getCountry().getId());
			preparedStatement.setString(5, cyclist.getTeam().getId());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Cyclist select(int id) {
		// TODO Auto-generated method stub
		Cyclist cyclist = null;
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_CYCLIST_BY_ID);
			preparedStatement.setInt(1,id);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				String country = rs.getString("country");
				String team = rs.getString("team");
				
				cyclist = new Cyclist(id, name, email, birthday, new Country(country), new Team(team));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cyclist;
	}

	@Override
	public List<Cyclist> selectAll() {
		// TODO Auto-generated method stub
		List<Cyclist> cyclistes = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_CYCLIST_SQL);
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				String country = rs.getString("country");
				String team = rs.getString("team");
				
				cyclistes.add(new Cyclist(id, name, email, birthday,new Country(country), new Team(team)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cyclistes;
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_CYCLIST_SQL);
			preparedStatement.setInt(1, id);

			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Cyclist cyclist) throws SQLException {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_CYCLIST_SQL);
			preparedStatement.setString(1, cyclist.getName());
			preparedStatement.setString(2, cyclist.getEmail());
			preparedStatement.setDate(3, (Date) cyclist.getBirthdate());
			preparedStatement.setString(4, cyclist.getCountry().getId());
			preparedStatement.setString(5, cyclist.getTeam().getId());
			preparedStatement.setInt(6, cyclist.getId());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
