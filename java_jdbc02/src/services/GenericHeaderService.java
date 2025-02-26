package services;

import java.util.Scanner;

import exception.ServiceException;

public class GenericHeaderService {
	public static int generenciarService(Scanner sc) {
		System.out.println();
		System.out.println("1- Usuario | 2- Livro | 3- Avançar");
		
		int choice = sc.nextInt();
		
		if(choice != 1 && choice != 2 && choice != 3) {
			throw new ServiceException("Valor incorreto: ");
		}
	
		
		return choice;
	}
}
