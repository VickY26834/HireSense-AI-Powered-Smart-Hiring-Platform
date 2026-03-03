<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employer DashBoard |<%=application.getAttribute("appName")%></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<%@ include file="includes/header.jsp"%>
	<%
	//String userId=(String)session.getAttribute("userId");
	if (session == null || session.getAttribute("userId") == null
			|| !"employer".equals((String) session.getAttribute("userRole"))) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>
	<main class="container py-5">
		<h2 class="mb-4">
			Welcome,
			<%=session.getAttribute("userName")%>👔
		</h2>
		<div class="p-4 mb-4 job-form-card">
			<h5>Post a new Job</h5>
			<form action="PostJobServlet" method="post">
				<div class="mb-3">
					<input type="text" class="form-control" placeholder="Job Title"
						name="title" required />
				</div>
				<div class="mb-3">
					<textarea name="description" class="form-control"
						placeholder="Job Description" required id=""></textarea>
				</div>
				<div class="mb-3">
					<input type="text" class="form-control"
						placeholder="Required Skills(comma separated)" name="skills"
						required />
				</div>
				<div class="mb-3">
					<input type="text" name="company" class="form-control"
						placeholder="Company Name" required />
				</div>
				<div class="mb-3">
					<select name="location" class="form-select">
						<option value="" disabled selected>Select Location</option>
						<option value="banglore">Banglore</option>
						<option value="mumbai">Mumbai</option>
						<option value="pune">Pune</option>
						<option value="delhi">Delhi</option>
						<option value="chennai">Chennai</option>
						<option value="hyderabad">Hyderabad</option>
						<option value="kolkata">Kolkata</option>
					</select>
				</div>
				<div class="mb-3">
					<select name="experience" class="form-select" id="">
						<option value="" disabled selected>Experience Level</option>
						<option value="fresher">Fresher</option>
						<option value="1-3 years">1-3 years</option>
						<option value="3-5 years">3-5 years</option>
						<option value="5+ years">5+ years</option>
					</select>
				</div>
				<div class="mb-3">
					<select name="packageLpa" class="form-select" id="">
						<option value="" disabled selected>Package (LPA)</option>
						<option value="1-2 LPA">1-2 LPA</option>
						<option value="3-4 LPA">3-4 LPA</option>
						<option value="4-5 LPA">4-5 LPA</option>
						<option value="5+ LPA">5+ LPA</option>
					</select>
				</div>
				<div class="mb-3">
					<input type="number" name="vacancies" class="form-control"
						placeholder="Number of vacancies" required />
				</div>
				<button type="submit" class="btn btn-login">Post Job</button>
			</form>
		</div>

		<!-- search and filter job -->
		<form action="EmployerDashboardServlet" method="get">
			<div class="row g-2">
				<div class="col-md-4">
					<input type="text" name="search" class="form-control"
						placeholder="Search by title" value="${param.search}"  />
				</div>
				<div class="col-md-3">
					<select name="status" class="form-select">
						<option value="" disabled selected>Filter by Status</option>
						<option value="active" ${param.status=='active'?'selected':'' }>Active</option>
						<option value="inactive"
							${param.status=='inactive'?'selected':'' }>Inactive</option>
					</select>
				</div>
				<div class="col-md-3">
					<select name="sort" class="form-select">
						<option value="" disabled selected>Sort by Applicants</option>
						<option value="asc" ${param.sort=='asc'?'selected':'' }>Least
							to most</option>
						<option value="desc" ${param.sort=='desc'?'selected':'' }>Most
							to least</option>
					</select>
				</div>
				<div class="col-md-2">
					<button type="submit" class="btn btn-login">Search</button>
				</div>
			</div>
		</form>
		<!-- search and filter job ends-->
		<%java.util.List<hiresense.pojo.JobPojo>jobList=(java.util.List<hiresense.pojo.JobPojo>)request.getAttribute("jobList");
		  if(jobList!=null&&!jobList.isEmpty()){
		%>
		<div class="card bg-glass p-4 m-4">
			<h5><%=jobList.get(0).getCompany()%>'s Posted Job
			</h5>
			<table class="table text-white mt-3">
				<thead>
					<tr>
						<th>Job Title</th>
						<th>Applicants</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				  </thead>
				<tbody>
					<%
					for (hiresense.pojo.JobPojo pojo : jobList) {
					%>
					<tr>
						<td><%=pojo.getTitle()%></td>
						<td><%=pojo.getApplicantCount()%></td>
						<td><%=pojo.getStatus().toUpperCase()%></td>
						<td><a href="ViewApplicantsServlet?jobId=<%=pojo.getId()%>"
							class="btn btn-sm btn-info">View</a> <a
							href="ToggleJobStatusServlet?jobId=<%=pojo.getId()%>"
							class="btn btn-sm <%="active".equals(pojo.getStatus()) ? "btn-warning" : "btn-success"%>"><%="active".equals(pojo.getStatus()) ? "Deactivate" : "Activate"%></a>
						</td>
					</tr>

					<%
					}
					%>
				</tbody>
			</table>
		</div>

		<%
		} else {
		%>
		<p class="text-center">No Jobs Posted</p>
		<%
		}
		%>
	</main>
	<%
	String success = request.getParameter("success");
	if ("1".equals(success)) {
	%>
	<script>
  Swal.fire({
	  title: "Job Posted!",
	  text: "Your Job has been successfully posted!",
	  time:2000,
	  icon: "success"
	  showConfirmButton:false
	});
  </script>
	<%
	}
	%>

	<%
	String error = request.getParameter("error");
	if ("1".equals("error")) {
	%>
	<script>
  Swal.fire({
	  title: "Failed!",
	  text: "Something went wrong! Please try again",
	  icon: "error"
	  confirmButtonText:"Okay"
	});
  </script>
	<%
	}
	%>

	<%@ include file="includes/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
		crossorigin="anonymous"></script>
</body>
</html>