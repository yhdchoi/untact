package com.yhdc.untact.controller;

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
	
	// GET LIST
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public String showList(int boardId) {

		return "/usr/aticle/list";
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

		return articleService.writeNewArticle(title, content);
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

		return articleService.editArticle(id, title, content);
	}

	// DELETE AN ARTICLE
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public ResultData doDelete(Integer id) {

		// CHECK INPUT
		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "ID을 입력해 주세요.");
		}

		return articleService.deleteArticleById(id);
	}
}
