package com.cafe24.blognyj9112.exercisefeedback.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseFeedbackDao {

	private static final Logger logger = LoggerFactory.getLogger(ExerciseFeedbackDao.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	final String NS = "com.cafe24.blognyj9112.exercisefeedback.service.ExerciseFeedbackMapper.";
	
	//운동피드백 요청 리스트 검색
	public int memberNoSearchCount(String memberNo) {
		logger.debug("ExerciseFeedbackDao - memberNoSearchCount 실행");
		return sqlSession.selectOne(NS+"memberNoSearchCount",memberNo);
	}	
	//운동피드백 요청 리스트 검색
	public int exerciseFeedbackRequestListSearchCount(Map<String, Object> map) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackRequestListSearchCount 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackRequestListSearchCount",map);
	}	
	//운동피드백 요청 리스트 검색
	public List<ExerciseFeedbackRequest> exerciseFeedbackRequestListSearch(Map<String, Object> map) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackRequestListSearch 실행");
		return sqlSession.selectList(NS+"exerciseFeedbackRequestListSearch",map);
	}
	
	//운동피드 pt회원리스트 검색
	public List<ExerciseFeedbackRequest> exerciseFeedbackPtListSearch(Map<String, Object> map) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackPtListSearch 실행");
		return sqlSession.selectList(NS+"exerciseFeedbackPtListSearch",map);
	}
	
	//운동피드백 기간별 검색
	public List<ExerciseFeedbackRequest> exerciseFeedbackListSearchDate(Map<String, Object> map) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackListSearchDate 실행");
		return sqlSession.selectList(NS+"exerciseFeedbackListSearchDate",map);
	}
	
	//운동피드백 요청결과 디테일 검색
	public ExerciseFeedbackRequest exerciseFeedbackApprovalResult(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackApprovalResult 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackApprovalResult",exerciseFeedbackRequestNo);
	}
	
	//운동피드백 요청결과 카운트
	public int exerciseFeedbackApprovalresultTotalCount(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackApprovalresultTotalCount 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackApprovalresultTotalCount",exerciseFeedbackRequestNo);
	}
	
	//운동 피드백 요청 취소
	public int cancleExerciseFeedback(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackDao - cancleExerciseFeedback 실행");
		int row = sqlSession.insert(NS+"cancleRequestExercise",exerciseFeedbackRequestNo);
		return row;
	}	
		
	//운동 피드백 거절
	public int feedbackapprovalDenied(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackDao - feedbackapprovalDenied 실행");
		int row = sqlSession.insert(NS+"feedbackapprovalDenied",exerciseFeedbackRequestNo);
		return row;
	}	
	
	//운동 피드백 수락
	public int feedbackapprovalAccept(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackDao - feedbackapprovalAccept 실행");
		int row = sqlSession.insert(NS+"feedbackapprovalAccept",exerciseFeedbackRequestNo);
		return row;
	}	
	
	//운동 피드백 이전글
	public int prevExerciseFeedbackDetailCount(Map<String,Object> map) {
		logger.debug("ExerciseFeedbackDao - prevExerciseFeedbackDetailCount 실행");
		return sqlSession.selectOne(NS+"prevExerciseFeedbackDetailCount",map);
	}		
	
	//운동 피드백 다음글 
	public int nextExerciseFeedbackDetailCount(Map<String,Object> map) {
		logger.debug("ExerciseFeedbackDao - nextExerciseFeedbackDetailCount 실행");
		return sqlSession.selectOne(NS+"nextExerciseFeedbackDetailCount",map);
	}		
	
	//운동 피드백 다음글 
	public ExerciseFeedbackRequest nextExerciseFeedbackDetail(Map<String,Object> map) {
		logger.debug("ExerciseFeedbackDao - nextExerciseFeedbackDetail 실행");
		return sqlSession.selectOne(NS+"nextExerciseFeedbackDetail",map);
	}
	
	//운동 피드백 이전글
	public ExerciseFeedbackRequest prevExerciseFeedbackDetail(Map<String,Object> map) {
		logger.debug("ExerciseFeedbackDao - prevExerciseFeedbackDetail 실행");
		return sqlSession.selectOne(NS+"prevExerciseFeedbackDetail",map);
	}
	
	//운동 피드백 요청 상세보기
	public ExerciseFeedbackRequest exerciseFeedbackRequestDetail(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackRequestDetail 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackRequestDetail",exerciseFeedbackRequestNo);
	}
	
	//운동피드백 요청등록번호 최대값 검색
	public int exerciseFeedbackRequestNo() {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackRequestNo 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackRequestNo");
	}
	
	//운동피드백 요청 등록
	public int exerciseFeedbackRequest(ExerciseFeedbackRequest exerciseFeedbackRequest) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackRequest 실행");
		int row = sqlSession.insert(NS+"exerciseFeedbackRequest", exerciseFeedbackRequest);
		return row;
	}
	
	//운동피드백 요청리스트 전체 검색(pt회원)
	public int exerciseFeedbackListTotalCount(Map<String,Object> map) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackListTotalCount 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackListTotalCount",map);
	}
	
	//운동피드백 요청 리스트(pt회원)
	public List<ExerciseFeedbackRequest> exerciseFeedbackList(Map<String,Object> map) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackList 실행");
		 List<ExerciseFeedbackRequest> list = sqlSession.selectList(NS+"exerciseFeedbackList",map);
		return list;
	}
	
	//강사 선택
	public ExerciseFeedbackRequest exerciseFeedbackPtSelect(String memberNo) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackPtSelect 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackPtSelect",memberNo);
	}
	
	//강사리스트 전체 검색
	public int exerciseFeedbackPtTotalCount() {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackPtTotalCount 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackPtTotalCount");
	}
		
	//강사 리스트
	public List<ExerciseFeedbackRequest> exerciseFeedbackPtList(Map<String,Integer> map) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackPtList 실행");
		 List<ExerciseFeedbackRequest> list = sqlSession.selectList(NS+"exerciseFeedbackPtList",map);
		return list;
	}
	
	//운동피드백 요청리스트 전체 검색
	public int exerciseFeedbackRequestTotalCountadd() {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackRequestTotalCountadd 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackRequestTotalCountadd");
	}
	
	//운동피드백 요청리스트 전체 검색
	public int exerciseFeedbackRequestTotalCount(Map<String,Object> map) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackRequestTotalCount 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackRequestTotalCount",map);
	}
		
	//운동피드백 요청 리스트
	public List<ExerciseFeedbackRequest> exerciseFeedbackRequestList(Map<String,Object> map) {
		logger.debug("ExerciseFeedbackDao - exerciseFeedbackRequestList 실행");
		 List<ExerciseFeedbackRequest> list = sqlSession.selectList(NS+"exerciseFeedbackRequestList",map);
		return list;
	}
}

