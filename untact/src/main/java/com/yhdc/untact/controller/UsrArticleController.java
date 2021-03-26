package com.yhdc.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.util.Util;

@Controller
public class UsrArticleController {
	private  int articlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		articlesLastId = 0;
		articles = new ArrayList<>();
		
		//initial data
		articles.add(new Article(++articlesLastId, "2021-04-01 12:12:12", "2021-04-01 12:12:12", "Title1", "Content1"));
		articles.add(new Article(++articlesLastId, "2021-04-01 12:12:12", "2021-04-01 12:12:12", "Title2", "Content2"));		
	}
	
	
	//Show the requested article
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		
		return articles.get(id-1);
	}
	
	
	//Show all the articles
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}
	
	
	//Add an article
	@RequestMapping("/usr/article/add")
	@ResponseBody
	public Map<String, Object> doAdd(String title, String body) {	
		//Registering current date
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		
		articles.add(new Article(++articlesLastId, regDate, updateDate, title, body));
		
		//CodeCheck : 'S' == Success, 'F' == Fail
		Map<String, Object> rs = new HashMap<>();
		rs.put("resultCode", "S-1");
		rs.put("msg", "Successfully added.");
		rs.put("id", articlesLastId);
		
		return rs;
	}


	//Delete an article
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public Map<String, Object> doDelete(int id) {
				
		boolean deleteArticleResult = deleteArticle(id);		

		Map<String, Object> rs = new HashMap<>();
		
		if(deleteArticleResult) {
			rs.put("resultCode", "S-1");
			rs.put("msg", "Successfully added.");
		}
		else {
			rs.put("resultCode", "F-1");
			rs.put("msg", "Failed to delete.");
		}

		rs.put("id", id);
		
		return rs;
	}
	

	//Delete an article without tempering the array
	private boolean deleteArticle(int id) {
		for (Article article : articles) {
			if(article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}
	
	
	//Modify an article
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public Map<String, Object> doModify(int id, String title, String body) {
		Article selArticle = null;
		
		//Scan
		for(Article article : articles) {
			if(article.getId() == id) {
				selArticle = article;
				break;
			}
		}
		
		//Check
		Map<String, Object> rs = new HashMap<>();

		if(selArticle == null)  {
			rs.put("resultCode", "F-1");
			rs.put("msg", String.format("%d article does not exist.", id));
			return rs;
		}
		
		//Insert
		selArticle.setUpdateDate(Util.getNowDateStr());
		selArticle.setTitle(title);
		selArticle.setBody(body);

		rs.put("resultCode", "S-1");
		rs.put("msg", String.format("%d article has been modified.", id));		
		return rs;			
	}
		
}
