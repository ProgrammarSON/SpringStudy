package com.company.hellospring;

import java.util.*;

import com.company.hellospring.emp.EmpDTO;

public class CollectionTest {
	
	public static void main(String[] args) {
		//List test
		List<String> list = new ArrayList<String>();
				
		//추가
		list.add("바나나");
		list.add("사과");
		list.add(0,"딸기");
		//System.out.println(list);
		//변경
		list.set(1, "포도");
		//System.out.println(list);
		//조회
		//System.out.println(list.get(1));
		//삭제
		list.remove(1);
		//System.out.println(list);
		//전체조회
		
		System.out.print("일반 for list[ ");
		for(int i=0; i<list.size(); i++)
			System.out.print(list.get(i)+" ");
		System.out.println("]");
		
		System.out.print("확장 for list[ ");
		for(String s : list)
			System.out.print(s+" ");
		System.out.println("]");
		
		System.out.print("iterator list[ ");
		Iterator<String> it = list.iterator();
		while(it.hasNext()) 
			System.out.print(it.next() + " ");
		System.out.println("]");		
	
		List<EmpDTO> empList = new ArrayList<EmpDTO>();
		
		empList.add(new EmpDTO("100","kim","a@b.c","99/11/01","IT"));
		empList.add(new EmpDTO("101","park","a@b.c","99/11/01","IT"));
		empList.add(new EmpDTO("102","choi","a@b.c","99/11/01","IT"));
		System.out.println(empList);
		
		for(EmpDTO e : empList)
			System.out.println(e.getEmployeeId() + " " + e.getLastName());
		
		Iterator<EmpDTO> iter = empList.iterator();
		System.out.println("\n\niterator EmpDTO ===========");
		while(iter.hasNext()) {
			EmpDTO temp = iter.next();
			System.out.println(temp.getEmployeeId() + " " + temp.getLastName());
		}
		
	}
}
