package com.sainath.db.utils;

/**
 * Wrapper class for Connection
 * @author Sainath
 *
 */
public final class ConnectionImplWrapper implements Connection{
	
	private Connection connection;
	private boolean closed;
	public ConnectionImplWrapper(Connection connection, boolean closed) {
		this.connection = connection;
		this.closed = closed;
	}
	
	@Override
	public int addEmployee(String name, int deptID) throws Exception {
		if(closed) {
			throw new Exception("Connection is closed");
		}
		return connection.addEmployee(name, deptID);
	}
	@Override
	public int addEmployee(String name) throws Exception {
		if(closed) {
			throw new Exception("Connection is closed");
		}
		return connection.addEmployee(name);
	}
	@Override
	public int addDepartment(String name) throws Exception {
		if(closed) {
			throw new Exception("Connection is closed");
		}
		return connection.addDepartment(name);
	}
	@Override
	public void showEmployess() throws Exception {
		if(closed) {
			throw new Exception("Connection is closed");
		}
		connection.showEmployess();
	}
	@Override
	public void showDepartments() throws Exception {
		if(closed) {
			throw new Exception("Connection is closed");
		}
		connection.showDepartments();
	}
	@Override
	public void close() throws Exception {
		if(closed) {
			throw new Exception("Connection is closed");
		}
		setClosed(true);
		connection.close();
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	@Override
	public String toString() {
		return connection.toString();
	}
	
	
}
