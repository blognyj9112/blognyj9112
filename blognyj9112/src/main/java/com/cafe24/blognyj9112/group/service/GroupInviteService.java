package com.cafe24.blognyj9112.group.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kyungsu93.member.service.MemberDao;

@Service
@Transactional
public class GroupInviteService {

	@Autowired
	private GroupInviteDao groupInviteDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private GroupDao groupDao;
	private static final Logger logger = LoggerFactory.getLogger(GroupInviteService.class);
	/**
	 * 그룹메인
	 * @param groupName
	 * @return
	 */
	public Map<String, Object> detailGroupMain(String groupName) {
		logger.debug("GroupInviteService - detailGroupMain 실행");
		GroupInvite detailGroup = groupInviteDao.detailGroupMainNameNo(groupName);
		List<GroupCalendar> addHistoryMedicine = groupInviteDao.addHistoryMedication(groupName); //복약
		List<GroupCalendar> addHistoryTreatmemt = groupInviteDao.addHistorytreatment(groupName); //진료
		List<GroupCalendar> addHistoryHealthScreen = groupInviteDao.addHistoryHealthScreen(groupName); //건강검진
		List<GroupCalendar> addHistoryHealthSurvey = groupInviteDao.addHistoryHealthSurvey(groupName); //건강설문
		Map<String,Object> returnMap = new HashMap<String,Object>();
		int historyMedicineCount = 0;
		historyMedicineCount = addHistoryMedicine.size();
		if(historyMedicineCount > 0) {
			returnMap.put("historyMedicineCount", historyMedicineCount);
		}
		int historyTreatmemtCount = 0;
		historyTreatmemtCount = addHistoryTreatmemt.size();
		if(historyTreatmemtCount> 0) {
			returnMap.put("historyTreatmemtCount", historyTreatmemtCount);
		}
		int historyHealthScreenCount = 0;
		historyHealthScreenCount = addHistoryHealthScreen.size();
		if(historyHealthScreenCount>0) {
			returnMap.put("historyHealthScreenCount", historyHealthScreenCount);
		}
		int historyHealthSurveyCount = 0;
		historyHealthSurveyCount = addHistoryHealthSurvey.size();
		if(historyHealthSurveyCount>0) {
			returnMap.put("historyHealthSurveyCount", historyHealthSurveyCount);
		}
		returnMap.put("historyMedicineCount", historyMedicineCount);
		returnMap.put("historyTreatmemtCount", historyTreatmemtCount);
		returnMap.put("historyHealthScreenCount", historyHealthScreenCount);
		returnMap.put("historyHealthSurveyCount", historyHealthSurveyCount);
		returnMap.put("detailGroup", detailGroup);
		returnMap.put("addHistoryMedicine", addHistoryMedicine);
		returnMap.put("addHistoryTreatmemt", addHistoryTreatmemt);
		returnMap.put("addHistoryHealthScreen", addHistoryHealthScreen);
		returnMap.put("addHistoryHealthSurvey", addHistoryHealthSurvey);
		return returnMap;
	}
	/**
	 * 그룹가입생성체크
	 * @param memberNo
	 * @return
	 */
	public Map<String, Object> groupJoinCreateCheck(HttpSession session) {
		logger.debug("GroupInviteService - groupJoinCreateCheck 실행");
		String memberNo = (String) session.getAttribute("memberSessionNo");
		int sessionLevel = (Integer) session.getAttribute("memberSessionLevel");
		logger.debug("memberNo:"+memberNo);
		logger.debug("sessionLevel:"+sessionLevel);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		//가입햇는지
		int result = 0;
				result = groupInviteDao.memberGroupJoinCheckCount(memberNo);
		if(result>0) {
			List<GroupInvite> groupJoinList = groupInviteDao.memberGroupJoinCheck(memberNo);
			returnMap.put("groupJoinList", groupJoinList);
			returnMap.put("result", result);
		}else {
			returnMap.put("result", result);
		}
		//생성했는지
		int creationResult = 0;
		creationResult = groupDao.memberGroupCreateCheckCount(memberNo);
		if(creationResult>0) {
			List<Group> groupCreateList = groupDao.memberGroupCreateCheck(memberNo);
			returnMap.put("groupCreateList", groupCreateList);
			returnMap.put("creationResult", creationResult);
		}else {
			returnMap.put("creationResult", creationResult);
		}
		return returnMap;	
	}
	
	/**
	 * 회원 초대 취소
	 * @param groupInviteNo
	 */
	
