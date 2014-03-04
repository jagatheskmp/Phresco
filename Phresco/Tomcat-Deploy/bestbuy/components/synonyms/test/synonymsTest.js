
define(["synonyms/Synonyms", "framework/NavigationController", "framework/WidgetWithTemplate","synonyms/listener/synonymListener", "lib/bootstrap.min"], function(Synonyms, navigation, WidgetWithTemplate, synonymListener) {
	var ids;

	return { runTests: function (configData) {
		/**
		 * Test that the setMainContent method sets the text in the MyCart-widget
		 */
		module("Synonyms.js;Synonyms");
		asyncTest("Test - Create Synonyms.", function() {
			var synonyms, output1, output2, output, navigationController, widgetWithTemplate, synonymlistener, jsonData; 
			Clazz.config = configData;
			Clazz.navigationController = new Clazz.NavigationController({
				mainContainer : "basepage\\:widget",
				transitionType : Clazz.config.navigation.transitionType,
				isNative : Clazz.config.navigation.isNative
			});
			synonyms = new Synonyms();
			
			jsonData = {"primaryTerm":"jsunit012","listId":"1133827231862","directionality":"One-way","exactMatch":"Exact","term":["ee"]};
			synonyms.getAction(jsonData, "create", "", function(response){
				output1 = response.status;
				ids = response.rows[0].synonymId;
				primaryTerm = response.rows[0].primaryTerm;
			});
			
			setTimeout(function() {
				output2 = "SUCCESS";
				equal(output1, output2,	"Successfully created Synonyms");
				start();
			}, 2500);
				
		});
		
		asyncTest("Test - Duplicate Synonyms.", function() {
			
			var synonyms, output1, output2, output, navigationController, widgetWithTemplate, synonymlistener, jsonData; 
			Clazz.config = configData;
			Clazz.navigationController = new Clazz.NavigationController({
				mainContainer : "basepage\\:widget",
				transitionType : Clazz.config.navigation.transitionType,
				isNative : Clazz.config.navigation.isNative
			});
			synonyms = new Synonyms();			
			
			jsonData = {"primaryTerm":"jsunit012","listId":"1133827231862","directionality":"One-way","exactMatch":"Exact","term":["ee"]};
			synonyms.getAction(jsonData, "create", "", function(response){
				output1 = response.generalPurposeMessage[0];
			});
			
			setTimeout(function() {
				output2 = "primaryTerm:Primary Term is a duplicate and cannot be used";
				equal(output1, output2,	"Successfully Synonyms duplicate Checked");
				start();
			}, 2500);
		
		});
		
		asyncTest("Test - Reject Synonym data.", function() {
			var synonyms, output1, output2, output, navigationController, widgetWithTemplate, synonymlistener, jsonData, message; 
				Clazz.config = configData;
				Clazz.navigationController = new Clazz.NavigationController({
					mainContainer : "basepage\\:widget",
					transitionType : Clazz.config.navigation.transitionType,
					isNative : Clazz.config.navigation.isNative
				});
				synonyms = new Synonyms();
				
				jsonData = {};
				synonyms.getAction(jsonData, "reject", ids, function(response){
					$.each(response.rows, function(index, value) {
						if(value.synonymId == ids){
							message = value.status;
							output1 = message;
						} 
					});
				});
			
				setTimeout(function() {
					output2 = "Rejected";
					equal(output1, output2,	"Successfully Synonym Rejected");
					start();
				}, 2500);				
				
		});
		
		asyncTest("Test - Approve Synonym data.", function() {
			var synonyms, output1, output2, output, navigationController, widgetWithTemplate, synonymlistener, jsonData; 
				Clazz.config = configData;
				Clazz.navigationController = new Clazz.NavigationController({
					mainContainer : "basepage\\:widget",
					transitionType : Clazz.config.navigation.transitionType,
					isNative : Clazz.config.navigation.isNative
				});
				synonyms = new Synonyms();
				
				jsonData = {};
				synonyms.getAction(jsonData, "approve", ids, function(response){
					$.each(response.rows, function(index, value) {
						if(value.synonymId == ids){
							message = value.status;
							output1 = message;
						} 
					});
				});
			
				setTimeout(function() {
					output2 = "Approved";
					equal(output1, output2,	"Successfully Synonym approved");
					start();
				}, 1500);				
				
		});
		
		asyncTest("Test - Load Synonym data.", function() {
			var synonyms, output1, output2, output, navigationController, widgetWithTemplate, synonymlistener, requestBody; 
				Clazz.config = configData;
				Clazz.navigationController = new Clazz.NavigationController({
					mainContainer : "basepage\\:widget",
					transitionType : Clazz.config.navigation.transitionType,
					isNative : Clazz.config.navigation.isNative
				});
				synonyms = new Synonyms();
				synonymlistener = new synonymListener();
				requestBody = {};				
				requestBody.pageIndex = "1";
				requestBody.rowsPerPage = 10;
				requestBody.sortColumn  = "modifiedDate";
				requestBody.sortOrder  = "desc";
				requestBody.searchColumnValues  = [];
				requestBody.searchOper = "AND";
				synonymlistener.synonymAction(synonymlistener.getRequestHeader(requestBody), function(response){
					output1 = response.message;
				});
			
				setTimeout(function() {
					output2 = "Synonym listing was successful";
					equal(output1, output2,	"Successfully Synonym approved");
					start();
				}, 1600);				
				
		});
		
		/*
		asyncTest("Test - Update Synonym data.", function() {
			var synonyms, output1, output2, output, navigationController, widgetWithTemplate, synonymlistener, jsonData; 
			Clazz.config = configData;
			Clazz.navigationController = new Clazz.NavigationController({
				mainContainer : "basepage\\:widget",
				transitionType : Clazz.config.navigation.transitionType,
				isNative : Clazz.config.navigation.isNative
			});
			synonyms = new Synonyms();
			
			jsonData = {"primaryTerm":"components","directionality":"One-way","exactMatch":"Exact","synonymListType":"Global_syn_list1","startDate":null,"endDate":null,"id":0,"listId":1133827231862,"synonymId":ids,"createdDate":null,"createdBy":null,"modifiedBy":"Test.User","modifiedDate":"03-14-2013 17:51","statusId":6,"status":"Rejected","term":["ee"],"actions":null};
			//synonyms.getAction(jsonData, "update", "", function(response){
				//output1 = response.data.primaryTerm;
			//});
			
			
			jsonData= {};
			synonyms.getAction(jsonData, "update", ids, function(response){
				// output1 = response.data.primaryTerm;
				$.each(response.rows, function(index, value) {
						if(value.synonymId == ids){
							message = value.status;
							output1 = value.primaryTerm;
						} 
					});
			});
			
			jsonData = {"primaryTerm":"components"};
			setTimeout(function() {
				output2 = jsonData.primaryTerm;
				//var result = true;
				//for (var i = 0; i < output2.length; ++i) {
				//	if (output1.indexOf(output2[i]) === -1){
				//		result = false;
				//		break;
				//	}
				//}
				//equal(result, true,	"Successfully Synonym updated"); 
				equal(output1, output2,	"Successfully Synonym updated");
				start();
			}, 1500);				
				
		});*/
		
		
		
		/* asyncTest("Test - Delete Synonym data.", function() {
			var synonyms, output1, output2, output, navigationController, widgetWithTemplate, synonymlistener, jsonData; 
				Clazz.config = configData;
				Clazz.navigationController = new Clazz.NavigationController({
					mainContainer : "basepage\\:widget",
					transitionType : Clazz.config.navigation.transitionType,
					isNative : Clazz.config.navigation.isNative
				});
				synonyms = new Synonyms();
				
				jsonData = {};
				synonyms.getAction(jsonData, "delete", ids, function(response){
					$.each(response.rows, function(index, value) {
						if(value.synonymId != ids){
							output1 = "Synonym deleted successfully";
						}
					});
				});
			
				setTimeout(function() {
					output2 = "Synonym deleted successfully";
					equal(output1, output2,	"Successfully Synonym approved");
					start();
				}, 10000);				
				
		});  */
		
		
	}};
});
