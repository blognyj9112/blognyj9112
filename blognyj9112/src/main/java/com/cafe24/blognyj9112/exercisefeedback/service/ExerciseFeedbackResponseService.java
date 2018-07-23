package com.cafe24.blognyj9112.exercisefeedback.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.blognyj9112.payment.service.PointCharging;
import com.cafe24.blognyj9112.payment.service.PointChargingDao;
import com.cafe24.kyungsu93.message.service.Message;
import com.cafe24.kyungsu93.message.service.MessageDao;

@Service
@Transactional
public class ExerciseFeedbackResponseService {
	@Autowired
	private ExerciseFeedbackResponseDao exerciseFeedbackResponseDao;
	@Autowired
	private ExerciseFeedbackDao exerciseFeedbackDao;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private PointChargingDao pointChargingDao;
	private static final Logger logger = LoggerFactory.getLogger(ExerciseFeedbackResponseService.class);

	/**
	 * 운동 피드백 평가점수 등록
	 * @param exerciseFeedbackRequestNo
	 * @param exerciseFeedbackEvaluationGrade
	 */
	public void addexerciseFeedbackResponseResultevaluation(String exerciseFeedbackRequestNo,double exerciseFeedbackEvaluationGrade){
		logger.debug("ExerciseFeedbackResponseService - exerciseFeedbackResponseResultDetail실행");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("exerciseFeedbackRequestNo", exerciseFeedbackRequestNo);
		map.put("exerciseFeedbackEvaluationGrade", exerciseFeedbackEvaluationGrade);
		//평가 등록
		exerciseFeedbackResponseDao.addexerciseFeedbackResponseResultevaluation(map);
		//평균값
		ExerciseFeedbackResult feedbackteacherNo = exerciseFeedbackResponseDao.exerciseFeedbackSelectTeacherNo(exerciseFeedbackRequestNo);
		String teacherNo = feedbackteacherNo.getMemberNo();
		String teacherName = feedbackteacherNo.getMemberName();
		double average = exerciseFeedbackResponseDao.exerciseFeedbackEvaluationConculate(teacherNo);
		//평가정보 업데이트
		double averageResult = Math.round((average*100)/100.0);
		Map<String,Object> addMap = new HashMap<String,Object>();
		addMap.put("averageResult", averageResult); //평가점수
		addMap.put("teacherNo", teacherNo); //회원번호
		exerciseFeedbackResponseDao.updateEvaluationAverage(addMap);
		//메세지 
		logger.debug("ExerciseFeedbackResponseService - sendMessage 시작");
		Message message = new Message();
		int result = (messageDao.messageNo())+1;//메시지의 no 를 구함
		String messageNo = "message_";
		//받는사람
		message.setMemberReceiveNo(teacherNo);
		//보내는사람
		message.setSendMessageNo(messageNo+result);
		String memberSendNo = "member_1";//관리자
		String memberSendMessageId ="rlaansrl";
		message.setMemberSendNo(memberSendNo);
		message.setSendMessageId(memberSendMessageId);
		logger.debug("messageNo : "+message.getSendMessageNo());
		//메세지 내용
		String messageTitle = teacherName+"님의 운동피드백 평가점수가 업데이트 되었습니다.";
		message.setMessageTitle(messageTitle);
		String messageContent = teacherName+"님의 운동피드백 평가점수가 "+averageResult+"로 업데이트 되었습니다.";
		message.setMessageContent(messageContent);		
		messageDao.sendMessage(message);//send_Message 테이블 에 데이트를 셋
		messageDao.sendMessageContent(message);//발신자 테이블에 데이터 셋
		messageDao.receiveMessageContent(message);//수신자 테이블에 데이터 셋
		logger.debug("ExerciseFeedbackResponseService - sendMessage 완료");
	}
	/**
	 * 운동 피드백 상세 페이지
	 * @param exerciseFeedbackRequestNo
	 * @return
	 */
	public Map<String, Object> exerciseFeedbackResponseResultDetail(String exerciseFeedbackRequestNo){
		logger.debug("ExerciseFeedbackResponseService - groupDetail실행");
		ExerciseFeedbackResult exerciseFeedbackResult = new ExerciseFeedbackResult();
		exerciseFeedbackResult.setExerciseFeedbackRequestNo(exerciseFeedbackRequestNo);
		logger.debug("exerciseFeedbackRequestNo:"+exerciseFeedbackRequestNo);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		//상세 내용
		ExerciseFeedbackResult exerciseFeedbackResultDetail = exerciseFeedbackResponseDao.exerciseFeedbackResponseResultDetail(exerciseFeedbackRequestNo);
		logger.debug("exerciseFeedbackResultDetail:"+exerciseFeedbackResultDetail);
		returnMap.put("exerciseFeedbackResultDetail", exerciseFeedbackResultDetail);
		//운동피드백 평가 카운트
		int result = 0;
		result = exerciseFeedbackResponseDao.exerciseFeedbackResponseResultevaluationCount(exerciseFeedbackRequestNo);
		if(result>0) {
			//평가점수 검색
			ExerciseFeedbackResult exerciseFeedbackEvaluation = exerciseFeedbackResponseDao.exerciseFeedbackResponseResultevaluation(exerciseFeedbackRequestNo);
			logger.debug("exerciseFeedbackEvaluation:"+exerciseFeedbackEvaluation);		
			returnMap.put("result", result);
			returnMap.put("exerciseFeedbackEvaluation", exerciseFeedbackEvaluation);
		}
		returnMap.put("result", result);	
		//운동 피드백, 음식 피드백 리스트 가져오기
		int foodFeedbackResultCount = 0;
		int exerciseFeedbackResultCount = 0;
		foodFeedbackResultCount = exerciseFeedbackResponseDao.selectFoodFeedbackCount(exerciseFeedbackRequestNo);
		exerciseFeedbackResultCount = exerciseFeedbackResponseDao.selectExerciseFeedbackCount(exerciseFeedbackRequestNo);
		if(foodFeedbackResultCount > 0) {
			List<ExerciseFeedbackResult> selectFoodFeedback = exerciseFeedbackResponseDao.selectFoodFeedback(exerciseFeedbackRequestNo);
			returnMap.put("selectFoodFeedback", selectFoodFeedback);
			returnMap.put("FoodFeedbackResultCount", foodFeedbackResultCount);
			logger.debug("foodFeedbackResultCount:"+foodFeedbackResultCount);	
		}
		if(exerciseFeedbackResultCount > 0) {
			List<ExerciseFeedbackResult> selectExerciseFeedback = exerciseFeedbackResponseDao.selectExerciseFeedback(exerciseFeedbackRequestNo);
			returnMap.put("selectExerciseFeedback", selectExerciseFeedback);
			returnMap.put("exerciseFeedbackResultCount", exerciseFeedbackResultCount);
			logger.debug("selectExerciseFeedback:"+selectExerciseFeedback);
		}
		returnMap.put("foodFeedbackResultCount", foodFeedbackResultCount);
		returnMap.put("exerciseFeedbackResultCount", exerciseFeedbackResultCount);
		//이전글 다음글 
		int countPrev = 0;
		int countNext = 0;
		//이전글 다음글 카운트 
		countPrev = exerciseFeedbackResponseDao.prevExerciseFeedbackDetailCount(exerciseFeedbackRequestNo);
		countNext = exerciseFeedbackResponseDao.nextExerciseFeedbackCount(exerciseFeedbackRequestNo);
		logger.debug("countPrev:"+countPrev);	
		logger.debug("countNext:"+countNext);	
		if(countNext != 0){
			ExerciseFeedbackResult nextExerciseFeedback = exerciseFeedbackResponseDao.nextExerciseFeedbackDetail(exerciseFeedbackRequestNo);
			returnMap.put("nextExerciseFeedback", nextExerciseFeedback);
			returnMap.put("countNext", countNext);
			if(countPrev != 0){
				//둘다있을경우
				logger.debug("이전글,다음글이 있습니다.");
				ExerciseFeedbackResult prevExerciseFeedback = exerciseFeedbackResponseDao.prevExerciseFeedbackDetail(exerciseFeedbackRequestNo);
				returnMap.put("prevExerciseFeedback", prevExerciseFeedback);
				returnMap.put("countPrev", countPrev);
			}else {
				//이전글이 없을경우
				logger.debug("이전글이 없습니다.");
				returnMap.put("countPrev", countPrev);
			}
		}else {
			if(countPrev != 0) {
				//다음글이 없을 경우
				logger.debug("다음글이 없습니다.");
				ExerciseFeedbackResult prevExerciseFeedback = exerciseFeedbackResponseDao.prevExerciseFeedbackDetail(exerciseFeedbackRequestNo);
				returnMap.put("prevExerciseFeedback", prevExerciseFeedback);
				returnMap.put("countPrev", countPrev);
				returnMap.put("countNext", countNext);
			}
		}
		return returnMap;
	}
	
