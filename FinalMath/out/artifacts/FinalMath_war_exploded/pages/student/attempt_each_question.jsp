<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Models.User" %>
<%--
  Created by IntelliJ IDEA.
  User: abhii
  Date: 12/1/2018
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
<%--    <script src="https://code.responsivevoice.org/responsivevoice.js?key=w2UILPfm"></script>--%>

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

    <script>
        window.onload = function () {
            responsiveVoice.speak("Say question to listen to the question. Answer the questions asked by the system. Let's simplify the equation. Say question to start. Say simplify to begin.");
            document.getElementById("hint1").style.visibility = "hidden";
            document.getElementById("hint2").style.visibility = "hidden";
            document.getElementById("hint3").style.visibility = "hidden ";
            loadAnnyang();
        };

        function speakMath(equation, flag) {

            var equationTrim = equation.split(' ').join('');
            console.log(equationTrim);
            var speech = "";
            for (var i = 0; i < equationTrim.length; i++) {
                switch (equationTrim[i]) {
                    case '0':
                        speech = speech + " " + "zero";
                        break;
                    case '1':
                        speech = speech + " " + "one";
                        break;
                    case '2':
                        speech = speech + " " + "two";
                        break;
                    case '3':
                        speech = speech + " " + "three";
                        break;
                    case '4':
                        speech = speech + " " + "four";
                        break;
                    case '5':
                        speech = speech + " " + "five";
                        break;
                    case '6':
                        speech = speech + " " + "six";
                        break;
                    case '7':
                        speech = speech + " " + "seven";
                        break;
                    case '8':
                        speech = speech + " " + "eight";
                        break;
                    case '9':
                        speech = speech + " " + "nine";
                        break;
                    case '*':
                        speech = speech + " " + "multiplied by";
                        break;
                    case '/':
                        speech = speech + " " + "divided by";
                        break;
                    case '+':
                        speech = speech + " " + "plus";
                        break;
                    case '-':
                        speech = speech + " " + "minus";
                        break;
                    case 'x':
                        speech = speech + " " + "axe";
                        break;
                    case '(':
                        speech = speech + " " + "parentheses open";
                        break;
                    case ')':
                        speech = speech + " " + "parentheses close";
                        break;
                    case '=':
                        speech = speech + " " + "equals";
                        break;
                    default:
                        break;
                }
            }
            if(flag == 0) {
                responsiveVoice.speak(speech);
            } else if(flag == 1) {
                responsiveVoice.speak("Simplifying left side of the equation, you would get" +speech);
            } else if(flag == 2) {
                responsiveVoice.speak("Simplifying right side of the equation, you would get" +speech);
            } else if(flag == 3) {
                responsiveVoice.speak("Simplifying both side of the equation, you would get" +speech);
            }
        }

        function loadAnnyang() {
            if (annyang) {
                var equation = "${equation}";
                var leftConst = "${second[1]}";
                var leftCoeff = "${second[0]}";
                var rightConst = "${third[1]}";
                var rightCoeff = "${third[0]}";
                var coeff = "${fourth[0]}";
                var constant = "${fourth[1]}";
                var commands = {

                    'question': function () {
                        speakMath(equation, 0);
                    },

                    'go back': function () {
                        window.location = "../../StudentQuestions";
                    },

                    'Practice': function () {
                        window.location = "../../StudentQuestions";
                    },

                    'Quiz': function () {
                        window.location = "../../StudentQuiz";
                    },

                    'simplify': function () {
                        responsiveVoice.speak("Tell me the co-efficient on the left side.");
                    },

                    ':leftcoeff':  function(leftcoeff) {
                        if(leftcoeff == leftCoeff) {
                            responsiveVoice.speak("Tell me the constant on the left side.");
                        }else {
                            responsiveVoice.speak("Do you want to try again?");
                        }
                    },

                    'constant on left is :leftconst':  function(leftconst) {
                        if(leftconst == leftConst) {
                            responsiveVoice.speak("Tell me the co-efficient on the right side.");
                        } else {
                            responsiveVoice.speak("Do you want to try again?");
                        }
                    },

                    'the answer is :rightcoeff':  function(rightcoeff) {
                        if(rightcoeff == rightCoeff) {
                            responsiveVoice.speak("Tell me the constant on the right side.");
                        }else {
                            responsiveVoice.speak("Do you want to try again?");
                        }
                    },

                    'constant on right is :rightconst':  function(rightconst) {
                        if(rightconst == rightConst) {
                            responsiveVoice.speak("Excellent, now simplify the co-efficient on both side.");
                        } else {
                            responsiveVoice.speak("Do you want to try again?");
                        }
                    },

                    'hint one': function () {
                        document.getElementById("step1").style.visibility = "visible";
                        if (leftConst >= 0) {
                            var equation = leftCoeff + "x" + "+" + leftConst;
                            speakMath(equation, 1);
                        }
                        else {
                            var equation = leftCoeff + "x" + leftConst;
                            speakMath(equation, 1);
                        }
                    },

                    'hint two': function () {
                        document.getElementById("step2").style.visibility = "visible";
                        if (rightConst >= 0) {
                            var equation = rightCoeff + "x" + "+" + rightConst;
                            speakMath(equation, 2);
                        }
                        else {
                            var equation = rightCoeff + "x" + rightConst;
                            speakMath(equation, 2);
                        }
                    },

                    'hint three': function () {
                        document.getElementById("step3").style.visibility = "visible";
                        var equation = coeff + "x" + "=" + constant;
                        speakMath(equation, 3);
                    }
                };

                annyang.addCommands(commands);

                annyang.start();
            }
        }
    </script>
</head>
<body>
<%
    if (session != null) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.getRole().equals("student")) {
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
                <h1 class="page-header">Practice Questions</h1>
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
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Question
                    </div>
                    <div class="panel-body">
                        <h4>Simplify Equation</h4>
                        <blockquote>
                            <p>${equation}</p>
                        </blockquote>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <div class="col-lg-8">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Hints
                    </div>
                    <div class="panel-body">
                        <div id="hint1">
                            <h4>Simplify left side of the equation:</h4>
                            <blockquote>
                                <p>
                                    <c:if test="${second[1] >= 0}">
                                <p>${second[0]}x + ${second[1]}</p>
                                </c:if>
                                <c:if test="${second[1] < 0}">
                                    <p>${second[0]}x ${second[1]}</p>
                                </c:if>
                                </p>
                            </blockquote>
                        </div>
                        <div id="hint2">
                            <h4>Simplify right side of the equation:</h4>
                            <blockquote>
                                <p>
                                    <c:if test="${third[1] >= 0}">
                                <p>${third[0]}x + ${third[1]}</p>
                                </c:if>
                                <c:if test="${third[1] < 0}">
                                    <p>${third[0]}x ${third[1]}</p>
                                </c:if>
                                </p>
                            </blockquote>
                        </div>
                        <div id="hint3">
                            <h4>Simplify equation on both side:</h4>
                            <blockquote>
                                <p>
                                    <c:if test="${fourth[1] >= 0}">
                                <p>${fourth[0]}x = ${fourth[1]}</p>
                                </c:if>
                                <c:if test="${fourth[1] < 0}">
                                    <p>${fourth[0]}x = ${fourth[1]}</p>
                                </c:if>
                                </p>
                            </blockquote>
                        </div>
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

