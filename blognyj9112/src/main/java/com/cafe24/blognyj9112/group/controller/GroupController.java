package com.cafe24.blognyj9112.group.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.cafe24.blognyj9112.group.service.Group;
import com.cafe24.blognyj9112.group.service.GroupInvite;
import com.cafe24.blognyj9112.group.service.GroupInviteService;
import com.cafe24.blognyj9112.group.service.GroupService;

@Controller
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupInviteService groupInviteService;
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	//그룹 종류 등록 폼(관리자)
	@RequestMapping(value="/modifyGroupKind", method=RequestMethod.POST)
	public String modifyGroupKind(Group group) {
		logger.debug("GroupController - modifyGroupKind 리다이렉트 실행");
		groupService.updateGroupKind(group);
		return "redirect:/groupKindList";
	}
	
	//그룹 종류 수정 폼(관리자)
	@RequestMapping(value="/modifyGroupKind", method=RequestMethod.GET)
	public String modifyGroupKind(Model model,@RequestParam(value="groupKindName") String groupKindName) {
		logger.debug("GroupController - modifyGroupKind 포워드 실행");
		Group group = groupService.groupKindNoSelect(groupKindName);
		String groupKindNo = group.getGroupKindNo();
		model.addAttribute("groupKindNo",groupKindNo);
		model.addAttribute("groupKindName", groupKindName);
		return "group/modifyGroupKind";
	}
	
	//그룹 종류 리스트(관리자)
	@RequestMapping(value="/groupKindList", method=RequestMethod.GET)
	public String groupKindList(Model model
								,@RequestParam(value="currentPage", defaultValue="1") int currentPage
								,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - groupKindList 포워드 실행");
		Map<String,Object> map = groupService.groupKindList(currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "group/groupKindList";
	}
	
	//그룹 종류 등록 폼(관리자)
	@RequestMapping(value="/addGroupKind", method=RequestMethod.POST)
	public String addGroupKind(Group group) {
		logger.debug("GroupController - addGroupKind 리다이렉트 실행");
		groupService.addGroupKind(group);
		return "redirect:/groupKindList";
	}
	
	//그룹 종류 등록 폼(관리자)
	@RequestMapping(value="/addGroupKind", method=RequestMethod.GET)
	public String addGroupKind() {
		logger.debug("GroupController - addGroupKind 포워드 실행");
		return "group/addGroupKind";
	}
	
	//그룹 메인
	@RequestMapping(value="/detailGroupMain", method=RequestMethod.GET)
	public String detailGroupMain(Model model,@RequestParam(value="groupName") String groupName) {
		logger.debug("GroupController - detailGroupMain 포워드 실행");
		Map<String,Object> map = groupInviteService.detailGroupMain(groupName);
		model.addAttribute("historyMedicineCount", map.get("historyMedicineCount"));
		model.addAttribute("historyTreatmemtCount", map.get("historyTreatmemtCount"));
		model.addAttribute("historyHealthScreenCount", map.get("historyHealthScreenCount"));
		model.addAttribute("historyHealthSurveyCount", map.get("historyHealthSurveyCount"));
		model.addAttribute("detailGroup", map.get("detailGroup"));
		model.addAttribute("addHistoryMedicine", map.get("addHistoryMedicine"));
		model.addAttribute("addHistoryTreatmemt", map.get("addHistoryTreatmemt"));
		model.addAttribute("addHistoryHealthScreen", map.get("addHistoryHealthScreen"));
		model.addAttribute("addHistoryHealthSurvey", map.get("addHistoryHealthSurvey"));
		model.addAttribute("groupName", groupName);
		logger.debug("map:"+map);
		return "group/detailGroupMain";
	}
	
	//그룹 메인
	@RequestMapping(value="/groupMain", method=RequestMethod.GET)
	public String groupMain(Model model,HttpSession session) {
		logger.debug("GroupController - groupMain 포워드 실행");
		Map<String,Object> map = groupInviteService.groupJoinCreateCheck(session);
		model.addAttribute("map", map);
		logger.debug("map:"+map);
		return "group/groupMain";
	}
	//회원초대취소
	@RequestMapping(value="/groupInviteMemberCancle", method=RequestMethod.POST)
	public String groupInviteMemberCancle(@RequestParam(value="groupInviteNo") String groupInviteNo) {
		logger.debug("GroupController - groupInviteMemberCancle 리다이렉트 실행");
		groupInviteService.groupInviteMemberCancle(groupInviteNo);
		return "redirect:/inviteMemberList";
	}
	
	//그룹회원추방
	@RequestMapping(value="/outGroupMember", method=RequestMethod.POST)
	public String outGroupMember(@RequestParam(value="memberNo") String memberNo) {
		logger.debug("GroupController - outGroupMember 리다이렉트 실행");
		groupInviteService.outGroupMember(memberNo);
		return "redirect:/groupMemberList";
	}
	
	//그룹 삭제 진행 취소
	@RequestMapping(value="/groupDeleteCancle", method=RequestMethod.POST)
	public String groupDeleteCancle(@RequestParam(value="groupNo") String groupNo) {
		logger.debug("GroupController - groupDeleteCancle 리다이렉트 실행");
		groupService.cancleDeleteGroup(groupNo);
		return "redirect:/deleteGroupList";
	}
	//그룹 관계도
	@RequestMapping(value="/groupMemberRelation", method=RequestMethod.GET)
	public String groupMemberRelation() {
		logger.debug("GroupController - groupMemberRelation 포워드 실행");
		return "group/groupMemberRelation";
	}
	
	//그룹 캘린더 
	@RequestMapping(value="/groupCalendar", method=RequestMethod.GET)
	public String groupCalendar(Model model,@RequestParam(value="groupName") String groupName) {
		logger.debug("GroupController - groupCalendar 포워드 실행");
		model.addAttribute("groupName",groupName);
		return "group/groupCalendar";
	}
	
	//그룹 삭제 진행
	@RequestMapping(value="/deleteGroup", method=RequestMethod.GET)
	public String acceptGroupList(@RequestParam(value="groupNo") String groupNo) {
		logger.debug("GroupController - deleteGroup 리다이렉트 실행");
		groupService.deleteGroup(groupNo);
		return "redirect:/groupList";
	}
	
	//그룹초대 수락
	@RequestMapping(value="/accpetGroup", method=RequestMethod.GET)
	public String acceptGroupList(GroupInvite groupInvite) {
		logger.debug("GroupController - acceptGroupList 포워드 실행");
		groupInviteService.acceptGroupList(groupInvite);
		return "redirect:/inviteGroupList";
	}
	
	//그룹 회원 리스트 키워드 검색
	@RequestMapping(value="/groupMembersListSearchKeyoption", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView groupMembersListSearchKeyoption(@RequestParam(value="KeyOption") String KeyOption
									,@RequestParam(value="groupName") String groupName	
									,@RequestParam(value="KeyWord")String KeyWord
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - groupMembersList groupMembersListSearchKeyoption ModelAndView 실행");
		Map<String,Object> map = groupInviteService.groupMembersListSearchKeyoption(groupName, KeyOption, KeyWord, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("group/groupMembersList");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("groupName", groupName);
		modelAndView.addObject("KeyOption", map.get("KeyOption"));
		modelAndView.addObject("KeyWord", map.get("KeyWord"));
		modelAndView.addObject("searchKeywordresult", map.get("total"));
		modelAndView.addObject("totalBlock", map.get("totalBlock"));
		modelAndView.addObject("firstBlockPage", map.get("firstBlockPage"));
		modelAndView.addObject("lastBlockPage", map.get("lastBlockPage"));
		modelAndView.addObject("lastPage", map.get("lastPage"));
		modelAndView.addObject("currentPage", currentPage);
		return modelAndView;
	}
	//그룹 회원 리스트 날짜 검색
	@RequestMapping(value="/groupMembersListSearchDate", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView groupMembersListSearchDate(@RequestParam(value="startDate") String startDate
									,@RequestParam(value="groupName") String groupName
									,@RequestParam(value="endDate")String endDate
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - groupMembersList groupMembersListSearchDate ModelAndView 실행");
		Map<String,Object> map = groupInviteService.groupMembersListSearchDate(groupName, startDate, endDate, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("group/groupMembersList");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("groupName", groupName);
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
	//그룹 회원 리스트
	@RequestMapping(value="/groupMembersList", method=RequestMethod.GET)
	public String groupMemberList(Model model
					,@RequestParam(value="groupName") String groupName
					,@RequestParam(value="currentPage", defaultValue="1") int currentPage
					,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - groupMemberList 포워드 실행");
		logger.debug("groupName:"+groupName);
		Map<String,Object> map = groupInviteService.groupMemberList(currentPage, pagePerRow, groupName);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("groupName", groupName);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		model.addAttribute("groupNoSend", map.get("groupNoSend"));
		model.addAttribute("memberCountResult", map.get("memberCountResult"));
		return "group/groupMembersList";
	}
	
	//그룹 상세보기
	@RequestMapping(value="/groupDetail", method=RequestMethod.GET)
	public String groupDetail(Model model,HttpSession session
					,@RequestParam(value="groupNo") String groupNo ) {
		logger.debug("GroupController - groupDetail 포워드 실행");
		Map<String,Object> map = groupService.groupDetail(groupNo,session);
		model.addAttribute("map", map);
		logger.debug("map:"+map);
		return "group/groupDetail";
	}
	//나를 초대한 그룹 리스트 키워드 검색
	@RequestMapping(value="/inviteGroupListKeywordSearch", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView inviteGroupListSearchKeyoption(@RequestParam(value="KeyOption") String KeyOption
									,HttpSession session
									,@RequestParam(value="KeyWord")String KeyWord
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - inviteGroupList inviteGroupListSearchKeyoption ModelAndView 실행");
		Map<String,Object> map = groupInviteService.inviteGroupListSearchKeyoption(session, KeyOption, KeyWord, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("group/inviteGroupList");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("KeyOption", map.get("KeyOption"));
		modelAndView.addObject("KeyWord", map.get("KeyWord"));
		modelAndView.addObject("searchKeywordresult", map.get("total"));
		modelAndView.addObject("totalBlock", map.get("totalBlock"));
		modelAndView.addObject("firstBlockPage", map.get("firstBlockPage"));
		modelAndView.addObject("lastBlockPage", map.get("lastBlockPage"));
		modelAndView.addObject("lastPage", map.get("lastPage"));
		modelAndView.addObject("currentPage", currentPage);
		return modelAndView;
	}
	//나를 초대한 그룹 리스트 날짜 검색
	@RequestMapping(value="/inviteGroupListSearch", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView inviteGroupListSearchDate(@RequestParam(value="startDate") String startDate
									,HttpSession session
									,@RequestParam(value="endDate")String endDate
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - inviteGroupList inviteGroupListSearchDate ModelAndView 실행");
		Map<String,Object> map = groupInviteService.inviteGroupListSearchDate(session, startDate, endDate, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("group/inviteGroupList");
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
	//나를 초대한 그룹 리스트
	@RequestMapping(value="/inviteGroupList", method=RequestMethod.GET)
	public String inviteGroupList(Model model
									,HttpSession session
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - inviteGroupList 포워드 실행");
		Map<String,Object> map = groupInviteService.inviteGroupList(session, currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "group/inviteGroupList";
	}
	
	//그룹에 초대한 멤버 리스트 키워드 검색
	@RequestMapping(value="/groupInviteListSearchKeyoption", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView groupInviteListSearchKeyoption(@RequestParam(value="KeyOption") String KeyOption
									,@RequestParam(value="groupNo") String groupNo	
									,@RequestParam(value="KeyWord")String KeyWord
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - inviteMemberList groupInviteListSearchKeyoption ModelAndView 실행");
		Map<String,Object> map = groupInviteService.groupInviteListSearchKeyoption(groupNo, KeyOption, KeyWord, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("group/inviteMemberList");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("KeyOption", map.get("KeyOption"));
		modelAndView.addObject("KeyWord", map.get("KeyWord"));
		modelAndView.addObject("searchKeywordresult", map.get("total"));
		modelAndView.addObject("totalBlock", map.get("totalBlock"));
		modelAndView.addObject("firstBlockPage", map.get("firstBlockPage"));
		modelAndView.addObject("lastBlockPage", map.get("lastBlockPage"));
		modelAndView.addObject("lastPage", map.get("lastPage"));
		modelAndView.addObject("currentPage", currentPage);
		return modelAndView;
	}
	//그룹에 초대한 멤버 리스트 날짜 검색
	@RequestMapping(value="/groupInviteListSearchDate", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView groupInviteListSearchDate(@RequestParam(value="startDate") String startDate
									,@RequestParam(value="groupNo") String groupNo
									,@RequestParam(value="endDate")String endDate
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - inviteMemberList groupInviteListSearchDate ModelAndView 실행");
		Map<String,Object> map = groupInviteService.groupInviteListSearchDate(groupNo, startDate, endDate, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("group/inviteMemberList");
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
	//그룹에 초대한 멤버 리스트
	@RequestMapping(value="/inviteMemberList", method=RequestMethod.GET)
	public String inviteMemberList(Model model
									,@RequestParam(value="groupNo") String groupNo
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - inviteMemberList 포워드 실행");
		Map<String,Object> map = groupInviteService.groupInviteList(groupNo, currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("groupName", map.get("groupName"));
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "group/inviteMemberList";
	}
	
	//그룹 멤버초대 등록
	@RequestMapping(value="/inviteMember", method=RequestMethod.POST)
	public String inviteMember(GroupInvite groupInvite,HttpServletRequest request) {
		logger.debug("GroupController - inviteMemberForm 리다이렉트 실행");
		groupInviteService.addInviteMember(groupInvite);
		return "redirect:/groupList";
	}

	//그룹 멤버초대 폼
	@RequestMapping(value="/inviteMemberForm", method=RequestMethod.GET)
	public String inviteMemberForm(Model model,@RequestParam(value="groupNo") String groupNo) {
		logger.debug("GroupController - inviteMemberForm 포워드 실행");
		Group groupTable = groupInviteService.inviteGroup(groupNo);
		model.addAttribute("groupTable", groupTable);
		return "group/inviteMemberForm";
	}
	
	//그룹 수정 완료
	@RequestMapping(value="/modifyGroup",method=RequestMethod.POST)
	public String modifyGroup(@RequestParam(value="groupNo") String groupNo
							,@RequestParam(value="groupKindNo") String groupKindNo
							,@RequestParam(value="groupInfo") String groupInfo) {
		logger.debug("GroupController - modifyGroup 리다이렉트 실행.");
		groupService.modifyGroupResult(groupNo, groupInfo, groupKindNo);
		return "redirect:/groupList";
	}
		
	//그룹 수정
	@RequestMapping(value="/modifyGroup", method=RequestMethod.GET)
	public String modifyGroup(Model model,@RequestParam(value="groupNo") String groupNo) {
		logger.debug("GroupController - modifyGroup 포워드 실행.");
		Group groupTable = groupService.modifyGroup(groupNo);
		model.addAttribute("groupTable", groupTable);
		return "group/modifyGroup";
	}	
	
	//삭제 유예 등록된 그룹 리스트키워드 검색
	@RequestMapping(value="/deleteGroupListKeywordSearch", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView deleteGroupListSearchKeyoption(@RequestParam(value="KeyOption") String KeyOption
									,HttpSession session
									,@RequestParam(value="KeyWord")String KeyWord
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - deleteGroupList deleteGroupListKeywordSearch ModelAndView 실행");
		Map<String,Object> map = groupService.deleteGroupListSearchKeyoption(session, KeyOption, KeyWord, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("group/deleteGroupList");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("KeyOption", map.get("KeyOption"));
		modelAndView.addObject("KeyWord", map.get("KeyWord"));
		modelAndView.addObject("searchKeywordresult", map.get("total"));
		modelAndView.addObject("totalBlock", map.get("totalBlock"));
		modelAndView.addObject("firstBlockPage", map.get("firstBlockPage"));
		modelAndView.addObject("lastBlockPage", map.get("lastBlockPage"));
		modelAndView.addObject("lastPage", map.get("lastPage"));
		modelAndView.addObject("currentPage", currentPage);
		return modelAndView;
	}
	//삭제 유예 등록된 그룹 리스트 날짜 검색
	@RequestMapping(value="/deleteGroupListSearch", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView deleteGroupListSearchDate(@RequestParam(value="startDate") String startDate
									,HttpSession session
									,@RequestParam(value="endDate")String endDate
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - deleteGroupList deleteGroupListSearchDate ModelAndView 실행");
		Map<String,Object> map = groupService.deleteGroupListSearchDate(session, startDate, endDate, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("group/deleteGroupList");
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
	//삭제 유예 등록된 그룹 리스트
	@RequestMapping(value="/deleteGroupList", method=RequestMethod.GET)
	public String deleteGroupList(Model model
							,HttpSession session
							,@RequestParam(value="currentPage", defaultValue="1") int currentPage
							,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - deleteGroupList 포워드 실행");
		Map<String,Object> map = groupService.deleteGroupList(session, currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "group/deleteGroupList";
	}
	
	//생성된 그룹 리스트 키워드 검색
	@RequestMapping(value="/groupListKeywordSearch", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView groupListKeywordSearch(@RequestParam(value="KeyOption") String KeyOption
									,HttpSession session
									,@RequestParam(value="KeyWord")String KeyWord
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - groupList groupListSearchKeyoption ModelAndView 실행");
		Map<String,Object> map = groupService.groupListSearchKeyoption(session, KeyOption, KeyWord, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("group/groupList");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("KeyOption", map.get("KeyOption"));
		modelAndView.addObject("KeyWord", map.get("KeyWord"));
		modelAndView.addObject("searchKeywordresult", map.get("total"));
		modelAndView.addObject("totalBlock", map.get("totalBlock"));
		modelAndView.addObject("firstBlockPage", map.get("firstBlockPage"));
		modelAndView.addObject("lastBlockPage", map.get("lastBlockPage"));
		modelAndView.addObject("lastPage", map.get("lastPage"));
		modelAndView.addObject("currentPage", currentPage);
		return modelAndView;
	}
	//생성된 그룹 리스트 날짜 검색
	@RequestMapping(value="/groupListSearch", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView groupListSearch(@RequestParam(value="startDate") String startDate
									,HttpSession session
									,@RequestParam(value="endDate")String endDate
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - groupList groupListSearch ModelAndView 실행");
		Map<String,Object> map = groupService.groupListSearch(session, startDate, endDate, currentPage, pagePerRow);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("group/groupList");
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
	//생성된 그룹 리스트
	@RequestMapping(value="/groupList", method=RequestMethod.GET)
	public String groupList(Model model
							,HttpSession session
							,@RequestParam(value="currentPage", defaultValue="1") int currentPage
							,@RequestParam(value="pagePerRow", defaultValue="10")int pagePerRow) {
		logger.debug("GroupController - groupList 포워드 실행");
		Map<String,Object> map = groupService.groupList(session, currentPage, pagePerRow);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastBlockPage", map.get("lastBlockPage"));
		model.addAttribute("firstBlockPage", map.get("firstBlockPage"));
		model.addAttribute("totalBlock", map.get("totalBlock"));
		return "group/groupList";
	}
	
	//그룹 생성
	@RequestMapping(value="/addGroup", method=RequestMethod.POST)
	public String addGroup(Group group) {
		logger.debug("GroupController - addGroup 리다이렉트 실행");
		groupService.addGroup(group);
		return "redirect:/groupList";
	}
	
	@RequestMapping(value="/addGroup", method=RequestMethod.GET)
	public String addGroup() {
		logger.debug("GroupController - addGroup 포워드 실행");
		return "group/addGroup";
	}
	
}
