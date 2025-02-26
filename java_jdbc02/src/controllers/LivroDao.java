package controllers;

import java.sql.SQLException;

import models.Livro;

public interface LivroDao <Type> extends GenericDao<Type>{
	Type getTitle(String title) throws SQLException;
	Type getAutor(String autor) throws SQLException;
}
