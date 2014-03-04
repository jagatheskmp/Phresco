
<div id="dialog-modal" title="Alert"></div>  
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="5">
	<tr>
		<td>
			<div
				style="margin-left: 5px; background: #e1eff8;">

				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="left">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="5" cellpadding="0">
										<tr>
											<td>
												<div id="synonymGroupContainer" class="itemSelect"
													style="padding: 0; margin: 0; width: 100%; height: 100%;">
													<table id="synonymGroup"></table>
														<div id="synonymGroupPager"></div>
												</div>
											</td>
										</tr>

									</table>

								</td>
							</tr>
						</table>
					</td>
				</tr>


			</div>
		</td>
	</tr>
</table>



<!-- ui-dialog -->
<div id="confirmDialogSynonym"></div>

<div class="template">

<div id="SynonymPrimaryTermCombinedSearch"><input type="text" name="synonymCombinedSearch" id="synonymCombinedSearch"  height="10px"  maxlength="100"></div>	
	

	<p id="deleteSynonym">Are you sure you want to delete following Synonym Terms?</p>

	<form id="synonymFormCreateTemplate">
		<ul>
			<li>
			<label for="primaryTerm">Primary Term*</label> <input type=""text placeholder="Type Term name" class="title primaryTerm" name="primaryTerm" id="primaryTerm" required><span class="form_hint">Enter a Primary term:<br> "PS3 to Playstation 3"</span></li>
			</li>
	        <li class='clearfix'>
				    	<label class='wide' for="displayRecurrence">Synonym Lists*</label><br/>
						<input type="radio" id="1133827231862" value="1133827231862" name="listId" class="listId" ><label class='wide' for="listId">Global Synonym List</label><br/>
						<input type="radio" id="1147192016834" value="1147192016834" name="listId" class="listId" ><label class='wide' for="1147192016834">Artist</label><br/>
						<input type="radio" id="1147192098290" value="1147192098290" name="listId" class="listId" ><label class='wide' for="1147192098290">Music</label><br/>
						<input type="radio" id="1147192120811" value="1147192120811" name="listId" class="listId" ><label class='wide' for="1147192120811">Song</label><br/>
   			</li>	    	
	        <li class='clearfix'>
				    	<label class='wide' for="displayRecurrence">Synonym Type*</label><br/>
					<input type="radio" name="directionality"  value="One-way" synonym="" id="One-way"><label for="One-way">One-way Synonym</label><br/>
					<input type="radio" name="directionality"  value="Two-way" synonym="" id="Two-way"><label for="Two-way">Two-way Synonym</label><br/>
   			</li>	   
	        <li class='clearfix'>
				    	<label class='wide' for="displayRecurrence">Match*</label><br/>
					<input type="radio" name="exactMatch"  value="Exact" synonym="" id="Exact"><label for="Exact">Exact</label><br/>
					<input type="radio" name="exactMatch"  value="Broad" synonym="" id="Broad"><label for="Broad">Broad</label><br/>
   			</li>	   
   			
			<li><label>Term(s)*</label>
				<textarea placeholder="Type Term(s) &amp; press Enter Key or Add Term(s) button." id="termCloud" class="cloud"></textarea>
			</li>
		</ul>
	</form>

	<form id="synonymFormEditTemplate">
		<ul>
			<li>
			<label for="primaryTerm">Primary Term*</label> <input type=""text placeholder="Type Term name" class="title primaryTerm" name="primaryTerm" id="primaryTerm" required><span class="form_hint">Enter a Primary term:<br> "PS3 to Playstation 3"</span></li>
			<textarea name="synonymId" class="hidden" id="synonymId"></textarea>
			<input name="statusId" type="hidden" id="statusId"></input>
			<input type="hidden" name="status" id="status">
			</li>
	        <li class='clearfix'>
				    	<label class='wide' for="displayRecurrence">Synonym Lists*</label><br/>
						<input type="radio" id="1133827231862" value="1133827231862" name="listId" class="listId" ><label class='wide' for="listId">Global Synonym List</label><br/>
						<input type="radio" id="1147192016834" value="1147192016834" name="listId" class="listId" ><label class='wide' for="1147192016834">Artist</label><br/>
						<input type="radio" id="1147192098290" value="1147192098290" name="listId" class="listId" ><label class='wide' for="1147192098290">Music</label><br/>
						<input type="radio" id="1147192120811" value="1147192120811" name="listId" class="listId" ><label class='wide' for="1147192120811">Song</label><br/>
   			</li>	    	
	        <li class='clearfix'>
				    	<label class='wide' for="displayRecurrence">Synonym Type*</label><br/>
					<input type="radio" name="directionality"  value="One-way" synonym="" id="One-way"><label for="One-way">One-way Synonym</label><br/>
					<input type="radio" name="directionality"  value="Two-way" synonym="" id="Two-way"><label for="Two-way">Two-way Synonym</label><br/>
   			</li>	   
	        <li class='clearfix'>
				    	<label class='wide' for="displayRecurrence">Match*</label><br/>
					<input type="radio" name="exactMatch"  value="Exact" synonym="" id="Exact"><label for="Exact">Exact</label><br/>
					<input type="radio" name="exactMatch"  value="Broad" synonym="" id="Broad"><label for="Broad">Broad</label><br/>
   			</li>	   
   			
			<li><label>Term(s)*</label>
				<textarea placeholder="Type Term(s) &amp; press Enter Key or Add Term(s) button." id="termCloud" class="cloud"></textarea>
			</li>
		</ul>
	</form>
</div>