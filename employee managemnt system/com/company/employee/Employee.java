package com.company.employee;

public class Employee {
	private int empId;
	private String name;
	private String department;
	private double salary;

	public Employee(int empId, String name, String department, double salary) {
		this.empId = empId;
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void displayEmployee() {
		System.out.println("Employee ID: " + empId);
		System.out.println("Name: " + name);
		System.out.println("Department: " + department);
		System.out.println("Salary: " + salary);
	}
}
