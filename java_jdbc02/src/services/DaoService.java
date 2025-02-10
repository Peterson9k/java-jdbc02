package services;

import java.sql.SQLException;
import java.util.List;

public interface DaoService <Type>{
	boolean add(Type service) throws SQLException;
	boolean update(Type service, Integer id) throws SQLException;
	boolean delete(Integer id) throws SQLException;
	List<Type> getList() throws SQLException;
	Type getId(Integer id) throws SQLException;
}
