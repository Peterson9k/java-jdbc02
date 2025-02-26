package services;

import enums.AdminStatus;
import models.Cliente;

public class UserService{
	public static String verificUser(Cliente userAdm) {
		if(userAdm.getEmail().equals("adm@gmail.com")) {
			System.out.println("STATUS: " + AdminStatus.ACCESS_CONCEDIDO.name());
			return AdminStatus.ACCESS_CONCEDIDO.name();
		}	
		return AdminStatus.ACCESS_NEGADO.name();
		
	}
}
