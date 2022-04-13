package com.semi.snack.model.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.dto.Attachment;
import com.semi.common.dto.PageInfo;
import com.semi.snack.model.dao.SnackDao;
import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.dto.SnackOrder;
import com.semi.snack.model.dto.UserPoint;


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

	public int deleteSnack(int sno) { //간식 삭제를 하기위한 메서드
		
		Connection conn = getConnection();
		
		int result1 = new SnackDao().deleteSnack(conn, sno);
		int result2 = 1;
		
		//미리 만들어둔 메서드를 통해 조회해서 결과가 있으면 담는다
		Attachment at = new SnackDao().selectAttachment(conn, sno);
		
		if(at != null) {
			result2 = new SnackDao().deleteAttachment(conn, sno); // 첨부파일을 지우는 메서드로 이동 시켜준다. 삭제하기 위해
		}
		
		if(result1 > 0 && result2 > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result1 * result2;
	}

	public UserPoint selectUserPoint(UserPoint up) {
		
		Connection conn = getConnection();
		
		UserPoint userPoint = new SnackDao().selectUserPoint(conn, up.getUserNo());
		
		if(userPoint != null) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return userPoint;
	}

	public int OrderEnd(UserPoint up) {
		
		Connection conn = getConnection();
		
		int result = new SnackDao().OrderEnd(conn, up);
		
		if ( result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int insertOrder(String snackNo, int uno) {
		
		Connection conn = getConnection();
		
		int result2 = new SnackDao().insertOrder(conn, snackNo, uno);
		
		if ( result2 > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		
		return result2;
	}

	public Snack selectPrice(String snackNo) {
		
		Connection conn = getConnection();

		Snack snack = new SnackDao().selectSnack(conn, snackNo);
		
		if(snack != null) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return snack;
		
	}

	public ArrayList<SnackOrder> selectSnackOrderList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<SnackOrder> list = new SnackDao().selectSnackOrderList(conn, pi);
		System.out.println("서비스 list" + list);
		close(conn);
		
		return list;
	}

	public int getListCount() { 
		
		Connection conn = getConnection();
		
		int listCount = new SnackDao().getListCount(conn);
		
		close(conn);
		return listCount;
	}



			
}
