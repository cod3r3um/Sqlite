package co.memo.view;

import java.util.List;
import java.util.Scanner;

import co.memo.access.MemoAccess;
import co.memo.access.MemoDAO;
import co.memo.model.Memo;
import co.memo.util.ScannerUtil;

public class MemoCliApp {

	MemoAccess memoList = new MemoDAO(); // 데이터베이스 입출력 처리하세요.
	Scanner scanner = new Scanner(System.in);

	public void start() {
		int menuNum;

		do {
			menuTitle();
			System.out.print("입력 > ");
			menuNum = scanner.nextInt();
			switch (menuNum) {
			case 1:
				write();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				FindAll();
				break;
			case 5:
				FindByDate();
				break;
			case 6:
				FindByContent();
				break;
			}
		} while (menuNum != 0);
		System.out.println("프로그램 종료.");
	}

	private void write() {
		Memo memo = ScannerUtil.readMemo();
		memoList.write(memo);
	}

	private void update() {
		Memo memo = new Memo();
		memo.setTitle(ScannerUtil.readStr("수정할 메모의 제목을 입력하세요."));
		memo.setContent(ScannerUtil.readStr("내용변경"));
		memoList.update(memo);
	}

	private void delete() {
		String title = ScannerUtil.readStr("삭제할 메모의 제목을 입력하세요.");
		memoList.delete(title);
	}

	private void FindAll() {
		List<Memo> list = memoList.FindAll();
		for(Memo memo : list) {
			System.out.println(memo);
		}

	}

	private void FindByDate() {
		String date = ScannerUtil.readStr("조회할 날짜를 입력하세요.");
		Memo memo = memoList.FindByDate(date);
		System.out.println(memo);
	}

	private void FindByContent() {
		String content = ScannerUtil.readStr("조회할 내용을 입력하세요.");
		Memo memo = memoList.FindByContent(content);
		System.out.println(memo);

	}

	private void menuTitle() {
		System.out.println("== 메모 관리 ==");
		System.out.println("=1. 메모 추가");
		System.out.println("=2. 메모 수정");
		System.out.println("=3. 메모 삭제");
		System.out.println("=4. 전체 조회");
		System.out.println("=5. 날짜로 조회");
		System.out.println("=6. 내용으로 조회");
		System.out.println("=0. 종료");
		System.out.println("===============");

	}

}
