package application;

import java.sql.SQLException;

import dao.ClienteDao;
import dao.EmprestimoDao;
import dao.LivroDao;
import entities.Cliente;
import entities.Emprestimo;
import entities.Livro;
import services.DaoService;

public class Program {
	private static DaoService<Cliente> cliente = new ClienteDao();
	private static DaoService<Livro> livro = new LivroDao();
	private static DaoService<Emprestimo> emprestimo = new EmprestimoDao();
	

	public static void main(String[] args) throws SQLException {	
		// 1 ->  create client
			// add true
			// update true
			// delete true
			// list true
			// get true
			
		// 2 -> create book
			// add true
			// update true
			// delete true
			// list true
			// get true
		
	
		
		
		
		
		
		
	}
// ter uma lista com todos os itens e os reorganizalos pelo id
// verificaçao pra nao retorna erro do banco de dados
	
	
}
