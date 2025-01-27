package com.example.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class RestApplicationTests {

	@Test
	void t1() {

		ArrayList list = new ArrayList();

		list.add(1);
		list.add(2);
		list.add(3);

		if(list.get(1) instanceof String) {
			System.out.println("1번째 값은 문자열입니다.");
		}

		for(int i=0; i<list.size(); i++) {
			if(list.get(i) instanceof Integer) {
				int a = (int)list.get(i);
			} else if(list.get(i) instanceof String) {
				String a = (String)list.get(i);
			}
		}

	}

}
