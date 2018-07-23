package com.cafe24.blognyj9112.bloodpressure.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BloodPressureService {
	@Autowired
	private BloodPressureDao bloodPressureDao;
	private static final Logger logger = LoggerFactory.getLogger(BloodPressureService.class);

	/**
	 * 건강 설문에 혈압이 연결되어있는지 건강설문에서 혈압번호 카운트
	 * @param bloodPressureNo
	 * @return returnMap
	 */
	public Map<String, Integer> bloodPressureNoCountToHealthScreen(String bloodPressureNo) {
		Map<String,Integer> returnMap = new HashMap<String,Integer>();
		int count = 0;
		count = bloodPressureDao.bloodPressureNoCountToHealthScreen(bloodPressureNo);
		returnMap.put("count", count);
		return returnMap;
	}
	
	/**
	 * 혈압 차트
	 * @param memberNo
	 * @return bloodPressureDao.selectBloodPressureChart(memberNo)
	 */
	public List<BloodPressure> selectBloodPressureChart(String memberNo) {
		logger.debug("BloodPressureService - selectBloodPressureChart 실행");
		return bloodPressureDao.selectBloodPressureChart(memberNo);
	}	
	/**
	 * 혈압 수정
	 * @param bloodPressureNo
	 */
	public void updateBloodPressure(String bloodPressureNo) {
		logger.debug("BloodPressureService - updateBloodPressure 실행");
		BloodPressure bloodPressure = new BloodPressure();
		int diastolicPressure = bloodPressure.getDiastolicPressure();
		int systolicPressure = bloodPressure.getSystolicPressure();
		bloodPressure.setDiastolicPressure(diastolicPressure);
		bloodPressure.setSystolicPressure(systolicPressure);
		bloodPressure.setBloodPressureNo(bloodPressureNo);
		bloodPressureDao.updateBloodPressure(bloodPressure);		
	}
	
	/**
	 * 하나의 혈압 선택
	 * @param bloodPressureNo
	 * @return bloodPressure
	 */
	public BloodPressure selectBloodPressureOne(String bloodPressureNo) {
		logger.debug("BloodPressureService - selectBloodPressureOne 실행");
		BloodPressure bloodPressure = bloodPressureDao.selectBloodPressureOne(bloodPressureNo);
		return bloodPressure;
	}

	/**
	 * 혈압 삭제
	 * @param bloodPressureNo
	 * @return 
	 */
	public void deleteBloodPressure(String bloodPressureNo) {
		logger.debug("BloodPressureService - deleteBloodPressure 실행");
		bloodPressureDao.deleteBloodPressure(bloodPressureNo);
	}	
	
	/**
	 * 혈압 기간별 검색
	 * @param startDate
	 * @param endDate
	 * @param currentPage
	 * @param pagePerRow
	 * @return returnMap
	 */
	public Map<String,Object> bloodPressureSearch(HttpSession session,String startDate, String endDate,int currentPage, int pagePerRow) {
		logger.debug("BloodPressureService - bloodPressureSearch 실행");
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
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		logger.debug("가져온 데이터:"+startDate+","+endDate);
		List<BloodPressure> list = bloodPressureDao.bloodPressureSearchDate(map);
		int total = bloodPressureDao.bloodPressureSearchDateCount(map);
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
		returnMap.put("startDate", startDate);
		returnMap.put("endDate", endDate);
		returnMap.put("total", total);
		return returnMap;
	}
	/**
	 * 혈압 리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @return returnMap
	 */
	public Map<String, Object> bloodPressureList(HttpSession session,int currentPage, int pagePerRow) {
		logger.debug("BloodPressureService - bloodPressureList 실행");
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
		List<BloodPressure> list = bloodPressureDao.bloodPressureList(map);
		int total = bloodPressureDao.bloodPressureCount(map);
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
		//컨트롤러에 보낼 리턴값 
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("firstBlockPage", firstBlockPage);
		returnMap.put("lastBlockPage", lastBlockPage);
		returnMap.put("totalBlock", totalBlock);
		return returnMap;
	}
	
	/**
	 * 혈압 등록
	 * @param bloodPressure
	 */
	public void addBloodPressure(BloodPressure bloodPressure) {
		logger.debug("BloodPressureService - addBloodPressure실행");		
		bloodPressure.setBloodPressureNo("blood_pressure_"+(bloodPressureDao.selectBloodPressureNo()+1));
		bloodPressureDao.addBloodPressure(bloodPressure);
	}
}
