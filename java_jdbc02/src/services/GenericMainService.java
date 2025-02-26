package services;

import java.util.Scanner;

import exception.ServiceException;

public class GenericMainService {

	public static int mainService(Scanner sc) {
		
		System.out.println();
		System.out.println("1- Emprestimo | 2- Sair ");
		
		int choice = sc.nextInt();
		
		if(choice != 1 && choice != 2) {
			throw new ServiceException("Valor incorreto: ");
		}
	
		
		return choice;
		
		
	}
	
}
