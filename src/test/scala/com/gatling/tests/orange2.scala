package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class orange3 extends Simulation {

	val httpProtocol = http
		.baseUrl("https://opensource-demo.orangehrmlive.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())


	val uri2 = "https://o385127.ingest.sentry.io/api/6071844/envelope"


	val scn = scenario("orange2")
		.exec(http("request_0")
			.get("/web/index.php/auth/login")
			.resources(http("request_1")
			.get("/web/dist/css/chunk-vendors.css?1666596668857"),


				http("request_2")
					.get("/web/dist/css/app.css?1666596668857"),
				http("request_3")
					.get("/web/dist/js/chunk-vendors.js?1666596668857"),
				http("request_4")
					.get("/web/dist/js/app.js?1666596668857"),
				http("request_5")
					.get("/web/index.php/core/i18n/messages"),
				http("request_6")
					.get("/web/dist/favicon.ico?1666596668857"),
				http("request_7")
					.get("/web/images/ohrm_branding.png?1666596668857"),
				http("request_8")
					.get("/web/dist/img/blob.svg"),
				http("request_9")


					.post(uri2 + "/?sentry_key=7f832b1e318847f49f65d4d716db714a&sentry_version=7&sentry_client=sentry.javascript.browser%2F7.16.0")
			.body(RawFileBody("com/gatling/tests/orange2/0009_request.txt"))))
		.pause(17)
		.exec(http("request_10")
			.post("/web/index.php/auth/validate")
			.formParam("_token", "af72def05574b694ccb7c.VNfXpZRXffizr0ifyW4otCXijt6xB7bMMQrnBjP5UmQ.DeXuz6E9DpCF5WXz8V9761-o4LPuccWWemyNV2qeIDAGpaGRog1Mnsv2fA")
			.formParam("username", "Admin")
			.formParam("password", "admin123")
			.resources(http("request_11")
			.post(uri2 + "/?sentry_key=7f832b1e318847f49f65d4d716db714a&sentry_version=7&sentry_client=sentry.javascript.browser%2F7.16.0")
			.body(RawFileBody("com/gatling/tests/orange2/0011_request.txt")),

				http("request_12")
					.get("/web/index.php/core/i18n/messages"),
				http("request_13")
					.get("/web/images/orange.png?1666596668857"),
				http("request_14")
					.get("/web/images/orangehrm-logo.png?1666596668857"),
				http("request_15")
					.get("/web/index.php/pim/viewPhoto/empNumber/7"),
				http("request_16")
					.get("/web/index.php/api/v2/dashboard/employees/time-at-work?timezoneOffset=5.5&currentDate=2022-12-04&currentTime=09:37"),
				http("request_17")
					.get("/web/index.php/api/v2/dashboard/employees/action-summary"),
				http("request_18")
					.get("/web/index.php/api/v2/dashboard/shortcuts"),
				http("request_19")
					.get("/web/index.php/api/v2/dashboard/employees/leaves?date=2022-12-04"),
				http("request_20")
					.get("/web/index.php/api/v2/dashboard/employees/subunit"),
				http("request_21")
					.get("/web/index.php/api/v2/dashboard/employees/locations")))


	setUp(scn.inject(atOnceUsers(10))).protocols(httpProtocol)
}