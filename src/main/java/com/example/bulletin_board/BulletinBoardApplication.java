package com.example.bulletin_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
사용자 board_manager
비밀번호 root
DB이름 boarddb

1. DB연결
2. POST CURD 기능 및 DB
3. Comment와 Post entity 데이터간 관계 설정
4. comment CURD 기능 및 DB
5. HTML로 화면 만들기
6. Javascript로 이벤트 리스너 등록
7. css로 꾸미기

메인 페이지
글 목록만(제목) + 새 글 작성 버튼

글 상세페이지
1.각 글 상세페이지에 들어가면 제목,내용,작성(수정)일시가 나옴
2.글 수정,삭제 기능
3. 댓글 생성 버튼
4. 댓글 확인,수정,삭제 기능
 */
@SpringBootApplication
public class BulletinBoardApplication {

	public static void main(String[] args) {
		//System.out.println("실행 확인");
		SpringApplication.run(BulletinBoardApplication.class, args);
	}
}
