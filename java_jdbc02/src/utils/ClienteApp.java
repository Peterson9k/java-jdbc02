package utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import models.Cliente;
import services.ClienteService;
import services.UserService;

public class ClienteApp {
		
	
	public static Cliente cliente(Scanner sc, Cliente clienteConta) throws SQLException {
		
		System.out.println("1- Criar Conta | 2- Login");
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1: 
			
			System.out.println("Siga os proximos passos para criar sua conta");
			
			System.out.print("Name: ");
			String nameCreate = sc.next();
			clienteConta.setNome(nameCreate);
			
			System.out.print("Email: ");
			String emailCreate = sc.next();
			clienteConta.setEmail(emailCreate);
			
			System.out.print("Telefone: ");
			Long telefoneCreate = sc.nextLong();
			clienteConta.setTelefone(telefoneCreate);
			System.out.println("processando...");
			ClienteService.createCliente(clienteConta);
			return ClienteService.getCliente(clienteConta);	
			
		case 2:
				
			System.out.print("Digite seu email para acessar sua conta: ");
			String emailGet = sc.next();
			clienteConta.setEmail(emailGet);
			Cliente cliente = ClienteService.getCliente(clienteConta);
			clienteConta.setId(cliente.getId());
			clienteConta.setEmail(cliente.getEmail());
			clienteConta.setNome(cliente.getNome());
			clienteConta.setTelefone(cliente.getTelefone());
			
			System.out.println("processando...");
			return clienteConta;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
	}

	public static Cliente clienteId(Scanner sc, Cliente userAdm) throws SQLException{
			
			UserService.verificUser(userAdm);
			System.out.print("Acessar Cliente (ID): ");
			int id = sc.nextInt();
			System.out.println("processando...");
			
			Cliente cliente = ClienteService.getClienteId(id);
			userAdm.setId(cliente.getId());
			userAdm.setEmail(cliente.getEmail());
			userAdm.setNome(cliente.getEmail());
			userAdm.setTelefone(cliente.getTelefone());
			
			
			return userAdm;
	}
	
	public static Cliente update(Scanner sc, Cliente cliente) throws SQLException {
		
		
		System.out.println("Atualizar dados do Cliente: ");
		
		System.out.print("Nome: ");
		String name = sc.next();
		cliente.setNome(name);
		
		System.out.print("Telefone: ");
		Long telefone = sc.nextLong();
		cliente.setTelefone(telefone);
		ClienteService.updateCliente(cliente); 
		System.out.println("processando...");
		return ClienteService.getCliente(cliente);
	}
	
	public static void delete(Scanner sc, Cliente cliente) throws SQLException {
		
		System.out.println("Deletar cliente:");
		System.out.println("processando...");
		ClienteService.deleteCliente(cliente);
	}
	
	public static List<Cliente> list(Cliente userAdm) throws SQLException{
		
		UserService.verificUser(userAdm);
		System.out.println("Acessar lista de clientes:");
		System.out.println("processando...");
		return ClienteService.listCliente();
	}
	
	
	
}
