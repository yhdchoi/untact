<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='far fa-clipboard'></i></span> <span>${board.name} POST</span>" />

<%@ include file="../common/head.jspf"%>

	<div class="component-title-bar container mx-auto">
	  POST ARTICLE
	</div>
	
	<div class="section-article-write">
	  <div class="container mx-auto">
	    <div class="card bordered shadow-lg item-bt-1-not-last-child">
	      <div class="card-title">
	        <a href="javascript:history.back();" class="cursor-pointer">
	          <i class="fas fa-chevron-left"></i>
	        </a>
	        <span>게시물 작성</span>
	      </div>
	      <div class="px-4 py-8">
	        <form class="grid form-type-1">
	          <div class="form-control">
	            <label class="cursor-pointer label">
	              작성자
	            </label>
	            <div class="plain-text">
	              홍길동
	            </div>
	          </div>
	
	          <div class="form-control">
	            <label class="label">
	              <span class="label-text">제목</span>
	            </label>
	            <input type="text" placeholder="제목" class="input input-bordered">
	          </div>
	
	          <div class="form-control">
	            <label class="label">
	              <span class="label-text">본문</span>
	            </label>
	            <textarea placeholder="본문" class="h-80 textarea textarea-bordered"></textarea>
	          </div>
	
	          <div class="form-control">
	            <label class="label">
	              <span class="label-text">본문 이미지 1</span>
	            </label>
	            <div>
	              <input class="thumb-available" data-thumb-selector="next().next()" type="file" name="file" placeholder="본문 이미지 1" accept="image/png, image/jpeg, image/png">
	              <div class="mt-2">
	
	              </div>
	            </div>
	          </div>
	
	          <div class="form-control">
	            <label class="label">
	              <span class="label-text">본문 이미지 2</span>
	            </label>
	            <div>
	              <input class="thumb-available" data-thumb-selector="next()" data-thumb-selector="" class="" type="file" name="file" placeholder="본문 이미지 2" accept="image/png, image/jpeg, image/png">
	              <div class="mt-2">
	
	              </div>
	            </div>
	          </div>
	
	          <div class="mt-4 btn-wrap gap-1">
	            <a href="#" class="btn btn-primary btn-sm mb-1">
	              <span><i class="fas fa-save"></i></span>
	              &nbsp;
	              <span>작성</span>
	            </a>
	            
	            <a onclick="if ( !confirm('삭제하시겠습니까?') ) return false;" href="#"  class="btn btn-error btn-sm mb-1">
	              <span><i class="fas fa-trash"></i></span>
	              &nbsp;
	              <span>삭제</span>
	            </a>
	            <a href="#" class="btn btn-sm mb-1" title="자세히 보기">
	              <span><i class="fas fa-list"></i></span>
	              &nbsp;
	              <span>리스트</span>
	            </a>
	            <a href="#" class="btn btn-sm mb-1" title="자세히 보기">
	              <span><i class="fas fa-list"></i></span>
	              &nbsp;
	              <span>상세페이지</span>
	            </a>
	          </div>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>

<%@ include file="../common/foot.jspf"%>