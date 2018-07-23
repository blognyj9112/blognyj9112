package com.cafe24.blognyj9112.group.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kyungsu93.message.service.Message;
import com.cafe24.kyungsu93.message.service.MessageDao;

@Service
@Transactional
public class GroupService {
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private GroupInviteDao groupInviteDao;
	@Autowired
	private MessageDao messageDao;
	private static final Logger logger = LoggerFactory.getLogger(GroupService.class);
	/**
	 * 그룹종류선택리스트
	 * @return
	 */
	public Map<String, Object> groupKindListSelect() {
		logger.debug("GroupService - groupKindListSelect 실행");
		List<Group> list = groupDao.groupKindListSelect();
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("list", list);
		return returnMap;
		
	}
	/**
	 * 그룹종류 수정
	 * @param group
	 */
	public void updateGroupKind(Group group) {
		logger.debug("GroupService - updateGroupKind 실행");
		groupDao.updateGroupKind(group);
	}
	/**
	 * 그룹종류넘버선택
	 * @param groupKindName
	 * @return
	 */
	public Group groupKindNoSelect(String groupKindName) {
		logger.debug("GroupService - groupKindNoSelect 실행");
		Group group = groupDao.groupKindNoSelect(groupKindName);
		return group;
	}
	/**
	 * 그룹종류리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String, Object> groupKindList( int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupKindList 실행");
		Map<String,Integer> map = new HashMap<String,Integer>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		//리스트 배열
		List<Group> list = groupDao.groupKindList(map);
		//리스트에 등록된 총 게시물 수 
		int total = groupDao.groupKindListTotalCount();
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
	 * 그룹종류등록
	 * @param group
	 */
	public void addGroupKind(Group group){
		logger.debug("GroupService - addGroupKind 실행");
		group.setGroupKindNo("group_kind_"+(groupDao.groupKindNo()+1));
		groupDao.addGroupKind(group);
		
	}
	/**
	 * 그룹 상세 
	 * @param groupNo
	 * @return returnMap
	 */
	public Map<String, Object> groupDetail(String groupNo, HttpSession session){
		logger.debug("GroupService - groupDetail실행");
		Map<String,Object> map = new HashMap<String,Object>();
		String memberNo = (String) session.getAttribute("memberSessionNo");
		int sessionLevel = (Integer) session.getAttribute("memberSessionLevel");
		map.put("memberNo", memberNo);
		map.put("sessionLevel", sessionLevel);
		map.put("groupNo", groupNo);
		Group groupDetail = groupDao.modifyGroup(groupNo);
		logger.debug("groupDetail:"+groupDetail);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("groupDetail", groupDetail);
		logger.debug("groupDetail:"+groupDetail);
		//이전글 다음글 
		int countPrev = 0;
		int countNext = 0;
		//이전글 다음글 카운트 
		countPrev = groupDao.prevGroupDetailCount(map);
		countNext = groupDao.nextGroupCount(map);
		if(countNext != 0){
			Group nextGroup = groupDao.nextGroupDetail(map);
			returnMap.put("nextGroup", nextGroup);
			returnMap.put("countNext", countNext);
			if(countPrev != 0){
				//둘다있을경우
				logger.debug("이전글,다음글이 있습니다.");
				Group prevGroup = groupDao.prevGroupDetail(map);
				returnMap.put("prevGroup", prevGroup);
				returnMap.put("countPrev", countPrev);
			}else {
				//이전글이 없을경우
				logger.debug("이전글이 없습니다.");
				returnMap.put("countPrev", countPrev);
			}
		}else {
			if(countPrev != 0) {
				//다음글이 없을 경우
				logger.debug("다음글이 없습니다.");
				Group prevGroup = groupDao.prevGroupDetail(map);
				returnMap.put("prevGroup", prevGroup);
				returnMap.put("countNext", countNext);
				returnMap.put("countPrev", countPrev);
			}
		}
		return returnMap;
	}
	
