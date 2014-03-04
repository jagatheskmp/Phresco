<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Facet</title>

<link rel="stylesheet" type="text/css" href="css/itemSelectGrid.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="css/jsTree/style.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="js/jQuery/jquery-ui/css/cupertino/jquery-ui-1.8.12.custom.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="js/jQuery/jquery.jqGrid-4.2.0/src/css/ui.jqgrid.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="css/style.css" >


<style>
.ui-dialogform {
	overflow: hidden;
	width: 96%!important;
}

/*TODO: Move style to CSS - Facet Page CSS Rules */
/* Hide autocomplete on Field for Faceting Dialog */
.ui-dialog  + .ui-autocomplete
{ 
display:none!important;
}
.ui-autocomplete
{ 
/*display:none!important;*/
max-height: 200px; overflow-y: scroll; overflow-x: hidden;
}
</style>


</head>
<body>
<div id="dialog-modal" title="Alert"></div>  

<div class='ui-dialog ui-dialogform facetsForm'>
	<!-- ui-dialog -->
	<div id="confirmDialogFacets"></div>
	
	<div id="createFacets">
	<form>
		<input type="hidden" name="facetId" id="facetId">
		<input type="hidden" name="countFacetValues" id="countFacetValues">
    <div class='widget g1'>
    <h2>Facets</h2>	
	<ul>
	<!-- <span data-bind="text: systemName"></span> -->
	    <li><label class='wide' for="attributeName"><b>Field for Faceting:*</b><br><i>Look Up Value.</i></label><br/>  <input id="attributeName" data-bind="value: attributeName" required readonly/><input type="hidden" id="attributeId" data-bind="value: attributeId"/><span name=""></span><img class="ui-datepicker-trigger" src="images/faceting.gif" alt="..." title="..."></li>
	    <li>
	    	<label class='wide' for="facetsName"><b>System Name:*</b><br><i>Internal Use Only.Must Be unique.</i></label><br/> 
	    	<input name='systemName' id="systemName" data-bind="value: systemName" required />
	    </li>
	    <li><label class='wide' for="displayName"><b>Display Name:*</b><br><i>Shown on site. Does not need to be unique.</i></label><br/></label>  <input name='displayName' id="displayName" data-bind="value: displayName" required /></li>
	    	    <li class='clearfix'>
		    	<label class='wide' for="facetDisplay"><b>Displayed or Hidden Facet:*</b></label><br/>
				<input name="facetDisplay" type="radio" value="Y" data-bind="checked: facetDisplay" /><label for='facetDisplay'> Displayed</label><br>
	    		<input name="facetDisplay" type="radio" value="N" data-bind="checked: facetDisplay" /><label for='facetDisplay'> Hidden</label>
		</li>	    	
	    <li><label for="facetsPosition"><b>Position:*</b></label> <select id="facetsPosition" data-bind="options: facetsPositionOptionValues, optionsText: 'name', optionsValue: 'value', value: facetsPosition" required ></select></li>
	    <li><label for="startDate1"><b>Start Date:*</b></label> <input id="startDate" name="startDate" data-bind="datepicker: startDate, datepickerOptions: {dateFormat:'mm-dd-yy'}" required placeholder="Click to select date." readonly/></li>
	    <li><label class='wide' for="facetsEndDate"><b>End Date:</b><br><i>Not Required.</i></label><br/>  <input id="endDate" name="endDate" data-bind="datepicker: endDate, datepickerOptions: {dateFormat:'mm-dd-yy'}" placeholder="Click to select date." readonly/></li>
	    <li><label class='wide' for="displayMode"><b>Mode:*</b><br><i>Where this facet displays - search and/or browse pages.</i></label><br/> <select id="displayMode" data-bind="options: displayModeOptionValues, optionsText: 'name', optionsValue: 'value', value: displayMode" required ></select></li>
	    <li><label class='wide' for="glossaryItem"><b>Glossary Item:</b><br><i>Enter Asset ID.</i></label> <br/> <input name='glossaryItem' id="glossaryItem" data-bind="value: glossaryItem" /></li>
    </ul>
    </div>
    <div class='widget g1' >
	    <a href="javascript:void(0)" onclick="toggle('searchPanel', this)">
	    	<img src="images/down.png" align="right" style="border:none;">
	    </a>
	<h2>Advanced Options</h2>
		 <div id="searchPanel" style="display: none;">
		 <ul>
		    	    <li class='clearfix'>
				    	<label class='wide' for="displayRecurrence"><b>Allow Recurrence:</b></label>
				    	<label class='wide' for="displayRecurrence">If this Facet is selected, other values continue to display</label><br/>
						<input name="displayRecurrence" type="radio" value="Y" data-bind="checked: displayRecurrence" /><label for='displayRecurrence'> Yes</label><br>
			    		<input name="displayRecurrence" type="radio" value="N" data-bind="checked: displayRecurrence" /><label for='displayRecurrence'> No</label>
	   				</li>	    	
		    	    <li class='clearfix'>
				    	<label class='wide' for="displayFacetRemoveLink"><b>Can Facet selection be undone:</b></label>
				    	<label class='wide' for="displayRecurrence">Should the user be able 'undo' the Facet selection?</label><br/>
						<input name="displayFacetRemoveLink" type="radio" value="Y" data-bind="checked: displayFacetRemoveLink" /><label for='displayFacetRemoveLink'> Yes</label><br>
			    		<input name="displayFacetRemoveLink" type="radio" value="N" data-bind="checked: displayFacetRemoveLink" /><label for='displayFacetRemoveLink'> No</label>
	   				</li>
		    	    <li class='clearfix'>
				    	<label class='wide' for="displayMobileFacet"><b>Remove Facet from Display (Mobile Phone)</b></label>
				    	<label class='wide' for="displayMobileFacet">if contract is changed, remove this Facet from display</label><br/>
						<input name="displayMobileFacet" type="radio" value="Y" data-bind="checked: displayMobileFacet" /><label for='displayMobileFacet'> Yes</label><br>
			    		<input name="displayMobileFacet" type="radio" value="N" data-bind="checked: displayMobileFacet" /><label for='displayMobileFacet'> No</label>
	   				</li>
		    	    <li class='clearfix'>
				    	<label class='wide' for="displayMobileFacetRemoveLink"><b>Hide Display Facets (Mobile Phone)</b></label>
				    	<label class='wide' for="displayMobileFacetRemoveLink">Should this Facet be removed when plan is not two-year new?</label><br/>
						<input name="displayMobileFacetRemoveLink" type="radio" value="Y" data-bind="checked: displayMobileFacetRemoveLink" /><label for='displayMobileFacetRemoveLink'> Yes</label><br>
			    		<input name="displayMobileFacetRemoveLink" type="radio" value="N" data-bind="checked: displayMobileFacetRemoveLink" /><label for='displayMobileFacetRemoveLink'> No</label>
	   				</li>
		</ul>    
	    </div>
	</div>
    <div class='widget g1'>
	<h2>Sort Order</h2>
	 <label class='wide'><b>Sort Facet Values By: </b></label><br/>
	    <li>				    
				<input type = "radio"  name = "sortTypeId"  id = "mostResult"  value = "num_res_least" checked="checked" />	<label for="mostResult">Number of results sorted most results to least</label>  <br />
				<input type = "radio" name = "sortTypeId" id = "alphabeticalOrder"  value = "AZ" />  <label for="alphabeticalOrder">Alphabetical order (A-Z)</label> <br />
				<input type = "radio"  name = "sortTypeId" id = "specificOrderSequence" value = "specificOrderSequence"  />  <label for="specificOrderSequence">Specific order sequence</label>  <br />	  
	    	<ul> 	
	    		<li> 
	    		<input type = "radio"  name = "sortTypeIdSpecific"   id = "nonspecificOrderSequenceAZ" value = "num_spec_AZ"  />  <label for="nonspecificOrderSequenceAZ"> Non-specified values sorted alphabetically A-Z</label>  <br />
    			<input type = "radio"  name = "sortTypeIdSpecific"   id = "nonspecificOrderSequence" value = "non_spec_least" />  <label for="nonspecificOrderSequence">Non-specified values sorted most results to least</label>  <br />
    			<input type = "radio"  name = "sortTypeIdSpecific"   id = "nonspecificexcludeAllDoNotDisplay" value = "excludeAll" />  <label for="nonspecificexcludeAllDoNotDisplay">Do not display remaining values</label>  <br />  </li>

	    	</ul>
	    </li>
   
	</div>
 <div class='widget g1'>   
    <h2>Number of Values</h2>
    <ul>
    	<label class='wide' for="facetsMinimumNumber">Minimum number of Facet values to display*</label> 	<select name="minValue" 	 id="minValue" ><option value="" >-</option></select><br /><br />
   
    	<label class='wide' for="facetsMaximumNumber">Maximum number of Facet values to display*</label> <select name="maxValue"  id="maxValue" ><option value="" >-</option></select>
    	<p><span class="facetsMaxMinClass" id="facetsMaxMinClass" style="display:none">The miniumum number of Facet values must be equal to or less than the maximum number of Facet values. Please update</span></p>
    </ul>
