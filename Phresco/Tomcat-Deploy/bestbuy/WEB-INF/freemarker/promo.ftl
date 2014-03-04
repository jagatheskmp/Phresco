 
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
		<tr>
			<td>
            
		             <div id="promoGroupContainer"  class="itemSelect" style="padding:0;margin:0;width:100%;height:100%;">
	                	<table id="promoGroup"></table>
	                	 <div id="promoGroupPager"></div>
             		 </div> 
        	</td>
      	 </tr>	
    </table>


<div id="confirmDialogPromo"></div>		
<div class="template">
<p id="deletePromo">Are you sure you want to delete  following Promo Names?</p>
		<form id="promoCreateTemplate">	
				<ul>
				<li>
						<label>Promo Name</label><input type="text" name="promoName"  class='title' id="promoName" placeholder="Enter the Promo Name" required><span class="form_hint">Enter a Promo name:<br> "Apple summer blowout sale."</span> 
				</li>
				<li>
					<label>SKU IDs*</label>
					<textarea placeholder="Type term &amp; press enter to add" id="newSkuId" class="cloud"></textarea>
				</li>
				<li>
					 <label for="startDatePromo">Start Date</label>
					 <input type="text" name="startDatePromo" id="startDatePromo" placeholder="Click to select date." required pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" readonly/> <span class="form_hint">Proper format "12-22-2012"</span>
  					 <input type="hidden" name="startDate" id="startDate">
				</li>
				<li>
					<label for="endDatePromo">End Date</label>
					<input type="text" name="endDatePromo" id="endDatePromo" placeholder="Click to select date." pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" readonly/><span class="form_hint">Proper format "12-22-2012"</span>
					<input type="hidden" name="endDate" id="endDate">
				</li>
				</ul>
		</form>

		<form id="promoEditTemplate">			
				<ul>
				<li>
						<label>Promo Name</label><input type="text" name="promoName" class='title' id="promoName" placeholder="Enter the Promo Name" required><span class="form_hint">Enter a Promo name:<br> "Apple summer blowout sale."</span> 
						<input name="promoId" id="promoId" type="hidden">
						<input name="statusId" id="statusId" type="hidden">
					    <input name="status" id="status" type="hidden">
					    <input name="modifiedBy" id="modifiedBy" type="hidden">
					    <input name="modifiedDate" id="modifiedDate" type="hidden">
					    <input name="createdBy" id="createdBy" type="hidden">
					    <input name="createdDate" id="createdDate" type="hidden">
				</li>
				<li>
					<label>SKU IDs*</label>
					<textarea placeholder="Type term &amp; press enter to add" id="newSkuId" class="cloud"></textarea>
				</li>
				<li>
					 <label for="startDate">Start Date</label>
					 <input type="text" name="startDatePromo" id="startDatePromo" placeholder="Click to select date." required pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" /><span class="form_hint">Proper format "12-22-2012"</span>
  					 <input type="hidden" name="startDate" id="startDate">
				</li>
				<li>
					<label for="endDate">End Date</label>
					<input type="text" class="endDatePromo" name="endDate" id="endDatePromo" placeholder="Click to select date." pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" /><span class="form_hint">Proper format "12-22-2012"</span>
					<input type="hidden" name="endDate" id="endDate">
				</li>
				</ul>
		</form>
	  
</div>	