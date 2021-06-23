package com.yedam.lambda;

interface Run {
	void run();
}

class RunCls implements Run {
	@Override
	public void run() {
		System.out.println("달립니다.");
	}
}



public class FunctionalEx1 {
	public static void main(String[] args) {
		
		RunCls cls = new RunCls();
		cls.run();
		
//		Run r = new Run() {		// 익명 구현객체
//			@Override
//			public void run() {
//				System.out.println("빨리 달립니다.");				
//			}
//		};
		
		Run r = () -> System.out.println("빨리 달립니다.");	 	// 람다 표현식
		r.run();
	
	}

}
