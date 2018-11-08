package com.company.hellospring.common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.company.hellospring.UserSearchDTO;
import com.company.hellospring.UserService;

@Component
public class Scheduler {
	
	@Autowired
	UserService userService;
		
	/*@Scheduled(cron="0/30 * 9 * * *")	//9시 시간대에 30초간격으로 실행
	public void jobTest1() {
		System.out.println("job 실행");
		UserSearchDTO searchDTO = new UserSearchDTO();
		searchDTO.setStart(1);
		searchDTO.setEnd(3);
		System.out.println(userService.getUsers(searchDTO));
	}*/
}
