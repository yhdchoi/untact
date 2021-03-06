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

	Article getArticlePrintById(@Param("id") int id);

	Board getBoardById(@Param("id") int id);

	int getLastInsertId();

	int getArticlesTotalCount(@Param("boardId") int boardId, @Param("searchType") String searchType,
			@Param("keyword") String keyword);

	List<Article> getPrintArticles(@Param("boardId") int boardId, @Param("searchType") String searchType,
			@Param("keyword") String keyword, @Param("limitFrom") int limitFrom, @Param("limitTake") int limitTake);

	// WRITE
	void writeNewArticle(@Param("boardId") int boardId, @Param("memberId") int memeberId, @Param("title") String title,
			@Param("content") String content);

	// EDIT
	boolean editArticle(@Param("id") int id, @Param("title") String title, @Param("content") String content);

	// DELETE
	void deleteArticleById(@Param("id") int id);

}