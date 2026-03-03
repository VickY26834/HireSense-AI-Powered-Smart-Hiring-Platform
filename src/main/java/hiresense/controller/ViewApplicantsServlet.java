package hiresense.controller;

import java.io.IOException;
import java.util.List;

import hiresense.dao.ApplicationDao;
import hiresense.dao.JobDao;
import hiresense.pojo.ApplicationPojo;
import hiresense.pojo.JobPojo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewApplicantsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("userId")==null||!"employer".equals((String)session.getAttribute("userRole"))) {
			response.sendRedirect("login.jsp");
			return;
		}
		try {
			int jobId=Integer.parseInt(request.getParameter("jobId"));
			String status=request.getParameter("status")!=null?request.getParameter("status"):"applied";
			
			//Fetch job details
			JobPojo job= JobDao.getJobById(jobId);
			if(job==null) {
				response.sendRedirect("EmployerDasboardServlet?error=InvalidJob");
				return;
			}
		  List<ApplicationPojo>list=ApplicationDao.getApplicationByJobAndStatus(jobId, status);
		  
			request.setAttribute("job",job );
			request.setAttribute("applicants", list);
			request.setAttribute("status", status);
			RequestDispatcher rd=request.getRequestDispatcher("ViewApplicants.jsp");
			rd.forward(request, response);
		}catch(Exception ex) {
			throw new ServletException("Unable to fetch applicants or job details");
		}
	}

	

}
