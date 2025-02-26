package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import database.DataBaseConnection;
import models.Cliente;

public class ClienteDaoImpl implements GenericDao<Cliente>{
	
	private Connection conn;
	
	public ClienteDaoImpl() {
		this.conn = DataBaseConnection.getConnection();
	}
	
	// user add
	@Override
	public int add(Cliente cliente) throws SQLException {
		
		String insertClient = "INSERT INTO clientes (nome, email, telefone) VALUE (?, ?, ?)";
		try(PreparedStatement insertClientStmt = conn.prepareStatement(insertClient)){
			insertClientStmt.setString(1, cliente.getNome());
			insertClientStmt.setString(2, cliente.getEmail());
			insertClientStmt.setLong(3, cliente.getTelefone());
			
			return insertClientStmt.executeUpdate();
		}
	}
	
	// user get
	@Override
	public Cliente get(Cliente cliente) throws SQLException {
		
		String selectClient = "SELECT * FROM clientes WHERE email = ?";
		try(PreparedStatement selectClientStmt = conn.prepareStatement(selectClient)){
			Cliente clienteSelect = null;
			
			selectClientStmt.setString(1, cliente.getEmail());
			ResultSet rs = selectClientStmt.executeQuery();
			
			if(rs.next()) {
				clienteSelect = new Cliente(
							rs.getInt("id"),
							rs.getString("nome"), 
							rs.getString("email"), 
							rs.getLong("telefone"));
			}
			
			return clienteSelect;
		}
	}	
	
	// user getId
	@Override
	public Cliente getId(Integer id) throws SQLException {
	
		String selectClient = "SELECT * FROM clientes WHERE id=?";
		try(PreparedStatement selectClientStmt = conn.prepareStatement(selectClient)){
			
			Cliente cliente = null;
			
			selectClientStmt.setInt(1, id);
			ResultSet rs = selectClientStmt.executeQuery();
			
			if(rs.next()) {
				cliente = new Cliente(
						rs.getInt("id"),
						rs.getString("nome"), 
						rs.getString("email"), 
						rs.getLong("telefone"));
			}
			
			return cliente;
		}
	}
	
	// user update
	@Override
	public int update(Cliente cliente) throws SQLException{

		String updateClient = "UPDATE clientes SET nome=?,telefone=? WHERE id=? ";
		try(PreparedStatement updateClientStmt = conn.prepareStatement(updateClient)){
			
			updateClientStmt.setString(1, cliente.getNome());
			updateClientStmt.setLong(2, cliente.getTelefone());
			updateClientStmt.setInt(3, cliente.getId());
	
			return updateClientStmt.executeUpdate();
		}
		
	
	}

	// user delete
	@Override
	public int delete(Integer id) throws SQLException{
	
		String deleteClient = "DELETE FROM clientes WHERE id = ?";
		try(PreparedStatement deleteClientStmt = conn.prepareStatement(deleteClient)){
		
			deleteClientStmt.setInt(1, id);
			return deleteClientStmt.executeUpdate();
		}
	}

	// user list
	@Override
	public List<Cliente> getList() throws SQLException{
		
		String selectClients = "SELECT * FROM clientes WHERE id > 1";
		try(PreparedStatement selectClientsStmt = conn.prepareStatement(selectClients);
			ResultSet rs = selectClientsStmt.executeQuery()){
			
			List<Cliente> list = new ArrayList<Cliente>();
			while(rs.next()) {
				list.add(new Cliente(
						rs.getInt("id"),
						rs.getString("nome"), 
						rs.getString("email"), 
						rs.getLong("telefone")));
			}
			
			return list;
		}
	}
}
