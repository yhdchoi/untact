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

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;

	// LIST
	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId, String searchType,
			String keyword, @RequestParam(defaultValue = "1") int page) {

		Board board = articleService.getBoardById(boardId);

		if (Util.isEmpty(searchType)) {
			searchType = "titleAndContent";
		}

		if (board == null) {
			return Util.msgAndBack(req, boardId + "번 게시판이 존제하지 않습니다.");
		}

		req.setAttribute("board", board);

		int totalItemsCount = articleService.getArticlesTotalCount(boardId, searchType, keyword);

		if (keyword == null || keyword.trim().length() == 0) {
		}

		req.setAttribute("totalItemsCount", totalItemsCount);

		// MAX NUMBER OF POSTS ON A PAGE
		int itemsInPage = 20;

		// TOTAL NUMBER OF PAGE
		int totalPage = (int) Math.ceil(totalItemsCount / (double) itemsInPage);

		// CURRENT PAGE (TEMP)
		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);

		List<Article> articles = articleService.getPrintArticles(boardId, searchType, keyword, itemsInPage, page);

		req.setAttribute("articles", articles);

		return "usr/article/list";
	}

	// GET
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(Integer id) {

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

	// DETAIL
	@RequestMapping("/usr/article/detail")
	public String showDeatil(HttpServletRequest req, int id) {
		Article article = articleService.getArticlePrintById(id);

		if (article == null) {
			return Util.msgAndBack(req, id + "번 게시물은 존제하지 않습니다.");
		}

		Board board = articleService.getBoardById(article.getBoardId());

		req.setAttribute("article", article);
		req.setAttribute("board", board);

		return "usr/article/detail";
	}

	// SHOW WRITE
	@RequestMapping("/usr/article/write")
	public String showWrite(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId) {

		Board board = articleService.getBoardById(boardId);

		if (board == null) {
			return Util.msgAndBack(req, boardId + "번 게시판은 존제하지 않습니다.");
		}

		req.setAttribute("board", board);

		return "usr/article/write";
	}

	// WRITE
	@RequestMapping("/usr/article/doWrite")
	public String doWrite(HttpServletRequest req, int boardId, String title, String content) {

		// CHECK INPUT
		if (Util.isEmpty(title)) {
			return Util.msgAndBack(req, "제목을 작성해 주세요.");
		}

		if (Util.isEmpty(content)) {
			return Util.msgAndBack(req, "내용을 작성해 주세요.");
		}

		int memberId = 3; // TEMP

		ResultData writeArticleRd = articleService.writeNewArticle(boardId, memberId, title, content);

		if (writeArticleRd.isFail()) {
			return Util.msgAndBack(req, writeArticleRd.getMsg());
		}

		String replaceUri = "detail?id=" + writeArticleRd.getBody().get("id");
		return Util.msgAndReplace(req, writeArticleRd.getMsg(), replaceUri);
	}

	// EDIT
	@RequestMapping("/usr/article/doEdit")
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

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return new ResultData("F-4", "존재하지 않는 게시물 번호입니다.");
		}

		return articleService.editArticle(id, title, content);
	}

	// DELETE
	@RequestMapping("/usr/article/doDelete")
	public String doDelete(HttpServletRequest req, Integer id) {

		// CHECK INPUT
		if (Util.isEmpty(id)) {
			return Util.msgAndBack(req, "게시물 ID를 입력해 주세요.");
		}

		ResultData rd = articleService.deleteArticleById(id);

		if (rd.isFail()) {
			return Util.msgAndBack(req, rd.getMsg());
		}

		String redirectUri = "../article/list?boardId=" + rd.getBody().get("boardId");
		return Util.msgAndReplace(req, rd.getMsg(), redirectUri);
	}

}
