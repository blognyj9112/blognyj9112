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
public class ExerciseFeedbackService {

	@Autowired
	private ExerciseFeedbackDao exerciseFeedbackDao;
	@Autowired
	private PointChargingDao pointChargingDao;
	@Autowired
	private MessageDao messageDao;
	private static final Logger logger = LoggerFactory.getLogger(ExerciseFeedbackService.class);	

	/**
	 * 피드백 전 포인트 검색
	 * @param memberNo
	 * @return
	 */
	public int exerciseFeedbackMemberPointCheck(String memberNo) {
		PointCharging pointCharging = pointChargingDao.selectMemberPoint(memberNo);
		int count = pointCharging.getMemberPoint();
		return count;
	}
	/**
	 * 운동피드백 요청 일반 검색
	 * @param session
	 * @param keyOption
	 * @param keyWord
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> exerciseFeedbackRequestListSearch(HttpSession session,String keyOption, String keyWord,int currentPage, int pagePerRow) {
		logger.debug("ExerciseFeedbackService - exerciseFeedbackRequestListSearch 실행");
		Map<String,Object> map = new HashMap<String,Object>();
		String memberNo = (String) session.getAttribute("memberSessionNo");
		int sessionLevel = (Integer) session.getAttribute("memberSessionLevel");
		logger.debug("memberNo:"+memberNo);
		logger.debug("sessionLevel:"+sessionLevel);
		map.put("sessionLevel", sessionLevel);
		map.put("memberNo", memberNo);
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		map.put("keyWord", keyWord);
		map.put("keyOption", keyOption);
		logger.debug("가져온 데이터:"+keyWord+","+keyOption);
		List<ExerciseFeedbackRequest> list = exerciseFeedbackDao.exerciseFeedbackRequestListSearch(map);
		int total = exerciseFeedbackDao.exerciseFeedbackRequestListSearchCount(map);
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
	 * 운동 피드백 PT 회원 검색
	 * @param keyOption
	 * @param keyword
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> exerciseFeedbackPtListSearch(String keyOption, String keyWord,int currentPage, int pagePerRow) {
		logger.debug("ExerciseFeedbackService - exerciseFeedbackPtListSearch 실행");
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		map.put("keyWord", keyWord);
		map.put("keyOption", keyOption);
		logger.debug("가져온 데이터:"+keyWord+","+keyOption);
		List<ExerciseFeedbackRequest> list = exerciseFeedbackDao.exerciseFeedbackPtListSearch(map);
		int total = list.size();
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
	 * 운동 피드백 기간별 검색
	 * @param startDate
	 * @param endDate
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> exerciseFeedbackListSearchDate(HttpSession session,String startDate, String endDate,int currentPage, int pagePerRow) {
		logger.debug("ExerciseFeedbackService - exerciseFeedbackListSearchDate 실행");
		Map<String,Object> map = new HashMap<String,Object>();
		String memberNo = (String) session.getAttribute("memberSessionNo");
		int sessionLevel = (Integer) session.getAttribute("memberSessionLevel");
		logger.debug("memberNo:"+memberNo);
		logger.debug("sessionLevel:"+sessionLevel);
		map.put("sessionLevel", sessionLevel);
		map.put("memberNo", memberNo);
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		logger.debug("가져온 데이터:"+startDate+","+endDate);
		List<ExerciseFeedbackRequest> list = exerciseFeedbackDao.exerciseFeedbackListSearchDate(map);
		int total = list.size();
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
	 * 운동 피드백 요청 취소
	 * @param exerciseFeedbackRequestNo
	 */
	public void cancleExerciseFeedback(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackService - cancleExerciseFeedback실행");	
		exerciseFeedbackDao.cancleExerciseFeedback(exerciseFeedbackRequestNo);
	}
	
	/**
	 * 운동 피드백 요청 거절
	 * @param exerciseFeedbackRequestNo
	 */
	public void feedbackapprovalDenied(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackService - feedbackapprovalDenied실행");	
		exerciseFeedbackDao.feedbackapprovalDenied(exerciseFeedbackRequestNo);
	}
	
	/**
	 * 운동 피드백 요청 수락
	 * @param exerciseFeedbackRequestNo
	 */
	public void feedbackapprovalAccept(String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackService - feedbackapprovalAccept실행");	
		exerciseFeedbackDao.feedbackapprovalAccept(exerciseFeedbackRequestNo);
	}
	