	/**
	 * 피드백결과리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> exerciseFeedbackResultList(HttpSession session, int currentPage, int pagePerRow) {
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		String memberNo = (String) session.getAttribute("memberSessionNo");
		int sessionLevel = (Integer) session.getAttribute("memberSessionLevel");
		logger.debug("memberNo:"+memberNo);
		logger.debug("sessionLevel:"+sessionLevel);
		map.put("sessionLevel", sessionLevel);
		map.put("memberNo", memberNo);
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		List<ExerciseFeedbackResult> list = exerciseFeedbackResponseDao.exerciseFeedbackResult(map);
		int total = exerciseFeedbackResponseDao.resultTotalCount(map);
		logger.debug("total:"+total);
		int lastPage = total/pagePerRow;
        if(total % pagePerRow != 0) {
            lastPage++;
        }
        logger.debug("list:"+list);
        logger.debug("lastPage:"+lastPage);
        logger.debug("currentPage:"+currentPage);
        logger.debug("beginRow:"+beginRow);
        logger.debug("pagePerRow:"+pagePerRow);
        logger.debug("======================page block=========================");
       
        int pagePerBlock = pagePerRow; //보여줄 블록 수 
        int block = currentPage/pagePerBlock;
        int totalBlock = total/pagePerBlock;//총 블록수
        
        if(currentPage % pagePerBlock != 0) {
        	block ++;
        }
        int firstBlockPage = (block-1)*pagePerBlock+1;
        int lastBlockPage = block*pagePerBlock;
        
		if(lastPage > 0) {			
			if(lastPage % pagePerBlock != 0) {
				totalBlock++;
			}
		}
		if(lastBlockPage >= totalBlock) {
			lastBlockPage = totalBlock;
		}
		logger.debug("firstBlockPage:"+firstBlockPage);
		logger.debug("lastBlockPage:"+lastBlockPage);
		logger.debug("block:"+block);
		logger.debug("totalBlock:"+totalBlock);
		logger.debug("======================page block=========================");
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("firstBlockPage", firstBlockPage);
		returnMap.put("lastBlockPage", lastBlockPage);
		returnMap.put("totalBlock", totalBlock);
		returnMap.put("total", total);
		return returnMap;
	}
	
	/**
	 * 음식피드백삭제
	 * @param exerciseFeedbackRequestNo
	 * @param foodInfoNo
	 */
	public void deleteFoodFeedback(String exerciseFeedbackRequestNo,String foodInfoNo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("exerciseFeedbackRequestNo", exerciseFeedbackRequestNo);
		map.put("foodInfoNo", foodInfoNo);
		exerciseFeedbackResponseDao.deleteFoodFeedback(map);
		
	}
	/**
	 * 운동피드백 삭제
	 * @param exerciseFeedbackRequestNo
	 * @param exerciseNo
	 */
	public void deleteExerciseFeedback(String exerciseFeedbackRequestNo,String exerciseNo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("exerciseFeedbackRequestNo", exerciseFeedbackRequestNo);
		map.put("exerciseNo", exerciseNo);
		exerciseFeedbackResponseDao.deleteExerciseFeedback(map);
		
	}
	/**
	 * 멤버 탈퇴 확인
	 * @param memberNo
	 * @return
	 */
	public int memberSearchCount(String memberNo) {
		return exerciseFeedbackDao.memberNoSearchCount(memberNo);
	}
	
