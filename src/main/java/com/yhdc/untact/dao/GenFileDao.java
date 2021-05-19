package com.yhdc.untact.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yhdc.untact.dto.GenFile;

@Mapper
public interface GenFileDao {
	void saveMeta(Map<String, Object> param);
	
	GenFile getGenFile(@Param("relTypeCode") String relTypeCode, @Param("relId") int relId, @Param("typeCode") String tpeCode, 
			@Param("type2Code") String tpe2Code, @Param("fileNo") int fileNo);

	GenFile getGenFileById(@Param("id") int id);
	
	void changeRelId(@Param("id") int id, @Param("relId") int relId);
	
	void deleteFiles(@Param("relTypeCode") String relTypeCode, @Param("relId") int relId);
	
	List<GenFile> getGenFiles(@Param("relTypeCode") String relTypeCode, @Param("relId") int relId, 
			@Param("typeCode") String typeCode,	@Param("type2Code") String type2Code);
	
	List<GenFile> getGenFilesByRelTypeCodeAndRelId(@Param("relTypeCode") String relTypeCode, @Param("id") int id);
	
	void deleteFile(@Param("id") int id);
	
	List<GenFile> getGenFileDetails(@Param("relTypeCode") String relTypeCode, @Param("relId") List<Integer> relId, 
			@Param("typeCode") String typeCode, @Param("type2Code") String type2Code);
}
