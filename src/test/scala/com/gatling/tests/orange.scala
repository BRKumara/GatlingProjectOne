package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class orange extends Simulation {

	val httpProtocol = http
		.baseUrl("https://opensource-demo.orangehrmlive.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("application/json")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:107.0) Gecko/20100101 Firefox/107.0")


	val scn = scenario("orange")
		.exec(http("orangelogin")
			.post("/web/index.php/auth/validate")

			.formParam("_token", "5028de539e91daacef5e949ac31dc0a.XiZkOE3iPt2kYbHMDzbw6cptCtBfBdmIkHI4Pq-xUsU.JhYmZ3-1eI_JBciOQ0CqoaIZZLkzUKDLxwRIW__IIfQuXjN8DqtppZMM8A")
			.formParam("username", "Admin")
			.formParam("password", "admin123")
			.resources(http("request_1")
			.get("/web/index.php/core/i18n/messages"),
				http("request_2")
					.get("/web/images/orange.png?1666596668857"),
				http("request_3")
					.get("/web/index.php/api/v2/dashboard/shortcuts"),
				http("request_4")
					.get("/web/images/orangehrm-logo.png?1666596668857"),
				http("request_5")
					.get("/web/index.php/api/v2/dashboard/employees/leaves?date=2022-12-03"),
				http("request_6")
					.get("/web/index.php/api/v2/dashboard/employees/subunit"),
				http("request_7")
					.get("/web/index.php/api/v2/dashboard/employees/time-at-work?timezoneOffset=5.5&currentDate=2022-12-03&currentTime=08:27"),
				http("request_8")
					.get("/web/index.php/api/v2/dashboard/employees/locations"),
				http("request_9")
					.get("/web/index.php/api/v2/dashboard/employees/action-summary")
				,
				http("request_10")
					.get("/web/index.php/pim/viewPhoto/empNumber/7")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}