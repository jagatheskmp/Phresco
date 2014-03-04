<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Welcome to Best Buy Business Merchandising Tool</title>

<link rel="stylesheet" type="text/css" href="css/itemSelectGrid.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jsTree/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="js/jQuery/jquery-ui/css/cupertino/jquery-ui-1.8.12.custom.css" media="screen" />
<link rel="stylesheet" type="text/css" href="js/jQuery/jquery.jqGrid-4.2.0/src/css/ui.jqgrid.css" media="screen" />
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<script>
     function logout() {
     var pageContext = (window.location.pathname.split('/'))[1] +"/j_spring_security_logout"
     window.location=(pageContext);
	}
</script>
	<div class='header' width="98%" border="0" align="center" cellpadding="0" style="margin-top: 15px;" cellspacing="0">
		<img class='headerImage' src="images/best-buy-logo copy.png" width="121" height="70">
		<div style="width: auto; height: auto; float: right">


		</div>
		<br>
		<div style="width: auto; height: auto; float: right; padding: 0; margin: 0; margin-top: -20px;">

				<#if model["loggedUser"].role.name = "ROLE_ADMIN">
				<p style="width: 100%; clear: both; margin: 0; padding: 0; text-align: left; font-weight: bold;">Hello, ${model["loggedUser"].firstName} ${model["loggedUser"].lastName} (${model["loggedUser"].loginName})</p>
				<div class='floatLeft' style="border-right: 1px solid grey; padding-right: 8px;">
						<a class='floatLeft' style="cursor: pointer; white-space: nowrap;" onClick="$('#adminPageButton').click()" href="#admin" style="cursor: hand;">Admin Menu</a>
						</br>
						<a id='removeSKUs' class='floatLeft' style="cursor: pointer; white-space: nowrap;">Remove SKU</a>				
				</div>
				</#if>
				<div class='floatRight'>
					<button style="border: none; background: transparent;margin-top: 5px;" onClick="logout()">
					<a style="cursor: hand;"><img src="images/logout.png" width="20" height="22" hspace="5" vspace="1" align="absmiddle" style="cursor: hand;display: inline-block;top: -4px;position: relative;left: 4px;margin-top: 5px;margin-left: -10px;"></a>
					<a style="cursor: hand;">Logout</a>
					</button>
				</div>
		</div>
	</div>
	<input type="hidden" id="tabSelection" value="">