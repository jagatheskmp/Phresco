<div class='deleteContain'></div>
<input type="hidden" id="tabSelection" value="">
<!--
	<div class="widget wide">
	    <h3 id='headerText'>Search Tools</h3>
				<li><label class='wide'><b>Admins</label></b><p>Explanation about this tool, what it can do and live site impact.</p></li>
				<li><label class='wide'><b>Admins</label></b><p>Explanation about this tool, what it can do and live site impact.</p></li>
				<li><label class='wide'><b>Admins</label></b><p>Explanation about this tool, what it can do and live site impact.</p></li>
				<li><label class='wide'><b>Admins</label></b><p>Explanation about this tool, what it can do and live site impact.</p></li>
				<li><label class='wide'><b>Admins</label></b><p>Explanation about this tool, what it can do and live site impact.</p></li>
				<li><label class='wide'><b>Admins</label></b><p>Explanation about this tool, what it can do and live site impact.</p></li>
				<li><label class='wide'><b>Admins</label></b><p>Explanation about this tool, what it can do and live site impact.</p></li>																			
			</li>			
		</div>
	<div class="widget">
	    <h3 id='headerText'>Items that Need Your Attention</h3>
			<li><label class='wide'><b>Admins Name</label></b><p> in the Admin tool has become invalid.</p></li>
			<p>Some content info here</p>																					
			</li>			
		</div>	
	<div class="widget">
	    <h3 id='headerText'>Your latest updates</h3>
			<p>Some updates listed here.</p>																					
			</li>			
		</div>	
</div>	
-->


<table id='deleteSkudialog' title="Remove SKU" width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
	<tr>
		<td>
				<tr>
					<td>
					<form id='delete'>			
						<div id ='boostAndBlockEditCreateFooter' class='createFooterBoostAndBlock clearfix'>
						<label class='wide'>Enter SKU to have it removed from the SOLR Index.</label>
						</br>
						<label class='wide'>NOTE: Only use this in emergency cases.</label>
						</br>
						<label class="inline" for='submitDelete'>SKU: </label>
						<input placeholder='Enter Numerical SKU ID'  required= "required" pattern="[0-9]*" name='skuId'>	
			 			<input type="submit" name="submitButton" value='Remove' style="display:none">
			 			<span id="submitDelete" class="button " type="button" ">Remove</span>
					</div>
					<form>
					</td>
				</tr>

			<div style="width: 1200px; margin-left: 5px; background: #e1eff8;">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="left">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="5" cellpadding="0">
										<tr>
											<td>
												<div id="deleteSkusGroupContainer" class="itemSelect"
													style="padding: 0; margin: 0; width: 100%; height: 100%;">
													<table id="deleteSkusGroup"></table>
														<div id="deleteSkusGroupPager"></div>
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
<div id="confirmDialogDeleteSkus"></div>

