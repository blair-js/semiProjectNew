package com.semi.snack.model.service;

import static com.semi.common.JDBCTemplate.*;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;

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
			
}
