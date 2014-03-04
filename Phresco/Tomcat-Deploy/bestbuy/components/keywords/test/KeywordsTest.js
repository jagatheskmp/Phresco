/*global require */
	data = {
			"apiAddress" : {
				"host" : "http://localhost",
				"port" : "8034"
			},
			
			"webSocketAddress" : {
				"host" : "ws://localhost",
				"port" : "8034"
			},
			
			"pagination" : {
				"rowsPerPage" : 10
			},
			
			"navigation" : {
				"transitionType" : 1,
				"isNative" : false
			},

			"components" : {
				"login" : {
					"moduleName":"login",
					"host":"http://localhost",
					"port":"8034",
					"path" : "../components/login/js"
				},
			},
		};
		
		configJson = {
			
			paths : {
				abstract : "js/abstract",
				framework : "js/framework",
				listener : "widget/listener",
				api : "js/api",
				lib : "lib",
				Clazz : "framework/Class", 
				common : "js/common_components/common",
				components: "../components",
				login: "components/login/js",
				header: "js/common_components/modules/header/js",
				footer: "js/common_components/modules/footer/js",
				navigation: "components/navigation/js",
				synonyms: "components/synonyms/js",
				pagination: "components/pagination/js",
				banners: "components/banners/js",
				keywords: "components/keywords/js",
				promopages: "components/promopages/js",
				facetorder: "components/facetorder/js",
				facets: "components/facets/js",
				boostblock: "components/boostblock/js",
				useradmin: "components/useradmin/js"
			}
		};
		
		var commonVariables = {
			globalconfig : "",
			webserviceurl : "",
			
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
			promoTitle : "Promolist List",
			
			facets : "facets",
			facetsContext : "facets",
			facetsTitle : "Facets List",
			
			facetOrder : "facetOrder",
			facetOrderContext : "",
			
			boostblock : "boostblock",
			boostblockContext : "boostAndBlock",
			boostblockTitle : "Boost and Block",
			
			pagination : "pagination",
			paginationContext : "",
			
			useradmin : "useradmin",
			useradminContext : "admin",
			useradminTitle : "Users",
			
			navigation : "navigation",
			navigationContext : "",

			categoryTree : "category",
			
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

		// setup require.js
		requirejs.config(configJson);
		
require([ "jquery", "lib/signals", "lib/jquery.tablesorter", "lib/handlebars-1.0.0.beta.6", "keywords/Keyword", "framework/NavigationController"], function($, signals, tablesorter, handlebars, Keyword, navigation) {
	/**
	 * Test that the setMainContent method sets the text in the MyCart-widget
	 */
	  module("Keyword.js;Keyword");
	asyncTest("Test Keyword with same Data.", function() {
		var keywords, output1, output2, output, navigationController, widgetWithTemplate, jsonData;
			
			Clazz.config = data;
			Clazz.navigationController = new Clazz.NavigationController({
				mainContainer : "basepage\\:widget",
				transitionType : Clazz.config.navigation.transitionType,
				isNative : Clazz.config.navigation.isNative
			});
			
			keywords = new Keyword();
			jsonData = {"status":"SUCCESS","message":"Redirect listing was successful","errorCode":null,"successCode":"Listing.Success","data":{"pageIndex":1,"rowsPerPage":10,"totalCountOfPages":2,"totalCount":12,"sortColumn":"modifiedDate","sortOrder":"desc","searchOper":"AND","searchColumnValues":[],"totalCountOfRecords":12},"page":"1","rows":[{"redirectTerm":"sfdsadf","redirectType":"URL","searchProfileType":"Global","redirectUrl":"sdf","startDate":"03-01-2023 01:05","endDate":"04-04-2023 00:00","redirectId":33000,"statusId":3,"searchProfileId":1,"modifiedBy":"MerchandisingUI.BestBuy","modifiedDate":"02-01-2013 12:50","createdDate":null,"createdBy":null,"status":"Approved","id":0,"actions":[{"key":"Edit","value":"Edit"},{"key":"Reject","value":"Reject"},{"key":"Delete","value":"Delete"}]},{"redirectTerm":"Nokia","redirectType":"URL","searchProfileType":"Global","redirectUrl":"best/com","startDate":"01-30-2013 15:42","endDate":"01-31-2013 00:00","redirectId":32700,"statusId":4,"searchProfileId":1,"modifiedBy":"MerchandisingUI.BestBuy","modifiedDate":"01-31-2013 00:00","createdDate":null,"createdBy":null,"status":"Expired","id":1,"actions":[{"key":"Edit","value":"Edit"},{"key":"Delete","value":"Delete"}]},{"redirectTerm":"LG_Tv","redirectType":"URL","searchProfileType":"Global","redirectUrl":"best/com","startDate":"01-30-2013 15:43","endDate":"01-31-2013 00:00","redirectId":32701,"statusId":4,"searchProfileId":1,"modifiedBy":"MerchandisingUI.BestBuy","modifiedDate":"01-31-2013 00:00","createdDate":null,"createdBy":null,"status":"Expired","id":2,"actions":[{"key":"Edit","value":"Edit"},{"key":"Delete","value":"Delete"}]},{"redirectTerm":"g","redirectType":"URL","searchProfileType":"Global","redirectUrl":"g","startDate":"01-29-2013 14:26","endDate":"01-30-2013 00:00","redirectId":32600,"statusId":4,"searchProfileId":1,"modifiedBy":"MerchandisingUI.BestBuy","modifiedDate":"01-30-2013 00:00","createdDate":null,"createdBy":null,"status":"Expired","id":3,"actions":[{"key":"Edit","value":"Edit"},{"key":"Delete","value":"Delete"}]},{"redirectTerm":"test3","redirectType":"URL","searchProfileType":"Global","redirectUrl":"test3/url","startDate":"01-24-2013 00:00","endDate":null,"redirectId":27056,"statusId":3,"searchProfileId":1,"modifiedBy":"MerchandisingUI.BestBuy","modifiedDate":"01-29-2013 13:11","createdDate":null,"createdBy":null,"status":"Approved","id":4,"actions":[{"key":"Edit","value":"Edit"},{"key":"Reject","value":"Reject"},{"key":"Delete","value":"Delete"}]},{"redirectTerm":"Myogenic2","redirectType":"URL","searchProfileType":"Global","redirectUrl":"http://www.weather.com","startDate":"01-21-2013 00:00","endDate":"01-23-2013 00:00","redirectId":31110,"statusId":4,"searchProfileId":1,"modifiedBy":"MerchandisingUI.BestBuy","modifiedDate":"01-23-2013 00:00","createdDate":null,"createdBy":null,"status":"Expired","id":5,"actions":[{"key":"Edit","value":"Edit"},{"key":"Delete","value":"Delete"}]},{"redirectTerm":"TermLakshmi3","redirectType":"URL","searchProfileType":"Global","redirectUrl":"http://www.google.com","startDate":"01-21-2013 00:00","endDate":"01-23-2013 00:00","redirectId":31050,"statusId":4,"searchProfileId":1,"modifiedBy":"MerchandisingUI.BestBuy","modifiedDate":"01-23-2013 00:00","createdDate":null,"createdBy":null,"status":"Expired","id":6,"actions":[{"key":"Edit","value":"Edit"},{"key":"Delete","value":"Delete"}]},{"redirectTerm":"KeywordRedirect1","redirectType":"URL","searchProfileType":"Global","redirectUrl":"KeywordRedirect1/Url","startDate":"01-21-2013 00:00","endDate":"01-22-2013 00:00","redirectId":29119,"statusId":4,"searchProfileId":1,"modifiedBy":"MerchandisingUI.BestBuy","modifiedDate":"01-22-2013 00:00","createdDate":null,"createdBy":null,"status":"Expired","id":7,"actions":[{"key":"Edit","value":"Edit"},{"key":"Delete","value":"Delete"}]},{"redirectTerm":"abcde","redirectType":"URL","searchProfileType":"Global","redirectUrl":"http://www.abcde.com","startDate":"01-21-2013 00:00","endDate":"01-21-2013 00:00","redirectId":30051,"statusId":4,"searchProfileId":1,"modifiedBy":"Joseph.Ford","modifiedDate":"01-21-2013 00:00","createdDate":null,"createdBy":null,"status":"Expired","id":8,"actions":[{"key":"Edit","value":"Edit"},{"key":"Delete","value":"Delete"}]},{"redirectTerm":"cod","redirectType":"URL","searchProfileType":"Global","redirectUrl":"/URL","startDate":"01-17-2013 00:00","endDate":"01-18-2013 00:00","redirectId":27054,"statusId":4,"searchProfileId":1,"modifiedBy":"MerchandisingUI.BestBuy","modifiedDate":"01-18-2013 00:00","createdDate":null,"createdBy":null,"status":"Expired","id":9,"actions":[{"key":"Edit","value":"Edit"},{"key":"Delete","value":"Delete"}]}],"generalPurposeMessage":null}

			setTimeout(function() {
				start();
				output1 = jsonData.status;
				output2 = "SUCCESS";
				equal(output1, output2,	"Success case for Keywords");
			}, 2000);	
	});
	
	asyncTest("Test Keyword with different Data.", function() {
		var keywords, output1, output2, output, navigationController, widgetWithTemplate, jsonData;
			
			Clazz.config = data;
			Clazz.navigationController = new Clazz.NavigationController({
				mainContainer : "basepage\\:widget",
				transitionType : Clazz.config.navigation.transitionType,
				isNative : Clazz.config.navigation.isNative
			});
			
			keywords = new Keyword();
			jsonData = {"status":"ERROR","message":"Error while retrieving the Keyword list","errorCode":"Listing.Error","successCode":null,"data":null,"page":"1","rows":null,"generalPurposeMessage":null}

			setTimeout(function() {
				start();
				output1 = jsonData.status;
				output2 = "ERROR";
				equal(output1, output2,	"Success case for Keywords");
			}, 2000);	
	});
});
