package co.edu.ufps.parcial.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConexionMySQL {
	private Connection con = null;
	private static ConexionMySQL db;
	private PreparedStatement preparedStatement;
	
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DBNAME = "ciclismo";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	private ConexionMySQL() {
		
		try {
			Class.forName(DRIVER);
			con = (Connection)DriverManager.getConnection(URL+DBNAME,USERNAME,PASSWORD);
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() {
		try {
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ConexionMySQL getConexion() {
		if(db==null) {
			db = new ConexionMySQL();
		}
		
		return db;
	}
	
	public ResultSet query() throws SQLException {
		ResultSet res = preparedStatement.executeQuery();
		return res;
	}
	
	public int execute() throws SQLException {
		int result = preparedStatement.executeUpdate();
		return result;
	}
	
	public Connection getCon() {
		return this.con;
	}
	
	public PreparedStatement setPreparedStatement(String sql) throws SQLException {
		this.preparedStatement = con.prepareStatement(sql);
		return preparedStatement;
	}
	
}
