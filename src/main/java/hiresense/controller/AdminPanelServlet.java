package hiresense.controller;

import java.io.IOException;
import java.util.List;

import hiresense.dao.JobDao;
import hiresense.dao.UserDao;
import hiresense.pojo.JobPojo;
import hiresense.pojo.UserPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class AdminPanelServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("userId")==null||!"admin".equals((String)session.getAttribute("userRole"))){
			response.sendRedirect("login.jsp");
			return;
		}
		String search=request.getParameter("search");
		String role=request.getParameter("role");
		String status=request.getParameter("status");
		
		try {
			List<UserPojo> users=UserDao.getFilteredUsers(search, role, status);
			List<JobPojo> jobs=JobDao.getAllJobsWithEmployerAndApplicantCount();
			request.setAttribute("users", users);
			request.setAttribute("jobs", jobs);
			
			request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	

}
