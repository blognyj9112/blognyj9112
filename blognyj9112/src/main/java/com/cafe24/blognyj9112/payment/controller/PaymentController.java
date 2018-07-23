package com.cafe24.blognyj9112.payment.controller;

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

import com.cafe24.blognyj9112.payment.service.PointCharging;
import com.cafe24.blognyj9112.payment.service.PointChargingService;
import com.cafe24.blognyj9112.payment.service.Refund;
import com.cafe24.blognyj9112.payment.service.RefundService;


@Controller
public class PaymentController {
	@Autowired
	private PointChargingService pointChargingService;
	@Autowired
	private RefundService refundService;
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	//환불 상세보기
	@RequestMapping(value="/refundListDetail", method=RequestMethod.GET)
	public String refundDetail(Model model,@RequestParam(value="refundNo") String refundNo) {
		logger.debug("PaymentController - refundDetail 포워드 실행");
		Map<String,Object> map = refundService.refundListDetail(refundNo);
		model.addAttribute("map", map);
		logger.debug("map:"+map);
		return "payment/refundListDetail";
	}
	
	//포인트 지급
	@RequestMapping(value="/addPoint", method=RequestMethod.POST)
	public String addPoint(PointCharging pointCharging) {
		logger.debug("PaymentController - addPoint 리다이렉트 실행");
		pointChargingService.addPoint(pointCharging);
		return "redirect:/pointChargingList";
	}
	
	//환불 지급 완료
	@RequestMapping(value="/completeRefund", method=RequestMethod.GET)
	public String completeRefund(HttpSession session,@RequestParam(value="refundNo") String refundNo) {
		logger.debug("PaymentController - completeRefund 리다이렉트 실행");
		refundService.completeRefund(session, refundNo);
		return "redirect:/refundCompleteList";
	}
	
	//환불 거절
	@RequestMapping(value="/deniedRefund", method=RequestMethod.GET)
	public String deniedRefund(HttpSession session,@RequestParam(value="refundNo") String refundNo) {
		logger.debug("PaymentController - deniedRefund 리다이렉트 실행");
		refundService.deniedRefund(session, refundNo);
		return "redirect:/refundList";
	}
	
	//환불 승인
	@RequestMapping(value="/acceptRefund", method=RequestMethod.GET)
	public String acceptRefund(HttpSession session,@RequestParam(value="refundNo") String refundNo) {
		logger.debug("PaymentController - acceptRefund 리다이렉트 실행");
		refundService.acceptRefund(session, refundNo);
		return "redirect:/refundList";
	}
	//포인트 결제 신청 거절
	@RequestMapping(value="/deniedCharging", method=RequestMethod.GET)
	public String deniedPointCharging(HttpSession session,@RequestParam(value="pointChargingNo") String pointChargingNo) {
		logger.debug("PaymentController - deniedPointCharging 실행");
		pointChargingService.deniedPointCharging(session, pointChargingNo);
		return "redirect:/pointChargingList";
	}
	
	//포인트 결제 승인
	@RequestMapping(value="/acceptCharging", method=RequestMethod.GET)
	public String acceptPointCharging(HttpSession session,@RequestParam(value="pointChargingNo") String pointChargingNo) {
		logger.debug("PaymentController - acceptPointCharging 실행");
		pointChargingService.acceptPointCharging(session, pointChargingNo);
		return "redirect:/pointChargingList";
	}
	
	//환불 신청 완료
	@RequestMapping(value="/addRefund", method=RequestMethod.POST)
	public String addRefund(Refund refund) {
		logger.debug("PaymentController - addRefund 리다이렉트 실행");
		refundService.addrefund(refund);
		return "redirect:/refundApprovalList";
	}
	
	//환불 신청 
	@RequestMapping(value="/addRefund", method=RequestMethod.GET)
	public String addRefund(Model model,HttpSession session) {
		logger.debug("PaymentController - addRefund 포워드 실행");
		Map<String,Object> map = refundService.memberPoint(session);
		model.addAttribute("map",map);
		return "payment/addRefund";
	}
		
	//포인트지급
	@RequestMapping(value="/addPointToMember", method=RequestMethod.GET)
	public String addPointToMember() {
		logger.debug("PaymentController - addPointToMember 포워드 실행");
		return "payment/addPointToMember";
	}
	
	//포인트 결제 신청 완료 결과
	@RequestMapping(value="/pointChargingResult", method=RequestMethod.GET)
	public String pointChargingResult(Model model,PointCharging pointCharging) {
		logger.debug("PaymentController - pointChargingResult 포워드 실행");
		model.addAttribute("pointCharging",pointCharging);
		return "payment/pointChargingResult";
	}
	
	//포인트 결제 신청 완료
	@RequestMapping(value="/pointCharging", method=RequestMethod.POST)
	public String pointCharging(PointCharging pointCharging) {
		logger.debug("PaymentController - pointCharging 리다이렉트 실행");
		pointChargingService.addPointCharging(pointCharging);
		return "redirect:/pointChargingResult";
	}
	
	//포인트 결제 신청 
	@RequestMapping(value="/addPointCharging", method=RequestMethod.GET)
	public String addpointCharging(Model model,HttpSession session) {
		logger.debug("PaymentController - addPointCharging 포워드 실행");
		PointCharging pointCharging = pointChargingService.point(session);
		model.addAttribute("pointCharging", pointCharging);
		return "payment/addPointCharging";
	}
	
	//환불 지급완료 리스트
	@RequestMapping(value="/refundCompleteList", method=RequestMethod.GET)
	public String refundCompleteList(Model model
								,@RequestParam(value="currentPage", defaultValue="1") int currentPage
								,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("PaymentController - refundCompleteList 포워드 실행");
		Map<String,Object> map = refundService.refundCompleteList(currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "payment/refundCompleteList";
	}
	
	//환불 승인 리스트
	@RequestMapping(value="/refundApprovalList", method=RequestMethod.GET)
	public String refundApprovalList(Model model
								,@RequestParam(value="currentPage", defaultValue="1") int currentPage
								,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("PaymentController - refundApprovalList 포워드 실행");
		Map<String,Object> map = refundService.refundApprovalList(currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "payment/refundApprovalList";
	}
	
	//환불 승인 대기 리스트
	@RequestMapping(value="/refundList", method=RequestMethod.GET)
	public String refundList(Model model
								,@RequestParam(value="currentPage", defaultValue="1") int currentPage
								,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("PaymentController - refundList 포워드 실행");
		Map<String,Object> map = refundService.refundList(currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "payment/refundList";
	}
	
	//포인트 결제 승인 리스트
	@RequestMapping(value="/pointChargingApprovalList", method=RequestMethod.GET)
	public String PointChargingApprovalList(Model model
								,@RequestParam(value="currentPage", defaultValue="1") int currentPage
								,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("PaymentController - pointChargingApprovalList 포워드 실행");
		Map<String,Object> map = pointChargingService.pointChargingApprovalList(currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "payment/pointChargingApprovalList";
	}
	
	//포인트 결제 승인 대기 리스트
	@RequestMapping(value="/pointChargingList", method=RequestMethod.GET)
	public String PointChargingList(Model model
								,@RequestParam(value="currentPage", defaultValue="1") int currentPage
								,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("PaymentController - pointChargingList 포워드 실행");
		Map<String,Object> map = pointChargingService.pointChargingList(currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "payment/pointChargingList";
	}
}
