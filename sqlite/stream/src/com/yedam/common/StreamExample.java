package com.yedam.common;

import java.util.List;
import java.util.OptionalDouble;

import com.yedam.Person;
import com.yedam.PersonDAO;

public class StreamExample {
	public static void main(String[] args) {
		
		// 1. 스트림 2. 중간처리/최종처리 3. 결과
		List<Person> list = null;
		PersonDAO dao = new PersonDAO();
		list = dao.getPersonList();
		OptionalDouble avg = list.stream()	// 스트림 생성
		.filter((Person t) -> t.getAge()%2 == 0)	// 필터링
//		.filter(t -> t.getId() > 900)
		.mapToInt((p) -> p.getAge())	// 매핑 a -> b
//		.forEach(v -> System.out.println(v));
		.average(); // 나이 평균
		if(avg.isPresent()) {
			System.out.println("평균 나이: "+ avg.getAsDouble());			
		} else {
			System.out.println("만족하는 요소가 없습니다.");
		}
	}

}
