package co.edu.ufps.parcial.dao;

import java.sql.SQLException;
import java.util.List;

import co.edu.ufps.parcial.modelo.Team;



public interface TeamDao {
	public void insert(Team team) throws SQLException;
	public Team select(String id);
	public List<Team> selectAll();
	public void delete(String id) throws SQLException;
	public void update(Team team) throws SQLException;
	
}
