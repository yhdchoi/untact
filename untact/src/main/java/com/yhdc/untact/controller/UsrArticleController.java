package com.yhdc.untact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.ResultData;
import com.yhdc.untact.service.ArticleService;
import com.yhdc.untact.util.Util;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;

	// LIST ARTICLES
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> doList() {
		return articleService.articles;
	}

	// GET AN ARTICLE
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

	// WRITE A NEW ARTICLE
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

		// IF OK
		int id = articleService.writeNewArticle(title, content);
		Article article = articleService.getArticleById(id);

		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "article", article);
	}

	// EDIT ARTICLE
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

		boolean result = articleService.editArticle(id, title, content);

		// CHECK ARTICLE
		if (result == false) {
			return new ResultData("F-4", id + "번 글이 존제하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 수정되었습니다.", "article", articleService.getArticleById(id));
	}

	// DELETE AN ARTICLE
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public ResultData doDelete(Integer id) {

		// CHECK INPUT
		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "제목을 작성해 주세요.");
		}

		boolean result = articleService.deleteArticleById(id);

		// CHECK ARTICLE
		if (result == false) {
			return new ResultData("F-1", id + "번 글이 존제하지 않습니.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 삭재되었습니다.", "id", id);
	}
}
