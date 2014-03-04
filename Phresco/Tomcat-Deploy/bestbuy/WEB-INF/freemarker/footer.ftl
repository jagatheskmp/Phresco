<script>
 var require = {
            waitSeconds: 1,
            urlArgs : "bust="+new Date().getTime()
        };
</script>
<script type="text/javascript" src="js/jQuery/require-jquery.js"></script>
<script type="text/javascript" src="js/util/merchValidationUI.js"></script>
<script type="text/javascript" src="js/jQuery/jquery.jqGrid-4.2.0/js/i18n/grid.locale-en.js"></script>
<script>

$(document).ready(function(){

	//Initialize the tabs
	$("#tabsh").tabs();


	// Get the value from the URL and seperate the # value .  This value determine which tabs needs to clicked . We put this value in a hidden input box which is used in appController.js to call the specfic tab
	if(((window.location.hash) == "" ) || (window.location.hash ==null))
	{
	$('#tabSelection').val("synonyms");
		
	}else if((window.location.hash) == "boostAndBlock" ){
		$('#tabSelection').val("boostAndBlock");
	}else{
		$('#tabSelection').val(	(window.location.hash).replace('#',''));
	}
	var pageContext = "/" + (window.location.pathname.split('/'))[1];
	window.history.replaceState("String", "Title", pageContext);
});
	require({
		baseUrl : "js"
	}, [ "merchUIController/appController"]);
</script>