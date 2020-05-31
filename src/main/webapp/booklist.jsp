<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>booklist</title>
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
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<table class="table" border="1px">
						<tr class="table_header">
							<td>
								图书名称
							</td>
							<td>
								图书类型
							</td>
							<td>
								图书编号（字母＋数字）
							</td>
							<td>
								作者
							</td>
							<td>
								图书价格
							</td>
							<td>
								图书出版社
							</td>
							<td>
								状态（缺货、正常）
							</td>
							<td>
								图书数量
							</td>
							<td>
								Operation(处理删除的友情提醒)
							</td>
						</tr>
						<c:forEach items="${list}" var="BookInfo">
						<tr class="row1">
							<td>
								${BookInfo.getBookName()}
							</td>
							<td>
								${BookInfo.getBookType()}
							</td>
							<td>
								${BookInfo.getBookNum()}
							</td>
							<td>
								${BookInfo.getAuthor()}
							</td>
							<td>
								${BookInfo.getBookPrice()}
							</td>
							<td>
								${BookInfo.getPress()}
							</td>
							<td>
								${BookInfo.getState()}
							</td>
							<td>
								${BookInfo.getCount()}
							</td>
							<td>
								 <a href="/book/remove?id=${BookInfo.getBookId()}">删除</a>&nbsp;
								 <a href="/book/update?id=${BookInfo.getBookId()}">更新</a> 
							</td>
						</tr>
						</c:forEach>
					</table>
					<p>
						<a href="/add.jsp">添加</a>
					</p>
				</div>
			</div>
		</div>
	</body>
</html>