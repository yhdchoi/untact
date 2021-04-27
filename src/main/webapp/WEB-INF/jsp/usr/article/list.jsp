<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='far fa-clipboard'></i></span> <span>${board.name} LIST</span>" />

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
		<div class="plain-link-wrap gap-3 mt-4">
            <a href="write?boardId=${board.id}" class="plain-link">
                <span><i class="fas fa-edit"></i></span>
                <span>글 작성</span>
            </a>
       	</div>
		<hr />
		
		<div class="search-form-box mt-2 px-4">
			<form action="" class="grid gap-2">
				<input type="hidden" name="boardId" value="${board.id}" />
				<div class="form-control">
					<label class="label">
						<span class="label-text">옵션</span>
					</label>
					<select class="select select-bordered" name="searchType">
						<option value="titleAndContent">제목+내용</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
					<script>
						const param__searchType = '${param.searchType}';
						if (param__searchType.length > 0) {
							$('.search-form-box form [name="searchType"]')
									.val('${param.searchType}');
						}
					</script>
				</div>
				
				<div class="form-control">
					<label class="label">
						<span class="label-text">제목</span>
					</label>
					<input value="${param.keyword}" class="input input-bordered"
						name="keyword" type="text" placeholder="검색어를 입력해주세요."
						maxlength="10" />
				</div>

				<div class="form-control">
					<label class="label">
						<span class="label-text">제목</span>
					</label>
					<input value="${param.keyword}" class="input input-bordered"
						name="keyword" type="text" placeholder="검색어를 입력해주세요."
						maxlength="10" />
				</div>
				<div class="form-control">
					<label class="label">
						<span class="label-text">검색</span>
					</label>
					<input type="submit" class="btn btn-sm btn-primary" value="검색" />
				</div>
			</form>
		</div>	
		
		<div class="articles mt-2">
			<c:if test="${articles == null || articles.size() == 0}">
				검색결과가 존재하지 않습니다.
			</c:if>
			<c:forEach items="${articles}" var="article">
				<div>
					ID : ${article.id}<br>
					REG DATE : ${article.regDate}<br>
					UPDATE DATE : ${article.updateDate}<br>
					TITLE : ${article.title}<br>
				</div>
				<hr />
			</c:forEach>
		</div>
		<div class="pages mt-4 mb-4 text-center">
			<c:set var="pageMenuArmSize" value="4" />
			<c:set var="startPage"
				value="${page - pageMenuArmSize >= 1  ? page - pageMenuArmSize : 1}" />
			<c:set var="endPage"
				value="${page + pageMenuArmSize <= totalPage ? page + pageMenuArmSize : totalPage}" />

			<c:set var="uriBase" value="?boardId=${board.id}" />
			<c:set var="uriBase"
				value="${uriBase}&searchType=${param.searchType}" />
			<c:set var="uriBase"
				value="${uriBase}&keyword=${param.keyword}" />
				
			<c:set var="aClassStr"
				value="px-2 inline-block border border-gray-200 rounded text-lg hover:bg-gray-200" />

			<c:if test="${startPage > 1}">
				<a class="${aClassStr}" href="${uriBase}&page=1">◀◀</a>
				<a class="${aClassStr}" href="${uriBase}&page=${startPage - 1}">◀</a>
			</c:if>

			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a class="${aClassStr} ${page == i ? 'text-red-500' : ''}"
					href="${uriBase}&page=${i}">${i}</a>
			</c:forEach>

			<c:if test="${endPage < totalPage}">
				<a class="${aClassStr}" href="${uriBase}&page=${endPage + 1}">▶</a>
				<a class="${aClassStr}" href="${uriBase}&page=${totalPage}">▶▶</a>
			</c:if>	
		</div>
	</div>
</div>

<%@ include file="../common/foot.jspf"%>
