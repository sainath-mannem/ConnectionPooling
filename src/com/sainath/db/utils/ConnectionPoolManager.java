package com.sainath.db.utils;

import java.util.Vector;

public class ConnectionPoolManager {
	
	private final static int POOL_SIZE = 4;
	private final static int CONNECTION_TIME_OUT = 5000;
	
	static class ConnectionPool {
		
		private Vector<Connection> connections =  new Vector<>();
		static final ConnectionPool connectionPool = new ConnectionPool();
		
		Connection getConnection() {
			if(connections.isEmpty())
				return null;
			Connection con = connections.get(0);
			connections.remove(con);
			return con;
		}
		void addConnection(Connection connection) {
			connections.addElement(connection);
		}
		boolean isEmpty() {
			return connections.isEmpty();
		}
		int poolSize() {
			return connections.size();
		}
		private ConnectionPool() {
		}
	}
	
	private static final ConnectionPool connectionPool = ConnectionPool.connectionPool;
	
	static {
		for (int i = 0; i < POOL_SIZE; i++) {
			try {
				connectionPool.addConnection(ConnectionImpl.getInstance(connectionPool, i));
			} catch (Exception e) {
				System.out.println("Connection Pool failed to initialize connections");
			}
		}
		System.out.println("Connection Pool successfully initialize all connections");
	}
	
	public static Connection getConnection() throws Exception {
		//Default wait time
		return getConnection(CONNECTION_TIME_OUT);
	}
	public static Connection getConnection(int waitTime) throws Exception {
		synchronized (connectionPool) {
			if(connectionPool.isEmpty()) {
				System.out.println("Pool is full,"+ Thread.currentThread().getName() +" is wait for "+waitTime);
				connectionPool.wait(waitTime);
			}
			if(connectionPool.isEmpty()) {
				throw new Exception("Connection request is timedout "+ Thread.currentThread().getName());
			}
			Connection con = connectionPool.getConnection();
			if(con == null)
				throw new Exception("Not able to get the connection "+ Thread.currentThread().getName());
			System.out.println("Connection "+con+ " is used by "+Thread.currentThread().getName() +", the pool size is "+ connectionPool.poolSize() );
			return con;
		}
	}
	
}
