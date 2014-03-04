<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Boost & Block</title>

<link rel="stylesheet" type="text/css" href="css/itemSelectGrid.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jsTree/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="js/jQuery/jquery-ui/css/cupertino/jquery-ui-1.8.12.custom.css" media="screen" />
<link rel="stylesheet" type="text/css" href="js/jQuery/jquery.jqGrid-4.2.0/src/css/ui.jqgrid.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jQuery/require-jquery.js"></script>
<script type="text/javascript" src="js/jQuery/jquery.jqGrid-4.2.0/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="js/util/merchValidationUI.js"></script>



<script>
	//Below line loads the required JS files
	require(
			{
				baseUrl : "js/"
			},
			[ "boostAndBlock/boostAndBlockCreateModifyForm/boostAndBlockFormController" ]);
</script>

</head>
<body>
<div id="dialog-modal" class='ui-dialog' title="Alert"></div> 

	<div id="boostAndBlockTemplate">
	    <h2 id='headerText'></h2>
	
		<div id="boostAndBlockSearchPage" style="display:block;margin-top:5px" class='widgetboostAndBlock '>
<form class="validate">
			<li><label class='wide'><b>Create New Boost/Block for Keyword Search Results</label></b></li>
			<li><label><b>Search Term*</b></label> <input name="SearchTerm" type="text" data-bind="value: query" /><br></li>
			<li><label><b>Search Profile*</b></label> <select id="searchProfileOptions"
				data-bind="options: searchProfileOptions, optionsText: 'name', value: searchProfile" required>
			</select></li>
			
			<li>
			<li><b>Select a Category below*<b></li>
			<li data-bind="attr: { 'id': 'tree'}" class="tree jstree-default">
			<p>Click Here To Edit Category</p>
			</li>
						<li><strong><b name="categorySeq">Selected Category:</b> <span data-bind="attr: { 'class': 'categoryPath'}"></span></strong></li>
						
			<li><input data-bind="attr: { 'id': 'categoryLastNodeName'},value: categorySeq" disabled="disabled" hidden></li>
			<li><input data-bind="attr: { 'id': 'category'},value: category" type="hidden"></li>
			<li>
				<span id="save" class="button buttonSubmitCreateFacetCancel" type="button" data-bind="event: { click: submitNext}">Next</span>
				<span class="button cancelBoostAndBlock" type="button" ">Cancel</span>
			</li>
			
			</li>
</form>			
		</div>

		<div id="boostAndBlockPage" style="visibility:hidden;display:none;margin-top:5px" >
		<div class='widgetboostAndBlock widget'>
			<li><input name="boostBlockId" type="hidden" id="boostBlockId"></input><br></li>
			<li><b>Boosts and Blocks for: </b><span id='baseQuery' data-bind="text: query"></span></li>
			<li><b>Profile: </b><span id='searchProfileName' data-bind="text: searchProfile().name"></span><span id='searchProfileId' class='hidden' data-bind="text: searchProfile().value"></span></li>
			
			<li><b>Category: </b><span id='categorySeq' data-bind="text: categorySeq"></span></li>
			<!--ko if: createdDate -->
				<li><b>Created: </b><span data-bind="text: createdDate"></span> By: <span data-bind="text: createdBy"></span></li>
				<li><b>Last Edited: </b><span data-bind="text: lastEditedDate"></span> By: <span data-bind="text: lastEditedBy"></span></li>
			<!-- /ko -->
			<li><input data-bind="attr: { 'id': 'category'},value: category" type="hidden"></li>
		</div>
		<div class='widgetboostAndBlock '>
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
				<tr>
					<td>
						<div id="boostedProductContainer" class="itemSelect" style="padding: 1; margin: 0; width: 100%; height: 100%;">
							<table id="boostedProduct">
							</table>
						</div>
					</td>
				</tr>
			</table>
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
				<tr>
					<td>
						<div id="blockedProductContainer" class="itemSelect" style="border:1;padding: 1; margin: 0; width: 100%; height: 100%;">
							<table id="blockedProduct">
							</table>
						</div>
					</td>
				</tr>
			</table>
			<table id ='boostAndBlockEditCreate' width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
				<tr>
					<td>

						<div id="boostBlockSearchProductContainer" class="itemSelect" style="padding: 0; margin: 0; width: 100%; height: 100%;">
							<table id="boostBlockSearchProduct"></table>
							<div id="boostBlockSearchProductPager"></div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div id ='boostAndBlockEditCreateFooter' class='createFooterBoostAndBlock clearfix'>
			
			 <span id="submitBoostAndBlock" class="button " type="button" ">Save</span>
			 <span id="cancelBoostAndBlock" class="button cancelBoostAndBlock" type="button" ">Cancel</span>
			
		</div>
		<center>

		<form class='boostAndBlockReadOnlyShow'>
		<a href='#' onClick="window.close()" /><span class='button' id='boostAndBlockReadOnly' type="button" value="Close Window" >Close Window</span></a>

		</form>
		</center>
	 </div>
	
</div>	
</body>