</div>

  <div id="facetValueOrderLessThan100" style="visibility:hidden;display:none;margin-top:5px" >
   <div style="width:100%" class='left g3' border="0" cellspacing="5" cellpadding="0">
   <table>
          <tr>
            <td>        
           
                <div id="facetsValuesGridContainer"  class="itemSelect" style="padding:0;margin:0;width:100%;height:100%;">
                <table id="facetsValues">
                </table>
              </div>   
              </td>
          </tr>          
         
    </table>
    </div>
    </div> 
    <div id="facetValueOrderGreaterThan100" style="visibility:hidden;display:none;margin-top:5px" >
    <div style="width:100%" class='left g3 jqgrid' border="0" cellspacing="5" cellpadding="0">
      <table>
          <tr>
            <td>        
           
               <div id="facetValuesOrderGridContainer"  class="itemSelect" style="padding:0;margin:0;width:100%;height:100%;">
                <table id="facetValuesOrder">
                </table>
              </div>   
              </td>
          </tr>          
         
      </table>
    </div>
    <div style="width:100%" class='left g3' border="0" cellspacing="5" cellpadding="0">
     <table>
          <tr>
            <td>   
               <div id="facetValuesBrandNameGridContainer"  class="itemSelect" style="padding:0;margin:0;width:100%;height:100%;">
                <table id="facetValuesBrandName">
                </table>
                <div id="facetValuesBrandNameGridPager"></div>
              </div>   
              </td>
          </tr>          
         
    </table>
    </div>
   </div> 
    <div style="width:100%" class='left g3' border="0" cellspacing="5" cellpadding="0">
     <table>
          <tr>
            <td>   
               <div id="facetValuesInvalidGridContainer"  class="itemSelect" style="padding:0;margin:0;width:100%;height:100%;">
                <table id="facetValuesInvalid">
                </table>
              </div>   
              </td>
          </tr>          
         
    </table>
    </div>
    <div class='widget g1 createFooter pages'>
  
    <h2>Page(s) Where Facet Appears*</h2>

    <div data-bind="foreach: categoryWrapper">
    	<div class="context-container">
		    <ul class='page'>
	    	<!--<span href='#' alt='Click Here to Remove Page' class='removePage ui-icon ui-icon-closethick' data-bind='click: $parent.facetRemoveContext.bind($data, $parent.parent)'>&nbsp;&nbsp;&nbsp;&nbsp;</span></li><li>-->
			<!-- Remove Page Button -->
	    	<li><span href='#' alt='Click Here to Remove Page' class='removeContext ui-icon ui-icon-closethick' data-bind='click: $parent.facetsRemovePage.bind($data, $parent)'>&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
	    	<br/>
			<!-- Create/Change Category Button -->
			<!--ko ifnot: isEdit -->
		    	<li data-bind="attr: { 'id': 'tree'+ $index()}, value: facetsContextUsePartialMatch" " class="tree jstree-default conditionalShow"><span class='button'>Page</span>
		    <!-- /ko -->
		    	<li class="conditionalHide">
		    	<table class='tablepage' style="width: 100%;">
			    	<tr>
				    	<td><p>Page: <span data-bind="text: categoryPath, attr: { 'class': 'categoryPath'+ $index()}"></span></p></td>
						<td>
							<!--ko if:facetAddPagesFacetVisibility()== 'Y' -->
							<span>Display Status: Displayed</span>
							<!-- /ko -->
							<!--ko if:facetAddPagesFacetVisibility() == 'N' -->
							<span>Display Status: Hidden</span>
							<!-- /ko -->
						</td>

						<!-- TODO: Change FacetID to Facet Name wording change to Facet Dependency-->
						<td>
						<!--ko if: facetAddPagesDependentFacet()== 'Y' -->
				    	<p>Facet Display ID: <span data-bind="text: facetAddPagesNameArray()[facetAddPagesDisplayName()]"></span></p>
						<!-- /ko -->
						</td>
					<tr>
				</table>
				</li>
		    <li class="conditionalShow">
		    	<strong>Selected Category: <span data-bind="text: categoryPath, attr: { 'name': 'categoryPath','id': 'categoryPath'+ $index()}"></span></strong>
		    	<input class='categoryPath' data-bind="attr: { 'name': 'categoryId','id': 'category'+ $index()},value: contextPathId" readonly>
		    </li>
		    <li  class="conditionalShow"><label class='wide' >Apply to sub-category:</label> <input type="checkbox" data-bind="checked: applySubCategory"/></li>
	    	<li class="conditionalShow">
		<!--ko  if: contextPathId -->
	    	<label class='wide' for="facetAddPagesDependentFacet">Dependent Upon Another Facet?:</label><br/>
	    	<input type="radio" value="N" data-bind="checked: facetAddPagesDependentFacet" /> <label>No</label><br/><input type="radio" value="Y" data-bind="checked: facetAddPagesDependentFacet" /><label>Yes</label>

		<!-- /ko -->
	    	</li>
		<!-- TODO: Make dependent on 'Dependent Upon Another Facet; selector-->

		<!--ko  if: facetAddPagesDependentFacet()=='Y' -->
		    <li class="conditionalShow"><label class='wide' for="facetAddPagesDisplayName">Select Facet Display Name:<br><i>Grayed out Facets are dependent on this Facet and cannot be selected.</i></label>
			<br/>
			<!--ko if: valid()=='N' -->
				<div class="invalid-facet">The Selected Facet has been deleted.</div>
			<!-- /ko --> 
			<select name='depFacetId' data-bind="options: facetAddPagesDisplayNameOptions, optionsText: 'name', optionsValue: 'value', value: facetAddPagesDisplayName, event: { change: updateDisplayName}" required ></select>
			</li>
		
		<!--ko if: facetAddPagesDisplayName -->
		    <!--<li class="conditionalShow">
            <label for="facetAddPagesDisplayName">Facet Values</label> <select id="facetAddPagesFacet" data-bind=" value: facetAddPagesFacetValues, options: facetAddPagesFacetOptions, optionsText: 'name', optionsValue: 'value' " required ></select>   
  
			</li>-->
		<!-- /ko -->
		<li class="conditionalShow" data-bind="visible:facetAddPagesDisplayName">
  			<input class='autoCompleteButton' data-bind="autoComplete: myOptions, value: mySelectedOption, optionsText: 'name', optionsValue: 'id', autoCompleteOptions: {autoFocus: false}, attr: { 'id': 'facetVal' + $index() }" />
  			<span class="showFacetValues ui-button-text" data-bind="attr: { 'id': 'facetVal' + $index() +'Button'}">Show Values</span>
		</li>
		<!-- /ko -->
		<!--ko  if: (facetAddPagesDependentFacet()=='Y' && facetAddPagesDisplayNameOptions().length == 0) -->
		<li><div class="invalid-facet">Error: No dependant Facets found.</div></li>
		<!-- /ko --> 
           <li class="conditionalShow">
           <label class='wide'  for="facetAddPagesFacetVisibility">Display or Hide on this page:</label><br/>
           <!-- ko if: $parent.facetDisplay()=='Y' -->
            <input type="radio" value="N" data-bind="checked: facetAddPagesFacetVisibility" /><label>Hide</label><br/>
			<input type="radio" value="Y" data-bind="checked: facetAddPagesFacetVisibility" /><label>Display</label> <br/>
           <!-- /ko -->   
           <!-- ko if: $parent.facetDisplay()=='N' -->
           <input type="radio" value="N" data-bind="checked: facetAddPagesFacetVisibility,  attr: { 'disabled': 'disabled' }" /><label>Hide</label><br/>
           <input type="radio" value="N" data-bind="checked: facetAddPagesFacetVisibility,  attr: { 'disabled': 'disabled' }" /><label>Display</label><br/>
           <!-- /ko -->              
			</li>
    </ul>
		</div>
    </div>
