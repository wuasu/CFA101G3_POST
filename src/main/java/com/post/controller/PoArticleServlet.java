package com.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemVO;
import com.post.model.PostService;
import com.post.model.PostVO;

@WebServlet("/post/poArticleServlet")
public class PoArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//接收作者的發文(標題.內容...並存回資料庫
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 取得參數(ajax傳送來的需求)
		Integer post_cat_id = Integer.parseInt(request.getParameter("post_cat_id"));
		String post_title = request.getParameter("post_title").trim();
		String post_content = request.getParameter("post_content").trim();
		
		Date post_time = new Date(new java.util.Date().getTime());

//		HttpSession session = request.getSession();
//		MemVO user = (MemVO) session.getAttribute("user");
//		Integer post_mem_id = user.getMem_id();
		//*******測試 暫時將會員寫死*******//
		Integer post_mem_id = 2; 
		//*******測試 暫時將會員寫死*******//


		// 調用Service與方法
		PostService postService = new PostService();
		PostVO postVo = postService.addPost(post_title, post_content, post_time, post_cat_id, post_mem_id);
		response.getWriter().print("success");//若成功送到資料庫會出現的字

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
