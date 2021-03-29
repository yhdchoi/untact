package com.yhdc.untact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhdc.untact.dao.ArticleDao;
import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	// LIST
	public List<Article> doList() {
		return articleDao.doList();
	}

	// GET
	public Article getArticleById(int id) {

		return articleDao.getArticleById(id);
	}

	// WRITE
	public ResultData writeNewArticle(String title, String content) {
		int id = articleDao.writeNewArticle(title, content);
		Article article = articleDao.getArticleById(id);

		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "article", article);

	}

	// EDIT
	public ResultData editArticle(int id, String title, String content) {
		Article article = getArticleById(id);

		if (article == null) {
			return new ResultData("F-4", id + "번 글이 존제하지 않습니다.", "id", id);
		}

		articleDao.editArticle(id, title, content);

		return new ResultData("S-1", id + "번 글이 수정되었습니다.", "article", article);
	}

	// DELETE
	public ResultData deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 글이 존제하지 않습니.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 삭재되었습니다.", "id", id);
	}
}
