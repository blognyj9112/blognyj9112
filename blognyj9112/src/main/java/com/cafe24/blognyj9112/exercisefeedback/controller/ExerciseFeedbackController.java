package com.cafe24.blognyj9112.exercisefeedback.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.blognyj9112.exercisefeedback.service.ExerciseFeedbackRequest;
import com.cafe24.blognyj9112.exercisefeedback.service.ExerciseFeedbackResponseService;
import com.cafe24.blognyj9112.exercisefeedback.service.ExerciseFeedbackResult;
import com.cafe24.blognyj9112.exercisefeedback.service.ExerciseFeedbackService;

@Controller
public class ExerciseFeedbackController {
	@Autowired
	private ExerciseFeedbackService exerciseFeedbackService;
	@Autowired
	private ExerciseFeedbackResponseService exerciseFeedbackResponseService;
	private static final Logger logger = LoggerFactory.getLogger(ExerciseFeedbackController.class);
	
	//운동 피드백 평가 등록
	@RequestMapping(value="/addexerciseFeedbackResponseResultevaluation", method=RequestMethod.GET)
	public String addexerciseFeedbackResponseResultevaluation(@RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo
									,@RequestParam(value="exerciseFeedbackEvaluationGrade") double exerciseFeedbackEvaluationGrade) {
		logger.debug("ExerciseFeedbackController - addexerciseFeedbackResponseResultevaluation 리다이렉트 실행");
		exerciseFeedbackResponseService.addexerciseFeedbackResponseResultevaluation(exerciseFeedbackRequestNo, exerciseFeedbackEvaluationGrade);
		return "redirect:/exerciseFeedResponseResultDetail?exerciseFeedbackRequestNo="+exerciseFeedbackRequestNo;
	}
	//운동 피드백 상세보기
	@RequestMapping(value="/exerciseFeedResponseResultDetail", method=RequestMethod.GET)
	public String exercuseFeedResponseResultDetail(Model model
							,@RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo ) {
		logger.debug("ExerciseFeedbackController - exercuseFeedResponseResultDetail 포워드 실행");
		Map<String,Object> map = exerciseFeedbackResponseService.exerciseFeedbackResponseResultDetail(exerciseFeedbackRequestNo);
		model.addAttribute("map", map);
		logger.debug("map:"+map);
		return "exercisefeedback/exerciseFeedResponseResultDetail";
	}
	
