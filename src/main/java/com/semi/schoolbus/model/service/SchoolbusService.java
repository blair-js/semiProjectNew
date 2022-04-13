package com.semi.schoolbus.model.service;

import static com.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.schoolbus.model.dao.SchoolbusDao;
import com.semi.schoolbus.model.dto.Schoolbus;
import com.semi.schoolbus.model.dto.UserReservation;

public class SchoolbusService {

	public ArrayList<Schoolbus> selectBList() {
		// 통학버스 일정 조회 해오는 메소드
		Connection conn = getConnection();
		
		ArrayList<Schoolbus> list = new SchoolbusDao().selectBList(conn);
		
		close(conn);
		
		return list;
	}

	public int insertSchoolbus(int userNo, int busNo) {
		// 테이블 두개 DB 접근 해야되니까 Dao 메소드 두개 실행
		// 하나는 잔여좌석수 -1 해주는 메소드, 하나는 예약테이블에 추가하는 메소드
		Connection conn = getConnection();
		
		int result1 = new SchoolbusDao().insertSchoolbus(conn, userNo, busNo);
		
		int result2 = new SchoolbusDao().seatCountupdate(conn, busNo);
		
		if(result1 * result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1 * result2;
	}

	public ArrayList<UserReservation> selectReList() {
		// 버스 예약한 회원 목록 조회
		Connection conn = getConnection();
		
		ArrayList<UserReservation> list = new SchoolbusDao().selectReList(conn);
		
		close(conn);
		
		return list;
	}

	public int deleteAllSchoolbus() {
		// 통학버스 예약 내용 전체삭제 , 버스 테이블 잔여좌석수 30으로 리셋
		Connection conn = getConnection();
		
		int result1 = new SchoolbusDao().seatCountReset(conn);
		
		int result2 = new SchoolbusDao().deleteAllSchoolbus(conn);
		
		if(result1 * result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1 * result2;
	}

}
