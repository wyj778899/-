<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>emplist</title>
		<link rel="stylesheet" type="text/css" href="../css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<div id="header">
					<div id="rightheader">
						<p>
							2009/11/20
							<br />
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="#">main</a>
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
						
					<h1>
						
						Welcome! ${sessionScope.user.name }
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								编号
							</td>
							<td>
								姓名
							</td>
							<td>
								Operation(删除部门员工一并删除)
							</td>
						</tr>
					<c:forEach items="${list}" var="departments">	
						<tr class="row1">
							<td>
								${departments.getId()}
							</td>
							<td>
								${departments.getName()}
							</td>
							<td>
								<a href="/car/remove?id=${departments.getId()}">delete emp</a>&nbsp;<a href="/car/queryById?id=${departments.getId()}">update emp</a>&nbsp;<a href="/emp/query?page=1&depName=${departments.getName()}">show emps</a>
							</td>
						</tr>
					</c:forEach>
					</table>
					<p>
						<a href="/add.jsp">adddep</a>
					</p>
				</div>
			</div>
		</div>
	</body>
</html>