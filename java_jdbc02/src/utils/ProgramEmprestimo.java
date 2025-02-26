package utils;

import java.sql.SQLException;
import java.util.Scanner;

import enums.AdminStatus;
import models.Cliente;
import models.Emprestimo;
import models.Livro;
import services.GenericHeaderService;
import services.GenericMainService;
import services.UserService;

public class ProgramEmprestimo {

	
	
	public static int programEmprestimo (Scanner sc, Cliente cliente,Livro livro,  Emprestimo emprestimo) throws SQLException {
		
		String userService = UserService.verificUser(cliente);
		if(userService.compareToIgnoreCase(AdminStatus.ACCESS_CONCEDIDO.name()) == 0) {
			return emprestimoAdmin(sc, cliente, livro,  emprestimo);
		}
		return emprestimo(sc,  cliente, livro, emprestimo);
	}
	
	private static int emprestimoAdmin(Scanner sc, Cliente cliente,Livro livro,  Emprestimo emprestimo) throws SQLException {
		
		System.out.printf("1- Realizar Emprestimo%n2- Acessar Emprestimo%n3- Atualizar Emprestimo%n4- Devolver Emprestimo%n5- Listar Emprestimos%n6- Sair%n");
		
		int choiceEmprestimo = sc.nextInt();
		
		switch (choiceEmprestimo) {
		case 1: 
			emprestimo = EmprestimoApp.realizarEmprestimo(sc, cliente, livro);
			System.out.println(emprestimo);
		break;
		
		case 2: 
			emprestimo = EmprestimoApp.emprestimoId(sc, cliente);
			System.out.println(emprestimo);
		break;
		
		case 3:
			emprestimo = EmprestimoApp.update(sc, emprestimo);
			System.out.println(emprestimo);
		break;
		
		case 4:
			EmprestimoApp.delete(cliente, livro, emprestimo);
			GenericMainService.mainService(sc);
		break;
		
		case 5:
			EmprestimoApp.listAdm(cliente).forEach(System.out::println);
		break;
		
		case 6:			
			return GenericMainService.mainService(sc);
	
		default:
			throw new IllegalArgumentException("Unexpected value: " + choiceEmprestimo);
		}
		return GenericMainService.mainService(sc);
	}
	
	private static int emprestimo(Scanner sc, Cliente cliente, Livro livro,  Emprestimo emprestimo) throws SQLException {

		System.out.printf("1- Realizar Emprestimo%n2- Atualizar Emprestimo%n3- Devolver Emprestimo%n4- Sair%n");
		
		int choiceEmprestimo = sc.nextInt();
		
		switch (choiceEmprestimo) {
		case 1: 
			emprestimo = EmprestimoApp.realizarEmprestimo(sc, cliente, livro);
			System.out.println(emprestimo.toStringUser());
		break;
		
		case 2: 
			emprestimo = EmprestimoApp.update(sc, emprestimo);
			System.out.printf(emprestimo.toStringUser());
		break;
	
		case 3:
			
			EmprestimoApp.delete(cliente,livro,emprestimo);
			return GenericMainService.mainService(sc);
		
		case 4:
			return GenericMainService.mainService(sc);
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + choiceEmprestimo);
		}
		
		return GenericMainService.mainService(sc);
	}
	
}
