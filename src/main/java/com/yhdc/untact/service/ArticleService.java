package com.yhdc.untact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhdc.untact.dao.ArticleDao;
import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.Board;
import com.yhdc.untact.dto.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public Board getBoardById(int id) {
		return articleDao.getBoardById(id);
	}
	
	public int getArticlesTotalCount(int boardId, String searchType, String keyword) {
		if (keyword != null && keyword.length() == 0) {
			keyword = null;
		}
		return articleDao.getArticlesTotalCount(boardId, searchType, keyword);
	}
	
	public List<Article> getPrintArticles(int boardId, String searchType, String keyword, int itemsInPage, int page) {
		if (keyword != null && keyword.length() == 0) {
			keyword = null;
		}
		
		int limitFrom = (page - 1) * itemsInPage;
		int limitTake = itemsInPage;
		
		return articleDao.getPrintArticles(boardId, searchType, keyword, limitFrom, limitTake);
	}
	
	public Article getArticlePrintById(int id) {
		return articleDao.getArticlePrintById(id);
	}

	
	// WRITE
	public ResultData writeNewArticle(int boardId, int memberId, String title, String content) {
		int id = articleDao.getLastInsertId();

		articleDao.writeNewArticle(boardId, memberId, title, content);

		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "id", id);

	}

	// EDIT
	public ResultData editArticle(int id, String title, String content) {
		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-4", id + "번 글이 존제하지 않습니다.", "id", id);
		}

		articleDao.editArticle(id, title, content);

		return new ResultData("S-1", id + "번 글이 수정되었습니다.", "id", id);
	}

	// CHECK
	private boolean isEmpty(Article article) {
		if (article == null) {
			return true;
		} else if (article.isDelStatus()) {
			return true;
		}
		return false;
	}

	// DELETE
	public ResultData deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", id + "번 글이 존제하지 않습니.", "id", id);
		}
		
		articleDao.deleteArticleById(id);

		return new ResultData("S-1", id + "번 글이 삭재되었습니다.", "id", id, "boardId", article.getBoardId());
	}





}
