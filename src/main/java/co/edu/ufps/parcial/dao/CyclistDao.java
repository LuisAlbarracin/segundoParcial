package co.edu.ufps.parcial.dao;

import java.sql.SQLException;
import java.util.List;

import co.edu.ufps.parcial.modelo.Cyclist;



public interface CyclistDao {
	public void insert(Cyclist cyclist) throws SQLException;
	public Cyclist select(int id);
	public List<Cyclist> selectAll();
	public void delete(int id) throws SQLException;
	public void update(Cyclist cyclist) throws SQLException;
	
}
