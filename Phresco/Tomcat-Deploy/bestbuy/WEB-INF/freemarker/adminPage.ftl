
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


<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="5">
	<tr>
		<td>
			<div
				style="width: 1200px; margin-left: 5px; background: #e1eff8;">

				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="left">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="5" cellpadding="0">
										<tr>
											<td>
												<div id="adminGroupContainer" class="itemSelect"
													style="padding: 0; margin: 0; width: 100%; height: 100%;">
													<table id="adminGroup"></table>
														<div id="adminGroupPager"></div>
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
<div id="confirmDialogAdmin"></div>

<div class="template">

<div id="AdminPrimaryTermCombinedSearch"><input type="text" name="AdminCombinedSearch" id="AdminCombinedSearch"  height="10px"  maxlength="6"></div>	
	

	<p id="deleteAdmin">Are you sure you want to delete following Admin Terms?</p>

	<form id="adminFormCreateTemplate">
		<ul>
			<li>
				<label for="firstName">First Name*</label> <input type="text" placeholder="First Name" class="title" name="firstName" id="firstName" required>
			</li>
			<li>
				<label for="lastName">Last Name*</label> <input type="text" placeholder="Last Name" class="title" name="lastName" id="lastName" required>
			</li>
			<li>
				<label for="loginName">User ID*</label> <input type="text" placeholder="User ID" class="title" name="loginName" id="loginName" required>
			</li>
			<li>
				<label for="userStatus">User Status*</label> 
				<input type="radio" placeholder="User Status" class="title" name="active" id="active" value="true" checked><div>Active</div>
				<input type="radio" placeholder="User Status" class="title" name="active" id="active" value="false"><div>Inactive</div>
			</li>
			<li>
				<label for="roleId">Permission Group*</label> 
				<input type="radio" placeholder="User ID" class="title" name="roleId" id="roleId" value="1" checked><div>Administrative</div>
				<input type="radio" placeholder="User ID" class="title" name="roleId" id="roleId" value="2"><div>Individual Contributor</div>
			</li>
		</ul>
	</form>

	<form id="adminFormEditTemplate">
		<ul>
			<li>
				<label for="firstName">First Name*</label> <input type="text" placeholder="First Name" class="title" name="firstName" id="firstName" required>
			</li>
			<li>
				<label for="lastName">Last Name*</label> <input type="text" placeholder="Last Name" class="title" name="lastName" id="lastName" required>
			</li>
			<li>
				<label for="loginName">User ID*</label> <input type="text" placeholder="User ID" class="title" name="loginName" id="loginName" required>
				<input type="hidden" name="userId" id="userId">
			</li>
			<li>
				<label for="userStatus">User Status*</label> 
				<input type="radio" placeholder="User Status" class="title" name="active" id="active" value="true" checked><div>Active</div>
				<input type="radio" placeholder="User Status" class="title" name="active" id="active" value="false"><div>Inactive</div>
			</li>
			<li>
				<label for="roleId">Permission Group*</label> 
				<input type="radio" placeholder="User ID" class="title" name="roleId" id="roleId" value="1" checked><div>Administrative</div>
				<input type="radio" placeholder="User ID" class="title" name="roleId" id="roleId" value="2"><div>Individual Contributor</div>
			</li>
		</ul>
	</form>
</div>
