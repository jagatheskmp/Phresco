<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Welcome to Best Buy Business Merchandising Tool</title>
	<link rel="stylesheet" type="text/css" href="js/jQuery/jquery-ui/css/cupertino/jquery-ui-1.8.12.custom.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="js/jQuery/jquery.jqGrid-4.2.0/src/css/ui.jqgrid.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="css/login.css" media="screen"/>
	<script type="text/javascript" src="js/jQuery/require-jquery.js"></script>
	<script type="text/javascript" src="js/util/merchValidationUI.js"></script>
	<script type="text/javascript" src="js/jQuery/jquery.jqGrid-4.2.0/js/i18n/grid.locale-en.js"></script>
	</head>
	<body>
		<form id="logOutForm" class="ui-dialog login" name="login" action="">
			<ul><h2 for='login'>Sorry!</h2>
			<li><label><a>Something has gone wrong.</a></label><li>
			<li>
			<img src="images/errors/error.jpeg"  hspace="5" vspace="1" align="middle">
			</li>
			<button type="button">Back to Login</button>
			</ul>
		</form>
		<script>
			var pageContext = "/"+(window.location.pathname.split('/'))[1] +"/j_spring_security_check";
			document.getElementById("logOutForm").setAttribute("action", pageContext);
		</script>
	</body>
</html>