package com.semi.snack.model.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.dto.Attachment;
import com.semi.snack.model.dao.SnackDao;
import com.semi.snack.model.dto.Snack;


public class SnackService {
	/*
	 * public int snackInsert(Snack snack) {
	 * 
	 * //커넥션 연결 Connection conn = getConnection();
	 * 
	 * int result = new SnackDao().snackInsert(conn, snack);
	 * 
	 * System.out.println(result);
	 * 
	 * if(result > 0 ) { commit(conn); }else { rollback(conn); } close(conn); return
	 * result; }
	 */
	
	public int insertSnack(Snack snack, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new SnackDao().insertSnack(conn, snack); //게시글등록
		
		int result2 = new SnackDao().insertAttachment(conn, at, snack.getUserNo());
		
			if(result1 > 0 && result2 > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			close(conn);
			return result1 * result2;
	}

	public ArrayList<Snack> selectList() {
		Connection conn = getConnection();
		
		ArrayList<Snack> list = new SnackDao().selectList(conn);
		
		close(conn);
		
		return list;
	}

	public Snack selectSnack(int sno) {
		Connection conn = getConnection();
		
		Snack snack = new SnackDao().selectSnack(conn, sno);
		
		if(snack != null) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return snack;
	}



	public Attachment selectAttachment(int sno) {
		
		Connection conn = getConnection();
		
		Attachment at = new SnackDao().selectAttachment(conn, sno);
		
		close(conn);
		
		return at;
	}

	public int updateSnack(Snack snack, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new SnackDao().updateSnack(conn, snack);
		
		int result2 = 1;
		
		if(at != null) {
			if(at.getFileNo() != 0) {
				result2 = new SnackDao().updateAttachment(conn, at);
			}else {
				//이건 시퀀스 번호가 아닌 서블릿서 현재 게시물 지정을 해두었기에 insert메서드를 따로 만든다.
				result2 = new SnackDao().insertNewAttachment(conn, at);
			}
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			System.out.println("service at ----------" + at);
			rollback(conn);
		}
		close(conn);
		
		return result1 * result2;
	}


			
}
