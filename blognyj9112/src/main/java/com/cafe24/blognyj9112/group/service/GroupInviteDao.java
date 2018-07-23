package com.cafe24.blognyj9112.group.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupInviteDao {
	private static final Logger logger = LoggerFactory.getLogger(GroupInviteDao.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	final String NS = "com.cafe24.blognyj9112.group.service.GroupInviteMapper.";
	
	//그룹 회원 설문조사 리스트(그룹메인)
	public List<GroupCalendar> addHistoryHealthSurvey(String groupName) {
		logger.debug("GroupInviteDao - addHistoryHealthSurvey 실행");
		return sqlSession.selectList(NS+"addHistoryHealthSurvey",groupName);
	}
	
	//그룹 회원 건강검진 리스트(그룹메인)
	public List<GroupCalendar> addHistoryHealthScreen(String groupName) {
		logger.debug("GroupInviteDao - addHistoryHealthScreen 실행");
		return sqlSession.selectList(NS+"addHistoryHealthScreen",groupName);
	}
	
	//그룹 회원 복약일정 리스트(그룹메인)
	public List<GroupCalendar> addHistoryMedication(String groupName) {
		logger.debug("GroupInviteDao - addHistoryMedication 실행");
		return sqlSession.selectList(NS+"addHistoryMedication",groupName);
	}
	
	//그룹 회원 진료일정 리스트(그룹메인)
	public List<GroupCalendar> addHistorytreatment(String groupName) {
		logger.debug("GroupInviteDao - addHistorytreatment 실행");
		return sqlSession.selectList(NS+"addHistorytreatment",groupName);
	}
	//그룹멤버검색
	public GroupInvite groupMemberNo(String groupName) {
		logger.debug("GroupInviteDao - groupMemberNo 실행");
		return sqlSession.selectOne(NS+"groupMemberNo",groupName);
	}
	//그룹디테일
	public GroupInvite detailGroupMainNameNo(String groupName) {
		logger.debug("GroupInviteDao - detailGroupMainNameNo 실행");
		return sqlSession.selectOne(NS+"detailGroupMainNameNo",groupName);
	}
	
	//가입된 그룹 체크 카운트
	public int memberGroupJoinCheckCount(String memberNo) {
		logger.debug("GroupInviteDao - memberGroupJoinCheckCount 실행");
		return sqlSession.selectOne(NS+"memberGroupJoinCheckCount",memberNo);
	}
	
	//가입된 그룹 체크
	public List<GroupInvite> memberGroupJoinCheck(String memberNo) {
		logger.debug("GroupInviteDao - memberGroupJoinCheck 실행");
		return sqlSession.selectList(NS+"memberGroupJoinCheck",memberNo);
	}
	
	//그룹 회원 삭제
	public int deleteGroupMemberAll(String groupName) {
		logger.debug("GroupInviteDao - deleteGroupMemberAll 실행");
		return sqlSession.delete(NS+"deleteGroupMember", groupName);
	}
	//그룹 회원 설문조사 리스트
	public List<GroupCalendar> groupCalendarHealthSurvey(String groupName) {
		logger.debug("GroupInviteDao - groupCalendarHealthSurvey 실행");
		return sqlSession.selectList(NS+"groupCalendarHealthSurvey",groupName);
	}
	
	//그룹 회원 건강검진 리스트
	public List<GroupCalendar> groupCalendarHealthScreen(String groupName) {
		logger.debug("GroupInviteDao - groupCalendarHealthScreen 실행");
		return sqlSession.selectList(NS+"groupCalendarHealthScreen",groupName);
	}
	
	//그룹 회원 복약일정 리스트
	public List<GroupCalendar> groupCalendarMedication(String groupName) {
		logger.debug("GroupInviteDao - groupCalendarMedication 실행");
		return sqlSession.selectList(NS+"groupCalendarMedication",groupName);
	}
	
	//그룹 회원 진료일정 리스트
	public List<GroupCalendar> groupCalendartreatment(String groupName) {
		logger.debug("GroupInviteDao - groupCalendartreatment 실행");
		return sqlSession.selectList(NS+"groupCalendartreatment",groupName);
	}
	
	//회원 그룹초대 취소
	public int groupInviteMemberCancle(String groupInviteNo) {
		logger.debug("GroupInviteDao - groupInviteMemberCancle 실행");
		return sqlSession.delete(NS+"groupInviteMemberCancle", groupInviteNo);
	}
	
	//회원추방
	public int outGroupMember(String memberNo) {
		logger.debug("GroupInviteDao - outGroupMember 실행");
		return sqlSession.delete(NS+"outMember", memberNo);
	}
	//그룹장검색
	public GroupInvite groupRelationGroupCreateMember(String groupName) {
		logger.debug("GroupInviteDao - groupRelationGroupCreateMember 실행");
		return sqlSession.selectOne(NS+"groupRelationGroupCreateMember",groupName);
	}	
	
	//멤버 이름 리스트검색
	public List<GroupInvite> groupRelationMember(String groupName) {
		logger.debug("GroupInviteDao - groupRelationMember 실행");
		List<GroupInvite> list = sqlSession.selectList(NS+"groupRelationMember",groupName);
		return list;
	}	
	
	//멤버 이름 검색
	public GroupInvite memberNameSearch(String groupName) {
		logger.debug("GroupInviteDao - memberNameSearch 실행");
		return sqlSession.selectOne(NS+"memberNameSearch",groupName);
	}	

	//그룹 초대 수락
	public int acceptGroupList(GroupInvite groupInvite) {
		logger.debug("GroupInviteDao - acceptGroupList 실행");
		int row = sqlSession.insert(NS+"acceptGroupList",groupInvite);
		return row;
	}
	
	//그룹 회원리스트   날짜 검색 카운트
	public int groupMembersListSearchDateCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupMembersListSearchDateCount 실행");
		return sqlSession.selectOne(NS+"groupMembersListSearchDateCount",map);
	}
		
	//그룹 회원리스트   날짜검색
	public List<GroupInvite> groupMembersListSearchDate(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupMembersListSearchDate 실행");
		 List<GroupInvite> list = sqlSession.selectList(NS+"groupMembersListSearchDate",map);
		return list;
	}
	
	//그룹 회원리스트  키옵션 검색 카운트
	public int groupMembersListCountSearchKeyoption(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupMembersListCountSearchKeyoption 실행");
		return sqlSession.selectOne(NS+"groupMembersListCountSearchKeyoption",map);
	}
	
	//그룹 회원리스트 키옵션검색
	public List<GroupInvite> groupMembersListSearchKeyoption(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupMembersListSearchKeyoption 실행");
		 List<GroupInvite> list = sqlSession.selectList(NS+"groupMembersListSearchKeyoption",map);
		return list;
	}
	
	//그룹 회원리스트 카운트
	public int groupMemberListCount(String groupName) {
		logger.debug("GroupInviteDao - groupMemberListCount 실행");
		return sqlSession.selectOne(NS+"groupMembersListCount",groupName);
	}		
	
	//그룹 회원 리스트
	public List<GroupInvite> groupMemberList(Map<String, Object> map) {
		logger.debug("GroupInviteDao - groupMemberList 실행");
		 List<GroupInvite> list = sqlSession.selectList(NS+"groupMembersList",map);
		return list;
	}
	
	//그룹초대리스트  날짜 검색 카운트
	public int inviteGroupListSearchDatetotalCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - inviteGroupListSearchDatetotalCount 실행");
		return sqlSession.selectOne(NS+"inviteGroupListSearchDatetotalCount",map);
	}
		
	//그룹초대리스트  날짜검색
	public List<GroupInvite> inviteGroupListSearchDate(Map<String,Object> map) {
		logger.debug("GroupInviteDao - inviteGroupListSearchDate 실행");
		 List<GroupInvite> list = sqlSession.selectList(NS+"inviteGroupListSearchDate",map);
		return list;
	}
	
	//그룹초대리스트  키옵션 검색 카운트
	public int inviteGroupListSearchKeyoptiontotalCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - inviteGroupListSearchKeyoptiontotalCount 실행");
		return sqlSession.selectOne(NS+"inviteGroupListSearchKeyoptiontotalCount",map);
	}
	
	//그룹초대리스트  키옵션검색
	public List<GroupInvite> inviteGroupListSearchKeyoption(Map<String,Object> map) {
		logger.debug("GroupInviteDao - inviteGroupListSearchKeyoption 실행");
		 List<GroupInvite> list = sqlSession.selectList(NS+"inviteGroupListSearchKeyoption",map);
		return list;
	}
	//그룹초대리스트 카운트
	public int inviteGroupListtotalCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - inviteGroupListtotalCount 실행");
		return sqlSession.selectOne(NS+"inviteGroupListtotalCount",map);
	}
	//나를 초대한 그룹리스트
	public List<GroupInvite> inviteGroupList(Map<String, Object> map) {
		logger.debug("GroupInviteDao - inviteGroupList 실행");
		 List<GroupInvite> list = sqlSession.selectList(NS+"inviteGroupList",map);
		return list;
	}	
	
	public GroupInvite personalAgreeSearch(String memberNo) {
		logger.debug("GroupInviteDao - personalAgreeSearch 실행");
		return sqlSession.selectOne(NS+"personalAgreeSearch",memberNo);
	}
	
	public int inviteMember(GroupInvite groupInvite) {
		logger.debug("GroupInviteDao - addInviteMember 실행");
		return sqlSession.insert(NS+"inviteMember",groupInvite);
	}
	
	public int groupInviteNo(String groupInviteNo) {
		logger.debug("GroupInviteDao - groupInviteNo 실행");
		int row = sqlSession.selectOne(NS+"groupInviteNo", groupInviteNo);
		logger.debug("row:"+row);
		return row;
	}
	
	public GroupInvite groupInviteMemberName(String memberId) {
		logger.debug("GroupInviteDao - groupInviteMemberName 실행");
		return sqlSession.selectOne(NS+"groupInviteMemberName",memberId);
	}
		
	public int inviteMemberId(String memberId) {
		logger.debug("GroupInviteDao - inviteMemberId 실행");
		return sqlSession.selectOne(NS+"groupInviteMemberId",memberId);
	}
	
	public int totalCountInviteNo() {
		logger.debug("GroupInviteDao - totalCountInviteNo 실행");
		return sqlSession.selectOne(NS+"totalCountInviteNo");
	}
	
	public int totalCountInvite() {
		logger.debug("GroupInviteDao - totalCountInvite 실행");
		return sqlSession.selectOne(NS+"totalCountInvite");
	}
	
	public GroupInvite groupNameFind(String groupNo) {
		logger.debug("GroupInviteDao - groupNameFind 실행");
		return sqlSession.selectOne(NS+"groupNameFind",groupNo);
	}
	
	//그룹에 초대한 회원리스트 날짜 검색 카운트
	public int groupInviteListSearchDatetotalCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupInviteListSearchDatetotalCount 실행");
		return sqlSession.selectOne(NS+"groupInviteListSearchDatetotalCount",map);
	}
		
	//그룹에 초대한 회원리스트 날짜검색
	public List<GroupInvite> groupInviteListSearchDate(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupInviteSearchDateList 실행");
		 List<GroupInvite> list = sqlSession.selectList(NS+"groupInviteListSearchDate",map);
		return list;
	}
	
	//그룹에 초대한 회원리스트 키옵션 검색 카운트
	public int groupInviteListSearchKeyoptiontotalCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupInviteListSearchKeyoptiontotalCount 실행");
		return sqlSession.selectOne(NS+"groupInviteListSearchKeyoptiontotalCount",map);
	}
	
	//그룹에 초대한 회원리스트 키옵션검색
	public List<GroupInvite> groupInviteListSearchKeyoption(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupInviteListSearchKeyoption 실행");
		 List<GroupInvite> list = sqlSession.selectList(NS+"groupInviteListSearchKeyoption",map);
		return list;
	}
	
	//그룹에 초대한 회원리스트 카운트
	public int groupInviteListtotalCount(String groupNo) {
		logger.debug("GroupInviteDao - groupInviteListtotalCount 실행");
		return sqlSession.selectOne(NS+"groupInviteListtotalCount",groupNo);
	}
	//그룹에 초대한 회원리스트
	public List<GroupInvite> groupInviteList(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupInviteList 실행");
		 List<GroupInvite> list = sqlSession.selectList(NS+"groupInviteList",map);
		return list;
	}	
}
