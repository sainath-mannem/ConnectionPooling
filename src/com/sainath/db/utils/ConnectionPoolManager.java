package com.sainath.db.utils;

import java.util.Vector;

/**
 * This class is factory class for connections
 * @author Sainath
 *
 */
public class ConnectionPoolManager {
	
	/**
	 * Pool Size
	 */
	private final static int POOL_SIZE = 5;
	/**
	 * Connection request time out is 5 seconds
	 */
	private final static int CONNECTION_TIME_OUT = 5000;
	
	/**
	 * Connection pool, which maintains the pool
	 *
	 */
	static class ConnectionPool {
		
		private Vector<Connection> connections =  new Vector<>();
		private final static ConnectionPool connectionPool = new ConnectionPool();
		
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
		static ConnectionPool getConnectionPool() {
			return connectionPool;
		}
	}
	
	/**
	 * Only one reference to Connection pool handler
	 */
	private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	//Initializing the connection pool 
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
	
	/** 
	 * @return connection
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		//Default wait time
		return getConnection(CONNECTION_TIME_OUT);
	}
	/**
	 * @param waitTime
	 * @return connection
	 * @throws Exception
	 */
	public static Connection getConnection(int waitTime) throws Exception {
		//Synchronizing on connectionPool
		synchronized (connectionPool) {
			//if pool is empty. then wait till a connection is released
			if(connectionPool.isEmpty()) {
				System.out.println("Pool is full,"+ Thread.currentThread().getName() +" is wait for "+waitTime);
				connectionPool.wait(waitTime);
			}
			//After wait time or notify,checking connection pool is still empty,
			if(connectionPool.isEmpty()) {
				throw new Exception("Connection request is timedout "+ Thread.currentThread().getName());
			}
			//getting the connection
			Connection con = connectionPool.getConnection();
			if(con == null)
				throw new Exception("Not able to get the connection "+ Thread.currentThread().getName());
			System.out.println("Connection "+con+ " is used by "+Thread.currentThread().getName() +", the pool size is "+ connectionPool.poolSize() );
			return con;
		}
	}
	
}