	public void groupInviteMemberCancle(String groupInviteNo) {
		logger.debug("groupInviteDao - groupInviteMemberCancle 실행");
		groupInviteDao.groupInviteMemberCancle(groupInviteNo);
	}
	/**
	 * 그룹회원추방
	 * @param memberNo
	 */
	public void outGroupMember(String memberNo) {
		logger.debug("groupInviteDao - outGroupMember 실행");
		groupInviteDao.outGroupMember(memberNo);
	}
	/**
	 * 그룹 캘린더 리스트
	 * @param groupName
	 * @return
	 */
	public Map<String, Object> groupCalendarList(String groupName) {
		logger.debug("GroupInviteService - groupCalendarList 실행");
		List<GroupCalendar> groupCalendarMedication = groupInviteDao.groupCalendarMedication(groupName); //복약
		List<GroupCalendar> groupCalendarTreatment = groupInviteDao.groupCalendartreatment(groupName); //진료
		List<GroupCalendar> groupCalendarHealthScreen = groupInviteDao.groupCalendarHealthScreen(groupName); //건강검진
		List<GroupCalendar> groupCalendarHealthSurvey = groupInviteDao.groupCalendarHealthSurvey(groupName); //건강설문
		List<GroupInvite> groupRelationMember = groupInviteDao.groupRelationMember(groupName); //회원리스트
		GroupInvite creationMember = groupInviteDao.groupRelationGroupCreateMember(groupName); //그룹장정보
		logger.debug("groupCalendarMedication:"+groupCalendarMedication);
		logger.debug("groupCalendartreatment:"+groupCalendarTreatment);
		logger.debug("groupRelationMember:"+groupRelationMember);
		logger.debug("groupCalendarHealthSurvey:"+groupCalendarHealthSurvey);
		logger.debug("groupCalendarHealthScreen:"+groupCalendarHealthScreen);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("groupCalendarMedication", groupCalendarMedication);
		returnMap.put("groupCalendarHealthScreen", groupCalendarHealthScreen);
		returnMap.put("groupRelationMember", groupRelationMember);
		returnMap.put("groupCalendarHealthSurvey", groupCalendarHealthSurvey);
		returnMap.put("groupCalendarTreatment", groupCalendarTreatment);
		returnMap.put("creationMember", creationMember);
		return returnMap;
	}
	
	/**
	 * 그룹관계도리스트
	 * @param groupName
	 * @return
	 */
	public Map<String, Object> groupRelationChart(String groupName) {
		logger.debug("GroupInviteService - groupRelationMember 실행");
		Map<String,Object> returnMap = new HashMap<String,Object>();
		GroupInvite createMember = groupInviteDao.groupRelationGroupCreateMember(groupName);
		String createMemb = createMember.getMemberName();
		returnMap.put("createMemb", createMemb);
		logger.debug("createMember:"+createMemb);
		List<GroupInvite> groupRelationMember = groupInviteDao.groupRelationMember(groupName);
		returnMap.put("groupRelationMember", groupRelationMember);
		logger.debug("groupRelationMember:"+groupRelationMember);
		return returnMap;
	}
	
