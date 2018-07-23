package com.cafe24.blognyj9112.group.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDao{
	private static final Logger logger = LoggerFactory.getLogger(GroupDao.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	final String NS = "com.cafe24.blognyj9112.group.service.GroupMapper.";

	//그룹종류 리스트 선택용
	public List<Group> groupKindListSelect() {
		logger.debug("GroupDao - groupKindListSelect 실행");
		 List<Group> list = sqlSession.selectList(NS+"groupKindListSelect");
		return list;
	}
	//그룹종류번호 카운트
	public int groupKindNo() {
		logger.debug("GroupInviteDao - groupKindNo 실행");
		return sqlSession.selectOne(NS+"groupKindNo");
	}
	//그룹종류 번호 선택
	public Group groupKindNoSelect(String groupKindName) {
		logger.debug("GroupInviteDao - groupKindNoSelect 실행");
		return sqlSession.selectOne(NS+"groupKindNoSelect",groupKindName);
	}
	
	//그룹종류업데이트
	public int updateGroupKind(Group group) {
		logger.debug("Group - updateGroupKind 실행");
		return sqlSession.update(NS+"updateGroupKind",group);
	}
	
	//그룹종류 리스트 카운트
	public int groupKindListTotalCount() {
		logger.debug("GroupInviteDao - groupKindListTotalCount 실행");
		return sqlSession.selectOne(NS+"groupKindListTotalCount");
	}
	//그룹종류 리스트
	public List<Group> groupKindList(Map<String,Integer> map) {
		logger.debug("GroupDao - groupKindList 실행");
		 List<Group> list = sqlSession.selectList(NS+"groupKindList",map);
		return list;
	}
	
	//그룹 종류 등록
	public int addGroupKind(Group group) {
		logger.debug("GroupDao - addGroupKind 실행");
		return sqlSession.insert(NS+"addGroupKind", group);
	}
	
	//그룹삭제리스트키옵션검색 카운트
	public int groupdeleteSearchKeyoptionCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupdeleteSearchKeyoptionCount 실행");
		return sqlSession.selectOne(NS+"groupdeleteSearchKeyoptionCount",map);
	}
	
	//그룹삭제리스트키옵션검색
	public List<Group> deleteGroupListSearchKeyoption(Map<String,Object> map) {
		logger.debug("GroupDao - deleteGroupListSearchKeyoption 실행");
		 List<Group> list = sqlSession.selectList(NS+"deleteGroupListSearchKeyoption",map);
		return list;
	}
	
	//그룹삭제리스트날짜검색 카운트
	public int groupdeleteSearchDateCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupdeleteSearchDateCount 실행");
		return sqlSession.selectOne(NS+"groupdeleteSearchDateCount",map);
	}
	
	//그룹삭제리스트날짜검색
	public List<Group> deleteGroupListSearchDate(Map<String,Object> map) {
		logger.debug("GroupDao - deleteGroupListSearchDate 실행");
		 List<Group> list = sqlSession.selectList(NS+"deleteGroupListSearchDate",map);
		return list;
	}
	
	//그룹 삭제 리스트 카운트
	public int groupdeleteSelectCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupdeleteSelectCount 실행");
		return sqlSession.selectOne(NS+"groupdeleteSelectCount",map);
	}
	//그룹리스트키옵션검색 카운트
	public int groupListSearchKeyoptionCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupListSearchKeyoptionCount 실행");
		return sqlSession.selectOne(NS+"groupListSearchKeyoptionCount",map);
	}
	
	//그룹리스트키옵션검색
	public List<Group> groupListSearchKeyoption(Map<String,Object> map) {
		logger.debug("GroupDao - groupListSearchKeyoption 실행");
		 List<Group> list = sqlSession.selectList(NS+"groupListSearchKeyoption",map);
		return list;
	}
	
	//그룹리스트날짜검색 카운트
	public int groupListSearchDateCount(Map<String,Object> map) {
		logger.debug("GroupInviteDao - groupListSearchDateCount 실행");
		return sqlSession.selectOne(NS+"groupListSearchDateCount",map);
	}
	
	//그룹리스트날짜검색
	public List<Group> groupListSearchDate(Map<String,Object> map) {
		logger.debug("GroupDao - groupListSearchDate 실행");
		 List<Group> list = sqlSession.selectList(NS+"groupListSearchDate",map);
		return list;
	}
	
	//그룹에 가입된 회원 리스트
	public List<Group> groupMemberList(String groupNo) {
		logger.debug("GroupInviteDao - groupMemberList 실행");
		return sqlSession.selectList(NS+"groupMemberList",groupNo);
	}
	
	//생성한 그룹 체크 카운트(그룹메인)
	public int memberGroupCreateCheckCount(String memberNo) {
		logger.debug("GroupInviteDao - memberGroupCreateCheckCount 실행");
		return sqlSession.selectOne(NS+"memberGroupCreateCheckCount",memberNo);
	}
	
	//생성한 그룹 체크(그룹메인)
	public List<Group> memberGroupCreateCheck(String memberNo) {
		logger.debug("GroupInviteDao - memberGroupCreateCheck 실행");
		return sqlSession.selectList(NS+"memberGroupCreateCheck",memberNo);
	}
	
	//그룹이름검색
	public Group groupNameSearch(String groupNo) {
		logger.debug("GroupInviteDao - groupNameSearch 실행");
		return sqlSession.selectOne(NS+"groupNameSearch",groupNo);
	}	
	//회원 번호검색
	public Group memberNoCreateCheck(String memberId) {
		logger.debug("GroupDao - memberNoCreateCheck 실행");
		return sqlSession.selectOne(NS+"memberNoCreateCheck",memberId);
	}
	
	//그룹 번호 검색
	public Group groupNoSearch(String groupName) {
		logger.debug("GroupDao - groupNoSearch 실행");
		return sqlSession.selectOne(NS+"groupNoSearch",groupName);
	}
	
	//그룹 상세 이전글 카운트
	public int prevGroupDetailCount(Map<String,Object> map) {
		logger.debug("GroupDao - prevGroupDetailCount 실행");
		return sqlSession.selectOne(NS+"prevGroupDetailCount",map);
	}		
	
	//그룹 상세 다음글 카운트
	public int nextGroupCount(Map<String,Object> map) {
		logger.debug("GroupDao - nextGroupCount 실행");
		return sqlSession.selectOne(NS+"nextGroupCount",map);
	}		
	
	//그룹 상세 다음글 
	public Group nextGroupDetail(Map<String,Object> map) {
		logger.debug("GroupDao - nextGroupDetail 실행");
		return sqlSession.selectOne(NS+"nextGroupDetail",map);
	}
	
	//그룹 상세 이전글
	public Group prevGroupDetail(Map<String,Object> map) {
		logger.debug("GroupDao - prevGroupDetail 실행");
		return sqlSession.selectOne(NS+"prevGroupDetail",map);
	}
	
	public List<Group> deleteGroupList(Map<String,Object> map) {
		logger.debug("GroupDao - deleteGroupList 실행");
		 List<Group> list = sqlSession.selectList(NS+"deleteGroupList",map);
		return list;
	}
	
	public int groupDeleteCheckMemberCount(String groupName) {
		logger.debug("GroupDao - groupDeleteCheckMemberCount 실행");
		return sqlSession.selectOne(NS+"groupDeleteCheckMemberCount",groupName);
	}
	
	public Group groupDdayNo(String groupExpiredDate) {
		logger.debug("GroupDao - groupDdayNo 실행");
		return sqlSession.selectOne(NS+"groupDdayNo", groupExpiredDate);
	}
	
	public Group groupDeleteCheckgroupNo(String groupNo) {
		logger.debug("GroupDao - groupDeleteCheckgroupNo 실행");
		return sqlSession.selectOne(NS+"groupDeleteCheckgroupNo", groupNo);
	}
		
	public Group groupDdaycheck() {
		logger.debug("GroupDao - groupDdaycheck 실행");
		return sqlSession.selectOne(NS+"groupDdaycheck");
	}
	
	public int groupdeleteCount() {
		logger.debug("GroupDao - groupdeleteCount 실행");
		return sqlSession.selectOne(NS+"groupdeleteCount");
	}
		
	public int deleteApproval(String groupNo) {
		logger.debug("GroupDao - deleteApproval 실행");
		return sqlSession.insert(NS+"deleteApproval", groupNo);
	}
	
	public int deleteGroupDelete(String groupNo) {
		logger.debug("GroupDao - deleteGroupDelete 실행");
		return sqlSession.delete(NS+"deleteGroupDelete", groupNo);
	}
	
	public int deleteGroup(String groupNo) {
		logger.debug("GroupDao - deleteGroup 실행");
		return sqlSession.delete(NS+"deleteGroup", groupNo);
	}
	
	public int modifyGroupResult(Group group) {
		logger.debug("Group - modifyGroupResult 실행");
		return sqlSession.update(NS+"modifyGroupResult",group);
	}
	
	public Group modifyGroup(String groupNo) {
		logger.debug("Group - modifyGroup 실행");
		return sqlSession.selectOne(NS+"modifyGroup",groupNo);
	}
	
	public List<Group> groupList(Map<String,Object> map) {
		logger.debug("GroupDao - groupList 실행");
		 List<Group> list = sqlSession.selectList(NS+"groupList",map);
		return list;
	}	
	
	public int checkGroupNoCount(String memberNo) {
		logger.debug("GroupDao - checkGroupNoCount 실행");
		return sqlSession.selectOne(NS+"checkGroupNoCount",memberNo);
	}
	
	public int checkGroupNameCount(String groupName) {
		logger.debug("GroupDao - checkGroupNameCount 실행");
		return sqlSession.selectOne(NS+"checkGroupNameCount",groupName);
	}
	
	public int groupListCount(Map<String,Object> map) {
		logger.debug("GroupDao - groupTotalCount 실행");
		return sqlSession.selectOne(NS+"groupListCount");
	}
	
	public int groupTotalCount() {
		logger.debug("GroupDao - groupTotalCount 실행");
		return sqlSession.selectOne(NS+"groupCount");
	}
	
	public int selectGroupNo() {
		logger.debug("GroupDao - selectGroupNo 실행");
		return sqlSession.selectOne(NS+"groupNo");
	}
	public int addGroup(Group Group) {
		logger.debug("GroupDao - addGroup 실행");
		int row = sqlSession.insert(NS+"addGroup", Group);
		return row;
	}
}
	