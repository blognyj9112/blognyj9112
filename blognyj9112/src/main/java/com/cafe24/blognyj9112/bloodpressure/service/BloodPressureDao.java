package com.cafe24.blognyj9112.bloodpressure.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BloodPressureDao {
	private static final Logger logger = LoggerFactory.getLogger(BloodPressureDao.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	final String NS = "com.cafe24.blognyj9112.bloodpressure.service.BloodPressureMapper.";
	
	
	//혈압이 건강검진표에 등록이 되어있는지 확인
	public int bloodPressureNoCountToHealthScreen(String bloodPressureNo) {
		logger.debug("BloodPressureDao - bloodPressureNoCountToHealthScreen 실행");
		return sqlSession.selectOne(NS+"bloodPressureNoCountToHealthScreen",bloodPressureNo);
	}
	
	//혈압 날짜 검색 카운트
	public int bloodPressureSearchDateCount(Map<String, Object> map) {
		logger.debug("BloodPressureDao - bloodPressureSearchDateCount 실행");
		return sqlSession.selectOne(NS+"bloodPressureSearchDateCount",map);
	}
	
	//혈압날짜 검색
	public List<BloodPressure> bloodPressureSearchDate(Map<String, Object> map) {
		logger.debug("BloodPressureDao - bloodPressureSearchDate 실행");
		return sqlSession.selectList(NS+"bloodPressureSearchDate",map);
	}
	
	//혈압차트리스트
	public List<BloodPressure> selectBloodPressureChart(String memberNo) {
		logger.debug("BloodPressureDao - selectBlppdPressureChart 실행");
		List<BloodPressure> list = sqlSession.selectList(NS+"bloodPressurechart",memberNo);
		return list;
	}
	
	//혈압 수정
	public int updateBloodPressure(BloodPressure bloodPressure) {
		logger.debug("BloodPressureDao - updateBloodPressure 실행");
		return sqlSession.update(NS+"updateBloodPressure",bloodPressure);
    }
	
	//혈압수정선택
	public BloodPressure selectBloodPressureOne(String bloodPressureNo) {
		logger.debug("BloodPressureDao - selectBloodPressureOne 실행");
		return sqlSession.selectOne(NS+"bloodPressureOne",bloodPressureNo);
	}
	
	//혈압 삭제
	public int deleteBloodPressure(String bloodPressureNo) {
		logger.debug("BloodPressureDao - deleteBloodPressure 실행");
		return sqlSession.delete(NS+"deleteBloodPressure",bloodPressureNo);
    }
	
	//혈압 리스트
	public List<BloodPressure> bloodPressureList(Map<String,Object> map) {
		logger.debug("BloodPressureDao - bloodPressureList 실행");
		 List<BloodPressure> list = sqlSession.selectList(NS+"bloodPressureList",map);
		return list;
	}
	
	//혈압 리스트 게시글 카운트
	public int bloodPressureCount(Map<String,Object> map) {
		logger.debug("BloodPressureDao - bloodPressureCount 실행");
		return sqlSession.selectOne(NS+"bloodPressureCount",map);
	}
	
	//혈압 번호 선택
	public int selectBloodPressureNo() {
		logger.debug("BloodPressureDao - selectBloodPressureNo 실행");
		int row = sqlSession.selectOne(NS+"bloodPressureNo");
		return row;
	}
	
	//혈압 등록
	public int addBloodPressure(BloodPressure bloodPressure) {
		logger.debug("BloodPressureDao - addBloodPressure 실행");
		int row = sqlSession.insert(NS+"addBloodPressure",bloodPressure);
		return row;
	}
	
}