	/**
	 * 그룹 수정
	 * @param groupNo
	 * @param groupInfo
	 * @param groupKindNo
	 */
	public void modifyGroupResult(String groupNo, String groupInfo,String groupKindNo) {
		logger.debug("GroupService - modifyGroupResult실행");
		Group group = new Group();
		group.setGroupInfo(groupInfo);
		group.setGroupKindNo(groupKindNo);
		group.setGroupNo(groupNo);
		groupDao.modifyGroupResult(group);
	}
	
	/**
	 * 하나의 그룹 선택
	 * @param groupNo
	 * @return
	 */
	public Group modifyGroup(String groupNo) {
		logger.debug("GroupService - modifyGroup 실행");
		Group group = groupDao.modifyGroup(groupNo);
		return group;
	}
	/**
	 * 그룹 삭제 유예 기간 리스트 키옵션 검색
	 * @param session
	 * @param KeyOption
	 * @param KeyWord
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> deleteGroupListSearchKeyoption(HttpSession session,String KeyOption, String KeyWord,int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupListSearchKeyoption 실행");
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
		map.put("KeyOption", KeyOption);
		map.put("KeyWord", KeyWord);
		logger.debug("가져온 데이터:"+KeyOption+","+KeyWord);
		List<Group> list = groupDao.deleteGroupListSearchKeyoption(map);
		int total = groupDao.groupdeleteSearchKeyoptionCount(map);
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
	 * 그룹 삭제 유예기간 리스트 날짜 검색
	 * @param session
	 * @param KeyOption
	 * @param KeyWord
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> deleteGroupListSearchDate(HttpSession session,String startDate, String endDate,int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupListSearch 실행");
		String memberNo = (String) session.getAttribute("memberSessionNo");
		int sessionLevel = (Integer) session.getAttribute("memberSessionLevel");
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		logger.debug("memberNo:"+memberNo);
		logger.debug("sessionLevel:"+sessionLevel);
		map.put("sessionLevel", sessionLevel);
		map.put("memberNo", memberNo);
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		logger.debug("가져온 데이터:"+startDate+","+endDate);
		List<Group> list = groupDao.deleteGroupListSearchDate(map);
		int total = groupDao.groupdeleteSearchDateCount(map);
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
	 * 그룹 삭제 리스트
	 * 유예기간이 지난경우 그룹 삭제
	 * @return
	 */
	public Map<String, Object> deleteGroupList(HttpSession session,int currentPage, int pagePerRow) {
		logger.debug("GroupService - deleteList 실행");
		//총 삭제 유예 기간에 있는 그룹 검색
		int deleteTotal = groupDao.groupdeleteCount();
		int i = 0;
		//그룹 유예기간 삭제 리스트에 등록된 게시물 수만큼 반복
		for( i = 0; i<deleteTotal; i++ ) {
			//현재 날짜와 비교해서 그룹삭제유예기간이 만료된 그룹 삭제 
		    Date today = new Date();
		    SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		    String now = date.format(today);
		    logger.debug("현재시간:"+now);
		    //삭제유예기간에 들어가있는 그룹중 중 제일 오래된 날짜 검색
			Group day = groupDao.groupDdaycheck();
			String groupExpiredDate = day.getGroupExpiredDate();
			logger.debug("유예 기간 groupExpiredDate:"+groupExpiredDate);
			int compare = now.compareTo(groupExpiredDate);
			//현재 날짜와 비교해서 유예기간 만료된 그룹 삭제
			if(compare < 0) {
				logger.debug("유예기간 날짜이 지났습니다. 그룹 삭제 진행.");
				Group number = groupDao.groupDdayNo(groupExpiredDate);
				String groupNo = number.getGroupNo();
				logger.debug("groupNo:"+groupNo);
				if(groupNo != null) {
					int count = 0;
					count = groupDao.deleteGroup(groupNo);
						if(count > 0) {
							groupDao.deleteGroupDelete(groupNo);
							//그룹에 속한 회원 삭제
							Group groupNameSearch = groupDao.groupNameSearch(groupNo);
							String groupName = groupNameSearch.getGroupName();
							groupInviteDao.deleteGroupMemberAll(groupName);
							logger.debug("그룹삭제완료.");
						}
					}
			}
		}
		//삭제유예기간에 있는 리스트 출력
		//페이징
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
		
		//삭제유예기간에 있는 리스트 출력
		List<Group> list = groupDao.deleteGroupList(map);
		int total = groupDao.groupdeleteSelectCount(map);
		int lastPage = total/pagePerRow;
        if(total % pagePerRow != 0) {
            lastPage++;
        }
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
	 * 그룹 회원 수 검색
	 * @param groupNo
	 */
	public Map<String, Object> memberCountSearch(String groupName) {	
		logger.debug("GroupService -  실행");
		Map<String,Object> returnMap = new HashMap<String,Object>();
		int memberCount = 0;
		memberCount = groupDao.groupDeleteCheckMemberCount(groupName);
		returnMap.put("memberCount", memberCount);
		return returnMap;
	}
	/**
	 * 그룹삭제 취소
	 * @param groupNo
	 */
	public void cancleDeleteGroup(String groupNo) {
		logger.debug("GroupService - cancleDeleteGroup 실행");
		groupDao.deleteGroupDelete(groupNo);
	}
	
	/**
	 * 그룹삭제 회원이 있을 경우 유예기간 등록
	 * @param groupNo
	 */
	public void deleteGroup(String groupNo) {
		logger.debug("GroupService - deleteGroup 실행");
		//그룹번호로 그룹명 검색
		Group groupname = groupDao.groupDeleteCheckgroupNo(groupNo);
		String groupName = groupname.getGroupName();
		logger.debug("groupName:"+groupName);
		if(groupName != null) {
			//그룹명으로  총 회원검색
			int memberCount = 0;
			memberCount = groupDao.groupDeleteCheckMemberCount(groupName);
			if(memberCount>0) {
				//회원이 있을경우 유예기간 등록
				groupDao.deleteApproval(groupNo);
				//회원에게 유예기간 알림
				List<Group> memberList = groupDao.groupMemberList(groupNo);
				logger.debug("memberList:"+memberList);
				for(int i=0; i<memberCount; i++) {
					for(Group group : memberList) {
					logger.debug("GroupService -delete- sendMessage 시작");
					Date today = new Date();
				    SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
				    String now = date.format(today);
				    logger.debug("현재시간:"+now);
				    //삭제유예기간에 들어가있는 그룹중 중 제일 오래된 날짜 검색
					Group day = groupDao.groupDdaycheck();
					String groupExpiredDate = day.getGroupExpiredDate();
					logger.debug("유예 기간 groupExpiredDate:"+groupExpiredDate);
					
					Message message = new Message();
					int result = (messageDao.messageNo())+1;//메시지의 no 를 구함
					String messageNo = "message_";
					//받는사람
					String memberReceiveNo = group.getMemberNo();
					group.setGroupNo(memberReceiveNo);
					message.setMemberReceiveNo(memberReceiveNo);
					//보내는사람
					message.setSendMessageNo(messageNo+result);
					String memberSendNo = "member_1";
					message.setMemberSendNo(memberSendNo);
					String sendMessageId = "rlaansrl";
					message.setSendMessageId(sendMessageId);
					logger.debug("messageNo : "+message.getSendMessageNo());
					//메세지 내용
					String messageTitle = groupName+"그룹이 삭제 유예기간으로 들어갔습니다!";
					message.setMessageTitle(messageTitle);
					String messageContent = groupName+"그룹이 삭제 유예기간으로 들어갔습니다."+groupExpiredDate+"일 안에 그룹 탈퇴를 하지않으면 자동 탈퇴 처리 됩니다.";
					message.setMessageContent(messageContent);
					
					messageDao.sendMessage(message);//send_Message 테이블 에 데이트를 셋
					messageDao.sendMessageContent(message);//발신자 테이블에 데이터 셋
					messageDao.receiveMessageContent(message);//수신자 테이블에 데이터 셋
					logger.debug("ExerciseFeedbackResponseService - sendMessage 완료");
					}
				}
			    
			}else {
				//회원이 없을 경우 바로 삭제
				groupDao.deleteGroup(groupNo);
			}		
		}
	}	
	/**
	 * 그룹리스트 일반 검색
	 * @param session
	 * @param KeyOption
	 * @param KeyWord
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> groupListSearchKeyoption(HttpSession session,String KeyOption, String KeyWord,int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupListSearchKeyoption 실행");
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
		map.put("KeyOption", KeyOption);
		map.put("KeyWord", KeyWord);
		logger.debug("가져온 데이터:"+KeyOption+","+KeyWord);
		List<Group> list = groupDao.groupListSearchKeyoption(map);
		int total = groupDao.groupListSearchKeyoptionCount(map);
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
	 * 그룹리스트 날짜 검색
	 * @param session
	 * @param startDate
	 * @param endDate
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String,Object> groupListSearch(HttpSession session,String startDate, String endDate,int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupListSearch 실행");
		String memberNo = (String) session.getAttribute("memberSessionNo");
		int sessionLevel = (Integer) session.getAttribute("memberSessionLevel");
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		logger.debug("memberNo:"+memberNo);
		logger.debug("sessionLevel:"+sessionLevel);
		map.put("sessionLevel", sessionLevel);
		map.put("memberNo", memberNo);
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		logger.debug("가져온 데이터:"+startDate+","+endDate);
		List<Group> list = groupDao.groupListSearchDate(map);
		int total = groupDao.groupListSearchDateCount(map);
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
	 * 생성된 그룹 리스트
	 * @param currentPage
	 * @param pagePerRow
	 * @return
	 */
	public Map<String, Object> groupList(HttpSession session, int currentPage, int pagePerRow) {
		logger.debug("GroupService - groupList 실행");
		String memberNo = (String) session.getAttribute("memberSessionNo");
		int sessionLevel = (Integer) session.getAttribute("memberSessionLevel");
		Map<String,Object> map = new HashMap<String,Object>();
		int beginRow = (currentPage-1)*pagePerRow;
		map.put("memberNo", memberNo);
		map.put("sessionLevel", sessionLevel);
		map.put("beginRow", beginRow);
		map.put("pagePerRow", pagePerRow);
		//리스트 배열
		List<Group> list = groupDao.groupList(map);
		//리스트에 등록된 총 게시물 수 
		int total = groupDao.groupListCount(map);
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
	 * 그룹명 검색
	 * @param groupName
	 * @return
	 */
	public  Map<String, Object> checkGroupName(String groupName) {
		logger.debug("GroupService - checkGroupName실행");	
		Map<String,Object> returnMap = new HashMap<String,Object>();
		int count = 0;
		//그룹 전체 검색
		count = groupDao.groupTotalCount();
		int result = 0;
		if(count >0) {
			//그룹명 검색
			result = groupDao.checkGroupNameCount(groupName);
			returnMap.put("result", result);
		}else {
			result = 0;
			returnMap.put("result", result);
		}
		return returnMap;
	}
	
	/**
	 * 그룹 생성
	 * @param group
	 */
	public void addGroup(Group group) {
		logger.debug("GroupService - addGroup실행");	
		String groupNo = group.getGroupNo();
		try {
			if(groupNo == null) {
				//그룹전체검색
				int count = 0;
				count = groupDao.groupTotalCount();
				if(count > 0) {
					int result = 0;
					String groupNo_temp = "group_";
					//그룹 번호 최대값 검색
					result = groupDao.selectGroupNo();
					if(result > 0) {
						if(1 <= result) {
							result++;
						}
						groupNo = groupNo_temp + result; 
					}
				}else {
					groupNo = "group_1";
				}
			}
			group.setGroupNo(groupNo);
			
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		groupDao.addGroup(group);
		}
	}

