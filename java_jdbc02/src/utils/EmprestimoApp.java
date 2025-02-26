package utils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import models.Cliente;
import models.Emprestimo;
import models.Livro;
import services.EmprestimoService;
import services.UserService;

public class EmprestimoApp {

	public static Emprestimo realizarEmprestimo(Scanner sc, Cliente cliente, Livro livro) throws SQLException {
		Emprestimo emprestimo = new Emprestimo();

		System.out.println("Siga os proximos passos para realiar um Emprestimo");
			
		LocalDate momentActual = LocalDate.now();
		emprestimo.setDateEmprestimo(momentActual);
			
			
		System.out.println("Dia | Mes | Ano");
		
		System.out.print("Dia: ");
		int day = sc.nextInt();
		
		System.out.print("Mes: ");
		int month = sc.nextInt();
			
		System.out.print("Ano: ");
		int year = sc.nextInt();
		
	
		emprestimo.setDateDevolucao(LocalDate.of(year, month, day));
		emprestimo.setLivroId(livro.getId());
		emprestimo.setClienteId(cliente.getId());
		EmprestimoService.createEmprestimo(emprestimo);
		System.out.println("processando...");
		return EmprestimoService.getEmprestimo(emprestimo);
	}
	
	public static Emprestimo emprestimoId(Scanner sc, Cliente userAdm) throws SQLException {
		
		UserService.verificUser(userAdm);
		System.out.print("Acessar Emprestimo (ID): ");
		int id = sc.nextInt();
		System.out.println("processando...");	
		return EmprestimoService.getEmprestimoId(id);
	}
	
	public static Emprestimo update(Scanner sc, Emprestimo emprestimo) throws SQLException{
		
		System.out.println("Atualizar Data de devoluçao");
		
		System.out.print("Dia: ");
		int day = sc.nextInt();
		
		System.out.print("Mes: ");
		int month = sc.nextInt();
			
		System.out.print("Ano: ");
		int year = sc.nextInt();
		
		emprestimo.setDateDevolucao(LocalDate.of(year, month, day));
		EmprestimoService.updateEmprestimo(emprestimo);
		
		return EmprestimoService.getEmprestimo(emprestimo);
	}
	
	public static void delete(Cliente cliente ,Livro livro,Emprestimo emprestimo) throws SQLException{
		emprestimo.setClienteId(cliente.getId());
		emprestimo.setLivroId(livro.getId());
		System.out.println("Devolver livro: ");
		System.out.println("processando...");
		EmprestimoService.deleteEmprestimo(emprestimo);
	}
	
	public static List<Emprestimo> listAdm(Cliente userAdm) throws SQLException{
		UserService.verificUser(userAdm);
		
		System.out.println("Acessar lista de Livros: ");
		System.out.println("processando...");
		return EmprestimoService.listEmprestimos();
	}
	
}
