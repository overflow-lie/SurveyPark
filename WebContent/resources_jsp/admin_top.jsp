<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.farquer.cn/survey" prefix="farquer" %>

<!-- Header Wrapper -->
	<div id="mws-header" class="clearfix">  
        <!-- User Area Wrapper -->
        <div id="mws-user-tools" class="clearfix">
                    

            <div id="mws-user-info" class="mws-inset">
                <div id="mws-user-functions">
                    <div id="mws-username">
                        Hello, <s:property value="#session.loginAdmin.adminName"/>
                    </div>
                    <ul>
                        <li><s:a namespace="/Admin" action="AdminAction_logout">Logout</s:a></li>
                    </ul>
                </div>
            </div>
            <!-- End User Functions -->    
        </div>
    </div>
    
    <!-- Main Wrapper -->
    <div id="mws-wrapper">
    	<!-- Necessary markup, do not remove -->
		<div id="mws-sidebar-stitch"></div>
		<div id="mws-sidebar-bg"></div>
        
        <!-- Sidebar Wrapper -->
        <div id="mws-sidebar">
        	
                     
            <!-- Main Navigation -->
            <div id="mws-navigation">
            	<ul>
					<li><s:a namespace="/Admin" action="AdminAction_toAdminMainPage" cssClass="mws-i-24 i-home">首页</s:a></li>
					
           			<farquer:auth targetRes="ExcelAction_showAllSurvey">
						<li><s:a namespace="/Admin" action="ExcelAction_showAllSurvey" cssClass="mws-i-24 i-polaroids">Excel报表</s:a></li>
					</farquer:auth>
					
					<farquer:auth targetRes="ResourceAction_showAllResources">
						<li><s:a namespace="/Admin" action="ResourceAction_showAllResources" cssClass="mws-i-24 i-chart">资源列表</s:a></li>
					</farquer:auth>
					
					<li>
						<a href="#" class="mws-i-24 i-list">权限</a>
						<ul>
							<farquer:auth targetRes="AuthorityAction_toCreatePage">
								<li><s:a namespace="/Admin" action="AuthorityAction_toCreatePage">创建权限</s:a></li>
							</farquer:auth>
							
							<farquer:auth targetRes="AuthorityAction_showAuthorities">
								<li><s:a namespace="/Admin" action="AuthorityAction_showAuthorities">权限列表</s:a></li>
							</farquer:auth>
						</ul>
					</li>
					
					<li>
						<a href="#" class="mws-i-24 i-cog">角色</a>
						<ul>
							<farquer:auth targetRes="RoleAction_toCreatePage">
								<li><s:a namespace="/Admin" action="RoleAction_toCreatePage">创建角色</s:a></li>
							</farquer:auth>
							
							<farquer:auth targetRes="RoleAction_showRoles">
								<li><s:a namespace="/Admin" action="RoleAction_showRoles">角色列表</s:a></li>
							</farquer:auth>
						</ul>
					</li>
					
					<li>
						<a href="#" class="mws-i-24 i-text-styling">管理员</a>
						<ul>
							<farquer:auth targetRes="AdminAction_showAdmins">
								<li><s:a namespace="/Admin" action="AdminAction_showAdmins">管理员列表</s:a></li>
							</farquer:auth>
							
							<farquer:auth targetRes="AdminAction_generateAdmin">
								<li><s:a namespace="/Admin" action="AdminAction_generateAdmin">生成管理员</s:a></li>
							</farquer:auth>
						</ul>
					</li>
					
					<li>
						<a href="#" class="mws-i-24 i-blocks-images">日志</a>
						<ul>
							<farquer:auth targetRes="LogAction_showLogs">
								<li><s:a namespace="/Admin" action="LogAction_showLogs" >显示日志</s:a></li>
							</farquer:auth>
							<farquer:auth targetRes="LogAction_toDownloadLogsPage">
								<li><s:a namespace="/Admin" action="LogAction_toDownloadLogsPage">下载日志</s:a></li>
							</farquer:auth>
						</ul>
					</li>
					
					<farquer:auth targetRes="AdminAction_calculationCode">
						<li><s:a id="calculationCodeId" namespace="/Admin" action="AdminAction_calculationCode" cssClass="mws-i-24 i-file-cabinet">计算权限码</s:a></li>					
					</farquer:auth>

                </ul>
            </div>
            <!-- End Navigation -->
        </div>



