<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='far fa-clipboard'></i></span> <span>${board.name} WRITE</span>" />

<%@ include file="../common/head.jspf"%>

<script>
	let ArticleWrite_submitFormDone = false;
	function ArticleWrite_submitForm(form) {
		if (ArticleWrite_submitFormDone) {
			return;
		}
		
		form.title.value = form.title.value.trim();
		
		if (form.title.value.length == 0) {
			alert('제목을 작성해주세요.');
			for.title.focus();
			
			return;
		}
		
		form.body.value = for.body.value.trim();
		
		if (for.body.value.length == 0) {
			alert('내용을 작성해주세요.');
			form.content.focus();
			
			return;
		}
		form.submit();
		ArticleWrite_submitFormDone = true;
	}
</script>


<div class="section section-article-list">
	<div class="container mx-auto">
		<form method="POST" action="doWrite" onsubmit="ArticleWrite_submitForm(this); return false;">
			<input type="hidden" name="boardId" value="${board.id}" />
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
				<button type="submit" href="#" class="btn btn-primary btn-sm mb-1">
					<span><i class="fas fa-save"></i></span>
					&nbsp;
					<span>작성</span>
				</button>
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