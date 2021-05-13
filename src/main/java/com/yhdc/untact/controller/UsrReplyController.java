package com.yhdc.untact.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.Reply;
import com.yhdc.untact.dto.ResultData;
import com.yhdc.untact.dto.Rq;
import com.yhdc.untact.service.ArticleService;
import com.yhdc.untact.service.ReplyService;
import com.yhdc.untact.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UsrReplyController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ReplyService replyService;
	
	// WRITE REPLY
	@RequestMapping("/usr/reply/doWrite")
	public String doWrite(HttpServletRequest req, String relTypeCode, int relId, String content, String redirectUri) {
		switch (relTypeCode) {
		case "article":
			Article article = articleService.getArticleById(relId);	
			if (article == null) {
				return Util.msgAndBack(req, "해당 게물이 존제하지 않습니다.");
			}
			break;

		default:
			return Util.msgAndBack(req, "올바르지 않은 relTypeCode 입니다.");
		}
		
		Rq rq = (Rq)req.getAttribute("rq");
		
		int memberId = rq.getLoggedInMemberId();
		
		ResultData writeResultData = replyService.write(relTypeCode, relId, memberId, content);
		
		return Util.msgAndReplace(req, writeResultData.getMsg(), redirectUri);
	}
	
	// DELTE REPLY
	@RequestMapping("/usr/reply/doDelete")
	public String doDelete(HttpServletRequest req, int id, String redirectUri) {
		Reply reply = replyService.getReplyById(id);
		
		if (reply == null) {
			return Util.msgAndBack(req, "존제하지 는 댓글입니다.");
		}
		
		Rq rq = (Rq)req.getAttribute("rq");
		
		if (reply.getMemberId() != rq.getLoggedInMemberId()) {		
			return Util.msgAndBack(req, "권한이 없습니다.");
		}
		
		ResultData deleteResultData = replyService.delete(id);
		
		return Util.msgAndReplace(req, deleteResultData.getMsg(), redirectUri);
	}
	
}
