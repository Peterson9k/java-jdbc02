package services;

import java.sql.SQLException;
import java.time.Period;
import java.util.List;

import controllers.EmprestimoDaoImpl;
import exception.DbException;
import exception.ServiceException;
import models.Cliente;
import models.Emprestimo;

public class EmprestimoService {
	
	private static EmprestimoDaoImpl serviceEmprestimo = new EmprestimoDaoImpl();
	
	
	// add loan
	public static boolean createEmprestimo(Emprestimo emprestimo) throws SQLException{
		
		if(getEmprestimo(emprestimo).getClienteId().equals(null)) {
			throw new ServiceException("Erro: nao pode pegar emprestado o mesmo livro, devolva para pedir novamente!");
		}
		
		int rows = serviceEmprestimo.add(emprestimo);
		
		if(rows == 0) {
			throw new DbException("Erro ao inserir Emprestimo: ");
		}
		
		Period period = Period.between(emprestimo.getDateEmprestimo(), emprestimo.getDateDevolucao());
		
		if(period.getYears() == 0 && period.getMonths() == 0 && period.getDays() == 0) {
			throw new ServiceException("Erro ao inserir data de devoluçao (min: 1 dia): ");
		}
			
		System.out.println("Emprestimo realizado!");
	
		return realizarEmprestimo( getEmprestimo(emprestimo).getLivroId() );
	}
	
	// get loan
	public static Emprestimo getEmprestimo(Emprestimo emprestimo) throws SQLException {
		
		Emprestimo emprestimoVerific = serviceEmprestimo.get(emprestimo);
		
		if(emprestimoVerific == null) {
			throw new DbException("Erro ao buscar Emprestimo: ");
		}
	
		return emprestimoVerific;
	}
	
	// getId loan (restricted)
	public static Emprestimo getEmprestimoId(int id) throws SQLException{
		
		Emprestimo clienteVerific = serviceEmprestimo.getId(id);
		
		if(clienteVerific == null) {
			throw new DbException("Erro ao buscar Emprestimo (ID): ");
		}
		System.out.println("Emprestimo encontrado (ID)!");
		return clienteVerific;
	}
	
	// update loan
	public static boolean updateEmprestimo(Emprestimo emprestimo) throws SQLException {
		
		int rows = serviceEmprestimo.update(emprestimo);
		if(rows == 0) {
			throw new DbException("Erro ao atulizar Emprestimo: ");
		}
		System.out.println("Atualizaçao realizada!");
		return true;
	}
	
	// delete loan
	public static boolean deleteEmprestimo(Emprestimo emprestimo) throws SQLException {
		
		emprestimo.setId(getEmprestimo(emprestimo).getId());

		int rows = serviceEmprestimo.delete(emprestimo.getId());
		if(rows == 0) {
			throw new DbException("Erro ao deletar Emprestimo: ");
		}
		System.out.println("Emprestimo deletado!");
		return devolverEmprestimo(getEmprestimo(emprestimo).getLivroId());
	}
	
	//getList loan (restricted)
	public static List<Emprestimo> listEmprestimos() throws SQLException{
	
		List<Emprestimo> emprestimoVerific = serviceEmprestimo.getList();
		
		if(emprestimoVerific.size() == 0) {
			throw new DbException("Erro ao buscar Clientes: ");
		}
		System.out.println("Lista acessada!");
		return emprestimoVerific;
	}
	
	// carry out loan
	private static boolean realizarEmprestimo(Integer id) throws SQLException {
		
		int rows = serviceEmprestimo.realizarEmprestimo(id);
		
		if(rows == 0) {
			throw new DbException("Erro ao realizar emprestimo (livro): ");
		}
		
		return true;
	}
	
	// return loan
	private static boolean devolverEmprestimo(Integer id) throws SQLException {
		
		int rows = serviceEmprestimo.devolverEmprestimo(id);
		
		if(rows == 0) {
			throw new DbException("Erro ao devolver emprestimo (livro): ");
		}
		
		return true;
	}
}

// as 2 funçoes privadas que so vao ser realizadas aqui dentro
