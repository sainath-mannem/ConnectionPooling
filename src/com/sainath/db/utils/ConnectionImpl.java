package com.sainath.db.utils;

import com.sainath.db.utils.ConnectionPoolManager.ConnectionPool;;

/**
 * @author Sainath
 * 
 * This class is Connection class, which have handler for database.
 * This class holds 
 *
 */
class ConnectionImpl implements Connection {

	private final ConnectionPool connectionPool;
	private int id;
	
	private ConnectionImpl(final ConnectionPool connectionPool, int id) throws Exception {
		if(connectionPool == null)
			throw new Exception("Connection pool is null");
		this.connectionPool = connectionPool;
		this.id = id;
	}

	static Connection getInstance(final ConnectionPool connectionPool, int id) throws Exception {
		ConnectionImpl connImpl = new ConnectionImpl(connectionPool, id);
		return new ConnectionImplWrapper(connImpl, false);
	}
	
	public int addEmployee(String name, int deptID) throws Exception {
		return Database.addEmployee(name, deptID, this);
	}
	public int addEmployee(String name) throws Exception {
		return Database.addEmployee(name, this);
	}
	public int addDepartment(String name) throws Exception {
		return Database.addDepartment(name, this);
	}
	public void showEmployess() throws Exception {
		Database.showEmpList(this);
	}
	
	public void showDepartments() throws Exception {
		Database.showDeptList(this);
	}
	public void close() {
		//Synchronizing on connectionPool
		synchronized (connectionPool) {
			//on close new wrapper is created
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
