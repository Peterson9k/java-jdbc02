package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import database.DataBaseConnection;

import entities.Emprestimo;
import entities.Livro;

import services.DaoService;

public class EmprestimoDao  implements DaoService<Emprestimo>{

	private Connection conn;
	
	public EmprestimoDao() {
		this.conn = DataBaseConnection.getConnection();
	}
	
	
	@Override
	public boolean add(Emprestimo service) throws SQLException {
		// TODO Auto-generated method stub
		
		
		String sqlEmprestimo = "INSERT INTO emprestimo (livro_id, cliente_id, data_emprestimo, data_devolucao) VALUES (?,?,?,?)";
		
		try(PreparedStatement adicionarEmprestimo = conn.prepareStatement(sqlEmprestimo);){
			
			DaoService<Livro> daoService = new LivroDao();
			
			if(daoService instanceof LivroDao) {
				LivroDao livroService = (LivroDao) daoService;
				livroService.realizarEmprestimo(livroService.getId(service.getLivroId()));
			}
			
			
			adicionarEmprestimo.setInt(1, service.getLivroId());
			adicionarEmprestimo.setInt(2, service.getClienteId());
			adicionarEmprestimo.setDate(3, Date.valueOf(service.getDateEmprestimo()));
			adicionarEmprestimo.setDate(4, Date.valueOf(service.getDateDevoluçao()));
			
			return true;
		}
	
	}

	@Override
	public boolean update(Emprestimo service, Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Emprestimo> getList() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Emprestimo getId(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