	/**
	 * 운동 피드백 요청글 상세 보기
	 * @param exerciseFeedbackRequestNo
	 * @return returnMap
	 */
	public Map<String, Object> exerciseFeedbackRequestDetail(HttpSession session,String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackService - exerciseFeedbackRequestDetail실행");	
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		String memberNo = (String) session.getAttribute("memberSessionNo");
		int sessionLevel = (Integer) session.getAttribute("memberSessionLevel");
		logger.debug("memberNo:"+memberNo);
		logger.debug("sessionLevel:"+sessionLevel);
		map.put("sessionLevel", sessionLevel);
		map.put("memberNo", memberNo);
		map.put("exerciseFeedbackRequestNo", exerciseFeedbackRequestNo);
		//운동 피드백 요청글 상세 보기
		ExerciseFeedbackRequest exerciseFeedbackDetail = exerciseFeedbackDao.exerciseFeedbackRequestDetail(exerciseFeedbackRequestNo);
		returnMap.put("exerciseFeedbackDetail", exerciseFeedbackDetail);
		//요청 수락 거절 결과값 검색
		int approvalResult = 0;
		approvalResult = exerciseFeedbackDao.exerciseFeedbackApprovalresultTotalCount(exerciseFeedbackRequestNo);
		if(approvalResult >0) {
			ExerciseFeedbackRequest exerciseFeedbackResultDetail = exerciseFeedbackDao.exerciseFeedbackApprovalResult(exerciseFeedbackRequestNo);
			returnMap.put("exerciseFeedbackResultDetail", exerciseFeedbackResultDetail);
			returnMap.put("approvalResult", approvalResult);
		}else {
			returnMap.put("approvalResult", approvalResult);
		}
		//이전글 다음글 
		int countPrev = 0;
		int countNext = 0;
		//이전글 다음글 카운트 
		countPrev = exerciseFeedbackDao.prevExerciseFeedbackDetailCount(map);
		countNext = exerciseFeedbackDao.nextExerciseFeedbackDetailCount(map);
		if(countPrev !=0) {
			if(countNext == 0) {
				//다음글이 없을 경우
				logger.debug("다음글이 없습니다.");
				ExerciseFeedbackRequest prevExerciseFeedback = exerciseFeedbackDao.prevExerciseFeedbackDetail(map);
				returnMap.put("prevExerciseFeedback", prevExerciseFeedback);
				returnMap.put("countNext", countNext);
				returnMap.put("countPrev", countPrev);
			}			
		}if(countNext != 0){
			ExerciseFeedbackRequest nextExerciseFeedback = exerciseFeedbackDao.nextExerciseFeedbackDetail(map);
			returnMap.put("nextExerciseFeedback", nextExerciseFeedback);
			returnMap.put("countNext", countNext);
			if(countPrev != 0){
				//둘다있을경우
				logger.debug("이전글,다음글이 있습니다.");
				ExerciseFeedbackRequest prevExerciseFeedback = exerciseFeedbackDao.prevExerciseFeedbackDetail(map);
				returnMap.put("prevExerciseFeedback", prevExerciseFeedback);
				returnMap.put("countPrev", countPrev);
			}else {
				//이전글이 없을경우
				logger.debug("이전글이 없습니다.");
				returnMap.put("countPrev", countPrev);
			}
		}
		return returnMap;
	}
	
	/**
	 * 운동피드백등록
	 * @param exerciseFeedbackRequest
	 */
	public void exerciseFeedbackRequest(HttpSession session,ExerciseFeedbackRequest exerciseFeedbackRequest) {
		logger.debug("ExerciseFeedbackService - exerciseFeedbackRequest실행");		
		String teacherNo = exerciseFeedbackRequest.getTeacherNo();
		String teacherName = exerciseFeedbackRequest.getMemberName();
		String memberName = (String) session.getAttribute("memberSessionName");
		logger.debug("teacherNo:"+teacherNo);
		logger.debug("teacherName:"+teacherName);
		logger.debug("memberName:"+memberName);
		exerciseFeedbackRequest.setExerciseFeedbackRequestNo("exercise_feedback_request_"+(exerciseFeedbackDao.exerciseFeedbackRequestNo()+1));
		exerciseFeedbackDao.exerciseFeedbackRequest(exerciseFeedbackRequest);
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
		String messageTitle = teacherName+"님에게 피드백 요청이 들어왔습니다.";
		message.setMessageTitle(messageTitle);
		String messageContent = teacherName+"님에게 "+memberName+"님의 운동 피드백 요청이 들어왔습니다. 자세한 내용은 피드백 요청 리스트에서 확인 가능합니다.";
		message.setMessageContent(messageContent);		
		messageDao.sendMessage(message);//send_Message 테이블 에 데이트를 셋
		messageDao.sendMessageContent(message);//발신자 테이블에 데이터 셋
		messageDao.receiveMessageContent(message);//수신자 테이블에 데이터 셋
		logger.debug("ExerciseFeedbackResponseService - sendMessage 완료");
	}
	
