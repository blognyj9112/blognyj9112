package com.cafe24.blognyj9112.payment.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointChargingDao {

	private static final Logger logger = LoggerFactory.getLogger(PointChargingDao.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	final String NS = "com.cafe24.blognyj9112.payment.service.PointChargingMapper.";

	//포인트 결제 승인 완료 업데이트
	public int updateapprovalResultPointCharging(PointCharging pointCharging) {
		logger.debug("pointChargingDao - updateapprovalResultPointCharging 실행");
		return sqlSession.update(NS+"updateapprovalResultPointCharging", pointCharging);
	}
	
	//멤버 포인트 업데이트
	public int updateMemberPointCharging(PointCharging updatePoint) {
		logger.debug("pointChargingDao - updateMemberPointCharging 실행");
		return sqlSession.update(NS+"updateMemberPointCharging", updatePoint);
	}
	
	//멤버 포인트 검색
	public PointCharging selectMemberPoint(String memberNo) {
		logger.debug("pointChargingDao - selectMemberPoint 실행");
		return sqlSession.selectOne(NS+"selectMemberPoint", memberNo);
	}
	
	//멤버No 검색
	public PointCharging pointCahrgingSearchMemberNo(String pointChargingNo) {
		logger.debug("pointChargingDao - pointCahrgingSearchMemberNo 실행");
		return sqlSession.selectOne(NS+"pointCahrgingSearchMemberNo", pointChargingNo);
	}
	
	//포인트 결제 삭제
	public int deletePointCharging(PointCharging pointCharging) {
		logger.debug("pointChargingDao - deletePointCharging 실행");
		int row = sqlSession.delete(NS+"deletePointCharging", pointCharging);
		return row;
	}
	
	//포인트 결제 승인 중복 검색
	public int pointChargingNoResultCount(String pointChargingNo) {
		logger.debug("pointChargingDao - pointChargingNoResultCount 실행");
		return sqlSession.selectOne(NS+"pointChargingNoResultCount",pointChargingNo);
	}
	
	//포인트 결제 신청 거절
	public int deniedPointCharging(PointCharging pointCharging) {
		logger.debug("pointChargingDao - deniedPointCharging 실행");
		int row = sqlSession.insert(NS+"deniedPointCharging", pointCharging);
		return row;
	}
	
	//포인트 결제 신청 승인
	public int acceptPointCharging(PointCharging pointCharging) {
		logger.debug("pointChargingDao - acceptPointCharging 실행");
		int row = sqlSession.insert(NS+"acceptPointCharging", pointCharging);
		return row;
	}
	
	//포인트 결제 금액
	public PointCharging pointChargingSum(String memberNo) {
		logger.debug("pointChargingDao - addPointCharging 실행");
		return sqlSession.selectOne(NS+"pointChargingSum", memberNo);
	}
	
	//포인트 결제 등록번호 최대값 검색
	public int PointChargingNo() {
		logger.debug("pointChargingDao - PointChargingNo 실행");
		return sqlSession.selectOne(NS+"PointChargingNo");
	}
	
	//포인트 결제 신청
	public int addPointCharging(PointCharging pointCharging) {
		logger.debug("pointChargingDao - addPointCharging 실행");
		int row = sqlSession.insert(NS+"addPointCharging", pointCharging);
		return row;
	}
	
	//포인트 결제 승인 완료 전체 검색
	public int pointChargingApprovalTotalCount() {
		logger.debug("pointChargingDao - pointChargingApprovalTotalCount 실행");
		return sqlSession.selectOne(NS+"pointChargingApprovalTotalCount");
	}
	
	//포인트 결제 승인 리스트
	public List<PointCharging> pointChargingApprovalList(Map<String,Integer> map) {
		logger.debug("pointChargingDao - pointChargingApprovalList 실행");
		 List<PointCharging> list = sqlSession.selectList(NS+"pointChargingApprovalList",map);
		return list;
	}	
	
	//포인트 결제 승인 대기 전체 검색
	public int pointChargingTotalCount() {
		logger.debug("pointChargingDao - pointChargingTotalCount 실행");
		return sqlSession.selectOne(NS+"pointChargingTotalCount");
	}
		
	//포인트 결제 승인 대기 리스트
	public List<PointCharging> pointChargingList(Map<String,Integer> map) {
		logger.debug("pointChargingDao - pointChargingList 실행");
		 List<PointCharging> list = sqlSession.selectList(NS+"pointChargingList",map);
		return list;
	}
}
