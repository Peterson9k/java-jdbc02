package utils;

import java.sql.SQLException;
import java.util.Scanner;

import enums.AdminStatus;
import models.Cliente;
import services.GenericHeaderService;
import services.UserService;

public class ProgramCliente {
	
	
	public static int programCliente (Scanner sc, Cliente cliente) throws SQLException {
		
		String userService = UserService.verificUser(cliente);
		if(userService.compareToIgnoreCase(AdminStatus.ACCESS_CONCEDIDO.name()) == 0) {
			return clienteAdmin(sc, cliente);
		}
		return cliente(sc,  cliente);
	}
	
	private static int clienteAdmin (Scanner sc , Cliente userAdm) throws SQLException {
		System.out.println("");
		System.out.printf("1- Acessar Conta%n2- Atualizar Conta%n3- Deletar Conta%n4- Listar Contas%n5- Sair%n");
		int choiceUser = sc.nextInt();
		
		switch (choiceUser) {
		
		case 1: 
			userAdm = ClienteApp.clienteId(sc, userAdm);
			
		break;
		
		case 2: 
			userAdm = ClienteApp.update(sc, userAdm);
		break;

		case 3: 
			ClienteApp.delete(sc, userAdm);
			userAdm = ClienteApp.cliente(sc, userAdm);
			return GenericHeaderService.generenciarService(sc);
		
		case 4: 
			ClienteApp.list(userAdm).forEach(System.out::println);
			return clienteAdmin(sc, userAdm);

		case 5: 
			return GenericHeaderService.generenciarService(sc);
		default:
			throw new IllegalArgumentException("Unexpected value: " + choiceUser);
		}
		
		return GenericHeaderService.generenciarService(sc);

	}

	private static int cliente(Scanner sc,  Cliente cliente) throws SQLException {
		System.out.printf("1- Atualizar Conta%n2- Deletar Conta%n3- Sair%n");
		int choiceUser = sc.nextInt();
		
		switch (choiceUser) {
		
		case 1: 
			cliente = ClienteApp.update(sc, cliente);
		break;
		
		case 2: 
			
			ClienteApp.delete(sc, cliente);
			cliente = ClienteApp.cliente(sc, cliente);
			return GenericHeaderService.generenciarService(sc);
		case 3: 
			return GenericHeaderService.generenciarService(sc);
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + choiceUser);
		}
		
		return GenericHeaderService.generenciarService(sc);
		
	}
	
	
}
