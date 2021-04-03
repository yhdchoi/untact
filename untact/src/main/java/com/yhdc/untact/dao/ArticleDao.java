package com.yhdc.untact.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yhdc.untact.dto.Article;

@Mapper
public interface ArticleDao {

	// GET
	Article getArticleById(@Param("id") int id);
	
	int getLastInsertId();

	// WRITE
	int writeNewArticle(@Param("boardId") int boardId, @Param("memberId") int memeberId, @Param("title") String title,
			@Param("content") String content);

	// EDIT
	boolean editArticle(@Param("id") int id, @Param("title") String title, @Param("content") String content);

	// DELETE
	boolean deleteArticleById(@Param("id") int id);

}