	/**
	 * 운동피드백 등록 메세지, 포인트 차감 및 추가
	 * @param 
	 */
	public void addExerciseFeedbackRequestResult(ExerciseFeedbackResult exerciseFeedback) {
		logger.debug("ExerciseFeedbackResponseService - addExerciseFeedbackRequestResult실행");	
		String exerciseFeedbackRequestNo = exerciseFeedback.getExerciseFeedbackRequestNo();
		logger.debug("exerciseFeedbackRequestNo 홗인 : "+exerciseFeedbackRequestNo);
		exerciseFeedbackResponseDao.addExerciseFeedbackRequestResult(exerciseFeedback); //운동피드백 등록
		//운동피드백 알림 메세지 보내기
		String searchMemberNo = exerciseFeedback.getMemberNo();
		logger.debug("searchMemberNo 홗인 : "+searchMemberNo);
		//멤버 포인트 차감
		PointCharging pointCharging = pointChargingDao.selectMemberPoint(searchMemberNo);
		int memberPoint = pointCharging.getMemberPoint();
		logger.debug("memberPoint 홗인 : "+memberPoint);
		memberPoint = memberPoint - 1000;
		logger.debug("memberPoint - 1000 차감 홗인 : "+memberPoint);
		PointCharging updatePoint = new PointCharging();
		updatePoint.setMemberPoint(memberPoint);
		updatePoint.setMemberNo(searchMemberNo);
		pointChargingDao.updateMemberPointCharging(updatePoint);
		ExerciseFeedbackRequest exerciseFeedbackMember = exerciseFeedbackDao.exerciseFeedbackPtSelect(searchMemberNo);
		String memberReceiveNo = exerciseFeedbackMember.getMemberNo();
		String memberName = exerciseFeedbackMember.getMemberName();
		ExerciseFeedbackRequest exerciseFeedbacksd = exerciseFeedbackDao.exerciseFeedbackRequestDetail(exerciseFeedbackRequestNo);
		String teacherNo = exerciseFeedbacksd.getTeacherNo();
		ExerciseFeedbackRequest exerciseFeedbackTeacherName = exerciseFeedbackDao.exerciseFeedbackPtSelect(teacherNo);
		String teacherName = exerciseFeedbackTeacherName.getMemberName();
		String teacherId = exerciseFeedbackTeacherName.getMemberId();
		//운동회원 포인트 추가
		PointCharging teacherPointCharging = pointChargingDao.selectMemberPoint(teacherNo);
		int teacherPoint = teacherPointCharging.getMemberPoint();
		logger.debug("teacherPoint 홗인 : "+teacherPoint);
		teacherPoint = teacherPoint + 1000;
		logger.debug("teacherPoint + 1000 홗인 : "+teacherPoint);
		PointCharging teacherupdatePoint = new PointCharging();
		teacherupdatePoint.setMemberPoint(teacherPoint);
		teacherupdatePoint.setMemberNo(teacherNo);
		pointChargingDao.updateMemberPointCharging(teacherupdatePoint);
		//메세지 
		logger.debug("ExerciseFeedbackResponseService - sendMessage 시작");
		Message message = new Message();
		int result = (messageDao.messageNo())+1;//메시지의 no 를 구함
		String messageNo = "message_";
		//받는사람 요청회원
		message.setMemberReceiveNo(memberReceiveNo);
		//보내는사람 pt회원
		message.setSendMessageNo(messageNo+result);
		message.setMemberSendNo(teacherNo);
		message.setSendMessageId(teacherId);
		logger.debug("messageNo : "+message.getSendMessageNo());
		//메세지 내용
		String messageTitle = teacherName+"님으로부터 운동피드백 답변이 완료 되었습니다";
		message.setMessageTitle(messageTitle);
		String messageContent = memberName+"님이"+teacherName+"에게 요청하신 운동 피드백 답변이 완료되어 회원님의 1000포인트가 차감되었습니다.";
		message.setMessageContent(messageContent);
		
		messageDao.sendMessage(message);//send_Message 테이블 에 데이트를 셋
		messageDao.sendMessageContent(message);//발신자 테이블에 데이터 셋
		messageDao.receiveMessageContent(message);//수신자 테이블에 데이터 셋
		logger.debug("ExerciseFeedbackResponseService - sendMessage 완료");
		}
	/**
	 * 운동 등록
	 * @param exerciseFeedbackResult
	 */
	public void addExerciseFeedback(ExerciseFeedbackResult exerciseFeedbackResult) {
		exerciseFeedbackResponseDao.addExerciseFeedback(exerciseFeedbackResult);
		}
	/**
	 * 음식등록
	 * @param exerciseFeedbackResult
	 */
	public void addFoodFeedback(ExerciseFeedbackResult exerciseFeedbackResult) {
		logger.debug("ExerciseFeedbackResponseService - addFoodFeedback실행");	
		exerciseFeedbackResponseDao.addFoodFeedback(exerciseFeedbackResult); //음식등록
		}
	
