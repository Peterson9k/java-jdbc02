package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DataBaseConnection;
import entities.Cliente;
import services.DaoService;

public class ClienteDao implements DaoService<Cliente>{
	
	private Connection conn;
	
	public ClienteDao() {
		this.conn = DataBaseConnection.getConnection();
	}
	
	// user add
	@Override
	public boolean add(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO clientes (nome, email, telefone) VALUE (?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setInt(3, cliente.getTelefone());
			
			int rows = stmt.executeUpdate();
			return rows > 0;
		}
		
	}
	
	// user update
	@Override
	public boolean update(Cliente cliente, Integer id) throws SQLException{

		String sql = "UPDATE clientes SET nome=?,email=?,telefone=? WHERE id=? ";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setInt(3, cliente.getTelefone());
			stmt.setInt(4, id);
			
			int rows = stmt.executeUpdate();
			return rows > 0;
		}
		
	
	}


	// user delete
	@Override
	public boolean delete(Integer id) throws SQLException{
		// TODO Auto-generated method stub
		String sql = "DELETE FROM clientes WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			// aprimorar: efetuar verificaçoes
			stmt.setInt(1, id);
			int rows = stmt.executeUpdate();
			return rows > 0;
		}
	
	}



	@Override
	public List<Cliente> getList() throws SQLException{
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM clientes";
		try(PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()	
			){
			
			List<Cliente> list = new ArrayList<Cliente>();
			while(rs.next()) {
				list.add(new Cliente(rs.getInt("id") ,rs.getString("nome"), rs.getString("email"), rs.getInt("telefone")));
			}
			return list;
		}
		
	}




	@Override
	public Cliente getId(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM clientes WHERE id=?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			
			Cliente cliente = null;
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				cliente = new Cliente(rs.getInt("id") ,rs.getString("nome"), rs.getString("email"), rs.getInt("telefone"));
			}
			
			return cliente;
		}
	}

}
