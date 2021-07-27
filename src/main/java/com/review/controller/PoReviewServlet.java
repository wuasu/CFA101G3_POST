package com.review.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemVO;
import com.review.model.ReviewService;

@WebServlet("/review/PoReviewServlet")
public class PoReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PoReviewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 接收參數(ajax傳來的)
		Integer smem_id = Integer.parseInt(request.getParameter("smem_id"));//取得賣家身份id
		Integer rev_score = Integer.parseInt(request.getParameter("rev_score"));//取的星數
		String rev_content = request.getParameter("rev_content").trim();//取得評價內容
		Timestamp rev_time = new Timestamp(new java.util.Date().getTime());//取得評價時間

		// 取得買方會員身份
		HttpSession session = request.getSession();
		MemVO user = (MemVO) session.getAttribute("user");
        Integer bmem_id = user.getMem_id();
		
		// *******測試 暫時固定買方會員*******//
//        Integer bmem_id = 4;
		// *******測試 暫時固定買方會員*******//
        
        
		List<String> errorMsgs = new LinkedList<>();
		ObjectMapper mapper = new ObjectMapper(); 	
		request.setAttribute("errorMsgs",errorMsgs);
		
		if(rev_score == null || rev_score == 0) {
			errorMsgs.add("*請給星數");
		}
		
		if (rev_content == null || rev_content.length() == 0) {
			errorMsgs.add("*請輸入評價內容");
		}
		
		// Send the use back to the form, if there were er nrors
		if (!errorMsgs.isEmpty()) {
			response.setStatus(401);
			String Msg = mapper.writeValueAsString(errorMsgs);
			response.getWriter().print(Msg);
			return;
		}
        

        
        
		//調用Service方法
		ReviewService messageService = new ReviewService();
		messageService.addReview(smem_id,bmem_id,rev_content,rev_time,rev_score);
		response.getWriter().print("評價成功送出");//成功送到資料庫
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
