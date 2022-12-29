package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class computer1 extends Simulation {

	val httpProtocol = http
		.baseUrl("https://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9,ko-KR;q=0.8,ko;q=0.7,si;q=0.6")
		.contentTypeHeader("application/x-www-form-urlencoded")
		.doNotTrackHeader("1")
		.originHeader("https://computer-database.gatling.io")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
	


val uri1 = "https://o385127.ingest.sentry.io/api/6071844/envelope"

	val scn = scenario("computer")
		.exec(http("request_0")
			.post("/computers")
			.formParam("name", "butest1")
			.formParam("introduced", "2020-12-12")
			.formParam("discontinued", "2022-12-12")
			.formParam("company", "15")
			.resources(http("request_1")
			.post(uri1 + "/?sentry_key=7f832b1e318847f49f65d4d716db714a&sentry_version=7&sentry_client=sentry.javascript.browser%2F7.16.0")
			.body(RawFileBody("com/gatling/tests/computer/0001_request.txt"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}