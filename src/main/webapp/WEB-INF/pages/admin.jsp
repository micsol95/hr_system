<%--
  Created by IntelliJ IDEA.
  User: Серый
  Date: 29.04.2016
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page session="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Admin</title>

    <!-- Bootstrap core CSS -->

    <%--<link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="<c:url value="/static/admin/css/bootstrap.min.css"/>">
    <link href="<c:url value="/static/admin/css/style.css"/>" rel="stylesheet">
    <%--<link href="fonts/css/font-awesome.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="<c:url value="/static/admin/fonts/css/font-awesome.min.css"/>">
    <%--<link href="css/animate.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="<c:url value="/static/admin/css/animate.min.css"/>">
    <!-- Custom styling plus plugins -->
    <%--<link href="css/custom.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="<c:url value="/static/admin/css/custom.css"/>">
    <%--<link rel="stylesheet" type="text/css" href="css/maps/jquery-jvectormap-2.0.3.css" />--%>
    <link rel="stylesheet" href="<c:url value="/static/admin/css/maps/jquery-jvectormap-2.0.3.css"/>">
    <%--<link href="css/icheck/flat/green.css" rel="stylesheet" />--%>
    <link rel="stylesheet" href="<c:url value="/static/admin/css/icheck/flat/green.css"/>">
    <%--<link href="css/floatexamples.css" rel="stylesheet" type="text/css" />--%>
    <link rel="stylesheet" href="<c:url value="/static/admin/css/floatexamples.css"/>">
    <script src="<c:url value="/static/admin/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/static/admin/js/nprogress.js"/>"></script>

</head>
<body class="nav-md">
<div class="modals">
    <!-- Modal -->
    <%@ include file="include/uploadPhotoModal.jsp" %>
</div>
<div class="container body">

    <div class="main_container">

        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">

                <div class="navbar nav_title" style="border: 0;">
                    <a href="<c:url value="/"/>" class="site_title"><i class="fa fa-paw"></i>
                        <span>Net<strong>Cracker</strong></span></a>
                </div>
                <div class="clearfix"></div>
                <security:authorize access="hasAnyRole('ROLE_HR')" var="isUserHr"/>
                <c:if test="${isUserHr}">
                    <button type="button" class="btn btn-sm btn-info sizeButton"><a class="Role_page" href="hr/interview_page">To Interview page</a></button>
                </c:if>
                <security:authorize access="hasAnyRole('ROLE_BA')" var="isUserBa"/>
                <c:if test="${isUserBa}">
                    <button type="button" class="btn btn-sm btn-info sizeButton"><a class="Role_page" href="ba/interview_page">To Interview page</a></button>
                </c:if>
                <security:authorize access="hasAnyRole('ROLE_DEV')" var="isUserDev"/>
                <c:if test="${isUserDev}">
                    <button type="button" class="btn btn-sm btn-info sizeButton"><a class="Role_page" href="dev/interview_page">To Interview page</a></button>
                </c:if>
                <!-- menu prile quick info -->
                <%@ include file="include/profilePicInf.jsp" %>
                <!-- /menu prile quick info -->

                <br/>

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

                    <div class="menu_section">
                        <h3>Administrator</h3>
                        <ul class="nav side-menu">
                            <li><a href="admin"><i class="fa fa-home"></i> Home </a></li>
                            <li><a href="admin/students"><i class="fa fa-list"></i> Students List </a></li>
                            <li><a href="admin/personal"><i class="fa fa-list-ul"></i> Personal List </a></li>
                            <li><a href="admin/interview_schedule"><i class="fa fa-calendar"></i> Interview Schedule
                            </a>
                            </li>
                            <li><a href="admin/admin_settings"><i class="fa fa-cogs"></i> System Setting </a></li>
                            <li><a href="admin/report"><i class="fa fa-calendar"></i> Reports
                            </a>
                            </li>
                            <li><a href="admin/template"><i class="fa fa-home"></i> Template </a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">

            <div class="nav_menu">
                <nav class="" role="navigation">
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown"
                               aria-expanded="false">
                                <img src="<c:url value="${photo}"/>" alt="">
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
                                <li>
                                    <a href="<c:url value="/logout"/>"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>

        </div>
        <!-- /top navigation -->


        <!-- page content -->
        <div class="right_col" role="main">

            <!-- top tiles -->
            <div class="row tile_count">
                <div class="animated flipInY col-md-4 col-sm-4 col-xs-4 tile_stats_count">
                    <div class="left"></div>
                    <div class="right">
                        <span class="count_top"><i class="fa fa-users"></i> Total Candidates</span>

                        <div class="count CountView"></div>
                    </div>
                </div>
                <div class="animated flipInY col-md-4 col-sm-4 col-xs-4 tile_stats_count">
                    <div class="left"></div>
                    <div class="right">
                        <span class="count_top"><i class="fa fa-user"></i> Total Personal</span>

                        <div class="count CountWorkers green"></div>
                    </div>
                </div>
            </div>
            <!-- /top tiles -->
            <div class="row">
                <div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
                    <img class="netcacker-img" src="<c:url value="/static/admin/images/Netcracker.png"/>" width="100%" alt="..." />
                </div>
            </div>
            <!-- footer content -->


            <!-- /footer content -->
        </div>
        <!-- /page content -->

    </div>

