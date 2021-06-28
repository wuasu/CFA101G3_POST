package com.category.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.category.model.CategoryImplXX;
import com.category.model.CategoryVO;

@WebServlet("/testServelt")
public class TestServlet extends HttpServlet {

       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryImplXX categoryImpl = new CategoryImplXX();
		CategoryVO findByCatId = categoryImpl.findByCatId(2);
		System.out.println(findByCatId);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
