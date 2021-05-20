package co.edu.ufps.parcial.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.ufps.parcial.modelo.Cyclist;
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
		return null;
	}

	@Override
	public List<Cyclist> selectAll() {
		// TODO Auto-generated method stub
		return null;
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
