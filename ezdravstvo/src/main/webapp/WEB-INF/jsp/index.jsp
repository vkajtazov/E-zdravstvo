<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- <link rel="shortcut icon" href="../../assets/ico/favicon.ico"> -->

    <title>WP Todo List</title>

    <!-- Bootstrap core CSS -->
    
    <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/font-awesome.min.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/ng-table/ng-table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/AngularJS-Toaster/toaster.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/select2/select2.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/select2/select2-bootstrap3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/angular-motion/dist/angular-motion.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/famfamfam-flags.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript">
    	var _contextPath = "${pageContext.request.contextPath}";
	</script>
  </head>

  <body ng-app="WP">

	
	<ng-include src="'menu/user.html'"></ng-include>
    

    <div class="container">
      	<div ng-view autoscroll="true"></div>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/jquery-cookie/jquery.cookie.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/momentjs/min/moment-with-langs.min.js"></script>
    <%-- <script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script> --%>
    <script src="${pageContext.request.contextPath}/resources/bower_components/ng-file-upload/angular-file-upload-shim.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-resource/angular-resource.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-route/angular-route.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-animate/angular-animate.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-sanitize/angular-sanitize.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-cookies/angular-cookies.js"></script>    
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-translate/angular-translate.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/ng-file-upload/angular-file-upload.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/ng-table/ng-table.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-strap/dist/angular-strap.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-strap/dist/angular-strap.tpl.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/AngularJS-Toaster/toaster.js"></script>
  	
  	<script src="${pageContext.request.contextPath}/resources/bower_components/select2/select2.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-ui-select2/src/select2.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.js"></script>  	
    
  	<script src="${pageContext.request.contextPath}/resources/js/dependencies.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
  	
    
  	<script src="${pageContext.request.contextPath}/resources/js/services.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/filters.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/controllers.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/router.js"></script>
  	
  	
  	
  	
  	<toaster-container toaster-options="{'time-out': 3000}"></toaster-container>
  </body>
</html>
