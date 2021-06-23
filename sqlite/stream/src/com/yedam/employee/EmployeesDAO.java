package com.yedam.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesDAO {
	
	// 필드로 선언
		static PreparedStatement psmt;
		static ResultSet rs;
		static Connection conn;

		// 전체리스트
		public ArrayList<Employees> getEmployeesList() {
			ArrayList<Employees> employeesList = new ArrayList<>();
			connect(); // Connection 객체 연결
			String sql = "select * from employees";
			try {
				psmt = conn.prepareStatement(sql); // PreparedStatement 쿼리를 실행, 결과를 받아옴
				rs = psmt.executeQuery(); // 쿼리 실행결과를 가져오는 부분
				while (rs.next()) {
					// System.out.println(rs.getInt("id"));
					Employees employees = new Employees();
					employees.setEmployeeId(rs.getInt("EmployeeId"));
					employees.setFirstName(rs.getString("FirstName"));
					employees.setLastName(rs.getString("LastName"));
					employees.setBirthDate(rs.getString("BirthDate"));
					employees.setTitle(rs.getString("Title"));
					employeesList.add(employees);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return employeesList;
		}

		public static void connect() {
			String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";
			try {
				conn = DriverManager.getConnection(url);
				System.out.println("연결 성공!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void close() {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

}
