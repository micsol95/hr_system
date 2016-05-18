<%--
  Created by IntelliJ IDEA.
  User: Серый
  Date: 29.04.2016
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Students</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/static/admin/css/bootstrap.min.css"/>" rel="stylesheet">

    <link href="<c:url value="/static/admin/fonts/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/admin/css/animate.min.css"/>" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="<c:url value="/static/admin/css/custom.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/admin/css/style.css"/>" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/admin/css/maps/jquery-jvectormap-2.0.3.css"/>"/>
    <link href="<c:url value="/static/admin/css/icheck/flat/green.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/admin/css/floatexamples.css"/>" rel="stylesheet" type="text/css"/>

    <link href="<c:url value="/static/admin/js/datatables/jquery.dataTables.min.css"/>" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value="/static/admin/js/datatables/buttons.bootstrap.min.css"/>" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value="/static/admin/js/datatables/fixedHeader.bootstrap.min.css"/>" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value="/static/admin/js/datatables/responsive.bootstrap.min.css"/>" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value="/static/admin/js/datatables/scroller.bootstrap.min.css"/>" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value="/static/admin/css/simplePagination.css"/>" rel="stylesheet">


    <script src="<c:url value="/static/admin/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/static/admin/js/nprogress.js"/>"></script>

</head>


<body class="nav-md">

<div class="container body">


    <div class="main_container">

        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">

                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Net<strong>Cracker</strong></span></a>
                </div>
                <div class="clearfix"></div>

                <!-- menu prile quick info -->
                <div class="profile">
                    <div class="profile_pic">
                        <img src="<c:url value="/static/admin/images/img.jpg"/>" alt="..."
                             class="img-circle profile_img">
                    </div>
                    <div class="profile_info">
                        <span>Welcome,</span>

                        <h2><sec:authentication property="principal.name"/>&nbsp;<sec:authentication property="principal.surname"/></h2>
                    </div>
                </div>
                <!-- /menu prile quick info -->

                <br/>

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

                    <div class="menu_section">
                        <h3>Administrator</h3>
                        <ul class="nav side-menu">
                            <li><a href="../"><i class="fa fa-home"></i> Home </a></li>
                            <li><a href="students"><i class="fa fa-list"></i> Students List </a></li>
                            <li><a href="personal"><i class="fa fa-list-ul"></i> Personal List </a></li>
                            <li><a href="interview_schedule"><i class="fa fa-calendar"></i> Interview Schedule </a></li>
                            <li><a href="admin_settings"><i class="fa fa-cogs"></i> System Setting </a></li>
                        </ul>
                    </div>

                </div>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
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
                                <img src="<c:url value="/static/admin/images/img.jpg"/>" alt="">
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
                                <li><a href="javascript:;"> Profile</a>
                                </li>
                                <li>
                                    <a href="javascript:;">
                                        <span class="badge bg-red pull-right">50%</span>
                                        <span>Settings</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:;">Help</a>
                                </li>
                                <li><a href="../logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
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
            <!-- Modal -->
            <div class="modal fade" id="modal-student" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Student Profile</h4>
                        </div>
                        <div class="modal-body">
                            ...
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- END Modal -->

            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2> Students List </h2>

                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <p class="text-muted font-13 m-b-30">

                            <div class="tabke-filters">
                                <button class="btn btn-default" type="button" data-toggle="collapse"
                                        data-target="#collapseFilters" aria-expanded="false"
                                        aria-controls="collapseExample">
                                    Filters
                                </button>
                                <div class="collapse" id="collapseFilters">
                                    <div class="well">
                                        <div class="row">
                                            <div class="col-md-4 col-sm-6 col-xs-6">
                                                <select id="study" class="form-control">
                                                    <option value="" disabled selected hidden>Select study</option>
                                                    <option value="">KPI</option>
                                                    <option value="">HAU</option>
                                                    <option value="">Shevchenko</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </p>
                            <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">
                                <select id="Rows" class="form-control">
                                    <option value="10" selected>10</option>
                                    <option value="15">15</option>
                                    <option value="25">25</option>
                                </select>
                            </div>
                            <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">

                             </div>
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <div id="search" class="nav nav-sidebar">
                                    <div id="custom-search-input">
                                        <div class="input-group col-md-12">
                                            <input type="text" id="fieldSearch" class="form-control" placeholder="Организации , адреса...">
                                        <span class="input-group-btn">
                                            <button id="buttonSearch" class="btn btn-info" type="button">
                                            </button>
                                            <div id="animationSearch">
                                                <div id="ball_outside"></div>
                                                <div id="ball_inside"></div>
                                            </div>
                                        </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <table id="StudentTable" class="table table-striped table-bordered clicked-tr">
                                <thead>
                                <tr>
                                    <th>Status</th>
                                    <th>Name</th>
                                    <th>Surname</th>
                                    <th>Patronymic</th>
                                    <th>email</th>
                                    <th>Redaction</th>
                                </tr>
                                </thead>
                                <tbody id="TableStudents">

                                </tbody>

                            </table>
                            <div id="dark-pagination" class="pagination"></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- footer content -->

            <footer>
                <div class="copyright-info">
                    <p class="pull-right">Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
                    </p>
                </div>
                <div class="clearfix"></div>
            </footer>
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
<div id="hider"></div>
<div class="ModelViewStudent"></div>
<div class="ModelStatus">
    <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">
        <select id="Status" class="form-control">
            <option value="" selected>Choose to change status</option>
            <option value="Rejected">Rejected</option>
            <option value="Ready">Ready</option>
            <option value="No_interview">No_interview</option>
            <option value="Interview">Interview</option>
            <option value="Interview_dated">Interview_dated</option>
            <option value="Interview_process">Interview_process</option>
            <option value="Interview_passed">Interview_passed</option>
            <option value="Trainee_accepted">Trainee_accepted</option>
            <option value="Job_accepted">Job_accepted</option>
        </select>
        <button type="button" id="UpdateStatus" class="btn btn-success">Click to change</button>
    </div>

