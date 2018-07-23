package com.cafe24.blognyj9112.payment.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.blognyj9112.group.service.Group;

@Repository
public class RefundDao {
	
	private static final Logger logger = LoggerFactory.getLogger(RefundDao.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	final String NS = "com.cafe24.blognyj9112.payment.service.RefundMapper.";

	//환불 상세 이전글 카운트
	public int prevrefundListDetailCount(String groupNo) {
		logger.debug("RefundDao - prevrefundListDetailCount 실행");
		return sqlSession.selectOne(NS+"prevrefundListDetailCount",groupNo);
	}		
	
	//환불 상세 다음글 카운트
	public int nextrefundListDetailCount(String groupNo) {
		logger.debug("RefundDao - nextrefundListDetailCount 실행");
		return sqlSession.selectOne(NS+"nextrefundListDetailCount",groupNo);
	}		
	
	//환불 상세 다음글 
	public Group nextrefundListDetail(String groupNo) {
		logger.debug("RefundDao - nextrefundListDetail 실행");
		return sqlSession.selectOne(NS+"nextrefundListDetail",groupNo);
	}
	
	//환불상세 이전글
	public Group prevrefundListDetail(String groupNo) {
		logger.debug("RefundDao - prevrefundListDetail 실행");
		return sqlSession.selectOne(NS+"prevrefundListDetail",groupNo);
	}
	//환불 리스트 자세히보기
	public Refund refundListDetail(String refundNo) {
		logger.debug("RefundDao - refundListDetail 실행");
		Refund refund = sqlSession.selectOne(NS+"refundListDetail",refundNo);
		return refund;
	}
	
	//환불 지급완료
	public int completeRefund(Refund refund) {
		logger.debug("RefundDao - completeRefund 실행");
		int row = sqlSession.insert(NS+"completeRefund", refund);
		return row;
	}
	
	//환불 신청 거절
	public int deniedRefund(Refund refund) {
		logger.debug("RefundDao - deniedRefund 실행");
		int row = sqlSession.insert(NS+"deniedRefund", refund);
		return row;
	}
	
	//환불 신청 승인
	public int acceptRefund(Refund refund) {
		logger.debug("RefundDao - acceptRefund 실행");
		int row = sqlSession.insert(NS+"acceptRefund", refund);
		return row;
	}
	
	//환불 등록번호 최대값 검색
	public int refundMaxNo() {
		logger.debug("RefundDao - refundNo 실행");
		return sqlSession.selectOne(NS+"refundNo");
	}
	
	//환불 신청
	public int addrefund(Refund refund) {
		logger.debug("RefundDao - addrefund 실행");
		int row = sqlSession.insert(NS+"addrefund", refund);
		return row;
	}
	
	//환불 지급 완료 전체 게시물 수 검색
	public int refundCompleteTotalCount() {
		logger.debug("RefundDao - refundCompleteTotalCount 실행");
		return sqlSession.selectOne(NS+"refundCompleteTotalCount");
	}
		
	//환불 지급 완료 리스트
	public List<Refund> refundCompleteList(Map<String,Integer> map) {
		logger.debug("RefundDao - refundCompleteList 실행");
		 List<Refund> list = sqlSession.selectList(NS+"refundCompleteList",map);
		return list;
	}
	
	//환불 승인 전체 게시물 수 검색
	public int refundApprovalTotalCount() {
		logger.debug("RefundDao - refundApprovalTotalCount 실행");
		return sqlSession.selectOne(NS+"refundApprovalTotalCount");
	}
		
	//환불 승인 리스트
	public List<Refund> refundApprovalList(Map<String,Integer> map) {
		logger.debug("RefundDao - refundApprovalList 실행");
		 List<Refund> list = sqlSession.selectList(NS+"refundApprovalList",map);
		return list;
	}
	
	//환불 승인 대기 전체 검색
	public int refundTotalCount() {
		logger.debug("RefundDao - refundTotalCount 실행");
		return sqlSession.selectOne(NS+"refundTotalCount");
	}
		
	//환불 승인 대기 리스트
	public List<Refund> refundList(Map<String,Integer> map) {
		logger.debug("RefundDao - refundList 실행");
		 List<Refund> list = sqlSession.selectList(NS+"refundList",map);
		return list;
	}
}
