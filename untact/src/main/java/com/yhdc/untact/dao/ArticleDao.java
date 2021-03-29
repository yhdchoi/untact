package com.yhdc.untact.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.util.Util;

@Component
public class ArticleDao {

	private int lastArticleId;
	private List<Article> articles;

	public ArticleDao() {
		lastArticleId = 0;
		articles = new ArrayList<>();
		makeInitData();
	}

	// INIT
	public void makeInitData() {
		for (int i = 0; i < 5; i++) {
			writeNewArticle("ttt", "ccc");
		}
	}

	// LIST
	public List<Article> doList() {
		return articles;
	}

	// GET
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	// WRITE
	public int writeNewArticle(String title, String content) {
		int id = lastArticleId + 1;
		String regDate = Util.getNowDateStr();
		String upDate = Util.getNowDateStr();

		Article newArticle = new Article(id, regDate, upDate, title, content);
		articles.add(newArticle);

		lastArticleId = id;
		return id;
	}

	// EDIT
	public boolean editArticle(int id, String title, String content) {
		Article article = getArticleById(id);

		if (article == null) {
			return false;
		}

		article.setUpDate(Util.getNowDateStr());
		article.setTitle(title);
		article.setContent(content);

		return true;
	}

	// DELETE
	public boolean deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (article == null) {
			return false;
		}

		articles.remove(article);
		return true;
	}
}
