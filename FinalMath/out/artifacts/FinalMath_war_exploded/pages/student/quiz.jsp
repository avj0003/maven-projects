<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Models.User" %>
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

    <!-- DataTables CSS -->
    <link href="../../vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="../../vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Responsive Voice -->
    <script src="http://code.responsivevoice.org/responsivevoice.js"></script>

    <!-- Custom Fonts -->
    <link href="../../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Annyang -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/annyang/2.6.0/annyang.min.js"></script>

    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>

    <!-- jwerty -->
    <script src="../../js/jwerty.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<%
    if (session != null) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.getRole().equals("student")) {
%>
<script>
    window.onload = function () {
        loadAnnyang();
        loadJwerty();
        setTimeout(responsiveVoice.speak("You are on the Quiz Page. Are you ready to take the quiz?"), 2000);
    };

    function loadJwerty() {
        <%
        for (int i=1; i<=2; i++) {
            %>
        jwerty.key('<%= i %>', function () { attempt(<%= i %>)});
        <%
    }
    %>
    }

    function attempt(k) {
        document.getElementById(k).click();
    }

    function loadAnnyang() {
        if (annyang) {
            var commands = {
                'Yes': function() {
                    setTimeout(responsiveVoice.speak("Excellent. We have 2 quizzes on a page. Press any key from 1 to 2 to attempt the quiz."), 2000);
                },

                'Practice': function() {
                    window.location="../../StudentQuestions";
                },

                'Dashboard': function() {
                    window.location="../../StudentDashboard";
                }
            };
            annyang.addCommands(commands);

            annyang.start();
        }
    }

</script>
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
            <a class="navbar-brand" href="StudentDashboard">MyAccessible+ Math</a>
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
                        <a href="StudentDashboard"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                    </li>
                    <li>
                        <a href="StudentQuestions"><i class="fa fa-question fa-fw"></i> Practice Questions</a>
                    </li>
                    <li>
                        <a href="StudentQuiz"><i class="fa fa-clone fa-fw"></i> Take a Quiz</a>
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
                <h1 class="page-header">Quizzes</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div id="somediv">

                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <table width="100%" class="table table-striped table-bordered table-hover"
                               id="dataTables-example" data-page-length="9">
                            <thead>
                            <tr>
                                <th>Quiz</th>
                                <th>Points</th>
                                <th>Due Date</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% int i=1; %>
                            <c:forEach var="quiz" items="${quizzes}">
                                <tr class="odd gradeX">
                                    <td>${quiz.getQuizName()}</td>
                                    <td>${quiz.getPoints()}</td>
                                    <td>${quiz.getDueDate()}</td>
                                    <td><a id="<%= i %>" href="../../AttemptQuiz?id=${quiz.getQuizId()}"
                                           class="btn btn-primary" style="float: right;">Attempt</a>
                                    </td>
                                    <% i++; %>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
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

<!-- DataTables JavaScript -->
<script src="../../vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="../../vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="../../vendor/datatables-responsive/dataTables.responsive.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../../dist/js/sb-admin-2.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTables-example').DataTable({
            responsive: true,
            searching: false,
            ordering: false,
            lengthChange: false
        });
    });
</script>
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