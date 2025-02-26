package application;

import java.sql.SQLException;
import java.util.Scanner;

import models.Cliente;
import models.Emprestimo;
import models.Livro;
import services.GenericHeaderService;
import services.GenericMainService;
import utils.ClienteApp;
import utils.LivroApp;
import utils.ProgramCliente;
import utils.ProgramEmprestimo;
import utils.ProgramLivro;




public class Program {
	public static void main(String[] args) throws SQLException {	

		Scanner sc = new Scanner(System.in);
		System.out.println("Sistema de Gerenciamento de Biblioteca");
		System.out.println();
		
		Cliente cliente = new Cliente();
		cliente = ClienteApp.cliente(sc, cliente);
		
		Livro livro = new Livro();
		Emprestimo emprestimo = new Emprestimo();
		
		int choiceIntHeader = GenericHeaderService.generenciarService(sc);
		
		while(choiceIntHeader == 1 || choiceIntHeader == 2 || choiceIntHeader == 3) {
			
			
			switch (choiceIntHeader) {
			case 1: 
				choiceIntHeader = ProgramCliente.programCliente(sc, cliente);
			break;
				
			case 2:
				choiceIntHeader = ProgramLivro.programLivro(sc, livro, cliente);
				
			break;
		
			case 3:
				
				if(livro.getId() == null) {
					System.out.println("Selecione um Livro antes!");
					choiceIntHeader = GenericHeaderService.generenciarService(sc);
					break;
				}
				
				
				int choiceIntMain = GenericMainService.mainService(sc);
				
				
				while(choiceIntMain == 1) {
					ProgramEmprestimo.programEmprestimo(sc, cliente, livro, emprestimo);
				}
				
				choiceIntHeader = GenericHeaderService.generenciarService(sc);
				
			break;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + choiceIntHeader);
			}
			

			
		}
		
		
		sc.close();
	}


	
}
