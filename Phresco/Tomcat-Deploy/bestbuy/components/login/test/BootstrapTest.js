
var commonVariables = {
	globalconfig : "",
	webserviceurl : "",
	contexturl : "src",
	
	login : "login",
	loginContext : "login",

	
	header : "header",
	headerContext : "synonymgroups",
	
	footer : "footer",
	footerContext : "synonymgroups",
	
	synonyms : "synonyms",
	synonymsContext : "synonymgroups",
	synonymsTitle : "Synonyms List",

	keywords : "keywords",
	keywordsContext : "redirect",
	keywordsTitle: "Keyword List",
	
	banners : "banners",
	bannersContext : "banner",
	bannersTitle : "Banners List",
	
	promo : "promo",
	promoContext : "promo",
	promoTitle : "Promo List",
	
	facets : "facets",
	facetsContext : "facets",
	facetsTitle : "Facets List",
	
	facetOrder : "facetOrder",
	facetOrderContext : "categoryFacet",
	
	boostblock : "boostblock",
	boostblockContext : "boostAndBlock",
	boostblockTitle : "Boost and Block",
	
	pagination : "pagination",
	paginationContext : "",
	
	useradmin : "useradmin",
	useradminContext : "admin",
	useradminTitle : "Users",
	
	search : "search",
	searchTitle : "Search",
	
	navigation : "navigation",
	navigationContext : "",

	categoryTree : "category",
	categoryTreeContent : null,
	
	requestBody : {
		pageIndex : "1",
		rowsPerPage: 10,
		sortColumn : "modifiedDate",
		sortOrder : "desc",
		searchColumnValues : [],
		searchOper: "AND"
	},
	
	edit : "Edit",
	approve : "Approve",
	reject : "Reject",
	deleted : "Delete",
	
	basePlaceholder : "basepage\\:widget",
	headerPlaceholder : "header\\:widget",
	contentPlaceholder : "content\\:widget",
	footerPlaceholder : "footer\\:widget",
	paginationPlaceholder : "pagination\\:widget"
};

define(["jquery"], function($) {
	$(document).ready(function(){
		

		$.get('src/components/login/test/config.json', function(data) {
			
			commonVariables.globalconfig = data;
			//commonVariables.webserviceurl = data.environments.environment.webservice.protocol + "://" + data.environments.environment.webservice.host + ":" + 
			//data.environments.environment.webservice.port + "/" + data.environments.environment.webservice.context + "/";
			commonVariables.webserviceurl = "bestbuy/";
			configJson = {
				// comment out the below line for production, this one is so require doesn't cache the result
				urlArgs: "time=" +  (new Date()).getTime(),
				baseUrl: "src/",
				
				paths : {
					abstract : "js/abstract",
					framework : "js/framework",
					listener : "js/common_components/listener",
					fastclick : "lib/fastclick",
					api : "js/api",
					lib : "lib",
					signals : "lib/signals",
					common : "js/common_components/common",
					modules: "js/common_components/modules",
					Clazz : "js/framework/Class",
					components: "components",
					configData: data,
				}
			};

			$.each(commonVariables.globalconfig.components, function(index, value){
				configJson.paths[index] = value.path;
			});

			// setup require.js
			var requireConfig = requirejs.config(configJson);
			
			/*requireConfig(['require', 'fastclick'], function(fastClick){
				
				new FastClick(document.body);
			});*/
			// fastclick 
			
			require(["loginTest"],	function (loginTest) {
				
				loginTest.runTests(data);
			});
		}, "json");
	});
});