</div>

<div id="custom_notifications" class="custom-notifications dsp_none">
    <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
    </ul>
    <div class="clearfix"></div>
    <div id="notif-group" class="tabbed_notifications"></div>
</div>

</body>
<%--<script src="js/bootstrap.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/admin/js/AdminHomeAjax.js"/>"></script>
<!-- gauge js -->
<%--<script type="text/javascript" src="js/gauge/gauge.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/gauge/gauge.min.js"/>"></script>
<%--<script type="text/javascript" src="js/gauge/gauge_demo.js"></script>--%>
<script src="<c:url value="/static/admin/js/gauge/gauge_demo.js"/>"></script>

<%--<script src="js/progressbar/bootstrap-progressbar.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/progressbar/bootstrap-progressbar.min.js"/>"></script>
<%--<script src="js/nicescroll/jquery.nicescroll.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/nicescroll/jquery.nicescroll.min.js"/>"></script>

<%--<script src="js/icheck/icheck.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/icheck/icheck.min.js"/>"></script>

<%--<script type="text/javascript" src="js/moment/moment.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/moment/moment.min.js"/>"></script>
<%--<script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>--%>
<script src="<c:url value="/static/admin/js/datepicker/daterangepicker.js"/>"></script>

<%--<script src="js/chartjs/chart.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/chartjs/chart.min.js"/>"></script>

<%--<script src="js/custom.js"></script>--%>
<script src="<c:url value="/static/admin/js/custom.js"/>"></script>

<%--<!--[if lte IE 8]><script type="text/javascript" src="js/excanvas.min.js"></script><![endif]-->--%>
<%--<script type="text/javascript" src="js/flot/jquery.flot.js"></script>--%>
<script src="<c:url value="/static/admin/js/flot/jquery.flot.js"/>"></script>
<%--<script type="text/javascript" src="js/flot/jquery.flot.pie.js"></script>--%>
<script src="<c:url value="/static/admin/js/flot/jquery.flot.pie.js"/>"></script>
<%--<script type="text/javascript" src="js/flot/jquery.flot.orderBars.js"></script>--%>
<script src="<c:url value="/static/admin/js/flot/jquery.flot.orderBars.js"/>"></script>
<%--<script type="text/javascript" src="js/flot/jquery.flot.time.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/flot/jquery.flot.time.min.js"/>"></script>
<%--<script type="text/javascript" src="js/flot/date.js"></script>--%>
<script src="<c:url value="/static/admin/js/flot/date.js"/>"></script>
<%--<script type="text/javascript" src="js/flot/jquery.flot.spline.js"></script>--%>
<script src="<c:url value="/static/admin/js/flot/jquery.flot.spline.js"/>"></script>
<%--<script type="text/javascript" src="js/flot/jquery.flot.stack.js"></script>--%>
<script src="<c:url value="/static/admin/js/flot/jquery.flot.stack.js"/>"></script>
<%--<script type="text/javascript" src="js/flot/curvedLines.js"></script>--%>
<script src="<c:url value="/static/admin/js/flot/curvedLines.js"/>"></script>
<%--<script type="text/javascript" src="js/flot/jquery.flot.resize.js"></script>--%>
<script src="<c:url value="/static/admin/js/flot/jquery.flot.resize.js"/>"></script>
<%--<script type="text/javascript" src="js/maps/jquery-jvectormap-2.0.3.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/maps/jquery-jvectormap-2.0.3.min.js"/>"></script>
<%--<script type="text/javascript" src="js/maps/gdp-data.js"></script>--%>
<script src="<c:url value="/static/admin/js/maps/gdp-data.js"/>"></script>
<%--<script type="text/javascript" src="js/maps/jquery-jvectormap-world-mill-en.js"></script>--%>
<script src="<c:url value="/static/admin/js/maps/jquery-jvectormap-world-mill-en.js"/>"></script>
<%--<script type="text/javascript" src="js/maps/jquery-jvectormap-us-aea-en.js"></script>--%>
<script src="<c:url value="/static/admin/js/maps/jquery-jvectormap-us-aea-en.js"/>"></script>
<%--<script src="js/pace/pace.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/pace/pace.min.js"/>"></script>
<!-- skycons -->
<%--<script src="js/skycons/skycons.min.js"></script>--%>
<script src="<c:url value="/static/admin/js/skycons/skycons.min.js"/>"></script>
<script src="<c:url value="/static/admin/js/AdminMainPage.js"/>"></script>
<script>
    NProgress.done();
</script>

<!-- /datepicker -->
<!-- /footer content -->


</html>

