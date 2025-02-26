package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DataBaseConnection;
import models.Emprestimo;
import models.Livro;

public class EmprestimoDaoImpl  implements EmprestimoDao<Emprestimo>{

	private Connection conn;
	
	public EmprestimoDaoImpl() {
		this.conn = DataBaseConnection.getConnection();
	}
	
	
	@Override
	public int add(Emprestimo service) throws SQLException {
		
		String insertEmprestimo = "INSERT INTO emprestimo (livro_id, cliente_id, data_emprestimo, data_devolucao) VALUES (?,?,?,?)";	
		try(PreparedStatement insertEmprestimoStmt = conn.prepareStatement(insertEmprestimo)){
			
			insertEmprestimoStmt.setInt(1, service.getLivroId());
			insertEmprestimoStmt.setInt(2, service.getClienteId());
			insertEmprestimoStmt.setDate(3, Date.valueOf(service.getDateEmprestimo()));
			insertEmprestimoStmt.setDate(4, Date.valueOf(service.getDateDevolucao()));
					
			return insertEmprestimoStmt.executeUpdate();
		}
	}
	
	
	@Override
	public Emprestimo get(Emprestimo service) throws SQLException {
		
		String selectEmprestimo = "SELECT * FROM emprestimo WHERE cliente_id = ? AND livro_id = ?";
		try(PreparedStatement selectEmprestimoStmt = conn.prepareStatement(selectEmprestimo)){
		
			selectEmprestimoStmt.setInt(1, service.getClienteId());
			selectEmprestimoStmt.setInt(2, service.getLivroId());
			ResultSet rs = selectEmprestimoStmt.executeQuery(); 
			
			if(rs.next()) {
				service = new Emprestimo(
						rs.getInt("id"),
						rs.getInt("livro_id"),
						rs.getInt("cliente_id"),
						rs.getDate("data_emprestimo").toLocalDate(),
						rs.getDate("data_devolucao").toLocalDate()
						);
			}
			
			return service;
		}
		
	} 
	

	@Override
	public Emprestimo getId(Integer id) throws SQLException {
		
		String getEmprestimo = "SELECT e.*, c.id AS idCliente, l.id AS idLivro FROM emprestimo e INNER JOIN clientes c ON e.cliente_id = c.id INNER JOIN livros l ON e.livro_id = l.id WHERE e.id = ?";
		try(PreparedStatement getEmprestimoStmt = conn.prepareStatement(getEmprestimo)){
			
			Emprestimo emprestimo = null;
			
			getEmprestimoStmt.setInt(1, id);
			ResultSet rs = getEmprestimoStmt.executeQuery();
			if(rs.next()) {
				emprestimo = new Emprestimo(
					rs.getInt("id"),
					rs.getInt("livro_id"),
					rs.getInt("cliente_id"),
					rs.getDate("data_emprestimo").toLocalDate(),
					rs.getDate("data_devolucao").toLocalDate());
			}
			
			return emprestimo;
		}
	}
	
	@Override
	public int update(Emprestimo emprestimo) throws SQLException {
		
		String updateEmprestimo = "UPDATE emprestimo emp INNER JOIN livros l ON emp.livro_id = l.id INNER JOIN clientes c ON emp.cliente_id = c.id SET = data_emprestimo=?,data_devolucao=? WHERE emp.id=?";
		try(PreparedStatement updateEmprestimoStmt = conn.prepareStatement(updateEmprestimo)){
			
			updateEmprestimoStmt.setDate(1, Date.valueOf(emprestimo.getDateEmprestimo()));
			updateEmprestimoStmt.setDate(2, Date.valueOf(emprestimo.getDateDevolucao()));
			updateEmprestimoStmt.setInt(3, emprestimo.getId());
		
			return updateEmprestimoStmt.executeUpdate();
		}
	}

	@Override
	public int delete(Integer id) throws SQLException {
		
		String deleteEmprestimo = "DELETE emp.* FROM emprestimo emp INNER JOIN livros l ON emp.livro_id=l.id INNER JOIN clientes c ON emp.cliente_id=c.id WHERE emp.id = ?";
		try(PreparedStatement deleteEmprestimoStmt = conn.prepareStatement(deleteEmprestimo)){
			
			deleteEmprestimoStmt.setInt(1, id);
			
			return deleteEmprestimoStmt.executeUpdate();
		}
	}

	@Override
	public List<Emprestimo> getList() throws SQLException {
		
		String selectEmprestimos = "SELECT * FROM emprestimo";
		try(PreparedStatement selectEmprestimoStmt = conn.prepareStatement(selectEmprestimos);
			ResultSet rs = selectEmprestimoStmt.executeQuery()){
			
			List<Emprestimo> emprestimo = new ArrayList<>();
			
			
			while(rs.next()) {
				emprestimo.add(new Emprestimo(
						rs.getInt("id"),
						rs.getInt("livro_id"),
						rs.getInt("cliente_id"),
						rs.getDate("data_emprestimo").toLocalDate(),
						rs.getDate("data_devolucao").toLocalDate()));
			}
			return emprestimo;
		}

		
		
	}


	@Override
	public int realizarEmprestimo(Integer id) throws SQLException {
		LivroDaoImpl LivroDao = new LivroDaoImpl();
		
		Livro livro = LivroDao.getId(id);
		livro.realizarEmprestimo();
		return LivroDao.update(livro);
	}
	
	@Override
	public int devolverEmprestimo(Integer id) throws SQLException{
		LivroDaoImpl LivroDao = new LivroDaoImpl();
		
		Livro livro = LivroDao.getId(id);
		livro.realizarDevolucao();
		
		return LivroDao.update(livro);
	}
}
