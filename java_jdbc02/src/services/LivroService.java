package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controllers.LivroDao;
import controllers.LivroDaoImpl;
import exception.DbException;
import exception.ServiceException;
import models.Cliente;
import models.Livro;

public class LivroService {
	
	private static LivroDao<Livro> serviceLivro = new LivroDaoImpl();
	
	

	// add book
	public static boolean createLivro(Livro livro) throws SQLException {
		
		int rows = serviceLivro.add(livro);
		if(rows ==0) {
			throw new DbException("Erro ao inserir Livro: ");
		}
		return true;
	}
	
	// get book
	public static Livro getLivro(Livro livro) throws SQLException {
		
		Livro livroVerific = serviceLivro.get(livro);
		
		if(livroVerific == null) {
			throw new DbException("Erro ao buscar Livro: ");
		}
		
		System.out.println("Livro acessado!");
		return livroVerific;
	}
	
	
	//getId book (restricted)
	public static Livro getLivroId(Integer id) throws SQLException {
		
		Livro livroVerific = serviceLivro.getId(id);
		
		if(livroVerific == null) {
			throw new DbException("Erro ao buscar Livro (ID): ");
		}
		System.out.println("Livro encontrado (ID)!");
		
		return livroVerific;
	}

	// update book
	public static boolean updateLivro(Livro livro) throws SQLException {
		
		int rows = serviceLivro.update(livro);
		if(rows == 0) {
			throw new DbException("Erro ao atulizar Livro: ");
		}
		
		System.out.println("Atualizaçao realizada!");
		return true;
	}
	
	// delete book
	public static boolean deleteLivro(Livro livro) throws SQLException {

		int rows = serviceLivro.delete(livro.getId());
		if(rows == 0) {
			throw new DbException("Erro ao deletar Livro: ");
		}
		System.out.println("Delete realizado!");
		return true;
	}
	
	// getList book 
	public static List<Livro> listLivro() throws SQLException{
		
		List<Livro> livrosVerific = new ArrayList<>();
		
		for(Livro livro : serviceLivro.getList()) {
			livrosVerific.add( new Livro(livro.getTitulo(), livro.getAutor(), livro.getAnoPublicado(), livro.getQuantidade()));
		}
		
		if(livrosVerific.size() == 0) {
			throw new DbException("Erro ao buscar Livros: ");
		}
		
		System.out.println("Lista acessada!");
		return livrosVerific;
	}
	
	// getList book (restricted)
	public static List<Livro> listLivroAdm(Cliente userAdm) throws SQLException{
		
		UserService.verificUser(userAdm);
		
		List<Livro> livrosVerific = serviceLivro.getList();		
		if(livrosVerific.size() == 0) {
			throw new DbException("Erro ao buscar Livros: ");
		}
		
		System.out.println("Lista acessada!");
		return livrosVerific;
	}
	
	// getTitle book
	public static Livro getLivroTitle(String title) throws SQLException {
		
		Livro livroVerific = serviceLivro.getTitle(title);
		
		if(livroVerific == null) {
			throw new DbException("Erro ao buscar Livro (title): ");
		}
		System.out.println("Livro encontrado!");
		return livroVerific;
	}
	
	// getAutor book
	public static Livro getLivroAutor(String autor) throws SQLException {
		
		Livro livroVerific = serviceLivro.getAutor(autor);
		
		if(livroVerific == null) {
			throw new DbException("Erro ao buscar Livro (autor): ");
		}
		System.out.println("Livro encontrado!");
		return livroVerific;
	}
}
