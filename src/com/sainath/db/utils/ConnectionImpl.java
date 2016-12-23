package com.sainath.db.utils;

import com.sainath.db.utils.ConnectionPoolManager.ConnectionPool;;

/**
 * @author Sainath
 * 
 * This class is Connection class, which have handler for database.
 * This class holds 
 *
 */
public class ConnectionImpl implements Connection {

	private ConnectionPool connectionPool = null;
	private Database database = null;
	private int id;
	
	private ConnectionImpl(ConnectionPool connectionPool, int id) throws Exception {
		if(connectionPool == null)
			throw new Exception("Connection pool is null");
		this.connectionPool = connectionPool;
		this.database = new Database(this);
		this.id = id;
	}

	static Connection getInstance(ConnectionPool connectionPool, int id) throws Exception {
		ConnectionImpl connImpl = new ConnectionImpl(connectionPool, id);
		return new ConnectionImplWrapper(connImpl, false);
	}
	
	public int addEmployee(String name, int deptID) throws Exception {
		if(database == null)
			throw new Exception("Connection is closed");
		return database.addEmployee(name, deptID);
	}
	public int addEmployee(String name) throws Exception {
		if(database == null)
			throw new Exception("Connection is closed");
		return database.addEmployee(name);
	}
	public int addDepartment(String name) throws Exception {
		if(database == null)
			throw new Exception("Connection is closed");
		return database.addDepartment(name);
	}
	public void showEmployess() throws Exception {
		if(database == null)
			throw new Exception("Connection is closed");
		database.showEmpList();
	}
	
	public void showDepartments() throws Exception {
		if(database == null)
			throw new Exception("Connection is closed");
		database.showDeptList();
	}
	public void close() {
		synchronized (connectionPool) {
			connectionPool.addConnection(new ConnectionImplWrapper(this, false));
			connectionPool.notify();
			System.out.println("the connection "+ id + " released to pool by "+Thread.currentThread().getName());
		}
	}

	@Override
	public String toString() {
		return "Connection [id=" + id + "]";
	}
	
}
