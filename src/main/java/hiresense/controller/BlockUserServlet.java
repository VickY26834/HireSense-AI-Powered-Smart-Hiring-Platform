package hiresense.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import hiresense.dao.UserDao;


public class BlockUserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("userId")==null||!"admin".equals((String)session.getAttribute("userRole"))){
			response.sendRedirect("login.jsp");
			return;
		}
		int userId=Integer.parseInt(request.getParameter("userId"));
		try {
			UserDao.updateStatus(userId, "blocked");
			response.sendRedirect("AdminPanelServlet");
		}catch(Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("AdminPanelServlet?error=1");
		}
		
	}

	

}
