package com.sainath.db.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sainath
 * 
 * This the sample database
 *
 */
public final class Database {

	private static List<Employee> empList = new CopyOnWriteArrayList<Employee>();
	private static List<Department> deptList = new CopyOnWriteArrayList<Department>();

	//Test data
	static {
		Department d1 = new Department(1, "Account");
		Department d2 = new Department(2, "Admin");
		Department d3 = new Department(3, "HR");
		Employee e1 = new Employee(1, "Sainath1", 1);
		Employee e2 = new Employee(2, "Sainath2", 2);
		Employee e3 = new Employee(3, "Sainath3", 1);
		Employee e4 = new Employee(4, "Sainath4", 3);
		Employee e5 = new Employee(5, "Sainath5", 3);
		Employee e6 = new Employee(6, "Sainath6");
		empList.addAll(Arrays.asList(e1, e2, e3, e4, e5, e6));
		deptList.addAll(Arrays.asList(d1, d2, d3));
	}
	
	/**
	 * Employee table 
	 *
	 */
	private static class Employee {
		int id;
		String name;
		int deptId;
		public Employee(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public Employee(int id, String name, int deptId) {
			this.id = id;
			this.name = name;
			this.deptId = deptId;
		}
		@Override
		public String toString() {
			return "Employee [id=" + id + ", name=" + name + ", deptId=" + deptId + "]\n";
		}
	}
	
	/**
	 * Department table
	 *
	 */
	private static class Department {
		int id;
		String name;
		public Department(int id, String name) {
			this.id = id;
			this.name = name;
		}
		@Override
		public String toString() {
			return "Department [id=" + id + ", name=" + name + "]\n";
		}
	}
	
	/**
	 * Adds an employee record with dept id
	 * @param name
	 * @param deptID
	 * @return
	 * @throws Exception
	 */
	public static int addEmployee(String name, int deptID, Connection con) throws Exception {
		if(con == null)
			throw new Exception("Connection is null");
		int id = empList.size() + 1;
		empList.add(new Employee(id, name, deptID));
		return id;
	}
	
	/**
	 * Adds an employee record
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static int addEmployee(String name, Connection con) throws Exception {
		if(con == null)
			throw new Exception("Connection is null");
		int id = empList.size() + 1;
		empList.add(new Employee(id, name));
		return id;
	}
	
	/**
	 * Adds a department
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static int addDepartment(String name, Connection con) throws Exception {
		if(con == null)
			throw new Exception("Connection is null");
		int id = deptList.size() + 1;
		deptList.add(new Department(id, name));
		return id;
	}
	
	/**
	 * Displays employee list
	 * @throws Exception
	 */
	public static void showEmpList(Connection con) throws Exception {
		if(con == null)
			throw new Exception("Connection is null");
		System.out.println(con + " is accessing empList");
		System.out.println(empList);
	}
	
	/**
	 * Displays department list
	 * @throws Exception
	 */
	public static void showDeptList(Connection con) throws Exception {
		if(con == null)
			throw new Exception("Connection is null");
		System.out.println(con + " is accessing deptList");
		System.out.println(deptList);
	}
}
