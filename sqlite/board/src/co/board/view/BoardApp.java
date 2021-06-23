package co.board.view;

import java.util.ArrayList;
import java.util.List;

import co.board.access.BoardAccess;
import co.board.access.BoardDAO;
import co.board.model.Board;
import co.board.util.ScannerUtil;

public class BoardApp {
	BoardAccess boardList = new BoardDAO();
	static String id;
	static String pw;
	
	
	public void start() {
		int menunum;
		id = ScannerUtil.readStr("아이디를 입력하세요.");
		pw = ScannerUtil.readStr("비밀번호를 입력하세요.");
		boolean b = boardList.login(id, pw);
		if (b == true) {
			do {
				menuTitle();
				menunum = ScannerUtil.readInt("메뉴를 선택하세요.");
				switch (menunum) {
				case 1:
					insert();
					break;
				case 2:
					update();
					break;
				case 3:
					delete();
					break;
				case 4:
					selectAll();
					break;
				case 5:
					findId();
					break;
				case 6:
					deleteComment();
					break;
				}
			} while (menunum != 0);
			System.out.println(" ======= 종료되었습니다 ======= ");
		} else {
			System.out.println("아이디와 비밀번호가 틀립니다.");
		}
	}

	public void menuTitle() {
		System.out.println(" ======== 게시판 관리 ======== ");
		System.out.println(" 1. 게시글 작성 ");
		System.out.println(" 2. 게시글 수정 ");
		System.out.println(" 3. 게시글 삭제 ");
		System.out.println(" 4. 전체 조회 ");
		System.out.println(" 5. 게시글 조회 및 댓글달기 ");
		System.out.println(" 6. 댓글 삭제 ");
		System.out.println(" 0. 종료");
	}

	private void findId() {
		int b_id = ScannerUtil.readInt("조회할 아이디를 입력하세요.");
		Board board = boardList.findId(b_id);
		System.out.println(board);
		// 부모 아이디가 b_id인 리스트를 가지고 오도록 하겠습니다.
		ArrayList<Board> clist = boardList.selectComment(b_id);
		System.out.println(" - 댓글 목록 - ");
		for (Board b : clist) {
			System.out.println(" > " + b.getB_content());
		}
		int menu = ScannerUtil.readComment();
		if (menu == 1) {
			String comment = ScannerUtil.readStr("댓글을 입력하세요.");
			board = new Board();
			board.setB_content(comment);
			board.setB_parentid(b_id);
			boardList.comment(board);
		} else {
			return;
		}

	}

	private void selectAll() {
		List<Board> list = boardList.selectAll();
		for (Board board : list) {
			System.out.println(board);
		}
	}

	private void delete() {
		int b_id = ScannerUtil.readInt("삭제할 글의 아이디를 입력하세요.");
		boolean r = boardList.right(b_id, id);
		if (r) {
			boardList.delete(b_id);			
			boardList.deleteComment(b_id);
		} else {
			System.out.println("권한이 없습니다.");
			start();
		}
	}
	
	private void deleteComment() {
		Board board = new Board();
		int b_id = ScannerUtil.readInt("삭제할 댓글의 아이디를 입력하세요.");
		
	}

	private void update() {
		Board board = new Board();
		board.setB_id(ScannerUtil.readInt("수정할 아이디를 입력하세요."));
		board.setB_content(ScannerUtil.readStr("수정할 내용을 입력하세요."));
		boardList.update(board);
	}

	private void insert() {
		Board board = ScannerUtil.readBoard();
		boardList.insert(board);
	}

	private void comment() {
		Board board = ScannerUtil.readBoard();
		boardList.comment(board);
	}
	
}
