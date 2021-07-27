package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemService;
import com.member.model.MemVO;


@WebServlet("/member/memServlet")
public class MemServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	String action = request.getParameter("action");
    	//取得買家會員個人資料(分頁顯示)
    	if("getMemberVO".equals(action)) {
    		String mem_idStr = request.getParameter("mem_id");
    		if(mem_idStr != null) {
    			int mem_id = Integer.parseInt(mem_idStr);
    			MemService memService = new MemService();
    			MemVO memVO = memService.getOne(mem_id);
    			ObjectMapper mapper = new ObjectMapper();
    			String json = mapper.writeValueAsString(memVO);
    			response.getWriter().print(json);
    		}
    	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
