package database;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import exception.DbException;

public class DataBaseConnection {
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			String url = getProps().getProperty("dburl");
			String user = getProps().getProperty("user");
			String pass = getProps().getProperty("password");
			
			try{
				
				conn = DriverManager.getConnection(url, user, pass);
			} catch(SQLException error) {
				throw new DbException("Error ao se conectar com db: " + error.getMessage());
			}
		}
		
		return conn;
	}
	
	private static Properties getProps() {
		
		Properties props = new Properties();
		try(FileReader rd = new FileReader(new File("db.properties"))){
			props.load(rd);			
		} catch (IOException error) {
			throw new DbException("Erro ao encontra arquivos (props): " + error.getMessage());
		}
		
		return props;
		
	}
	
}
