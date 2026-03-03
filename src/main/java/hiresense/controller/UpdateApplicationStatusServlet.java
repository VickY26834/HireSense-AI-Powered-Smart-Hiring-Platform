package hiresense.controller;

import java.io.IOException;

import hiresense.dao.ApplicationDao;
import hiresense.dao.JobDao;
import hiresense.dao.UserDao;
import hiresense.pojo.JobPojo;
import hiresense.pojo.UserPojo;
import hiresense.utils.MailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class UpdateApplicationStatusServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null ||
            !"employer".equals(session.getAttribute("userRole"))) {
            response.sendRedirect("login.jsp");
            return;
        }
        
       try {
    	   int appId = Integer.parseInt(request.getParameter("appId"));
           String newStatus = request.getParameter("status");
           int jobId = Integer.parseInt(request.getParameter("jobId"));
           
           if (!newStatus.equals("shortlisted") && !newStatus.equals("rejected")) {
               response.sendRedirect("ViewApplicantsServlet?jobId=" + jobId + "&error=invalid_status");
               return;
           }
           boolean updated=ApplicationDao.updateApplicationStatus(appId, newStatus);
           if (updated) {
           	UserPojo user = UserDao.getUserById(Integer.parseInt(session.getAttribute("userId").toString().trim()));
           	JobPojo job = JobDao.getJobById(jobId);
           	MailUtil.sendApplicationStatusUpdate(user.getName(),user.getEmail(),job.getTitle(),job.getCompany(),newStatus);

               response.sendRedirect("ViewApplicantsServlet?jobId=" + jobId + "&status=" + newStatus);
           } else {
               response.sendRedirect("ViewApplicantsServlet?jobId=" + jobId + "&error=update_failed");
           }
       }catch (Exception e) {
           e.printStackTrace();
           response.sendRedirect("error.jsp");
       }
	}

	

}
