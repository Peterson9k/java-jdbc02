package utils;

import java.sql.SQLException;
import java.util.Scanner;

import enums.AdminStatus;
import models.Cliente;
import models.Livro;
import services.GenericHeaderService;
import services.UserService;

public class ProgramLivro {
	
	public static int programLivro (Scanner sc, Livro livro, Cliente cliente) throws SQLException {
		
		String userService = UserService.verificUser(cliente);
		if(userService.compareToIgnoreCase(AdminStatus.ACCESS_CONCEDIDO.name()) == 0) {
			return livroAdmin(sc, livro, cliente);
		}
		return livro(sc, livro, cliente);
	}
	
	private static int livroAdmin (Scanner sc,  Livro livro, Cliente userAdm) throws SQLException {
		
		System.out.printf("1- Acessar Livro%n2- Atualizar Livro%n3- Deletar Livro%n4- Listar Livros (cliente)%n5- Listar Livros(adm)%n6- Acessar Livro (Titulo)%n7- Acessar Livro (Autor)%n8- Sair%n");
		
		int choiceLivro = sc.nextInt();
		switch (choiceLivro) {
		
		case 1:
			livro = LivroApp.livro(sc, userAdm);
			System.out.println(livro);
		break;
		
		case 2:
			livro = LivroApp.update(sc, userAdm, livro);
			System.out.println(livro);
			break;
			
		case 3:
			LivroApp.delete(sc, userAdm, livro);
			return GenericHeaderService.generenciarService(sc);
			
		case 4: 
			LivroApp.list().forEach(System.out::println);
			return livroAdmin(sc, livro, userAdm);
		
		case 5:
			LivroApp.listAdm(userAdm).forEach(System.out::println);;
			return livroAdmin(sc, livro, userAdm);
		
		case 6:
			LivroApp.livroTitle(sc, livro);
			System.out.println(livro);
			break;
		
		case 7:
			LivroApp.livroAutor(sc, livro);
			System.out.println(livro);
			break;
		
		case 8:				
			return GenericHeaderService.generenciarService(sc);

		default:
			throw new IllegalArgumentException("Unexpected value: " + choiceLivro);
		}
	
	
		return GenericHeaderService.generenciarService(sc);
	
	}

	private static int livro (Scanner sc,  Livro livro, Cliente cliente) throws SQLException {
	
		System.out.printf("%n1- Listar Livros (cliente)%n2- Acessar Livro (Titulo)%n3- Acessar Livro (Autor)%n4- Sair%n");
		
		int choiceLivro = sc.nextInt();
		switch (choiceLivro) {
		
		case 1:
			
			LivroApp.list().forEach(x -> System.out.printf(x.toStringUser())); //limit
		return livro(sc, livro, cliente);
			
		case 2:
			LivroApp.livroTitle(sc, livro);
			System.out.println(livro.toStringUser());
			
		break;
		
		case 3:
			LivroApp.livroAutor(sc, livro);
			System.out.println(livro.toStringUser());
		break;
		
		case 4: 
			return GenericHeaderService.generenciarService(sc);
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + choiceLivro);
		}
		return GenericHeaderService.generenciarService(sc);
	}

}
