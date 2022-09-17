package org.coding.mapper;

import java.util.ArrayList;

import org.coding.model.ReplyVO;

public interface ReplyMapper {
	
	// 댓글쓰기에 해당되는 DB작업 설계
		public int rewrite(ReplyVO reply);
		
		// 댓글목록리스트에 해당하는 DB작업 설계
		public ArrayList<ReplyVO> list(int bno); 

		// 댓글 수정에 해당하는 DB작업 설계
		public int modify(ReplyVO reply);
		
		// 댓글 삭제에 해당하는 DB작업 설계
		public int remove(int rno);

}
