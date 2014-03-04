<div id="facetOrderingTemplate">
<h2>Facet Ordering</h2>
<div id="singleTree" class="singleTree">tree</div>
<div id="sorting-block">
<h2>Facet Ordering</h2>
<p><strong>Facet Ordering for: </strong><span id="singleTreeCategoryPath"></span> (<span data-bind="text: category"></span>)</p>
<p><input id="singleTreeCategory" type="hidden" data-bind="value: category" readonly></p>
<br />
<!--ko if:category -->
<div class="save-cancel-button-group-upper" data-bind="visible:facets().length > 0">
	<span id="save_upper" class="button buttonSubmitCreateFacetCancel" type="button" data-bind="event: { click: saveData}">Edit</span>
	<a id="cancel" href="#" class="linkFacetCancel" data-bind="event: { click: resetData}">Cancel</a>
</div>
<div id="sorting">
<!--ko if:facets().length > 0 -->
	<table>
		<thead>
		    <tr>
		        <td width="5%">Display Order</td>
		        <td width="30%">Facet Name</td>
		        <td width="40%">Values</td>
		        <td width="10%">Status</td>
		        <td width="15%">Edit Display Order</td>
		    </tr>
	    </thead>
	    <tbody id="sortTable" data-bind="foreach: facets, sortable: facets">
	    <tr data-bind="setIndex: 'displayOrder'">
	        <td data-bind="text: $index()+1"></td>
	        <td data-bind="text: displayName+' ('+systemName+')'"></td>
	        <td data-bind="text: values"></td>
	        <td data-bind="text: status"></td>
	        <td><input class="editOrder" data-bind="value: changeVal, event: { change: $parent.reOrder}"/></td>	        
	    </tr>
	    </tbody>
	</table>
<!-- /ko -->
<div class="save-cancel-button-group"  data-bind="visible:facets().length > 0">
	<span id="save_lower" class="button buttonSubmitCreateFacetCancel" type="button" data-bind="event: { click: saveData}">Edit</span>
	<a href="#" id="cancel_lower" class="linkFacetCancel" data-bind="event: { click: resetData}">Cancel</a>
</div>
<!--ko ifnot:facets().length > 0 -->
	<p>No facets present for this category.</p>
<!-- /ko -->
</div>
<br style="clear:both" />
	<h2>Dependent Facets</h2>
	<!--ko if:dependantFacets().length > 0 -->
		<table>
			<thead>
		    <tr>
		        <td>Facet Name</td>
		        <td>Values</td>
		        <td>Status</td>
		    </tr>
		    </thead>
		    <tbody data-bind="foreach: dependantFacets">
		    <tr>
		        <td data-bind="text: displayName"></td>
		        <td data-bind="text: values"></td>
		        <td data-bind="text: status"></td>
		    </tr>
		    </tbody>
		</table>
	<!-- /ko -->
	<!--ko ifnot:dependantFacets().length > 0 -->
		<p>None</p>
	<!-- /ko -->
	<h2>Hidden Facets</h2>
	<!--ko if:hiddenFacets().length > 0 -->
		<table>
		<thead>
	    <tr>
	        <td>Facet Name</td>
	        <td>Values</td>
	        <td>Status</td>
	    </tr>
	    </thead>
	    <tbody data-bind="foreach: hiddenFacets">
	    <tr>
	        <td data-bind="text: displayName"></td>
	        <td data-bind="text: values"></td>
	        <td data-bind="text: status"></td>
	    </tr>
	    </tbody>
		</table>
	<!-- /ko -->
	<!--ko ifnot:hiddenFacets().length > 0 -->
		<p>None</p>
	<!-- /ko -->

<!-- /ko -->
<!--ko ifnot:category -->
<p>Please select a facet from the tree on the left hand side.</p>
<!-- /ko -->
</div>

</div>
<div class="template"> 
<p id="confirmFacetOrder">Are you sure you want to perform this action?</p>
</div>	