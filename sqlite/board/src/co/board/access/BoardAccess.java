package co.board.access;

import java.util.ArrayList;

import co.board.model.Board;

public interface BoardAccess {
	
	public void insert (Board board);
	
	public void update (Board board);
	
	public void delete (int b_id);
	
	public void comment (Board board);
	
	public ArrayList<Board> selectAll();
	
	public ArrayList<Board> selectComment(int b_id);
	
	public boolean login(String id, String pw);
	
	public Board findId (int b_id);

	public boolean right(int b_id, String id);

	public void deleteComment(int b_id);
}