	/**
	 * 그룹초대 수락
	 * @param inviteGroupNo
	 */
	public void acceptGroupList(GroupInvite groupInvite) {
		logger.debug("GroupInviteService - acceptGroupList 실행");
		logger.debug("groupInvite:"+groupInvite);
		String groupName = groupInvite.getGroupName();
		GroupInvite memberSearch = groupInviteDao.memberNameSearch(groupName);
		String memberName = memberSearch.getMemberName();
		String memberNo = memberSearch.getMemberNo();
		logger.debug("memberName:"+memberName);
		logger.debug("memberNo:"+memberNo);
		logger.debug("groupName:"+groupName);
		if(memberName != null) {
			GroupInvite personalSearch = groupInviteDao.personalAgreeSearch(memberNo);
			String personalInformationApproval = personalSearch.getPersonalInformationApproval();
			groupInvite.setPersonalInformationApproval(personalInformationApproval);
			groupInvite.setMemberNo(memberNo);
			groupInvite.setGroupName(groupName);
			logger.debug("personalInformationApproval:"+personalInformationApproval);
			logger.debug("groupInvite:"+groupInvite);
			groupInviteDao.acceptGroupList(groupInvite);
		}
	}
	public Map<String,Object> groupMembersListSearchDate(String groupName,String startDate, String endDate,int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupListSearch 실행");
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		map.put("startDate", startDate);
		map.put("groupName", groupName);
		map.put("endDate", endDate);
		logger.debug("가져온 데이터:"+startDate+","+endDate);
		List<GroupInvite> list = groupInviteDao.groupMembersListSearchDate(map);
		int total = groupInviteDao.groupMembersListSearchDateCount(map);
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
	
	public Map<String,Object> groupMembersListSearchKeyoption(String groupName, String KeyOption, String KeyWord,int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupMembersListSearchKeyoption 실행");
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		map.put("groupName", groupName);
		map.put("KeyOption", KeyOption);
		map.put("KeyWord", KeyWord);
		logger.debug("가져온 데이터:"+KeyOption+","+KeyWord);
		List<GroupInvite> list = groupInviteDao.groupMembersListSearchKeyoption(map);
		int total = groupInviteDao.groupMembersListCountSearchKeyoption(map);
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
		returnMap.put("KeyOption", KeyOption);
		returnMap.put("KeyWord", KeyWord);
		returnMap.put("total", total);
		return returnMap;
	}
	/**
	 * 그룹 회원 리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @param groupName
	 * @return
	 */
	public Map<String, Object> groupMemberList(int currentPage, int pagePerRow, String groupName) {
		logger.debug("GroupInviteService - groupMemberList 실행");
		logger.debug("groupName:"+groupName);
		//회원 수 조회
		int total = 0;
		total = groupInviteDao.groupMemberListCount(groupName);
		//회원이 있을경우 리스트 출력
		Group groupNoSearch = groupDao.groupNoSearch(groupName);
		String groupNoSend = groupNoSearch.getGroupNo();
		logger.debug("groupNoSend:"+groupNoSend);
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		map.put("groupName", groupName);
		List<GroupInvite> list = groupInviteDao.groupMemberList(map);
		Map<String,Object> returnMap = new HashMap<String,Object>();
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
		returnMap.put("groupNoSend", groupNoSend);
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("firstBlockPage", firstBlockPage);
		returnMap.put("lastBlockPage", lastBlockPage);
		returnMap.put("totalBlock", totalBlock);
		returnMap.put("memberCountResult", total);
		return returnMap;
	}
	
	/**
	 * 그룹 초대 리스트 키옵션 검색
	 * @param session
	 * @param KeyOption
	 * @param KeyWord
	 * @param currentPage
	 * @param pagePerRow
	 */
	public Map<String,Object> inviteGroupListSearchKeyoption(HttpSession session, String KeyOption, String KeyWord,int currentPage, int pagePerRow) {
		logger.debug("GroupService - inviteGroupListSearchKeyoption 실행");
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
		map.put("KeyOption", KeyOption);
		map.put("KeyWord", KeyWord);
		logger.debug("가져온 데이터:"+KeyOption+","+KeyWord);
		List<GroupInvite> list = groupInviteDao.inviteGroupListSearchKeyoption(map);
		int total = groupInviteDao.inviteGroupListSearchKeyoptiontotalCount(map);
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
		returnMap.put("KeyOption", KeyOption);
		returnMap.put("KeyWord", KeyWord);
		returnMap.put("total", total);
		return returnMap;
	}
	/**
	 * 그룹 초대 리스트 날짜 검색
	 * @param session
	 * @param startDate
	 * @param endDate
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> inviteGroupListSearchDate(HttpSession session,String startDate, String endDate,int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupListSearch 실행");
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
		List<GroupInvite> list = groupInviteDao.inviteGroupListSearchDate(map);
		int total = groupInviteDao.inviteGroupListSearchDatetotalCount(map);
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
	 * 나를 초대한 그룹 리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String, Object> inviteGroupList(HttpSession session,int currentPage, int pagePerRow){
		logger.debug("GroupInviteService - inviteGroupList 실행");
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
		List<GroupInvite> list = groupInviteDao.inviteGroupList(map);
		int total = groupInviteDao.totalCountInvite();
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
	 * 그룹에 초대한 멤버 리스트 날짜 검색
	 * @param groupNo
	 * @param startDate
	 * @param endDate
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> groupInviteListSearchDate(String groupNo,String startDate, String endDate,int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupListSearch 실행");
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		map.put("startDate", startDate);
		map.put("groupNo", groupNo);
		map.put("endDate", endDate);
		logger.debug("가져온 데이터:"+startDate+","+endDate);
		List<GroupInvite> list = groupInviteDao.groupInviteListSearchDate(map);
		int total = groupInviteDao.groupInviteListSearchDatetotalCount(map);
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
	 * 그룹에 초대한 멤버 리스트 키옵션 검색
	 * @param groupNo
	 * @param KeyOption
	 * @param KeyWord
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> groupInviteListSearchKeyoption(String groupNo, String KeyOption, String KeyWord,int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupInviteListSearchKeyoption 실행");
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		map.put("groupNo", groupNo);
		map.put("KeyOption", KeyOption);
		map.put("KeyWord", KeyWord);
		logger.debug("가져온 데이터:"+KeyOption+","+KeyWord);
		List<GroupInvite> list = groupInviteDao.groupInviteListSearchKeyoption(map);
		int total = groupInviteDao.groupInviteListSearchKeyoptiontotalCount(map);
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
		returnMap.put("KeyOption", KeyOption);
		returnMap.put("KeyWord", KeyWord);
		returnMap.put("total", total);
		return returnMap;
	}
	
	/**
	 * 그룹에 초대한 회원리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String, Object> groupInviteList(String groupNo, int currentPage, int pagePerRow){
		logger.debug("GroupInviteService - groupInviteList 실행");
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("groupNo", groupNo);
		map.put("pagePerRow", pagePerRow);
		List<GroupInvite> list = groupInviteDao.groupInviteList(map);
		GroupInvite	groupNameFind = groupInviteDao.groupNameFind(groupNo);
		String groupName = groupNameFind.getGroupName();
		int total = groupInviteDao.groupInviteListtotalCount(groupNo);
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
		returnMap.put("groupName", groupName);
		returnMap.put("lastPage", lastPage);
		returnMap.put("firstBlockPage", firstBlockPage);
		returnMap.put("lastBlockPage", lastBlockPage);
		returnMap.put("totalBlock", totalBlock);
		return returnMap;
	}
	
	/**
	 * 회원 초대
	 * @param groupInvite
	 */
	public void addInviteMember(GroupInvite groupInvite) {
		logger.debug("GroupInviteService - addInviteMember실행");		
		String groupInviteNo = groupInvite.getGroupInviteNo();
		String memberId = groupInvite.getMemberId();
		String groupInviteMessage = groupInvite.getGroupInviteMessage();
		String groupNo = groupInvite.getGroupNo();
		logger.debug("memberId:"+memberId);
		logger.debug("groupInviteNo:"+groupInviteNo);
		logger.debug("groupNo:"+groupNo);
		logger.debug("groupInviteMessage:"+groupInviteMessage);
		groupInvite = groupInviteDao.groupInviteMemberName(memberId);
		String memberNo = groupInvite.getMemberNo();
		logger.debug("memberNo:"+memberNo);
		try {
		if(groupInviteNo == null) {
			int count = 0;
			count = groupInviteDao.totalCountInvite();
			if(count > 0) {
				int result = 0;
				String groupInviteNo_temp = "group_invite_";
				result = groupInviteDao.groupInviteNo(groupInviteNo);
				if(result > 0) {
						if(1 <= result) {
							result++;
						}			
						groupInviteNo = groupInviteNo_temp + result; 
				}
			}else {
				groupInviteNo = "group_invite_1";
			}
		}
		String groupInviteApproval = "F";
		groupInvite.setGroupInviteApproval(groupInviteApproval);
		groupInvite.setGroupInviteNo(groupInviteNo);
		groupInvite.setMemberNo(memberNo);
		groupInvite.setGroupInviteMessage(groupInviteMessage);
		groupInvite.setGroupNo(groupNo);
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		groupInviteDao.inviteMember(groupInvite);
	}
	
	/**
	 * 회원 아이디 검색
	 * @param memberId
	 * @return
	 */
	public Map<String,Object> invitefind(String memberId){
		logger.debug("GroupInviteService - invitefind실행");		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		int count = 0;
		count = memberDao.memberListTotal();
		String name = null;
		int result = 0;
		if(count>0) {
			result = groupInviteDao.inviteMemberId(memberId);
			returnMap.put("result", result);
			if(result>0) {
				GroupInvite groupInvite = groupInviteDao.groupInviteMemberName(memberId);
				name = groupInvite.getMemberName();
				returnMap.put("name", name);
			}
		}else {
			result = 0;
			name = "공백";
			returnMap.put("result", result);
			returnMap.put("name", name);
		}
		return returnMap;
	}
	
	/**
	 * 하나의 그룹 검색
	 * @param groupNo
	 * @return
	 */
	public Group inviteGroup(String groupNo) {
		logger.debug("GroupInviteService - inviteGroup실행");	
		Group groupTable = groupDao.modifyGroup(groupNo);
		return groupTable;
	}
	
}
