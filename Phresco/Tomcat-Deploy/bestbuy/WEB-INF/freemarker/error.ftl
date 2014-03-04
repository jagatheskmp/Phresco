<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Welcome to Best Buy Business Merchandising Tool</title>
	<link rel="stylesheet" type="text/css" href="css/login.css" media="screen"/>
	</head>
	<body>
	<script>
        function error() {
        var pageContext = (window.location.pathname.split('/'))[1] +"/login"
        window.location.href=(pageContext);
		}
    </script>
		<form class="ui-dialog login" action="login">
			<ul><h2 for='login'>Sorry!</h2>
			<li><label><a>Something has gone wrong.</a></label>
			<img src="images/errors/error.jpeg"  hspace="5" vspace="1" align="middle">
			</li>
			<li>
			<span class="button"><a href="login">Back to Login</a></span>
			</li>
			</ul>
		</form>
	</body>
</html>