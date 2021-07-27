package com.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.model.MessageService;

@WebServlet("/post/postActionServlet")
public class PostActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
  
    public PostActionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		String action = request.getParameter("action");
		if("hotPost".equals(action)) {
			MessageService messageService = new MessageService();
			List postList = messageService.message_count_sort();

			ObjectMapper mapper = new ObjectMapper();
			String writeValueAsString = mapper.writeValueAsString(postList); //轉JSON格式
			PrintWriter out = response.getWriter();
			out.print(writeValueAsString);
			
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
