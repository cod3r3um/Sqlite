package co.memo.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.memo.model.Memo;

// MemoAccess를 구현하는 클래스입니다.
// 기능을 작성하세요.
public class MemoDAO implements MemoAccess {
	
	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;
	
	public static void connect() {
		String url = "jdbc:sqlite:C:/sqlite/db/sample.db";
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

	@Override
	public void write(Memo memo) {
		connect();
		try {
			psmt = conn.prepareStatement("insert into memo(date, title, content) values (?, ?, ?)");
			psmt.setString(1, memo.getDate());
			psmt.setString(2, memo.getTitle());
			psmt.setString(3, memo.getContent());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 작성했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}		
	}

	@Override
	public void update(Memo memo) {
		connect();
		try {
			psmt = conn.prepareStatement("update memo set content = ? where title = ?");
			psmt.setString(1, memo.getContent());
			psmt.setString(2, memo.getTitle());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	@Override
	public void delete(String title) {
		connect();
		try {
			psmt = conn.prepareStatement("delete from memo where title = ?");
			psmt.setString(1, title);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}

	@Override
	public ArrayList<Memo> FindAll() {
		ArrayList<Memo> memoList = new ArrayList<>();
		connect();
		String sql = "select * from memo";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Memo memo = new Memo();
				memo.setDate(rs.getString("date"));
				memo.setTitle(rs.getString("title"));
				memo.setContent(rs.getString("content"));
				memoList.add(memo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return memoList;
		
	}

	public Memo FindByDate(String date) {
		connect();
		Memo memo = null;
		try {
			psmt = conn.prepareStatement("select * from memo where date = ?");
			psmt.setString(1, date);
			rs = psmt.executeQuery();
			if (rs.next()) {
				memo = new Memo();
				memo.setDate(rs.getString("date"));
				memo.setTitle(rs.getString("title"));
				memo.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return memo;
	}

	@Override
	public Memo FindByContent(String content) {
		connect();
		Memo memo = null;
		try {
			psmt = conn.prepareStatement("select * from memo where content = ?");
			psmt.setString(1, content);
			rs = psmt.executeQuery();
			if (rs.next()) {
				memo = new Memo();
				memo.setDate(rs.getString("date"));
				memo.setTitle(rs.getString("title"));
				memo.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memo;
	}
	
}
