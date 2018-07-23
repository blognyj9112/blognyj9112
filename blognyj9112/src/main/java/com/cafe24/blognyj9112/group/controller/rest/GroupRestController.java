package com.cafe24.blognyj9112.group.controller.rest;

import java.io.IOException;
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

import com.cafe24.blognyj9112.group.service.GroupInviteService;
import com.cafe24.blognyj9112.group.service.GroupService;
import com.google.gson.Gson;

@RestController
public class GroupRestController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupInviteService groupInviteService;
	private static final Logger logger = LoggerFactory.getLogger(GroupRestController.class);

	//그룹 캘린더
	@RequestMapping(value="/groupCalendarList", method=RequestMethod.POST)
	@ResponseBody
	public void groupCalendar(HttpServletResponse response,@RequestParam(value="groupName") String groupName) {
		logger.debug("GroupRestController - groupCalendarList ajax 실행");
		logger.debug("groupName:"+groupName);
		Map<String,Object> map = groupInviteService.groupCalendarList(groupName);
		Gson gson = new Gson();
		String json = "";
		json = gson.toJson(map);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//그룹 종류 선택리스트
	@RequestMapping(value="/groupKindListSelect", method=RequestMethod.POST)
	@ResponseBody
	public void groupKindListSelect(HttpServletResponse response) {
		logger.debug("GroupRestController - groupKindListSelect ajax 실행");
		Map<String,Object> map = groupService.groupKindListSelect();
		Gson gson = new Gson();
		String json = "";
		json = gson.toJson(map);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//그룹 관계도
	@RequestMapping(value="/groupMemberRelationChart", method=RequestMethod.POST)
	@ResponseBody
	public void groupMemberRelationChart(HttpServletResponse response,@RequestParam(value="groupName") String groupName) {
		logger.debug("GroupRestController - groupMemberRelationChart ajax 실행");
		logger.debug("groupName:"+groupName);
		Map<String,Object> map = groupInviteService.groupRelationChart(groupName);
		logger.debug("createMemb:"+map.get("createMemb"));
		logger.debug("groupRelationMember:"+map.get("groupRelationMember"));
		Gson gson = new Gson();
		String json = "";
		json = gson.toJson(map);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//그룹 삭제유예기간 등록 검색
	@RequestMapping(value="/deleteGroupSearch", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> memberCountSearch(@RequestParam(value="groupName") String groupName) {
		logger.debug("GroupRestController - deleteApproval ajax 실행.");
		Map<String,Object> resultmap = groupService.memberCountSearch(groupName);
		logger.debug("resultmap:"+resultmap);
		return resultmap;
	}
	
	//회원 아이디검색
	@RequestMapping(value="/invitefind", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchMember(@RequestParam(value="memberId") String memberId) {
		logger.debug("GroupRestController - SearchMemberForm ajax 실행");
		logger.debug("memberId:"+memberId);
		Map<String,Object> map = groupInviteService.invitefind(memberId);
		logger.debug("result:"+map.get("result"));
		logger.debug("name:"+map.get("name"));
		return map;
	}
	
	//그룹명 중복확인
	@RequestMapping(value="/checkGroupName", method=RequestMethod.GET)
	@ResponseBody
    public Map<String, Object> checkGroupName(@RequestParam(value="groupName") String groupName) {
		logger.debug("GroupRestController - checkGroupName ajax 실행");
		logger.debug("groupName:"+groupName);
		Map<String,Object> map = groupService.checkGroupName(groupName);
		logger.debug("result:"+map.get("result"));
		return map;
    }
}
