package com.cafe24.blognyj9112.exercisefeedback.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseFeedbackResponseDao {
	private static final Logger logger = LoggerFactory.getLogger(ExerciseFeedbackResponseDao.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	final String NS = "com.cafe24.blognyj9112.exercisefeedback.service.ExerciseFeedbackResponseMapper.";
	
	//음식피드백 상세 평가 카운트
	public int selectFoodFeedbackCount(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - selectFoodFeedbackCount 실행");
		return sqlSession.selectOne(NS+"selectFoodFeedbackCount",exerciseFeedbackRequestNo);
	}	
	
	//운동피드백 상세 평가 카운트
	public int selectExerciseFeedbackCount(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - selectExerciseFeedbackCount 실행");
		return sqlSession.selectOne(NS+"selectExerciseFeedbackCount",exerciseFeedbackRequestNo);
	}	
	
	//음식피드백 선택
	public List<ExerciseFeedbackResult> selectFoodFeedback(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - selectFoodFeedback 실행");
		return sqlSession.selectList(NS+"selectFoodFeedback",exerciseFeedbackRequestNo);
	}

	//운동피드백 선택
	public List<ExerciseFeedbackResult> selectExerciseFeedback(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - selectExerciseFeedback 실행");
		return sqlSession.selectList(NS+"selectExerciseFeedback",exerciseFeedbackRequestNo);
	}
	
	//운동피드백 결과 카운트
	public int resultTotalCount(Map<String, Object> map) {
		logger.debug("ExerciseFeedbackResponseDao - resultTotalCount 실행");
		return sqlSession.selectOne(NS+"resultTotalCount",map);
	}	
	
	//평가 등록
	public int updateEvaluationAverage(Map<String, Object> addMap) {
		logger.debug("ExerciseFeedbackResponseDao - updateEvaluationAverage 실행");
		return sqlSession.update(NS+"updateEvaluationAverage",addMap);
	}
	
	//운동피드백 평가 등록 선생님 선택
	public ExerciseFeedbackResult exerciseFeedbackSelectTeacherNo(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - exerciseFeedbackSelectTeacherNo 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackSelectTeacherNo",exerciseFeedbackRequestNo);
	}
	
	//운동피드백 평가 평균 계산
	public double exerciseFeedbackEvaluationConculate(String teacherNo) {
		logger.debug("ExerciseFeedbackResponseDao - exerciseFeedbackEvaluationConculate 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackEvaluationConculate",teacherNo);
	}
	
	//운동 피드백 평가 등록
	public int addexerciseFeedbackResponseResultevaluation(Map<String, Object> map) {
		logger.debug("ExerciseFeedbackResponseDao - addexerciseFeedbackResponseResultevaluation 실행");
		return sqlSession.insert(NS+"addexerciseFeedbackResponseResultevaluation",map);
	}
	
	//운동피드백 평가 선택
	public ExerciseFeedbackResult exerciseFeedbackResponseResultevaluation(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - exerciseFeedbackResponseResultevaluation 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackResponseResultevaluation",exerciseFeedbackRequestNo);
	}
	
	//운동피드백 상세 평가 카운트
	public int exerciseFeedbackResponseResultevaluationCount(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - exerciseFeedbackResponseResultevaluationCount 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackResponseResultevaluationCount",exerciseFeedbackRequestNo);
	}		
	
	//운동피드백 상세 이전글 카운트
	public int prevExerciseFeedbackDetailCount(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - prevExerciseFeedbackDetailCount 실행");
		return sqlSession.selectOne(NS+"prevExerciseFeedbackDetailCount",exerciseFeedbackRequestNo);
	}		
	
	//운동피드백 상세 다음글 카운트
	public int nextExerciseFeedbackCount(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - nextExerciseFeedbackCount 실행");
		return sqlSession.selectOne(NS+"nextExerciseFeedbackCount",exerciseFeedbackRequestNo);
	}		
	
	//운동피드백 상세 다음글 
	public ExerciseFeedbackResult nextExerciseFeedbackDetail(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - nextExerciseFeedbackDetail 실행");
		return sqlSession.selectOne(NS+"nextExerciseFeedbackDetail",exerciseFeedbackRequestNo);
	}
	
	//운동피드백 상세 이전글
	public ExerciseFeedbackResult prevExerciseFeedbackDetail(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - prevExerciseFeedbackDetail 실행");
		return sqlSession.selectOne(NS+"prevExerciseFeedbackDetail",exerciseFeedbackRequestNo);
	}
	
	//운동피드백 상세 이전글
	public ExerciseFeedbackResult exerciseFeedbackResponseResultDetail(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseDao - exerciseFeedbackResponseResultDetail 실행");
		return sqlSession.selectOne(NS+"exerciseFeedbackResponseResultDetail",exerciseFeedbackRequestNo);
	}
	
	//피드백 결과 리스트
	public List<ExerciseFeedbackResult> exerciseFeedbackResult(Map<String, Object> map) {
		logger.debug("ExerciseFeedbackResponseDao - exerciseFeedbackResult 실행");
		 List<ExerciseFeedbackResult> list = sqlSession.selectList(NS+"exerciseFeedbackResult",map);
		return list;
	}
		
	//운동 피드백 등록
	public int addExerciseFeedbackRequestResult(ExerciseFeedbackResult exerciseFeedbackResult) {
		logger.debug("ExerciseFeedbackResponseDao - addExerciseFeedbackRequestResult 실행");
		int row = sqlSession.insert(NS+"addExerciseFeedbackRequestResult",exerciseFeedbackResult);
		return row;
	}
	//음식 삭제
	public int deleteFoodFeedback(Map<String, Object> map) {
		logger.debug("ExerciseFeedbackResponseDao - deleteFoodFeedback 실행");
		return sqlSession.delete(NS+"deleteFoodFeedback",map);
	}
	
	//운동 삭제
	public int deleteExerciseFeedback(Map<String, Object> map) {
		logger.debug("ExerciseFeedbackResponseDao - deleteExerciseFeedback 실행");
		return sqlSession.delete(NS+"deleteExerciseFeedback",map);
	}
	
	//음식 등록
	public int addFoodFeedback(ExerciseFeedbackResult exerciseFeedbackResult) {
		logger.debug("ExerciseFeedbackResponseDao - addFoodFeedback 실행");
		return sqlSession.insert(NS+"addFoodFeedback",exerciseFeedbackResult);
	}
	
	//운동 등록
	public int addExerciseFeedback(ExerciseFeedbackResult exerciseFeedbackResult) {
		logger.debug("ExerciseFeedbackResponseDao - addExerciseFeedback 실행");
		return sqlSession.insert(NS+"addExerciseFeedback",exerciseFeedbackResult);
	}
	
	//운동 검색
	public List<ExerciseFeedbackResult> exerciseSearch() {
		logger.debug("ExerciseFeedbackResponseDao - exerciseSearch 실행");
		 List<ExerciseFeedbackResult> list = sqlSession.selectList(NS+"exerciseSearch");
		return list;
	}
	
	//음식 검색
	public List<ExerciseFeedbackResult> foodSearch(Map<String, String> map) {
		logger.debug("ExerciseFeedbackResponseDao - foodSearch 실행");
		 List<ExerciseFeedbackResult> list = sqlSession.selectList(NS+"foodSearch",map);
		return list;
	}
	
	//음식 그룹 검색
	public List<ExerciseFeedbackResult> foodGroupSelectOptionList(Map<String, String> map) {
		logger.debug("ExerciseFeedbackResponseDao - foodGroupSelectOptionList 실행");
		 List<ExerciseFeedbackResult> list = sqlSession.selectList(NS+"foodGroupSelectOptionList",map);
		return list;
	}
	
}
