<%@ page import="Models.User" %>
<%@ include file="../../connection.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MyAccessbile+ Math</title>

    <!-- Bootstrap Core CSS -->
    <link href="../../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="../../vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Responsive Voice -->
    <script src="http://code.responsivevoice.org/responsivevoice.js"></script>

    <!-- Custom Fonts -->
    <link href="../../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Wiris Math Editor JS -->
    <script src="../../js/editor.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<script>
    var editor;
    window.onload = function () {
        // startArtyom();
        editor = com.wiris.jsEditor.JsEditor.newInstance({'language': 'en'});
        editor.insertInto(document.getElementById('editorContainer'));
        document.getElementById('getMathML').onclick = function () {
            document.getElementById('mathML').value = com.wiris.jsEditor.JsEditor.getInstance(document.getElementById('editorContainer')).getMathMLWithSemantics();
        }
    };
</script>
<body>
<%
    if (session != null) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.getRole().equals("professor")) {
%>
    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="../../DashboardServlet">MyAccessible+ Math</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="LogoutServlet"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="../../DashboardServlet"><i class="fa fa-home fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="../../DisplayStudentsServlet"><i class="fa fa-users fa-fw"></i> Students</a>
                        </li>
                        <li>
                            <a href="../../TopicsServlet"><i class="fa fa-list fa-fw"></i> Math Topics</a>
                        </li>
                        <li>
                            <a href="../../QuestionsServlet"><i class="fa fa-question fa-fw"></i> Math Questions</a>
                        </li>
                        <li>
                            <a href="../../QuizServlet"><i class="fa fa-clone fa-fw"></i> Quizzes</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Dashboard</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-user fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${count[0]}</div>
                                    <div>Total Students!</div>
                                </div>
                            </div>
                        </div>
                        <a href="../../DisplayStudentsServlet">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-tasks fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${count[1]}</div>
                                    <div>Practice Questions!</div>
                                </div>
                            </div>
                        </div>
                        <a href="../../QuestionsServlet">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-clone fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${count[2]}</div>
                                    <div>Number of Quizzes!</div>
                                </div>
                            </div>
                        </div>
                        <a href="../../QuizServlet">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-question fa-fw"></i> Add a Question
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <form method="POST" action="../../AddQuestionServlet" class="form-sample">
                                <p class="card-description">
                                    <b>Question Info</b>
                                </p>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-3 col-form-label">Math Topic</label>
                                            <div class="col-sm-9">
                                                <select name="topic_id" class="form-control">
                                                    <c:forEach var="topic" items="${topics}">
                                                        <option value="${topic.getTopicId()}">
                                                            ${topic.getTopicName()}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-3 col-form-label">Points</label>
                                            <div class="col-sm-9">
                                                <input name="points" type="text" class="form-control" required/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <p class="card-description">
                                    <b>Question</b>
                                </p>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div id="editorContainer" style="height:350px;"></div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-8">
                                        <textarea name="question_mathml" dir="ltr" style="width: 100%; height:100px;"
                                                  id="mathML" required></textarea>
                                    </div>
                                    <div class="col-md-4">
                                        <input id="getMathML" class="btn btn-dark mr-2" type="button"
                                               value="Get MathML"/>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-success mr-2">Add Question</button>
                            </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                </div>
                <!-- /.col-lg-8 -->
                <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-users fa-fw"></i> Students List
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="list-group">
                                <c:forEach var="user" items="${users}">
                                    <a href="#" class="list-group-item">
                                        <i class="fa fa-user fa-fw"></i> ${user.getFirstName() } ${user.getLastName()}
                                    <%--<span class="pull-right text-muted small"><em>4 minutes ago</em>--%>
                                    </span>
                                    </a>
                                </c:forEach>
                            </div>
                            <!-- /.list-group -->
                            <a href="../../DisplayStudentsServlet" class="btn btn-default btn-block">View All Students</a>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-4 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../../vendor/raphael/raphael.min.js"></script>
    <script src="../../vendor/morrisjs/morris.min.js"></script>
    <script src="../../data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../../dist/js/sb-admin-2.js"></script>

</body>
<%
            } else {
                session.invalidate();
                response.sendRedirect("../../index.jsp");
            }
        } else {
            response.sendRedirect("../../index.jsp");
        }
    } else {
        response.sendRedirect("../../index.jsp");
    }
%>
</html>