package com.yhdc.untact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhdc.untact.dao.ReplyDao;
import com.yhdc.untact.dto.Reply;
import com.yhdc.untact.dto.ResultData;

@Service
public class ReplyService {
	@Autowired
	private ReplyDao replyDao;
	
	public ResultData write(String relTypeCode, int relId, int memberId, String content) {
		replyDao.write(relTypeCode, relId, memberId, content);
		int id = replyDao.getLastInsertId();
		
		return new ResultData("S_1", "댓글이 작성되었습니다.", "id", id);
	}
	
	public List<Reply> getPrintRepliesByRelTypeCodeAndRelId(String relTypeCode, int relId) {
		return replyDao.getPrintRepliesByRelTypeCodeAndRelId(relTypeCode, relId);
	}
	
	public Reply getReplyById(int id) {
		return replyDao.getReplyById(id);
	}
	
	public ResultData delete(int id) {
		replyDao.delete(id);
		return new ResultData("S-1", id + "번 댓글이 삭제되었습니다.", "id", id);
	}
	
	public ResultData edit(int id, String body) {
        replyDao.edit(id, body);

        return new ResultData("S-1", id + "번 댓글이 수정되었습니다.", "id", id);
    }
}
