package com.cafe24.blognyj9112.bloodpressure.controller.rest;
import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.blognyj9112.bloodpressure.service.BloodPressure;
import com.cafe24.blognyj9112.bloodpressure.service.BloodPressureService;
import com.google.gson.Gson;


@RestController
public class BloodPressureRestController {

	@Autowired
	private BloodPressureService bloodPressureService;
	private static final Logger logger = LoggerFactory.getLogger(BloodPressureRestController.class);
	
	//건강검진표에 혈압이 등록되어있는지 검색
	@RequestMapping(value="/bloodPressureNoCountToHealthScreen", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> bloodPressureNoCountToHealthScreen(@RequestParam(value="bloodPressureNo") String bloodPressureNo) {
		logger.debug("BloodPressureRestController - bloodPressureNoCountToHealthScreen ajax 실행.");
		Map<String,Integer> resultmap = bloodPressureService.bloodPressureNoCountToHealthScreen(bloodPressureNo);
		logger.debug("resultmap:"+resultmap);
		return resultmap;
	}
	
	//혈압 차트
	 @RequestMapping(value="/bloodPressureChart", method=RequestMethod.POST)
	 @ResponseBody
	 public void chartData(HttpServletResponse response,@RequestParam(value="memberNo")String memberNo){
		 logger.debug("BloodPressureRestController - bloodPressureChart chartData ajax 실행");
		 List<BloodPressure>  list = bloodPressureService.selectBloodPressureChart(memberNo);
		logger.debug("memberNo : " + memberNo);
		//배열값 확인
		logger.debug("list : " +list);
		//javaScript 타입을 gson을 이용해 ajax에서 사용가능하게 데이터 타입을 변환.
		Gson gson = new Gson();
		String json = "";
		json = gson.toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
