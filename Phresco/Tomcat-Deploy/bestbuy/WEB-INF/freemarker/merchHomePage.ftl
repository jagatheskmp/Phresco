			<#include "header.ftl">
		<table class='gridTable'>
		<input type="hidden" id="tabSelection" value="">	
	<tr>
	<td clospan="3">
	<div id="dialog-modal" title="Alert"></div>  
	<div id="tabsh"  style=" font-size:9px;margin-left:20x; margin-bottom:5px;  border:none">
	    <ul class='navigationBar'>
			<li ><a id="synonymGroupButton" href="#synonyms">Synonyms</a></li>
			<li ><a id="redirectButton" href="#redirects">Keyword Redirects</a></li>
			<li ><a id="bannerButton"href="#banners">Banners</a></li>
			<li ><a id="promoButton" href="#promos">Promo Pages</a></li>
			<li ><a id="facetsButton" href="#facets">Facets</a></li>
			<li ><a id="facetOrderingButton" href="#facetOrdering">Facet Ordering</a></li>
			<li ><a id="boostAndBlockButton" href="#boostAndBlock">Boost/Blocks</a></li>
			<#if model["loggedUser"].role.name = "ROLE_ADMIN">
				<li class='hidden'><a id="adminPageButton" href="#adminPage">Admin Page</a></li>
				<li class='hidden'><a id="deleteSkuButton" href="#deleteSkus">Delete SKU's</a></li>
			</#if>
	    </ul>
	    <div id="synonyms" style="border:1px solid #dddddd; background: #f0f7fc;">
			<div id="synonym">
								<#include "synonymGroup.ftl">
			</div>    	
	    </div>
	    <div id="redirects" style="border:1px solid #dddddd; background: #f0f7fc;">
			<div id="redirect" >
						<#include "/keywordRedirect.ftl">
             </div>	
            				
	 	</div>
	 	<div id="banners" style="border:1px solid #dddddd; background: #f0f7fc;">
			<div id="banner" >
				<table>
					<tr>
					<td>
						<#include "/banner.ftl">
					</td>
					</tr>
				</table>  
             </div>		
	 	</div>
	 	<div id="promos" style="border:1px solid #dddddd; background: #f0f7fc;">
			<div id="promo" >
				<table>
					<tr>
					<td>
						<#include "/promo.ftl">
					</td>
					</tr>
				</table>  
             </div>		
	 	</div>	 	

	 	<div id="facets" style="border:1px solid #dddddd; background: #f0f7fc;">
			<div id="facet" >
				<table class='gridTable'>			
					<tr>
					<td>
						<#include "/facets.ftl">
					</td>
					</tr>
				</table>  
             </div>		
	 	</div>
	 	
	   <div id="facetOrdering" style="border:1px solid #dddddd; background: #f0f7fc;">
			<div id="facetOrdering" >
				<table class='gridTable'>			
					<tr>
					<td>
						<#include "/facetOrdering.ftl">
					</td>
					</tr>
				</table>  
             </div>		
	 	</div>

	    <div id="boostAndBlock" style="border:1px solid #dddddd; background: #f0f7fc;">
			<div id="boostAndBlock" >
				<table class='gridTable'>
					<tr>
					<td>
						<#include "/boostAndBlock.ftl">
					</td>
					</tr>
				</table>  
             </div>		
	 	</div>
		<#if model["loggedUser"].role.name = "ROLE_ADMIN">
	    	<div id="adminPage" style="border:1px solid #dddddd; background: #f0f7fc;">
				<div >
					<table class='gridTable'>
						<tr>
						<td>
							<#include "/adminPage.ftl">
						</td>
						</tr>
					</table>  
    	         </div>		
		 	</div>

    	<div id="deleteSkus" style="border:1px solid #dddddd; background: #f0f7fc;">
				<div id="deleteSku">
					<table class='gridTable'>
						<tr>
						<td>
							<#include "/deleteSkus.ftl">
						</td>
						</tr>
					</table>  
    	         </div>		
		 	</div>
		</#if>
	</div>
	</td>
	</tr>
</table>

						<p align="right">Copyright  2012 <STRONG>Best Buy	Co.</STRONG>. All rights reserved.</p>
<#include "footer.ftl">
</body>
</html>
