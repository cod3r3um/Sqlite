package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDAO {
	
	// 필드로 선언
	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;
		
	// 전체리스트
	public ArrayList<Person> getPersonList() {
		ArrayList<Person> personList = new ArrayList<>();
		connect(); // Connection 객체 연결
		String sql = "select * from person";
		try {
			psmt = conn.prepareStatement(sql); // PreparedStatement 쿼리를 실행, 결과를 받아옴
			rs = psmt.executeQuery(); // 쿼리 실행결과를 가져오는 부분
			while (rs.next()) {
				// System.out.println(rs.getInt("id"));
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				person.setPhone(rs.getString("phone"));
				personList.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return personList;
	}
	
	// Connection 객체 얻어오는 부분
	public static void connect() {
		String url = "jdbc:sqlite:C:/sqlite/db/sample.db";
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("연결 성공!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 리소스 반환하는 부분
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
