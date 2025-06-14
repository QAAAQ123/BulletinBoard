package com.example.bulletin_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
사용자 board_manager
비밀번호 root
DB이름 boarddb
 */
@SpringBootApplication
public class BulletinBoardApplication {

	public static void main(String[] args) {
		//System.out.println("실행 확인");
		SpringApplication.run(BulletinBoardApplication.class, args);
	}

}
