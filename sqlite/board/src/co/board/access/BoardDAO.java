package co.board.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.board.model.Board;

public class BoardDAO implements BoardAccess {
	
	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;
	
	public ArrayList<Board> selectAll() {
		ArrayList<Board> boardList = new ArrayList<>();
		connect(); // Connection 객체 연결
		String sql = "select * from board";
		try {
			psmt = conn.prepareStatement(sql); // PreparedStatement 쿼리를 실행, 결과를 받아옴
			rs = psmt.executeQuery(); // 쿼리 실행결과를 가져오는 부분
			while (rs.next()) {
				// System.out.println(rs.getInt("id"));
				Board board = new Board();
				board.setB_id(rs.getInt("b_id"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_writer(rs.getString("b_writer"));
				board.setB_parentid(rs.getInt("b_parentid"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return boardList;
	}
	
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

	
	public boolean login(String id, String pw) {
		connect();
		boolean b = false;
		try {
			psmt = conn.prepareStatement("select * from member where u_id = ? and u_pass = ?");
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			if (rs.next()) {
				b = true;
			} else {
				b = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return b;
	}
	
	public boolean right(int b_id, String id) {
		connect();
		boolean b = false;
		try {
			psmt = conn.prepareStatement("select * from board where b_id = ? and b_writer = ?");
			psmt.setInt(1, b_id);
			psmt.setString(2, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				b = true;
			} else {
				b = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close();
		}
		return b;		
	}
	
	
	public void deleteComment(int b_id) {
		connect();
		try {
			psmt = conn.prepareStatement("delete from board where b_parentid = ?");
			psmt.setInt(1, b_id);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}		
	}
	
	@Override
	public void insert(Board board) {
		connect();
		try {
			psmt = conn.prepareStatement("insert into board(b_title, b_content, b_writer) values(?, ?, ?)");
			psmt.setString(1, board.getB_title());
			psmt.setString(2, board.getB_content());
			psmt.setString(3, board.getB_writer());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	@Override
	public void update(Board board) {
		connect();
		try {
			psmt = conn.prepareStatement("update board set b_content = ? where b_id= ?");
			psmt.setString(1, board.getB_content());
			psmt.setInt(2, board.getB_id());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}

	@Override
	public void delete(int b_id) {
		connect();
		try {
			psmt = conn.prepareStatement("delete from board where b_id = ?");
			psmt.setInt(1, b_id);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}		
	}

	@Override
	public Board findId(int b_id) {
		connect();
		Board board = null;
		try {
		psmt = conn.prepareStatement("select * from board where b_id = ?");
		psmt.setInt(1, b_id);
		rs = psmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				board.setB_id(rs.getInt("b_id"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_writer(rs.getString("b_writer"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return board;
	}

	@Override
	public void comment(Board board) {
		connect();
		try {
			psmt = conn.prepareStatement("insert into board(b_title, b_content, b_writer, b_parentid) values('abc', ?, 'user1', ?)");
			psmt.setString(1, board.getB_content());
			psmt.setInt(2, board.getB_parentid());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public ArrayList<Board> selectComment(int b_id) {
		ArrayList<Board> boardList = new ArrayList<>();
		connect(); // Connection 객체 연결
		String sql = "select * from board where b_parentid = ?";
		try {
			psmt = conn.prepareStatement(sql); // PreparedStatement 쿼리를 실행, 결과를 받아옴
			psmt.setInt(1, b_id);
			rs = psmt.executeQuery(); // 쿼리 실행결과를 가져오는 부분
			while (rs.next()) {
				// System.out.println(rs.getInt("id"));
				Board board = new Board();
				board.setB_id(rs.getInt("b_id"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_writer(rs.getString("b_writer"));
				board.setB_parentid(rs.getInt("b_parentid"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return boardList;
	}

	

}
