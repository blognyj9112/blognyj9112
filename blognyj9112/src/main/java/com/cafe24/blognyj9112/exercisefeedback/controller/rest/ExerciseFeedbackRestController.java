package com.cafe24.blognyj9112.exercisefeedback.controller.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.blognyj9112.exercisefeedback.service.ExerciseFeedbackResponseService;
import com.cafe24.blognyj9112.exercisefeedback.service.ExerciseFeedbackResult;
import com.cafe24.blognyj9112.exercisefeedback.service.ExerciseFeedbackService;

@RestController
public class ExerciseFeedbackRestController {
	@Autowired
	private ExerciseFeedbackService exerciseFeedbackService;
	@Autowired
	private ExerciseFeedbackResponseService exerciseFeedbackResponseService;
	private static final Logger logger = LoggerFactory.getLogger(ExerciseFeedbackRestController.class);

	//음식피드백 삭제
	@RequestMapping(value="/deleteFoodFeedback",method=RequestMethod.GET)
	@ResponseBody
	public int deleteFoodFeedback(@RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo
								,@RequestParam(value="foodInfoNo") String foodInfoNo) {
		logger.debug("ExerciseFeedbackController - deleteFoodFeedback ajax 실행.");
		exerciseFeedbackResponseService.deleteFoodFeedback(exerciseFeedbackRequestNo, foodInfoNo);
		int count = 1;
		return count;
	}
	
	//운동피드백 삭제
	@RequestMapping(value="/deleteExerciseFeedback",method=RequestMethod.GET)
	@ResponseBody
	public int deleteExerciseFeedback(@RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo
								,@RequestParam(value="exerciseNo") String exerciseNo) {
		logger.debug("ExerciseFeedbackController - deleteExerciseFeedback ajax 실행.");
		exerciseFeedbackResponseService.deleteExerciseFeedback(exerciseFeedbackRequestNo, exerciseNo);
		int count = 1;
		return count;
	}
	
	//운동피드백 등록
	@RequestMapping(value="/addExerciseFeedback",method=RequestMethod.GET)
	@ResponseBody
	public int addExerciseFeedback(@RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo
								,@RequestParam(value="exerciseNo") String exerciseNo
								,@RequestParam(value="exerciseTime") int exerciseTime) {
		logger.debug("ExerciseFeedbackController - addExerciseFeedback ajax 실행.");
		ExerciseFeedbackResult exerciseFeedback = new ExerciseFeedbackResult();
		exerciseFeedback.setExerciseFeedbackRequestNo(exerciseFeedbackRequestNo);
		exerciseFeedback.setExerciseNo(exerciseNo);
		exerciseFeedback.setExerciseTime(exerciseTime);
		exerciseFeedbackResponseService.addExerciseFeedback(exerciseFeedback);
		int count = 1;
		return count;
	}
	
	//음식피드백 등록
	@RequestMapping(value="/addFoodFeedback", method= RequestMethod.GET)
	@ResponseBody
	public int addFoodFeedback(@RequestParam(value="exerciseFeedbackRequestNo") String exerciseFeedbackRequestNo
								,@RequestParam(value="foodInfoNo") String foodInfoNo
								,@RequestParam(value="ingestionAmount") int ingestionAmount) {
		logger.debug("ExerciseFeedbackController - addFoodFeedback ajax 실행.");
		ExerciseFeedbackResult exerciseFeedback = new ExerciseFeedbackResult();
		exerciseFeedback.setExerciseFeedbackRequestNo(exerciseFeedbackRequestNo);
		exerciseFeedback.setIngestionAmount(ingestionAmount);
		exerciseFeedback.setFoodInfoNo(foodInfoNo);
		exerciseFeedbackResponseService.addFoodFeedback(exerciseFeedback);
		int count = 1;
		return count;
	}
	
	//멤버 확인.
	@RequestMapping(value="/memberSearchCount", method=RequestMethod.GET)
	@ResponseBody
    public int memberSearchCount(@RequestParam(value="memberNo") String memberNo) {
		logger.debug("ExerciseFeedbackRestController - memberSearchCount ajax 실행");
		int count = exerciseFeedbackResponseService.memberSearchCount(memberNo);
		logger.debug("count:"+count);
		return count;
    }
	
	//멤버 포인트 확인.
	@RequestMapping(value="/exerciseFeedbackMemberPointCheck", method=RequestMethod.GET)
	@ResponseBody
    public int exerciseFeedbackMemberPointCheck(@RequestParam(value="memberNo") String memberNo) {
		logger.debug("ExerciseFeedbackRestController - exerciseFeedbackMemberPointCheck ajax 실행");
		int count = exerciseFeedbackService.exerciseFeedbackMemberPointCheck(memberNo);
		logger.debug("count:"+count);
		return count;
    }
	//운동검색
	@RequestMapping(value="/exerciseSearch", method=RequestMethod.GET)
	@ResponseBody
    public Map<String, Object> exerciseSearch() {
		logger.debug("ExerciseFeedbackRestController - exerciseSearch ajax 실행");
		Map<String,Object> map = exerciseFeedbackResponseService.exerciseSearch();
		logger.debug("exerciseSearch:"+map.get("exerciseSearch"));
		return map;
    }
	//음식검색
	@RequestMapping(value="/feedbackFoodSearch", method=RequestMethod.GET)
	@ResponseBody
    public Map<String, Object> feedbackFoodSearch(ExerciseFeedbackResult exerciseFeedbackResult) {
		logger.debug("ExerciseFeedbackRestController - feedbackFoodSearch ajax 실행");
		Map<String,Object> map = exerciseFeedbackResponseService.feedbackFoodSearch(exerciseFeedbackResult);
		logger.debug("result:"+map.get("result"));
		logger.debug("foodInfo:"+map.get("foodInfo"));
		return map;
    }
}
