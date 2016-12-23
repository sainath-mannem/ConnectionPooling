package com.sainath.db.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Database {
	Connection con = null;
	private static List<Employee> empList = new CopyOnWriteArrayList<Employee>();
	private static List<Department> deptList = new CopyOnWriteArrayList<Department>();
	Database(Connection con) {
		this.con = con;
	}
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
	public int addEmployee(String name, int deptID) throws Exception {
		if(con == null)
			throw new Exception("Connection is null");
		int id = empList.size() + 1;
		empList.add(new Employee(id, name, deptID));
		return id;
	}
	
	public int addEmployee(String name) throws Exception {
		if(con == null)
			throw new Exception("Connection is null");
		int id = empList.size() + 1;
		empList.add(new Employee(id, name));
		return id;
	}
	
	public int addDepartment(String name) throws Exception {
		if(con == null)
			throw new Exception("Connection is null");
		int id = deptList.size() + 1;
		deptList.add(new Department(id, name));
		return id;
	}
	
	public void showEmpList() throws Exception {
		if(con == null)
			throw new Exception("Connection is null");
		System.out.println(con + " is accessing empList");
		System.out.println(empList);
	}
	
	public void showDeptList() throws Exception {
		if(con == null)
			throw new Exception("Connection is null");
		System.out.println(con + " is accessing deptList");
		System.out.println(deptList);
	}
}
