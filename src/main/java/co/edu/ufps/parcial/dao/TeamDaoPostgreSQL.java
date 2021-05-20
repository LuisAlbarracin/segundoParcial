package co.edu.ufps.parcial.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.ufps.parcial.modelo.Team;
import co.edu.ufps.parcial.util.ConexionPostgreSQL;

public class TeamDaoPostgreSQL implements TeamDao {
	
	private ConexionPostgreSQL conexion;


	private static final String INSERT_TEAM_SQL = "INSERT INTO team (id,name, country) VALUES (?, ?, ?);";
	private static final String DELETE_TEAM_SQL = "DELETE FROM team WHERE id = ?;";
	private static final String UPDATE_TEAM_SQL = "UPDATE team SET name = ?, country = ?  WHERE id = ?;";
	private static final String SELECT_TEAM_BY_ID = "SELECT * FROM team WHERE id = ?;";
	private static final String SELECT_ALL_TEAM_SQL = "SELECT * FROM team;";

	public TeamDaoPostgreSQL() {
		this.conexion = ConexionPostgreSQL.getConexion();
	}
	
	@Override
	public void insert(Team team) throws SQLException {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_TEAM_SQL);
			preparedStatement.setString(1, team.getId());
			preparedStatement.setString(2, team.getName());
			preparedStatement.setString(2, team.getCountry());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Team select(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Team> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws SQLException {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_TEAM_SQL);
			preparedStatement.setString(1, id);

			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Team team) throws SQLException {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_TEAM_SQL);
			preparedStatement.setString(2, team.getName());
			preparedStatement.setString(2, team.getCountry());
			preparedStatement.setString(4, team.getId());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
