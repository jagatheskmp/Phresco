$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('com\photon\phresco\testcases\helloworld.feature');
formatter.feature({
  "id": "hello-world",
  "description": "As it is a Hello World project\r\nI want the browser to display Hello World",
  "name": "Hello World",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "hello-world;hello-world-should-be-displayed",
  "description": "",
  "name": "Hello World should be displayed",
  "keyword": "Scenario",
  "line": 5,
  "type": "scenario"
});
formatter.step({
  "name": "the user is on the widgets page",
  "keyword": "Given ",
  "line": 6
});
formatter.step({
  "name": "the page title returned should be \"Hello World\"",
  "keyword": "Then ",
  "line": 8
});
formatter.match({
  "location": "HelloWorldTestCase.The_user_is_on_the_Widgets_page()"
});
formatter.result({
  "duration": 9858763055,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Hello World",
      "offset": 35
    }
  ],
  "location": "HelloWorldTestCase.thePageTitleReturned(String)"
});
formatter.result({
  "duration": 181092289,
  "status": "failed",
  "error_message": "junit.framework.AssertionFailedError\r\n\tat junit.framework.Assert.fail(Assert.java:48)\r\n\tat junit.framework.Assert.assertTrue(Assert.java:20)\r\n\tat junit.framework.Assert.assertTrue(Assert.java:27)\r\n\tat com.photon.phresco.Screens.BaseScreen.isTextPresent(BaseScreen.java:207)\r\n\tat com.photon.phresco.testcases.HelloWorldTestCase.thePageTitleReturned(HelloWorldTestCase.java:61)\r\n\tat ✽.Then the page title returned should be \"Hello World\"(com\\photon\\phresco\\testcases\\helloworld.feature:8)\r\n"
});
});