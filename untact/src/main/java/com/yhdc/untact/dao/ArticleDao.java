package com.yhdc.untact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.Board;

@Mapper
public interface ArticleDao {

	// GET
	Article getArticleById(@Param("id") int id);
	
	int getLastInsertId();

	Board getBoardById(@Param("id") int id);
	
	// WRITE
	int writeNewArticle(@Param("boardId") int boardId, @Param("memberId") int memeberId, @Param("title") String title,
			@Param("content") String content);

	// EDIT
	boolean editArticle(@Param("id") int id, @Param("title") String title, @Param("content") String content);

	// DELETE
	boolean deleteArticleById(@Param("id") int id);

	int getArticlesTotalCount(@Param("boardId") int boardId);

	List<Article> getPrintArticles(@Param("boardId") int boardId, @Param("keyword") String keyword, @Param("limitFrom") int limitFrom, @Param("limitTake") int limitTake);

}