	/**
	 * 운동 리스트 검색
	 * @return returnMap
	 */
	public Map<String,Object> exerciseSearch(){
		logger.debug("ExerciseFeedbackResponseService - exerciseSearch실행");	
		Map<String,Object> returnMap = new HashMap<String,Object>();
		List<ExerciseFeedbackResult> exerciseSearch = exerciseFeedbackResponseDao.exerciseSearch();
		returnMap.put("exerciseSearch", exerciseSearch);
		logger.debug("exerciseSearch:"+exerciseSearch);	
		return returnMap;
	}
	/**
	 * 운동 피드백 음식 검색
	 * @param exerciseFeedbackResult
	 * @return returnMap
	 */
	public Map<String,Object> feedbackFoodSearch(ExerciseFeedbackResult exerciseFeedbackResult){
		logger.debug("ExerciseFeedbackResponseService - feedbackFoodSearch실행");	
		Map<String,String> map = new HashMap<String,String>();
		String foodGroup = exerciseFeedbackResult.getFoodGroup();
		String foodName = exerciseFeedbackResult.getFoodName();
		map.put("foodGroup", foodGroup);
		map.put("foodName", foodName);
		logger.debug("foodGroup:" +foodGroup);	
		logger.debug("foodName:"+foodName);	
		List<ExerciseFeedbackResult> foodInfo = exerciseFeedbackResponseDao.foodSearch(map);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		int result = 0;
		if(foodInfo.size()>0) {
			result = foodInfo.size();
			returnMap.put("result", result);
		}else if (foodInfo.size() == 0){
			result = 0;
			returnMap.put("result", result);
		}
		returnMap.put("foodInfo", foodInfo);		
		return returnMap;
	}
	/**
	 * 피드백 요청 답변 회원 검색
	 * @param exerciseFeedbackRequestNo
	 * @return returnMap
	 */
	public Map<String,Object> exerciseFeedResponse(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackResponseService - exerciseFeedResponse실행");	
		ExerciseFeedbackRequest exerciseFeedbackRequest = exerciseFeedbackDao.exerciseFeedbackRequestDetail(exerciseFeedbackRequestNo);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		String memberName = exerciseFeedbackRequest.getMemberName();
		String memberNo = exerciseFeedbackRequest.getMemberNo();
		ExerciseFeedbackRequest exerciseFeedbackset = new ExerciseFeedbackRequest();
		exerciseFeedbackset.setMemberName(memberName);
		exerciseFeedbackset.setMemberNo(memberNo);
		exerciseFeedbackset.setExerciseFeedbackRequestNo(exerciseFeedbackRequestNo);
		returnMap.put("exerciseFeedbackset", exerciseFeedbackset);
		return returnMap;
		
	}
}
