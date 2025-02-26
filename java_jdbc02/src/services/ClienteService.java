package services;

import java.sql.SQLException;
import java.util.List;

import controllers.ClienteDaoImpl;
import controllers.GenericDao;
import exception.DbException;
import exception.ServiceException;
import models.Cliente;

public class ClienteService {
	private static GenericDao<Cliente> serviceCliente = new ClienteDaoImpl();
	
	// add client
	public static boolean createCliente(Cliente cliente) throws SQLException {
		
		int rows = serviceCliente.add(cliente);
		if(rows == 0) {
			throw new DbException("Erro ao inserir Usuario: ");
		}
		return true;
	}
	
	// get client
	public static Cliente getCliente(Cliente cliente) throws SQLException {
		
		Cliente clienteVerific = serviceCliente.get(cliente);
		
		if(clienteVerific == null) {
			throw new DbException("Erro ao buscar Usuario: ");
		}
		System.out.println("Usuario acessado!");
		return clienteVerific;
	}
	
	// getId client (restricted) 
	public static Cliente getClienteId(Integer id) throws SQLException{
		
		Cliente clienteVerific = serviceCliente.getId(id);
		
		if(clienteVerific == null) {
			throw new DbException("Erro ao buscar Usuario (ID): ");
		}
		
		System.out.println("Usuario encontrado (ID)!");
		return clienteVerific;
	}
	
	
	// update client
	public static boolean updateCliente(Cliente cliente) throws SQLException {
		
		int rows = serviceCliente.update(cliente);
		if(rows == 0) {
			throw new DbException("Erro ao atulizar Usuario: ");
		}
		System.out.println("Atualizaçao realizada!");
		return true;
	}
	
	// delete client
	public static boolean deleteCliente(Cliente cliente) throws SQLException {

		int rows = serviceCliente.delete(cliente.getId());
		if(rows == 0) {
			throw new DbException("Erro ao deletar Usuario: ");
		}
		System.out.println("Delete realizado!");
		return true;
	}
	
	// getList client (restricted)
	public static List<Cliente> listCliente() throws SQLException{
		
		
		List<Cliente> clientesVerific = serviceCliente.getList();
		
		if(clientesVerific.size() == 0) {
			throw new DbException("Erro ao buscar Usuarios: ");
		}
		System.out.println("Lista acessada!");
		return clientesVerific;
	}
	
}
