package com.sainath.db.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sainath.db.utils.Connection;
import com.sainath.db.utils.ConnectionPoolManager;

public class TestConnectionPooling {
	
	public static void main(String[] args) throws Exception {
		ConnectionPoolManager.class.getName();
		//testConnectionPooling();
		testUsingExecutor();
		Thread.sleep(5000);
		/*System.out.println("Getting last snapshot of database before terminating");
		Thread.sleep(5000);
		try {
			Connection con = ConnectionPoolManager.getConnection();
			con.showDepartments();
			con.showEmployess();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	private static void testUsingExecutor() {
		ExecutorService exe = Executors.newCachedThreadPool();
		for (int i=1 ; i<2 ; i++) {
			exe.submit(new Runnable() {
				
				@Override
				public void run() {
					Connection con;
					try {
						con = ConnectionPoolManager.getConnection();
						con.showDepartments();
						con.close();
						//con.showDepartments();
						con = ConnectionPoolManager.getConnection();
						con.showDepartments();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}, "thread "+i);
		}
		exe.shutdown();
		
	}

	private static void testConnectionPooling() throws InterruptedException {
		
		
		Thread th1 = new Thread(new Runnable() {
			public void run() {
				try {
					
					Connection con = ConnectionPoolManager.getConnection();
					con.showDepartments();
					con.close();
				} catch (Exception e) {
					System.out.println("Thread 1 is failed, due to "+e.getMessage());
					e.printStackTrace();
				}
				
			}
		}, "Thread 1");
		
		Thread th2 = new Thread(new Runnable() {
			public void run() {
				try {
					Connection con = ConnectionPoolManager.getConnection();
					con.showEmployess();
					Thread.sleep(5000);
					con.close();
				} catch (Exception e) {
					System.out.println("Thread 2 is failed, due to "+e.getMessage());
					e.printStackTrace();
				}
				
			}
		}, "Thread 2");
		
		Thread th3 = new Thread(new Runnable() {
			public void run() {
				try {
					Connection con = ConnectionPoolManager.getConnection();
					con.addDepartment("IT");
					con.addEmployee("Mannem sainath", 3);
					Thread.sleep(5000);
					con.close();
				} catch (Exception e) {
					System.out.println("Thread 3 is failed, due to "+e.getMessage());
					e.printStackTrace();
				}
				
			}
		}, "Thread 3");
		
		Thread th4 = new Thread(new Runnable() {
			public void run() {
				try {
					
					Connection con = ConnectionPoolManager.getConnection();
					Thread.sleep(5000);
					con.close();
				} catch (Exception e) {
					System.out.println("Thread 4 is failed, due to "+e.getMessage());
					e.printStackTrace();
				}
				
			}
		}, "Thread 4");
		
		Thread th5 = new Thread(new Runnable() {
			public void run() {
				try {
					Connection con = ConnectionPoolManager.getConnection();
					Thread.sleep(5000);
					con.close();
				} catch (Exception e) {
					System.out.println("Thread 5 is failed, due to "+e.getMessage());
					e.printStackTrace();
				}
				
			}
		}, "Thread 5");
		
		Thread th6 = new Thread(new Runnable() {
			public void run() {
				try {
					Connection con = ConnectionPoolManager.getConnection();
					con.addEmployee("employee 10000");
					Thread.sleep(5000);
					con.close();
				} catch (Exception e) {
					System.out.println("Thread 6 is failed, due to "+e.getMessage());
					e.printStackTrace();
				}
				
			}
		}, "Thread 6");
		
		Thread th7 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(10);
					Connection con = ConnectionPoolManager.getConnection(10);
					con.close();
				} catch (Exception e) {
					System.out.println("Thread 7 is failed, due to "+e.getMessage());
					e.printStackTrace();
				}
				
			}
		}, "Thread 7");
		
		Thread th8 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(5000);
					Connection con = ConnectionPoolManager.getConnection();
					con.addDepartment("Front Desk");
					con.addEmployee("saina");
					con.close();
				} catch (Exception e) {
					System.out.println("Thread 8 is failed, due to "+e.getMessage());
					e.printStackTrace();
				}
				
			}
		}, "Thread 8");
		
		Thread th9 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(5000);
					Connection con = ConnectionPoolManager.getConnection();
					con.addDepartment("Onsite team");
					con.addEmployee("GOpal");
					con.close();
				} catch (Exception e) {
					System.out.println("Thread 9 is failed, due to "+e.getMessage());
					e.printStackTrace();
				}
				
			}
		}, "Thread 9");
		Thread th10 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(10);
					Connection con = ConnectionPoolManager.getConnection(10);
					con.close();
				} catch (Exception e) {
					System.out.println("Thread 10 is failed, due to "+e.getMessage());
					e.printStackTrace();
				}
				
			}
		}, "Thread 10");
		
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
		th6.start();
		th8.start();
		th9.start();
		th7.start();
		th10.start();
		
		th1.join();
		th2.join();
		th3.join();
		th4.join();
		th5.join();
		th6.join();
		th7.join();
		th8.join();
		th9.join();
		th10.join();
		System.out.println("All threads are terminated......");
		
	}

}

