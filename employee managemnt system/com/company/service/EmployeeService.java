package com.company.service;

import com.company.employee.Employee;
import com.company.exception.EmployeeNotFoundException;
import com.company.exception.InvalidSalaryException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeService {
	private static final String FILE_NAME = "employees.txt";

	public void addEmployee(Employee e) throws IOException, InvalidSalaryException {
		if (e.getSalary() < 0) {
			throw new InvalidSalaryException("Salary cannot be negative.");
		}

		FileWriter fw = new FileWriter(FILE_NAME, true);
		fw.write(e.getEmpId() + "," + e.getName() + "," + e.getDepartment() + "," + e.getSalary() + "\n");
		fw.close();
	}

	public void displayEmployees() throws IOException {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			System.out.println("No employees found.");
			return;
		}

		FileReader fr = new FileReader(FILE_NAME);
		int i;
		while ((i = fr.read()) != -1) {
			System.out.print((char) i);
		}
		fr.close();
	}

	public Employee searchEmployee(int empId) throws IOException, EmployeeNotFoundException {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			throw new EmployeeNotFoundException("Employee with ID " + empId + " not found.");
		}

		FileReader fr = new FileReader(FILE_NAME);
		StringBuilder content = new StringBuilder();
		int i;
		while ((i = fr.read()) != -1) {
			content.append((char) i);
		}
		fr.close();

		String[] lines = content.toString().split("\\R");
		for (String line : lines) {
			if (line.trim().isEmpty()) {
				continue;
			}
			String[] data = line.split(",");
			if (data.length == 4) {
				int id = Integer.parseInt(data[0]);
				if (id == empId) {
					return new Employee(id, data[1], data[2], Double.parseDouble(data[3]));
				}
			}
		}

		throw new EmployeeNotFoundException("Employee with ID " + empId + " not found.");
	}
}
