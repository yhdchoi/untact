<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='far fa-clipboard'></i></span> <span>${board.name} POST</span>" />

<%@ include file="../common/head.jspf"%>

<div class="section section-article-list">
	<div class="container mx-auto">
		<form method="POST" action="doWrite">
			<div class="form-control">
				<label class="label">
					제목
				</label>
				<input class="input input-bordered w-full" type="text" maxlength="100" name="title" placeholder="Enter title" />				
			</div>
			
			<div class="form-control">
				<label class="label">
					내용
				</label>
				<textarea class="textarea textarea-bordered w-full" maxlength="2000" name="content" placeholder="Write here"> </textarea>
			</div>
			
			<div class="mt-4 btn-wrap gap-1">
				<a href="#" class="btn btn-primary btn-sm mb-1">
					<span><i class="fas fa-save"></i></span>
					&nbsp;
					<span>작성</span>
				</a>
			</div>
			
			<div class="mt-4 btn-wrap gap-1">
				<a href="#" class="btn btn-sm mb-1" title="자세히 보기">
					<span><i class="fas fa-list"></i></span>
					&nbsp;
					<span>리스트</span>
				</a>
			</div>
		</form>	
	</div>
</div>

<%@ include file="../common/foot.jspf"%>