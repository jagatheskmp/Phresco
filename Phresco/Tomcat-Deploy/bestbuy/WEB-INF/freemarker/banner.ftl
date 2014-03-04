
<!-- All script End !-->
<div id="dialog-modal" title="Alert"></div>  
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
		<tr>
			<td>
            <div style="margin-left:5px; border:1px solid #d9d9d9; background:#e1eff8;">
			
				<table  border="0" cellspacing="0" cellpadding="0" align="center" width="100%">
					<tr>
						<td>
							<h1>                                   
								<table width="100%" border="0" cellpadding="0">
  									<tr>
    									<td width="96%">Banner Keyword Search</td>
    									
  									</tr>
								</table>
							</h1>
						</td>
		           	</tr>
		           	<tr>
      					
         				<td valign="top" bgcolor="#FBFBFB" >
         					<div type="hidden" id="searchPanel">
		           				<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
		             				<tr>
		               					<td>
		               					
		                             		<!--Change Done here for the   -->
		               						<table width="100%" border="0" cellspacing="5" cellpadding="0">
		                 								
		                 								<tr>
		                   									<td>
		                   										<table border="0" cellspacing="0" cellpadding="0">
		                     										<tr>
		                       											<td width="396">
		                         											<div id="search1" style="background:#F3F3F3; ">
			                           											<table width="100%" border="0" cellpadding="4" cellspacing="0" class="borderall">
			                             											<tr>
			                               												<td colspan="3">Keyword Search</td>
			                               												<td width="245">
			                               													<input type="text" name="bannerKeywordSearch" id="bannerKeywordSearch" size="24" maxlength="100">
			                               													
			                               												</td>
				                               										</tr>
			                             										</table>
		                             										</div>
																		</td>
		                       											
		                     										</tr>
		                     									</table>
		                     								</td>
		                     								<td>
															</td>
		                 								</tr>
		                 							
		                 								<tr>
		                   									<td>
	
		                     								</td>
		                   								</tr>
		                 							
		                 								<tr>
		                   									<td class="bordertop">
		                   										<table style="display:none" width="100%" border="0" align="center" cellpadding="1" cellspacing="0">
		                     										<tr>

		                       											<td id= "scandate" width="32%" valign="top">
		                       												<table width="100%" border="0" cellpadding="4" cellspacing="0" class="borderall">
		                         											
																				
		                         											</table>
		                         										</td>
		                       										</tr>
		                     									</table>
		                     								</td>
		                   								</tr>
		                 							</table>
												</td>
		             						</tr>
										</table>
										
												<div style="margin-bottom:4px">
														<table width="180" style="display:none" border="0" align="center" cellpadding="0" cellspacing="0">
															<tr>
																<td width="80">
																	<div id="resetBtnDiv" class="buttonwrapper"><a class="ovalShapebutton" href="javaScript:hideres()"><span>Reset</span></a></div>
																</td>
																<td width="100">
																	<div id="Search" class="buttonwrapper"><a class="ovalShapebutton" href="javaScript:showres()"><span>Search Synonyms</span></a></div>
																</td>
															</tr>
														</table>
									 			  </div>
										
										</div>
									</td>
								</tr>
							</table>
							</div>
							
						</td>
      				</tr>
      			
            		<tr>
        				<td>      
        					<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
  								<tr>
    								<td >
    									<div id="searchres" >
        									<table width="100%" border="0" cellspacing="5" cellpadding="0">
          										<tr>
            										<td>        
            										       <div id="bannerGridContainer"  class="itemSelect" style="padding:0;margin:0;width:100%;height:100%;">
                												<table id="bannerGrid">
                												</table>
                											<div id="bannerGridPager"></div>
              											</div>   
              										</td>
          										</tr>          									
          										
       			 							</table>
      									</div>
      								</td>
  								</tr>
							</table>
        				</td>
      				</tr>
      				
    			</table>
    		

</div>
	<!-- ui-dialog -->
	<div id="confirmDialogBanner"></div>
