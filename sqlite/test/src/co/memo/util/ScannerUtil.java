package co.memo.util;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import co.memo.model.Memo;

public class ScannerUtil {
	static Scanner scanner = new Scanner(System.in);
	
	public static int readInt() {
		int result = 0;
		while (true) {
			try {
				String temp = scanner.next();
				result = Integer.parseInt(temp);
				break;
			} catch (Exception e) {
				System.out.println("숫자 형식이 아닙니다.");
			}
		}
		return result;
	}
	
	// 한 줄 입력
	public static int readInt(String prompt) {
		System.out.println(prompt + ">");
		return readInt();
	}
	
	public static String readStr(String prompt) {
		System.out.println(prompt + ">");
		return readStr();
	}
	
	public static String readStr () {
		String result = "";
		try {
			result = scanner.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 입력
	public static String readDate () {
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		do {
			try {
				result = scanner.next();
				df.parse(result.trim());
				break;
			} catch (Exception e) {
				System.out.println("날짜 형식이 아닙니다.(yyyyMMdd)");
			}
			
		} while (true);
		return result;
	}
	
	// 입력
	public static Memo readMemo () {
		System.out.println("날짜, 제목, 내용을 입력하세요. > ");
		String result = scanner.next();
		String[] arr = result.split(",");
		Memo memo = new Memo(arr[0], arr[1], arr[2]);
		return memo;
	}
		
	public static int readComment() {
		System.out.println("1. 댓글 작성\n2. 이전 메뉴로 돌아가기");
		return scanner.nextInt();
		
	}
}
