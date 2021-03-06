package com.sainath.db.utils;

/**
 * Connection Contract
 * @author Sainath
 *
 */
public interface Connection {
	int addEmployee(String name, int deptID) throws Exception;
	int addEmployee(String name) throws Exception;
	int addDepartment(String name) throws Exception;
	void showEmployess() throws Exception;
	void showDepartments() throws Exception;
	void close() throws Exception;
}