	/**
	 * 운동피드백리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String, Object> exerciseFeedbackList(HttpSession session,int currentPage, int pagePerRow){
		logger.debug("ExerciseFeedbackService - exerciseFeedbackList 실행");
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
		//운동피드백 리스트
		List<ExerciseFeedbackRequest> list = exerciseFeedbackDao.exerciseFeedbackList(map);
		//게시판 전체 게시물 수
		int total = exerciseFeedbackDao.exerciseFeedbackListTotalCount(map);
		int lastPage = total/pagePerRow;
        if(total % pagePerRow != 0) {
            lastPage++;
        }
        logger.debug("list:"+list);
        logger.debug("lastPage:"+lastPage);
        logger.debug("currentPage:"+currentPage);
        logger.debug("beginRow:"+beginRow);
        logger.debug("pagePerRow:"+pagePerRow);
        logger.debug("====================== page block =========================");
       
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
		logger.debug("====================== page block =========================");
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("firstBlockPage", firstBlockPage);
		returnMap.put("lastBlockPage", lastBlockPage);
		returnMap.put("totalBlock", totalBlock);
		return returnMap;
	}
	
	/**
	 * 운동피드백강사선택
	 * @param memberNo
	 * @return
	 */
	public Map<String,Object> exerciseFeedbackPtSelect(String memberNo) {
		logger.debug("ExerciseFeedbackService - exerciseFeedbackPtSelect 실행");
		ExerciseFeedbackRequest exerciseFeedbackRequest = exerciseFeedbackDao.exerciseFeedbackPtSelect(memberNo);
		exerciseFeedbackRequest.setMemberNo(memberNo);
		String ptMemberName = exerciseFeedbackRequest.getMemberName();
		String ptMemberNo = exerciseFeedbackRequest.getMemberNo();
		logger.debug("ptMemberName:"+ptMemberName);
		logger.debug("ptMemberNo:"+ptMemberNo);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("ptMemberName", ptMemberName);
		returnMap.put("ptMemberNo", ptMemberNo);
		return returnMap;
	}	
	
	/**
	 * 운동피드백강사리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String, Object> exerciseFeedbackPtList(int currentPage, int pagePerRow){
		logger.debug("ExerciseFeedbackService - exerciseFeedbackPtList 실행");
		Map<String,Integer> map = new HashMap<String,Integer>();

		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		//강사 리스트
		List<ExerciseFeedbackRequest> exercisePtlist = exerciseFeedbackDao.exerciseFeedbackPtList(map);
		//게시판 전체 게시물 수
		int total = exerciseFeedbackDao.exerciseFeedbackPtTotalCount();
		int lastPage = total/pagePerRow;
        if(total % pagePerRow != 0) {
            lastPage++;
        }
        logger.debug("exercisePtlist:"+exercisePtlist);
        logger.debug("lastPage:"+lastPage);
        logger.debug("currentPage:"+currentPage);
        logger.debug("beginRow:"+beginRow);
        logger.debug("pagePerRow:"+pagePerRow);
        logger.debug("====================== page block =========================");
       
        int pagePerBlock = 10; //보여줄 블록 수 
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
		logger.debug("====================== page block =========================");
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("exercisePtlist", exercisePtlist);
		returnMap.put("lastPage", lastPage);
		returnMap.put("firstBlockPage", firstBlockPage);
		returnMap.put("lastBlockPage", lastBlockPage);
		returnMap.put("totalBlock", totalBlock);
		return returnMap;
	}
}
