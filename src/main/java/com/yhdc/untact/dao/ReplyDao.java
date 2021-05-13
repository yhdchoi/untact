package com.yhdc.untact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yhdc.untact.dto.Reply;

@Mapper
public interface ReplyDao {
	void write(@Param("relTypeCode") String relTyprCode, @Param("relId") int relId, @Param("memberId") int memberId, @Param("content") String content);
	
	int getLastInsertId();
	
	List<Reply> getPrintRepliesByRelTypeCodeAndRelId(@Param("relTypeCode") String relTypeCode, @Param("relId") int relId);
	
	Reply getReplyById(@Param("id") int id);
	
	void delete(@Param("id") int id);
}
