package hiresense.controller;

import java.io.IOException;
import java.util.List;

import hiresense.dao.ResumeAnalysisLogDAO;
import hiresense.pojo.ResumeAnalysisLogsPojo;
import hiresense.utils.AffindaAPI;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ResumeDetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            List<ResumeAnalysisLogsPojo> logs = ResumeAnalysisLogDAO.getLogsByUser(userId);
            if (logs.isEmpty()) {
                request.setAttribute("error", "No analysis found for this user.");
            } else {
                ResumeAnalysisLogsPojo latest = logs.get(0);
                String resultJson = latest.getJsonResult();
                request.setAttribute("summary", AffindaAPI.extractSummary(resultJson));
                request.setAttribute("skillsList", AffindaAPI.extractSkills(resultJson));
                request.setAttribute("personalDetails", AffindaAPI.extractPersonalDetails(resultJson));
                request.setAttribute("education", AffindaAPI.extractEducation(resultJson));
                request.setAttribute("workExperience", AffindaAPI.extracteWorkExperience(resultJson));

            }
            request.getRequestDispatcher("resumeDetails.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error fetching resume details", e);
        }
		
	}

	

}
