package com.yhdc.untact.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.Board;
import com.yhdc.untact.dto.ResultData;
import com.yhdc.untact.service.ArticleService;
import com.yhdc.untact.util.Util;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;

	// RETURN MSG
	private String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "common/redirect";
	}

	private String msgAndReplace(HttpServletRequest req, String msg, String replaceUrl) {
		req.setAttribute("msg", msg);
		req.setAttribute("replaceUrl", replaceUrl);
		return "common/redirect";
	}

	// LIST
	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId, String searchType, String keyword, @RequestParam(defaultValue = "1") int page) {
		Board board = articleService.getBoardById(boardId);

		if (board == null) {
			return msgAndBack(req, boardId + "번 게시판이 존제하지 않습니다.");
		}

		req.setAttribute("board", board);
		
		int totalItemsCount = articleService.getArticlesTotalCount(boardId);
		
		req.setAttribute("totalItemsCount", totalItemsCount);
		
		// MAX NUMBER OF POSTS IN A PAGE
		int itemsInPage = 10;
		// TOTAL NUMBER OF PAGE
		int totalPage = (int) Math.ceil(totalItemsCount / (double) itemsInPage);
		
		// CURRENT PAGE (TEMP)
		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);
		
		List<Article> articles = articleService.getPrintArticles(boardId, searchType, keyword, itemsInPage, page);
		
		System.out.println("articles : " + articles);
		
		req.setAttribute("articles", articles);
		
		return "usr/article/list";
	}

	// GET
	@RequestMapping("/usr/article/get")
	@ResponseBody
	public ResultData doGet(Integer id) {

		// CHECK INPUT
		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "ID을 입력해 주세요.");
		}

		Article article = articleService.getArticleById(id);

		// CHECK ARTICLE
		if (article == null) {
			return new ResultData("F-1", id + "번 글은 존제하지 않습니다.");
		}

		return new ResultData("S-1", id + "번 글이 입니다.", "article", article);
	}

	// WRITE
	@RequestMapping("/usr/article/write")
	@ResponseBody
	public ResultData doWrite(String title, String content) {

		// CHECK INPUT
		if (Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 작성해 주세요.");
		}

		if (Util.isEmpty(content)) {
			return new ResultData("F-2", "내용을 작성해 주세요.");
		}

		return articleService.writeNewArticle(title, content);
	}

	// EDIT
	@RequestMapping("/usr/article/edit")
	@ResponseBody
	public ResultData doEdit(Integer id, String title, String content) {

		// CHECK INPUT
		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "ID을 입력해 주세요.");
		}

		if (Util.isEmpty(title)) {
			return new ResultData("F-2", "제목을 작성해 주세요.");
		}

		if (Util.isEmpty(content)) {
			return new ResultData("F-3", "내용을 작성해 주세요.");
		}

		return articleService.editArticle(id, title, content);
	}

	// DELETE
	@RequestMapping("/usr/article/delete")
	public String doDelete(HttpServletRequest req, Integer id) {

		// CHECK INPUT
		if (Util.isEmpty(id)) {
			return msgAndBack(req, "게시물 ID를 입력해 주세요.");
		}

		ResultData rd = articleService.deleteArticleById(id);

		if (rd.isFail()) {
			return msgAndBack(req, rd.getMsg());
		}

		String redirectUrl = "../article/list?boardId=" + rd.getBody().get("boardId");

		return msgAndReplace(req, rd.getMsg(), redirectUrl);
	}
	

}
