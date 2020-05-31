<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
	</head>
	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="topheader">
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<form action="/book/save" method="post">
						<table cellpadding="0" cellspacing="0" border="0" class="form_table">
							<tr>
								<td valign="middle" align="right">
									图书名称:
								</td>	
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="bookName" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									图书类型:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="bookType" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									图书编号（字母＋数字）:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="bookNum" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									作者:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="author" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									图书价格:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="bookPrice" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									图书出版社:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="press" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									状态（缺货、正常）:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="state" list="typelist" placeholder="请选择"/>
									<datalist id="typelist">
									　　<option value="1">缺货</option>
									　　<option value="2">正常</option>
									</datalist>
								</td>

							</tr>
							<tr>
								<td valign="middle" align="right">
									图书数量:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="count" />
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="提交" />
						</p>
					</form>
				</div>
			</div>
		</div>	
	</body>
</html>