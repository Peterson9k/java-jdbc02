package controllers;

import java.sql.SQLException;

public interface EmprestimoDao <Type> extends GenericDao<Type> {
	int realizarEmprestimo(Integer id) throws SQLException;
	int devolverEmprestimo(Integer id) throws SQLException;
}