<div class="template">	
	<div id="createBanner">
	<form id="bannerFormCreateTemplate">
    <h2>Banner</h2>
	<ul>
	    <li><label for="bannerName">Banner Name*:</label> <input name="bannerName" class="title" id="bannerName" data-bind="value: bannerName" required /></li>
	    <li><label for="bannerType">Position*:</label> <select id="bannerType" data-bind="options: bannerTypeOptionValues, optionsText: 'name', optionsValue: 'value', value: bannerType" required disabled></select></li>
	    <li><label for="bannerPriority">Priority*:</label> <select id="bannerPriority" data-bind="options: bannerPriorityOptionValues, value: bannerPriority" required ></select></li>
	    <li><label for="startDate">Start Date*:</label> <input id="startDate" name="startDate" data-bind="datepicker: startDate, datepickerOptions: {dateFormat:'mm-dd-yy'}" placeholder="Click to select date." required readonly/></li>
	    <li><label for="endDate">End Date*:</label> <input id="endDate" name="endDate" data-bind="datepicker: endDate, datepickerOptions: {dateFormat:'mm-dd-yy'}" placeholder="Click to select date." readonly/></li>
    </ul>
    
    <h2>Page/s</h2>
    
    <div data-bind="foreach: contexts">
    	<div class="context-container">
		    <ul>
		    <li class='removable-container'>
	    	<span href='#' alt='Click Here to Remove Page/S' class='removeContext ui-icon ui-icon-closethick' data-bind='click: $parent.bannerRemoveContext.bind($data, $parent)'>&nbsp;&nbsp;&nbsp;&nbsp;</span></li><li>
		    	<label>Keywords*:</label>
				<textarea class="cloud keyword"  data-bind="value:keywords, attr: { 'id': 'keywordCloud' + $index() }"></textarea>
			</li>
		    <li><label>Search Profile*:</label> <select data-bind="options: bannerContextSearchProfileOptions, optionsText: 'name', optionsValue: 'value', value: searchProfileId, attr: { 'id': 'searchProfile_' + $index() }" ></select></li>
		    	
		    <!--<li>Use Synonyms: <input type="checkbox" value="use_synonyms" data-bind="checked: bannerContextUseSynonyms"/></li>
		    	<li>Use Partial Match:  <input type="checkbox" value="match_partial" data-bind="checked: bannerContextUsePartialMatch" /></li>-->
		    	
		    <li data-bind="attr: { 'id': 'tree'+ $index()}, value: bannerContextUsePartialMatch" " class="tree jstree-default"><h3>Click Here To Edit Category</h3></li>
		    <li>
		    	<strong>Selected Category*: <span data-bind="text: categoryPath, attr: { 'class': 'categoryPath'+ $index()}"></span></strong>
		    	<input data-bind="attr: { 'id': 'category'+ $index()},value: contextPathId, event: { change: removeAllFacets }" disabled="disabled" >
		    </li>
		    <li><label class='wide'>Apply to sub-category:</label> <input type="checkbox" data-bind="checked: inherit"/></li>
		    
		    <!-- ko if: contextPathId -->
		    <div data-bind="foreach: contextFacetWrapper">    
			    <div class="banner-facet-block">
			    	<li><a href='#' alt='Click Here to Remove Facet' class='remove-banner-facet ui-icon ui-icon-closethick' data-bind='click: $parent.bannerRemoveFacet.bind($data, $parent)'>Remove Facet</a></li>
				    <li><label class="wide" for="facetAddPagesDisplayName">Select Facet Display Name:<br><i>Grayed out facets are dependent on this facet and cannot be selected.</i></label>
					<br/>
					<select data-bind="options: facetAddPagesDisplayNameOptions, optionsText: 'name', optionsValue: 'value', value: facetId, event: { change: updateDisplayName }" required ></select>
					</li>
					<li data-bind="visible:facetId">
			        <label for="facetAddPagesDisplayName">Facet Values</label> 
			        <!--<select id="facetAddPagesFacet" data-bind=" options: facetAddPagesFacetOptions, optionsText: 'name', optionsValue: 'value', value: attributeValueId " required ></select>-->
			          	<input data-bind="autoComplete: myOptions, value: mySelectedOption, optionsText: 'name', optionsValue: 'id', autoCompleteOptions: {autoFocus: false}, attr: { 'id': 'facetVal' + $parentContext.$index() + $index() }" />
  						<span class="showFacetValuesBanner ui-button-text" data-bind="attr: { 'id': 'facetVal' + $parentContext.$index() + $index() +'Button'}">Show Values</span>   
					</li>
				</div>
			</div>
			<span class="button"><a id="addFacet" href='#' data-bind='click: bannerAddFacet'>Add Facet</a></span>
			<!-- /ko -->
			
		    </ul>
		</div>
    </div>
     <ul>
		<li>
     <span class='button' id='addContext' name="addPages"><a id="addPages"  href='#' data-bind='click: bannerAddContext'>Add Page</a></span> 
		</li>
    </ul>
     </br>


    
    <h2>Theme</h2>
    <ul>
    <li><label>Banner Template*:</label><select id="bannerTemplate" data-bind="options: templateOptionValues, value:bannerTemplate, event: { change: generateTemplateHeaders($data)}"></select></label></li>
    <!-- ko if: bannerTemplate() === 'HTML_ONLY' -->
        <li>
        	<label>Asset ID*:</label> <input id="assetId" data-bind="value: documentId">
        	<a id="testLink" href="#"><strong>Click Here To Test</strong></a>
        </li>
    <!-- /ko -->
	    </ul>


	<ul data-bind="foreach: templates, attr: { 'class': 'tagCloud-'+bannerTemplateSlotCountClass }" class="container_3">
     		<li>
     		<label><strong>Header:</strong></label><input data-bind='value: bannerHeader'> 
	        <div data-bind="foreach: bannerSlotSkuLists">
				<textarea cols="30" class="cloud"  data-bind="value:skuIds, attr: { 'id': 'slotCloud' + $parentContext.$index() + $index() }"></textarea>
			</div>
			</li>
	</ul>
	 <!--  
    <div data-bind="foreach: templates" class="container_3">
     	<ul><label><strong>Header:</strong></label><input data-bind='value: bannerHeader'> </ul>
    	<ul>
	        <div data-bind="foreach: bannerSlotSkuLists">
	     		<li class="threeItem">
					<textarea cols="30" class="cloud"  data-bind="value:skuIds, attr: { 'id': 'slotCloud' + $parentContext.$index() + $index() }"></textarea>
				</li>
			</div>
		</ul>
	-->
	</div>
</form>
</div>
</div>
<div class="template"> 
<p id="deleteBanner">Are you sure you want to delete following Banner?</p>
</div>	
