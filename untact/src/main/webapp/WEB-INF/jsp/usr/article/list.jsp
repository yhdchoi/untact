<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='far fa-clipboard'></i></span> <span>${board.name} BOARD</span>" />

<%@ include file="../common/head.jspf"%>

<div class="section section-article-list">
	<div class="container mx-auto">
		<div class="total-items">
			<span>TOTAL ITEMS : </span> 
			<span>${totalItemsCount}</span>			
		</div>
		
		<div class="total-pages">
			<span>TOTAL PAGES : </span> 
			<span>${totalPage}</span>			
		</div>

		<div class="total-items">
			<span>CURRENT PAGE : </span> 
			<span>${page}</span>			
		</div>
		
		<hr />
		<hr />
		
		<div class="search-form-box mt-2 px-4">
			<form action="" class="grid gap-2">
				
				<input type="hidden" name="boardId" value="${board.id}" />
					<div class="form-control">
					<label class="label">
						<span class="label-text">Search Category</span>
					</label>
					<select class="select select-bordered" name="searchKeywordType">
						<option value="titleAndBody">Title&Content</option>
						<option value="title">Title</option>
						<option value="body">Content</option>
					</select>
					<script>
						const param__searchKeywordType = '${param.searchType}';
						if ( param__searchKeywordType.length > 0 ) {
							$('.search-form-box form [name="searchType"]')
							.val('${param.searchType}');							
						}
					</script>
				</div>

				<div class="form-control">
					<label class="label">
						<span class="label-text">Search</span>
					</label>
					<input value="${param.keyword}" class="input input-bordered"
						name="searchKeyword" type="text" placeholder="Search for ..."
						maxlength="10" />
				</div>

				<div class="form-control">					
					<input type="submit" class="btn btn-sm btn-primary" value="Search" />
				</div>
			</form>
		</div>	
		
		<div class="articles mt-2">
			<c:forEach items="${articles}" var="article">
				<div>
					ID : ${article.id}<br>
					REG DATE : ${article.regDate}<br>
					UPDATE DATE : ${article.updateDate}<br>
					TITLE : ${article.title}<br>
					CONTENT : ${article.content}<br>
				</div>
				<hr />
			</c:forEach>
		</div>
		
		<div class="pages mt-4 mb-4 text-center">
			<c:set var="pageMenuArmSize" value="4" />
			<c:set var="startPage" value="${page - pageMenuArmSize >= 1 ? page - pageMenuArmSize : 1}" />
			<c:set var="endPage" value="${page + pageMenuArmSize <= totalPage ? page + pageMenuArmSize : totalPage}" />
		
			<c:set var="urlBase" value="?boardId=${board.id}" />
			<c:set var="urlBase" value="${urlBase}&searchType=${param.searchType}" />
			<c:set var="urlBase" value="${urlBase}&keyword=${param.keyword}" />
			
			<c:set var="aClassStr" value="px-2 inline-block border border-grey-200 rounded text-lg hover:bg-gray-200" />
			
			<c:if test="${startPage > 1}">
				<a class="${aClassStr}" href="${urlBase}&page=1">◀◀</a>
				<a class="${aClassStr}" href="${urlBase}&page=${startPage - 1}">◀</a>
			</c:if>
					
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a class="${aClassStr} ${page == i ? 'text-red-500' : ''}" href="${urlBase}&page=${i}">${i}</a>
			</c:forEach>
			
			<c:if test="${endPage < totalPage}">
				<a class="${aClassStr}" href="${urlBase}&page=${endPage + 1}">▶</a>
				<a class="${aClassStr}" href="${urlBase}&page=${totalPage1}">▶▶</a>
			</c:if>
			
			
		</div>
		
	</div>
</div>



<%@ include file="../common/foot.jspf"%>
