package utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import models.Cliente;
import models.Livro;
import services.LivroService;
import services.UserService;

public class LivroApp {
	
	public static Livro livro(Scanner sc, Cliente userAdm) throws SQLException{
		UserService.verificUser(userAdm);
		System.out.println("1- Criar Livro | 2- Acessar Livro");
		int choice = sc.nextInt();
		
		Livro livro = new Livro();
		
		switch (choice) {
		case 1: 
		
			System.out.println("Siga os proximos passos para criar um Livro");
			
			System.out.print("Titulo: ");
			String titutloCreate = sc.next();
			livro.setTitulo(titutloCreate);
			
			System.out.print("Autor: ");
			String autorCreate = sc.next();
			livro.setAutor(autorCreate);

			System.out.print("Ano de Publicaçao: ");
			int anoPublicacaoCreate = sc.nextInt();
			livro.setAnoPublicado(anoPublicacaoCreate);
			
			System.out.print("Quantidade: ");
			int quantidadeCreate = sc.nextInt();
			livro.setQuantidade(quantidadeCreate);			
			System.out.println("processando...");
			LivroService.createLivro(livro);
			return LivroService.getLivro(livro);
		
		case 2:
		
			System.out.println("Digite o titulo do livro: ");
			String tituloGet = sc.next();
			livro.setTitulo(tituloGet);
			System.out.println("processando...");
			return LivroService.getLivro(livro);
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		
		
	}

	
	public static Livro livroId(Scanner sc, Cliente userAdm) throws SQLException{
		
		UserService.verificUser(userAdm);
		
		System.out.print("Acessar Livro (ID): ");
		int id = sc.nextInt();
		System.out.println("processando...");
		return LivroService.getLivroId(id);
	}
	
	public static Livro update(Scanner sc, Cliente userAdm, Livro livro) throws SQLException {
		UserService.verificUser(userAdm);
		System.out.println("Atualizar dados do Livro: ");
		
		System.out.print("Autor: ");
		String autor = sc.next();
		livro.setAutor(autor);
		
		System.out.print("Ano de Publicaçao: ");
		int anoPublicacao = sc.nextInt();
		livro.setAnoPublicado(anoPublicacao);
		
		System.out.print("Quantidade: ");
		int quantidade = sc.nextInt();
		livro.setQuantidade(quantidade);
		System.out.println("processando...");
		LivroService.updateLivro(livro);
		return LivroService.getLivro(livro);
	}
	
	public static void delete(Scanner sc, Cliente userAdm, Livro livro) throws SQLException {
		UserService.verificUser(userAdm);
		System.out.println("Deletar livro:");
		System.out.println("processando...");
		LivroService.deleteLivro(livro);
	}

	
	public static List<Livro> list() throws SQLException{
		System.out.println("Acessar lista de Livros:");
		System.out.println("processando...");
		return LivroService.listLivro();
	}
	
	public static List<Livro> listAdm(Cliente cliente) throws SQLException{
		System.out.println("Acessar lista de Livros:");
		System.out.println("processando...");
		return LivroService.listLivroAdm(cliente);
	}
	
	public static void livroTitle(Scanner sc, Livro livro) throws SQLException {
		
		System.out.print("Acessar Livro (Titulo): ");
		String title = sc.next();
		System.out.println("processando...");
		Livro newLivroTitle = LivroService.getLivroTitle(title);
		livro.setId(newLivroTitle.getId());
		livro.setTitulo(newLivroTitle.getTitulo());
		livro.setAutor(newLivroTitle.getAutor());
		livro.setAnoPublicado(newLivroTitle.getAnoPublicado());
		livro.setQuantidade(newLivroTitle.getQuantidade());
	}
	
	public static void livroAutor(Scanner sc, Livro livro) throws SQLException {
		
		
		
		System.out.print("Acessar Livro (Autor): ");
		String autor = sc.next();
		System.out.println("processando...");

		Livro newLivroAutor = LivroService.getLivroAutor(autor);
		livro.setId(newLivroAutor.getId());
		livro.setTitulo(newLivroAutor.getTitulo());
		livro.setAutor(newLivroAutor.getAutor());
		livro.setAnoPublicado(newLivroAutor.getAnoPublicado());
		livro.setQuantidade(newLivroAutor.getQuantidade());
	}
	
	
}
