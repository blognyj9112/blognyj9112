package com.cafe24.blognyj9112.bloodpressure.controller;

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

import com.cafe24.blognyj9112.bloodpressure.service.BloodPressure;
import com.cafe24.blognyj9112.bloodpressure.service.BloodPressureService;

@Controller
public class BloodPressureController {
	
	@Autowired
	private BloodPressureService bloodPressureService;
	private static final Logger logger = LoggerFactory.getLogger(BloodPressureController.class);
	
	//혈압 검색
	@RequestMapping(value="/bloodPressureSearch", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchList(@RequestParam(value="startDate") String startDate,
									HttpSession session
									,@RequestParam(value="endDate")String endDate
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="5")int pagePerRow) {
		logger.debug("BloodPressureRestController - searchList bloodPressureSearch ModelAndView 실행");
		Map<String,Object> map = bloodPressureService.bloodPressureSearch(session, startDate, endDate, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("bloodpressure/bloodPressure");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("startDate", map.get("startDate"));
		modelAndView.addObject("endDate", map.get("endDate"));
		modelAndView.addObject("searchresult", map.get("total"));
		modelAndView.addObject("totalBlock", map.get("totalBlock"));
		modelAndView.addObject("firstBlockPage", map.get("firstBlockPage"));
		modelAndView.addObject("lastBlockPage", map.get("lastBlockPage"));
		modelAndView.addObject("lastPage", map.get("lastPage"));
		modelAndView.addObject("currentPage", currentPage);
		return modelAndView;
	}
	
	//혈압차트
	@RequestMapping(value="/bloodPressureChart", method=RequestMethod.GET)
	public String bloodPressureChart() {
		logger.debug("BloodpressureController - bloodPressureChart 포워드 실행");
		return "bloodpressure/bloodPressureChart";
	}
		
	//혈압 수정완료
	@RequestMapping(value="/modifyBloodPressure", method=RequestMethod.POST)
	public String updateBloodPressure(@RequestParam(value="bloodPressureNo", required=true) String bloodPressureNo) {
		logger.debug("BloodpressureController - modifyBloodPressure 리다이렉트 실행");
		bloodPressureService.updateBloodPressure(bloodPressureNo);
		return "redirect:/bloodPressure";
	}
	
	//혈압 수정
	@RequestMapping(value="/modifyBloodPressure", method=RequestMethod.GET)
	public String modifyBloodPressure(Model model
										,@RequestParam(value="bloodPressureNo") String bloodPressureNo) {
		logger.debug("BloodPressureController - modifyBloodPressure 포워드 실행");
		BloodPressure bloodPressure = bloodPressureService.selectBloodPressureOne(bloodPressureNo);
		model.addAttribute("bloodPressure", bloodPressure);
		logger.debug("BloodPressureController - bloodPressure :"+ bloodPressure);
		return "bloodpressure/modifyBloodPressure";
	}
	
	//혈압 삭제
	@RequestMapping(value="/deleteBloodPressure", method= {RequestMethod.POST,RequestMethod.GET})
	public String deleteBloodPressure(@RequestParam(value="bloodPressureNo") String bloodPressureNo) {
		logger.debug("BloodPressureController - deleteBloodPressure 리다이렉트 실행.");
		bloodPressureService.deleteBloodPressure(bloodPressureNo);
		return "redirect:/bloodPressure";
	}
	
	//혈압 리스트
	@RequestMapping(value="/bloodPressure", method=RequestMethod.GET)
	public String bloodPressureList(Model model
								,HttpSession session
								,@RequestParam(value="currentPage", defaultValue="1") int currentPage
								,@RequestParam(value="pagePerRow", defaultValue="5")int pagePerRow) {
		logger.debug("BloodPressureController - bloodPressureList 포워드 실행");
		Map<String,Object> map = bloodPressureService.bloodPressureList(session, currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "bloodpressure/bloodPressure";
	}
		
	//혈압 추가
	@RequestMapping(value="/addBloodPressure", method=RequestMethod.POST)
	public String addBloodPressure(BloodPressure bloodPressure) {
		logger.debug("BloodpressureController - addBloodPressure 리다이렉트 실행");
		bloodPressureService.addBloodPressure(bloodPressure);
		return "redirect:/bloodPressure";
	}
	
	//혈압 추가
	@RequestMapping(value="/addBloodPressure", method=RequestMethod.GET)
	public String addBloodPressure() {
		logger.debug("BloodpressureController - addBloodPressure 포워드 실행");
		return "bloodpressure/addBloodPressure";
	}
	
}