	//운동 피드백 완료 리스트
	@RequestMapping(value="/exerciseFeedResponseResultList", method=RequestMethod.GET)
	public String exerciseFeedbackResultList(Model model
								,HttpSession session
								,@RequestParam(value="currentPage", defaultValue="1") int currentPage
								,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("ExerciseFeedbackController - exercuseFeedResponseResultList 포워드 실행");
		Map<String,Object> map = exerciseFeedbackResponseService.exerciseFeedbackResultList(session, currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("exercisePtlist", map.get("exercisePtlist"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		model.addAttribute("list", map.get("list"));
		model.addAttribute("total", map.get("total"));
		logger.debug("ExerciseFeedbackController - exerciseFeedbackResultList 포워드 완료");
		return "exercisefeedback/exerciseFeedResponseResultList";
	}
	
	//운동피드백 등록
	@RequestMapping(value="/addExerciseFeedbackRequestResult",method=RequestMethod.POST)
	public String addExerciseFeedbackRequestResult(ExerciseFeedbackResult exerciseFeedback) {
		logger.debug("ExerciseFeedbackController - addExerciseFeedbackRequestResult 리다이렉트 실행.");
		exerciseFeedbackResponseService.addExerciseFeedbackRequestResult(exerciseFeedback);
		return "redirect:/exerciseFeedbackRequestList";
	}

	//운동피드백피티회원선택리스트 일반 검색
	@RequestMapping(value="/exerciseFeedbackPtListSearch", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView exerciseFeedbackPtListSearch(@RequestParam(value="keyWord") String keyWord
									,@RequestParam(value="keyOption")String keyOption
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("ExerciseFeedbackController - exerciseFeedbackPtList exerciseFeedbackPtListSearch ModelAndView 실행");
		Map<String,Object> map = exerciseFeedbackService.exerciseFeedbackPtListSearch(keyOption, keyWord, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("exercisefeedback/exerciseFeedbackPtList");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("keyWord", keyWord);
		modelAndView.addObject("keyOption", keyOption);
		modelAndView.addObject("searchKeywordresult", map.get("total"));
		modelAndView.addObject("totalBlock", map.get("totalBlock"));
		modelAndView.addObject("firstBlockPage", map.get("firstBlockPage"));
		modelAndView.addObject("lastBlockPage", map.get("lastBlockPage"));
		modelAndView.addObject("lastPage", map.get("lastPage"));
		modelAndView.addObject("currentPage", currentPage);
		return modelAndView;
	}
	//운동피드백 일반 검색
	@RequestMapping(value="/exerciseFeedbackRequestListSearch", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView exerciseFeedbackRequestListSearch(@RequestParam(value="keyWord") String keyWord
									,HttpSession session						
									,@RequestParam(value="keyOption") String keyOption
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("ExerciseFeedbackController - exerciseFeedbackList exerciseFeedbackRequestListSearch ModelAndView 실행");
		Map<String,Object> map = exerciseFeedbackService.exerciseFeedbackRequestListSearch(session, keyOption, keyWord, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("exercisefeedback/exerciseFeedbackList");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("keyWord", keyWord);
		modelAndView.addObject("keyOption", keyOption);
		modelAndView.addObject("searchKeywordresult", map.get("total"));
		modelAndView.addObject("totalBlock", map.get("totalBlock"));
		modelAndView.addObject("firstBlockPage", map.get("firstBlockPage"));
		modelAndView.addObject("lastBlockPage", map.get("lastBlockPage"));
		modelAndView.addObject("lastPage", map.get("lastPage"));
		modelAndView.addObject("currentPage", currentPage);
		return modelAndView;
	}
	//운동피드백 기간별 검색
	@RequestMapping(value="/exerciseFeedbackListSearch", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView exerciseFeedbackListSearch(@RequestParam(value="startDate") String startDate
									,HttpSession session
									,@RequestParam(value="endDate")String endDate
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("ExerciseFeedbackController - exerciseFeedbackList exerciseFeedbackListSearch ModelAndView 실행");
		Map<String,Object> map = exerciseFeedbackService.exerciseFeedbackListSearchDate(session, startDate, endDate, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("exercisefeedback/exerciseFeedbackList");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("startDate", startDate);
		modelAndView.addObject("endDate", endDate);
		modelAndView.addObject("searchresult", map.get("total"));
		modelAndView.addObject("totalBlock", map.get("totalBlock"));
		modelAndView.addObject("firstBlockPage", map.get("firstBlockPage"));
		modelAndView.addObject("lastBlockPage", map.get("lastBlockPage"));
		modelAndView.addObject("lastPage", map.get("lastPage"));
		modelAndView.addObject("currentPage", currentPage);
		return modelAndView;
	}
	//운동피드백 답변 작성
	@RequestMapping(value="/exerciseFeedResponse", method=RequestMethod.GET)
	public String exerciseFeedResponse(Model model, @RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackController - exerciseFeedResponse 포워드 실행.");
		Map<String,Object> map = exerciseFeedbackResponseService.exerciseFeedResponse(exerciseFeedbackRequestNo);
		model.addAttribute("exerciseFeedbackset", map.get("exerciseFeedbackset"));
		logger.debug("ExerciseFeedbackController - exerciseFeedResponse 포워드 완료");
		return "exercisefeedback/exerciseFeedResponse";
	}	
	
	//운동 피드백 요청 상세 보기
	@RequestMapping(value="/exerciseFeedbackRequestDetail",method=RequestMethod.GET)
	public String exerciseFeedbackRequestDetail(Model model
										,HttpSession session
										, @RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackController - exerciseFeedbackRequestDetail 포워드 실행.");
		Map<String,Object> map = exerciseFeedbackService.exerciseFeedbackRequestDetail(session, exerciseFeedbackRequestNo);
		model.addAttribute("map", map);
		logger.debug("map : "+map);
		logger.debug("ExerciseFeedbackController - exerciseFeedbackRequestDetail 포워드 완료");
		return "exercisefeedback/exerciseFeedbackRequestDetail";
	}
	
	//운동 피드백 요청 취소
	@RequestMapping(value="/cancleExerciseFeedback",method=RequestMethod.GET)
	public String cancleExerciseFeedback(@RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackController - cancleExerciseFeedback 취소 실행.");
		exerciseFeedbackService.cancleExerciseFeedback(exerciseFeedbackRequestNo);
		logger.debug("ExerciseFeedbackController - cancleExerciseFeedback 취소 완료");
		return "redirect:/exerciseFeedbackList";
	}
	
	//운동 피드백 거절
	@RequestMapping(value="/deniedExerciseFeedback",method=RequestMethod.GET)
	public String feedbackapprovalDenied(@RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackController - feedbackapprovalDenied 거절 실행.");
		exerciseFeedbackService.feedbackapprovalDenied(exerciseFeedbackRequestNo);
		logger.debug("ExerciseFeedbackController - feedbackapprovalDenied 거절 완료");
		return "redirect:/exerciseFeedbackRequestDetail?exerciseFeedbackRequestNo="+exerciseFeedbackRequestNo;
	}
	
	//운동 피드백 수락
	@RequestMapping(value="/acceptExerciseFeedback",method=RequestMethod.GET)
	public String acceptExerciseFeedback(@RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo) {
		logger.debug("ExerciseFeedbackController - acceptExerciseFeedback 수락 실행.");
		exerciseFeedbackService.feedbackapprovalAccept(exerciseFeedbackRequestNo);
		logger.debug("ExerciseFeedbackController - acceptExerciseFeedback 수락 완료");
		return "redirect:/exerciseFeedbackRequestDetail?exerciseFeedbackRequestNo="+exerciseFeedbackRequestNo;
	}
	
	//운동피드백 리스트
	@RequestMapping(value="/exerciseFeedbackList", method=RequestMethod.GET)
	public String exerciseFeedbackList(Model model
								,HttpSession session
								,@RequestParam(value="currentPage", defaultValue="1") int currentPage
								,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("ExerciseFeedbackController - exerciseFeedbackList 포워드 실행");
		Map<String,Object> map = exerciseFeedbackService.exerciseFeedbackList(session, currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		logger.debug("ExerciseFeedbackController - exerciseFeedbackList 포워드 완료");
		return "exercisefeedback/exerciseFeedbackList";
	}
		
	//운동 피드백 요청 완료
	@RequestMapping(value="/exerciseFeedbackRequest",method=RequestMethod.POST)
	public String exerciseFeedbackReqeustResult(HttpSession session,ExerciseFeedbackRequest exerciseFeedbackRequest) {
		logger.debug("ExerciseFeedbackController - exerciseFeedbackReqeust 리다이렉트 실행.");
		exerciseFeedbackService.exerciseFeedbackRequest(session, exerciseFeedbackRequest);
		logger.debug("ExerciseFeedbackController - exerciseFeedbackReqeust 리다이렉트 완료");
		return "redirect:/exerciseFeedbackList";
	}
	
	//운동피드백 요청
	@RequestMapping(value="/exerciseFeedbackRequest", method=RequestMethod.GET)
	public String exerciseFeedbackReqeust(Model model,@RequestParam(value="memberNo") String memberNo) {
		logger.debug("ExerciseFeedbackController - exerciseFeedbackReqeust 포워드 실행.");
		Map<String,Object> map = exerciseFeedbackService.exerciseFeedbackPtSelect(memberNo);
		logger.debug("memberNo:"+memberNo);
		model.addAttribute("map", map);
		logger.debug("ExerciseFeedbackController - exerciseFeedbackRequest 포워드 완료");
		return "exercisefeedback/exerciseFeedbackRequest";
	}	
	
	//강사 리스트
	@RequestMapping(value="/exerciseFeedbackPtList", method=RequestMethod.GET)
	public String exerciseFeedbackRequestList(Model model
								,HttpSession session
								,@RequestParam(value="currentPage", defaultValue="1") int currentPage
								,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("ExerciseFeedbackController - exerciseFeedbackPtList 포워드 실행");
		Map<String,Object> map = exerciseFeedbackService.exerciseFeedbackPtList(currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("exercisePtlist", map.get("exercisePtlist"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		logger.debug("ExerciseFeedbackController - exerciseFeedbackPtList 포워드 완료");
		return "exercisefeedback/exerciseFeedbackPtList";
	}
	
}
