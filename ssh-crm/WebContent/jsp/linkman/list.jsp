<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>联系人列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>

function changePage(pageNum) {
	$('#currentPageInput').val(pageNum);
	$('#pageForm').submit();
};

function changePageSize(pageSize) {
	$('#pageSizeInput').val(pageSize);
	$('#pageForm').submit();
};
	
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	
		
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：联系人管理 &gt; 联系人列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
								<FORM id="pageForm" name="customerForm"
									action="${pageContext.request.contextPath }/LinkmanAction-list"
									method=post>
									
									<input type="hidden" name="currentPage" id="currentPageInput" value="<s:property value="#linkmanPageBean.currentPage"/>" />
									<input type="hidden" name="pageSize"  id="pageSizeInput" value="<s:property value="#linkmanPageBean.pageSize"/>" />
									
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>联系人名称：</TD>
													<TD><INPUT class=textbox id=sChannel2
														style="WIDTH: 80px" maxLength=50 name="lkm_name" value="${param.lkm_name}"></TD>
													<TD>客户名称：</TD>
													<TD>
													<INPUT id="cust_id" type="hidden" name="customer.cust_id" value="${param['customer.cust_id']}">
													<INPUT class=textbox id="cust_name"  style="WIDTH: 80px" maxLength=50 name="customer.cust_name" value="${param['customer.cust_name']}">
													<input type="button"  value="选择客户" onclick="window.open('${pageContext.request.contextPath}/CustomerAction-list?select=true','','width=600,height=300')"/>
													</TD>
													
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
										</FORM>	
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>联系人名称</TD>
													<TD>性别</TD>
													<TD>办公电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<s:iterator value="#linkmanPageBean.list">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD><s:property value="lkm_name"/></TD>
													<TD><s:if test="lkm_gender=='1'">男</s:if>
														<s:else>女</s:else>
													</TD>
													<TD>${lkm_phone }</TD>
													<TD>${lkm_mobile }</TD>
													
													<TD>
													<a href="${pageContext.request.contextPath }/LinkmanAction-toEdit?lkm_id=<s:property value="lkm_id"/>">修改</a>
													&nbsp;&nbsp;
													<a href="${pageContext.request.contextPath }/linkmanServlet?method=delete&lkmId=${linkman.lkmId}">删除</a>
													</TD>
												</TR>
												</s:iterator>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${linkmanPageBean.totalCount}</B>]条记录,[<B>${linkmanPageBean.totalPage}</B>]页
												,每页显示
												<select name="pageSize" onchange="changePageSize($('#pageSizeSelect option:selected').val())" id="pageSizeSelect">
													<option value="3" <s:if test="#linkmanPageBean.pageSize==3">selected="selected"</s:if>>3</option>
													<option value="5" <s:if test="#linkmanPageBean.pageSize==5">selected="selected"</s:if>>5</option>
												</select>
												条
												[<A href="javascript:void(0)" <s:if test="#linkmanPageBean.currentPage>1">onclick="changePage(<s:property value="#linkmanPageBean.currentPage-1"/>)" </s:if>>前一页</A>] 
												<B><s:property value="#linkmanPageBean.currentPage"/></B>
												[<A href="javascript:void(0)" <s:if test="#linkmanPageBean.currentPage<#linkmanPageBean.totalPage">onclick="changePage(<s:property value="#linkmanPageBean.currentPage+ 1"/>)" </s:if>>后一页</A>] 
												到
												<input type="text" size="3" id="page" name="page" />
												页
												
												<input	type="button" value="Go" onclick="changePage($('#page').val())"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<s:debug></s:debug>
</BODY>
</HTML>
