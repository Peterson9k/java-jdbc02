package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import database.DataBaseConnection;
import database.DbException;
import entities.Livro;
import services.DaoService;

public class LivroDao implements DaoService<Livro>{
	private Connection conn;
	
	public LivroDao(){
		conn = DataBaseConnection.getConnection();
	}
	
	@Override
	public boolean add(Livro livro) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO livros (titulo, autor, ano_publicacao, quantidade) VALUES (?,?,?,?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getAutor());
			stmt.setInt(3, livro.getAnoPublicado());
			stmt.setInt(4, livro.getQuantidade());
			
			int rows = stmt.executeUpdate();
			return rows > 0;
		}
	}

	@Override
	public boolean update(Livro livro, Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE livros SET titulo=?,autor=?,ano_publicacao=?,quantidade=? WHERE id=?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getAutor());
			stmt.setInt(3, livro.getAnoPublicado());
			stmt.setInt(4, livro.getQuantidade());
			stmt.setInt(5, id);
			
			int rows = stmt.executeUpdate();
			return rows > 0;
		}	
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM livros WHERE id = ?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, id);
			int rows = stmt.executeUpdate();
			
			return rows > 0;
		}
	}

	@Override
	public List<Livro> getList() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM livros";
		try(PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()
		){
			List<Livro> list = new ArrayList<Livro>();
			
			while(rs.next()) {
				list.add(new Livro(
						rs.getInt("id"),
						rs.getString("titulo"), 
						rs.getString("autor"), 
						rs.getInt("ano_publicacao"), 
						rs.getInt("quantidade")));
			}
			
			
			return list;
		}
	
	}

	@Override
	public Livro getId(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM livros WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql);
		
		){
			stmt.setInt(1, id);
			Livro livro = null;
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				livro = new Livro(
							rs.getInt("id"),
							rs.getString("titulo"), 
							rs.getString("autor"), 
							rs.getInt("ano_publicacao"),
							rs.getInt("quantidade"));
			}
			return livro;
		}
	}	
	
	public Livro getLivroTitle(String title) throws SQLException{
		String sql = "SELECT * FROM livros WHERE titulo = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		){
			Livro livro = new Livro();
			
			if(rs.next()) {
				livro = new Livro(
							rs.getInt("id"),
							rs.getString("titulo"),
							rs.getString("autor"),
							rs.getInt("ano_publicacao"),
							rs.getInt("quantidade"));
			}
			
			return livro;
		}
	}

	public Livro getLivroAutor(String autor) throws SQLException{
		String sql = "SELECT * FROM livros WHERE autor = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
			){
				Livro livro = new Livro();
				
				if(rs.next()) {
					livro = new Livro(
								rs.getInt("id"),
								rs.getString("titulo"),
								rs.getString("autor"),
								rs.getInt("ano_publicacao"),
								rs.getInt("quantidade"));
				}
				
				return livro;
			}
	}
	
	public boolean realizarEmprestimo(Livro livro) throws SQLException {
		String sql = "UPDATE livros SET quantidade=? WHERE id=?";
		
		if(livro.getQuantidade() < 1) {
				throw new DbException("Livro indisponivel para emprestimo!");
		}
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			livro.realizarEmprestimo();
			stmt.setInt(1, livro.getQuantidade());
			
			int rows = stmt.executeUpdate();
			return rows > 0;
		}
	}
	
	
}
