package category.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.model.CategoryImpl;
import category.model.CategoryVO;

@WebServlet("/testServelt")
public class TestServlet extends HttpServlet {

       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryImpl categoryImpl = new CategoryImpl();
		CategoryVO findByPrimaryKey = categoryImpl.findByPrimaryKey(2);
		System.out.println(findByPrimaryKey);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
