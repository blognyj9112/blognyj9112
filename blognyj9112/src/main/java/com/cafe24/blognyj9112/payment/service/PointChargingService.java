package com.cafe24.blognyj9112.payment.service;

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
public class PointChargingService {

	@Autowired
	private PointChargingDao pointChargingDao;
	private static final Logger logger = LoggerFactory.getLogger(PointChargingService.class);
	public PointCharging point(HttpSession session) {
		String memberNo = (String) session.getAttribute("memberSessionNo");
		logger.debug("memberNo:"+memberNo);
		PointCharging pointCharging = pointChargingDao.selectMemberPoint(memberNo);
		int memberPoint = pointCharging.getMemberPoint();
		logger.debug("memberPoint:"+memberPoint);
		return pointCharging;
		
	}
	/**
	 * 포인트 지급
	 * @param memberNo
	 */
	public void addPoint(PointCharging pointCharging) {
		logger.debug("PointChargingService - addPoint실행");
		String memberNo = pointCharging.getMemberNo();
		int pointChargingSum = pointCharging.getPointChargingSum();
		logger.debug("지급할 포인트 :"+pointChargingSum);
		PointCharging memberPointSearch = pointChargingDao.selectMemberPoint(memberNo);
		int memberPoint = memberPointSearch.getMemberPoint();
		logger.debug("현재 memberPoint:"+memberPoint);
		memberPoint = memberPoint+pointChargingSum;
		if(memberPoint >0 ) {
			logger.debug("업데이트 memberPoint:"+memberPoint);
			logger.debug("업데이트 memberNo:"+memberNo);
			PointCharging updatePoint = new PointCharging();
			updatePoint.setMemberPoint(memberPoint);
			updatePoint.setMemberNo(memberNo);
			pointChargingDao.updateMemberPointCharging(updatePoint);
			logger.debug("포인트 적립 완료.");
		}
	}
	
	/**
	 * 포인트 승인 거절
	 * @param pointChargingNo
	 */
	public void deniedPointCharging(HttpSession session,String pointChargingNo) {
		logger.debug("PointChargingService - deniedPointCharging실행");
		PointCharging pointCharging = new PointCharging();
		pointCharging.setPointChargingNo(pointChargingNo);
		String memberNoDirector = (String) session.getAttribute("memberSessionNo");
		int count = 0;
		count = pointChargingDao.pointChargingNoResultCount(pointChargingNo);
		if(count > 0) {
			logger.debug("포인트 적립 중복.");
		}else {
		String pointChargingDirectorNo = memberNoDirector;
		pointCharging.setPointChargingDirectorNo(pointChargingDirectorNo);
		pointChargingDao.deniedPointCharging(pointCharging);
		}
	}
	/**
	 * 포인트 신청 승인
	 * @param pointChargingNo
	 */
	public void acceptPointCharging(HttpSession session,String pointChargingNo) {
		logger.debug("PointChargingService - pointChargingSum실행");
		
		PointCharging pointCharging = new PointCharging();
		pointCharging.setPointChargingNo(pointChargingNo);
		pointChargingDao.updateapprovalResultPointCharging(pointCharging);
		//포인트 적립 중복 체크
		int count = 0;
		count = pointChargingDao.pointChargingNoResultCount(pointChargingNo);
		if(count > 0) {
			logger.debug("포인트 적립 중복.");
		}else {
		String pointChargingDirectorNo = (String) session.getAttribute("memberSessionNo");
		pointCharging.setPointChargingDirectorNo(pointChargingDirectorNo);
		pointChargingDao.acceptPointCharging(pointCharging);
		//포인트 적립
		PointCharging memberSearch = pointChargingDao.pointCahrgingSearchMemberNo(pointChargingNo);
		String memberNo = memberSearch.getMemberNo();
		int pointChargingSum = memberSearch.getPointChargingSum();
		logger.debug("pointChargingSum:"+pointChargingSum);
			if(memberNo != null) {
				PointCharging memberPointSearch = pointChargingDao.selectMemberPoint(memberNo);
				int memberPoint = memberPointSearch.getMemberPoint();
				logger.debug("현재 memberPoint:"+memberPoint);
				memberPoint = memberPoint+pointChargingSum;
				if(memberPoint >0 ) {
					logger.debug("업데이트 memberPoint:"+memberPoint);
					logger.debug("업데이트 memberNo:"+memberNo);
					PointCharging updatePoint = new PointCharging();
					updatePoint.setMemberPoint(memberPoint);
					updatePoint.setMemberNo(memberNo);
					pointChargingDao.updateMemberPointCharging(updatePoint);
					logger.debug("포인트 적립 완료.");
				}
			}
		}
	}
	
	/**
	 * 포인트 신청 결과 
	 * @param memberNo
	 * @return pointCharging
	 */
	public PointCharging pointChargingSum(String memberNo) {
		logger.debug("PointChargingService - pointChargingSum실행");
		PointCharging pointCharging= pointChargingDao.pointChargingSum(memberNo);
		logger.debug("pointChargingSum:"+pointCharging.getPointChargingSum());
		return pointCharging;
	}
	
	/**
	 * 포인트 결제 신청 등록
	 * @param pointCharging
	 */
	public void addPointCharging(PointCharging pointCharging) {
		logger.debug("PointChargingService - addPointCharging실행");		
		String pointChargingNo = pointCharging.getPointChargingNo();
		logger.debug("pointChargingNo:"+pointChargingNo);
		int pointChargingSum = pointCharging.getPointChargingSum();
		logger.debug("pointChargingSum:"+pointChargingSum);
		try {
			if(pointChargingNo == null) {
				//포인트 결제 전체 검색
				int count = 0;
				count = pointChargingDao.pointChargingTotalCount();
				if(count > 0) {
					int result = 0;
					String pointChargingNo_temp = "point_charging_";
					//포인트 결제 번호 최대값 검색
					result = pointChargingDao.PointChargingNo();
					if(result > 0) {
						if(1 <= result) {
							result++;
						}
						pointChargingNo = pointChargingNo_temp + result; 
					}
				}else {
					pointChargingNo = "point_charging_1";
				}
			}
			pointCharging.setPointChargingNo(pointChargingNo);
			pointCharging.setPointChargingSum(pointChargingSum);
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		pointChargingDao.addPointCharging(pointCharging);
		}
	
	/**
	 * 포인트 결제 승인 완료 리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String, Object> pointChargingApprovalList(int currentPage, int pagePerRow){
		logger.debug("pointChargingService - pointChargingList 실행");
		Map<String,Integer> map = new HashMap<String,Integer>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		//포인트 결제 승인 리스트
		List<PointCharging> list = pointChargingDao.pointChargingApprovalList(map);
		//게시판 전체 게시물 수
		int total = pointChargingDao.pointChargingApprovalTotalCount();
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
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("firstBlockPage", firstBlockPage);
		returnMap.put("lastBlockPage", lastBlockPage);
		returnMap.put("totalBlock", totalBlock);
		return returnMap;
	}

	/**
	 * 포인트 결제 승인 대기 리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String, Object> pointChargingList(int currentPage, int pagePerRow){
		logger.debug("pointChargingService - pointChargingList 실행");
		Map<String,Integer> map = new HashMap<String,Integer>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		//포인트 결제 승인 대기 리스트
		List<PointCharging> list = pointChargingDao.pointChargingList(map);
		//게시판 전체 게시물 수
		int total = pointChargingDao.pointChargingTotalCount();

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
		
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("firstBlockPage", firstBlockPage);
		returnMap.put("lastBlockPage", lastBlockPage);
		returnMap.put("totalBlock", totalBlock);
		return returnMap;
	}
}
