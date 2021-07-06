package com.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.post.model.PostService;


@WebServlet("/post/postServlet")
public class PostServlet extends HttpServlet {     
//撈出後端的文章資訊送到前端
	
//	getPost指令：
//	"select p.POST_ID,p.POST_TITLE,p.POST_CONTENT,p.POST_TIME, c.CAT_NAME,m.MEM_NAME,m.MEM_HEADSHOT,p.POST_STATUS from MEMBER m "
//	+ "join POST p on m.MEM_ID = p.POST_MEM_ID "
//	+ "join CATEGORY c on p.POST_CAT_ID = c.CAT_ID";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		PostService postService = new PostService();
		List post = postService.getPost();
		
		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(post); //轉JSON格式
		PrintWriter out = response.getWriter();
		out.print(writeValueAsString);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
