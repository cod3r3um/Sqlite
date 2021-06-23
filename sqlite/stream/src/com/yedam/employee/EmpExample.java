package com.yedam.employee;

import java.util.ArrayList;
import java.util.stream.Stream;

public class EmpExample {
	public static void main(String[] args) {
		
		EmployeesDAO dao = new EmployeesDAO();
		ArrayList<Employees> list = dao.getEmployeesList();
		 list.stream()
		 .filter((t) -> Integer.parseInt(t.getBirthDate().substring(0,4)) > 1970)
		 .forEach((t) -> System.out.println(t.getFirstName() + " " + t.getLastName() +" " + "(" + t.getTitle() + ")"));
	}


}
