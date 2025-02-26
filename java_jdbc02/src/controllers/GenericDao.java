package controllers;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao <Type>{
	int add(Type service) throws SQLException;
	Type get(Type get) throws SQLException;
	Type getId(Integer id) throws SQLException;
	int update(Type service) throws SQLException;
	int delete(Integer id) throws SQLException;
	List<Type> getList() throws SQLException;

}
