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

       

    public PostServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		PostService postService = new PostService();
		List post = postService.getPost();
		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(post);
		PrintWriter out = response.getWriter();
		out.print(writeValueAsString);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
