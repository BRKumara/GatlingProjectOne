package com.gatling.tests

import scala.concurrent.duration._
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.core.structure.ScenarioBuilder


class ComeSearchandEdit5 extends Simulation {

	val httpProtocol = http
		.baseUrl("https://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9,ko-KR;q=0.8,ko;q=0.7,si;q=0.6")
		.doNotTrackHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")

//	val uri1 = "https://o385127.ingest.sentry.io/api/6071844/envelope"



	val search = exec(http("Home")
		.get("/computers"))
		.pause(2)
		.exec(http("Search").get("/computers?f=ACE"))
		.pause(2)
		.exec(http("Select").get("/computers/381"))


val update = exec(http("Update")
			.post("/computers/381")
			.formParam("name", "ACE")
			.formParam("introduced", "2000-12-12")
			.formParam("discontinued", "2022-12-12")
			.formParam("company", "2"))



	val users1 =scenario("User One").exec(search,update)
	val users2 =scenario("User Two").exec(search,update)

//	setUp(
//		users1.inject(rampUsers(10).during(10)),
//		users2.inject(rampUsers(20).during(10))
//	).protocols(httpProtocol)


	setUp(
//		users1.inject(atOnceUsers(10))
		users1.inject(constantUsersPerSec(20).during(15))
	).protocols(httpProtocol)


}