<ul><li>
    <span class='button' name="addPages"><a id="addPages"  href='#' data-bind='click: facetsAddPage'>Add Page</a></span> 
    </li></ul>
    <br>
</form>
</div>
<div class="template"> 
<p id="deleteFacets">Are you sure you want to delete following Facets?</p>
</div>	

<div class="findFieldFaceting" id="findFieldFaceting" class="template" title="Find Field for Faceting">
	<label class='wide'>Look up Value</label>
	<input id="lookUpFacetField" />
	<select size="20" id="resultField" style="float:left;"></select>
	<div id="facetValues" style="float:left; padding-left:20px;"></div>
</div>
<div class='createFooter clearfix'>
   <span id='buttonSubmitCreateFacet' class='button buttonSubmitCreateFacet' type='button' data-bind="event: { click: logData}">Create</span> <span id='buttonSubmitCreateFacetCancel' class='button buttonSubmitCreateFacetCancel' type='button'>Cancel</span>
</div>
</div>
<script type="text/javascript" src="js/util/merchValidationUI.js"></script>
<script type="text/javascript" src="js/jQuery/require-jquery.js"></script>

<script type="text/javascript" src="js/jQuery/jquery.jqGrid-4.2.0/js/i18n/grid.locale-en.js"></script>

<script>
	/*****************************
	 * Declare Constant Variable
	 *************************** */

	var currentTermValue;
	
	//Below line loads the required JS files
	require({
		baseUrl : "js/"
	}, [ "facets/facetsCreateModify/facetsFormController"]);

/* This method will be used to toggle the div on the click of the image up and down*/
	function toggle(id, link) {
		 var e = document.getElementById(id);
		  
		   if (e.style.display == '' || e.style.display == 'block') {
		     e.style.display = 'none';
		    link.innerHTML = '<img	src="images/down.png" align="right" style="border:none;">';
		   } else {
		     e.style.display = '';
		     link.innerHTML = '<img	src="images/up.png" align="right" style="border:none;">';
		   }
		 }
</script>
</body>
