package controllers;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import database.DataBaseConnection;
import exception.DbException;
import models.Livro;

public class LivroDaoImpl implements LivroDao<Livro>{
	private Connection conn;
	
	public LivroDaoImpl(){
		conn = DataBaseConnection.getConnection();
	}
	
	// book add
	@Override
	public int add(Livro livro) throws SQLException {
		
		String insertBook = "INSERT INTO livros (titulo, autor, ano_publicacao, quantidade) VALUES (?,?,?,?)";
		try(PreparedStatement insertBookStmt = conn.prepareStatement(insertBook)){
			
			insertBookStmt.setString(1, livro.getTitulo());
			insertBookStmt.setString(2, livro.getAutor());
			insertBookStmt.setInt(3, livro.getAnoPublicado());
			insertBookStmt.setInt(4, livro.getQuantidade());
				
			return insertBookStmt.executeUpdate();
		}
		
	
	}

	// book get
	@Override
	public Livro get(Livro service) throws SQLException {
		
		String selectBook = "SELECT * FROM livros WHERE titulo = ?";
		try(PreparedStatement selectBookStmt = conn.prepareStatement(selectBook)){
			
			Livro livroSelect = null;
			
			selectBookStmt.setString(1, service.getTitulo());
			ResultSet rs = selectBookStmt.executeQuery();
			if(rs.next()) {
				livroSelect = new Livro(
						rs.getInt("id"),
						rs.getString("titulo"), 
						rs.getString("autor"), 
						rs.getInt("ano_publicacao"), 
						rs.getInt("quantidade"));
			}
		
			return livroSelect;
		}
	}
	
	
	// book getId
	@Override
	public Livro getId(Integer id) throws SQLException {
		
		String selectBook = "SELECT * FROM livros WHERE id = ?";
		try(PreparedStatement selectBookStmt = conn.prepareStatement(selectBook)){
			
			Livro livro = null;
			
			selectBookStmt.setInt(1, id);
			ResultSet rs = selectBookStmt.executeQuery();
			
			if(rs.next()) {
				livro = new Livro(
							rs.getInt("id"),
							rs.getString("titulo"), 
							rs.getString("autor"), 
							rs.getInt("ano_publicacao"),
							rs.getInt("quantidade"));
				System.out.println("Livro selecionado com Sucesso!");
			}
			
			if(livro == null) {
				throw new DbException("Erro ao selecionar Livro: ");
			}
			return livro;
		}
	}	
	
	// book update
	@Override
	public int update(Livro livro) throws SQLException {
		
		String updateBook = "UPDATE livros SET autor=?,ano_publicacao=?,quantidade=? WHERE id=?";
		try(PreparedStatement updateBookStmt = conn.prepareStatement(updateBook)){
			
			updateBookStmt.setString(1, livro.getAutor());
			updateBookStmt.setInt(2, livro.getAnoPublicado());
			updateBookStmt.setInt(3, livro.getQuantidade());
			updateBookStmt.setInt(4, livro.getId());
		
			return updateBookStmt.executeUpdate();
		}	
	}

	// book delete
	@Override
	public int delete(Integer id) throws SQLException {
		
		String deleteBook = "DELETE FROM livros WHERE id = ?";
		try(PreparedStatement deleteBookStmt = conn.prepareStatement(deleteBook)){
			
			deleteBookStmt.setInt(1, id);
			return deleteBookStmt.executeUpdate();
		}
	}
	

	// book list
	@Override
	public List<Livro> getList() throws SQLException {
		
		String selectBooks = "SELECT * FROM livros";
		try(PreparedStatement selectBooksStmt = conn.prepareStatement(selectBooks);
			ResultSet rs = selectBooksStmt.executeQuery()){
			
			List<Livro> list = new ArrayList<Livro>();
			
			while(rs.next()) {
				list.add(new Livro(
						rs.getInt("id"),
						rs.getString("titulo"), 
						rs.getString("autor"), 
						rs.getInt("ano_publicacao"), 
						rs.getInt("quantidade")));
			}
			
			if(list.size() < 1) {
				throw new DbException("Nao foi possivel acessar a lista de Livros: ");

			}
			return list;
		}
	
	}

	// book title get
	@Override
	public Livro getTitle(String title) throws SQLException{
		
		String selectBook = "SELECT * FROM livros WHERE titulo = ?";
		try(PreparedStatement selectBookStmt = conn.prepareStatement(selectBook)){
			
			Livro livro = null;
			
			selectBookStmt.setString(1, title);
			ResultSet rs = selectBookStmt.executeQuery();
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

	// book author get
	@Override
	public Livro getAutor(String autor) throws SQLException{
		
		String selectBook = "SELECT * FROM livros WHERE autor = ?";
		try(PreparedStatement selectBookStmt = conn.prepareStatement(selectBook)){
			
				Livro livro = null;
				
				selectBookStmt.setString(1, autor);
				ResultSet rs = selectBookStmt.executeQuery();
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
}
