package com.semi.snack.model.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.semi.snack.model.dao.SnackDao;
import com.semi.snack.model.dto.Snack;

public class SnackService {

	public int snackInsert(Snack snack) {
		
		//커넥션 연결
		Connection conn = getConnection();

		int result = new SnackDao().snackInsert(conn, snack);
		
		System.out.println(result);
		
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}



}
