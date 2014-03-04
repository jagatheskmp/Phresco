 
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
		<tr>
			<td>
            		<div id="keywordRedirectContainer"  class="itemSelect" style="padding:0;margin:0;width:100%;height:100%;">
                		<table id="keywordRedirect"></table>
                	  <div id="keywordRedirectPager"></div>
                	 </div> 
        	</td>
      	 </tr>	
    </table>

<div id="confirmDialogRedirect"></div>	


<div class="template">
	<div id="RedirectTermURLCombinedSearch">
		<input type="text" name="redirectCombinedSearch" id="redirectCombinedSearch" height="10px" maxlength="100">
	</div>
<p id="deleteRedirect">Are you sure you want to delete following Keyword Term?</p>	
	<form id="redirectCreateTemplate">
		<ul>
			<li><label for='redirectTerm'>Redirect Term*</label><input type="text" name="redirectTerm" placeholder='Enter a redirect term:' id="redirectTerm" class='title' required><span class="form_hint">Enter a redirect term:<br> "Blue Laptop to Laptop Page"</span></li>
			<li><label for='redirectType'>Redirect Type*</label>
				<div style="margin-top: 1em;">
				<label for="URL" class='wide'>Url</label>
					<input type="radio" name="redirectType" value="URL" synonym="" id="URL" checked="checked">
				</div>
			</li>
			<li><label class='title' for="redirectUrl">Redirect Url*</label> <input type="url" class="title redirectUrl" name="redirectUrl" placeholder="Enter a redirect URL" id="redirectUrl" required><span class="form_hint">Enter a redirect URL:<br>"http://someaddress.com" or "/somepath"</span></li>
			<li><label for='searchProfileId'>Search Profile Id*</label> <select name="searchProfileId" id="searchProfileId" pattern="(((http|https|ftp)://([\w-\d]+\.)+[\w-\d]+){0,1} (/[\w~,;\-\./?%&+#=]*) )" required disabled>
					<option value="1" id="Global">Global</option>
			</select></li>
			<li><label for="startDate">Start Date*</label> <input type="text" name="startDate" id="startDate" placeholder="Click to select date." required pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" readonly/> <span class="form_hint">Proper format "12-22-2012"</span></li>
			<li><label for="endDate">End Date</label> <input type="text" class="endDate" name="endDate" id="endDate" placeholder="Click to select date." pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" readonly/> <span class="form_hint">Proper format "12-22-2012"</span></li>
		</ul>
	</form>

	<form id="redirectEditTemplate">
		<ul>
			<li><label for='redirectTerm'>Redirect Term*</label><input type="text" name="redirectTerm" placeholder='Enter a redirect term:' id="redirectTerm" class='title' required><span class="form_hint">Enter a redirect term:<br> "Blue Laptop to Laptop Page"</span></li>
			<textarea name="redirectId" id="redirectId" class='hidden'></textarea>
			<input name="status" type="text" id="status" class='hidden'/>
			<textarea name="statusId" id="statusId" class='hidden'></textarea>
			<li><label for='redirectType'>Redirect Type*</label>
				<div style="margin-top: 1em;">
				<label for="URL">Url</label>
					<input type="radio" name="redirectType" value="URL" synonym="" id="URL" checked="checked">
				</div>
			</li>
			<li><label class='title' for="redirectUrl">Redirect Url*</label> <input type="url" class="title redirectUrl" name="redirectUrl" placeholder="Enter a redirect URL" id="redirectUrl" required><span class="form_hint">Enter a redirect URL:<br>"http://someaddress.com" or "/somepath"</span></li>
			<li><label for='searchProfileId'>Search Profile Id*</label> <select name="searchProfileId" id="searchProfileId" pattern="(((http|https|ftp)://([\w-\d]+\.)+[\w-\d]+){0,1} (/[\w~,;\-\./?%&+#=]*) )" required disabled>
				<option value="1" id="Global">Global</option>
			</select></li>
			<li><label for="startDate">Start Date*</label> <input type="text" name="startDate" id="startDate" placeholder="Click to select date." required pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" /> <span class="form_hint">Proper format "12-22-2012"</span></li>
			<li><label for="endDate">End Date</label> <input type="text" class="endDate" name="endDate" id="endDate" placeholder="Click to select date." pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" /> <span class="form_hint">Proper format "12-22-2012"</span></li>		
			</ul>
	</form>
</div>