</div>
<script src="<c:url value="/static/admin/js/AjaxForStudents.js"/>"></script>
<script src="<c:url value="/static/admin/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/admin/js/jquery.simplePagination.js"/>"></script>
<!-- bootstrap progress js -->
<script src="<c:url value="/static/admin/js/progressbar/bootstrap-progressbar.min.js"/>"></script>
<script src="<c:url value="/static/admin/js/nicescroll/jquery.nicescroll.min.js"/>"></script>
<!-- icheck -->
<script src="<c:url value="/static/admin/js/icheck/icheck.min.js"/>"></script>

<script src="<c:url value="/static/admin/js/custom.js"/>"></script>




<!-- Datatables-->
<script src="<c:url value="/static/admin/js/datatables/jszip.min.js"/>"></script>
<script src="<c:url value="/static/admin/js/datatables/pdfmake.min.js"/>"></script>
<script src="<c:url value="/static/admin/js/datatables/vfs_fonts.js"/>"></script>
<script src="<c:url value="/static/admin/js/datatables/buttons.html5.min.js"/>"></script>
<script src="<c:url value="/static/admin/js/datatables/buttons.print.min.js"/>"></script>
<script src="<c:url value="/static/js/datatables/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/static/js/datatables/dataTables.bootstrap.js"/>"></script>
<script src="<c:url value="/static/js/datatables/dataTables.buttons.min.js"/>"></script>
<script src="<c:url value="/static/js/datatables/buttons.bootstrap.min.js"/>"></script>


<!-- pace -->
<script src="<c:url value="/static/admin/js/pace/pace.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/admin/js/Students.js"/>"></script>
<!-- /datepicker -->
<!-- /footer content -->
</body>

</html>








