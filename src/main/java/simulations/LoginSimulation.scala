package simulations


import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class LoginSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("application/json, text/javascript, */*; q=0.01")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:55.0) Gecko/20100101 Firefox/55.0")

	val headers_0 = Map(
		"Content-Type" -> "application/json; charset=utf-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_1 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_3 = Map(
		"Accept" -> "*/*",
		"Content-Type" -> "application/json; charset=utf-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_4 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_5 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_6 = Map("Accept" -> "*/*")

	val headers_10 = Map(
		"Accept" -> "*/*",
		"Pragma" -> "no-cache")

	val headers_16 = Map(
		"Accept" -> "*/*",
		"X-Requested-With" -> "XMLHttpRequest")

    val uri1 = "http://localhost:8080/NotSoSecureBank"
    val uri2 = "http://detectportal.firefox.com/success.txt"

	val scn = scenario("LoginSimulation")
		.exec(http("request_0")
			.post("/NotSoSecureBank/webresources/session")
			.headers(headers_0)
			.body(RawFileBody("LoginSimulation_0000_request.txt"))
			.resources(http("request_1")
			.get("/NotSoSecureBank/webresources/session")
			.headers(headers_1),
            http("request_2")
			.get("/NotSoSecureBank/webresources/user/test@test.com")
			.headers(headers_1)))
		.pause(12)
		.exec(http("request_3")
			.put("/NotSoSecureBank/webresources/user/test@test.com")
			.headers(headers_3)
			.body(RawFileBody("LoginSimulation_0003_request.txt"))
			.resources(http("request_4")
			.get("/NotSoSecureBank/mypage.html")
			.headers(headers_4),
            http("request_5")
			.get("/NotSoSecureBank/css/bootstrap.min.css")
			.headers(headers_5),
            http("request_6")
			.get("/NotSoSecureBank/common.js")
			.headers(headers_6),
            http("request_7")
			.get("/NotSoSecureBank/js/bootstrap.min.js")
			.headers(headers_6),
            http("request_8")
			.get("/NotSoSecureBank/css/navbar.css")
			.headers(headers_5),
            http("request_9")
			.get("/NotSoSecureBank/mypage.js")
			.headers(headers_6),
            http("request_10")
			.get(uri2 + "")
			.headers(headers_10)))
		.pause(1)
		.exec(http("request_11")
			.get("/NotSoSecureBank/webresources/session")
			.headers(headers_1)
			.resources(http("request_12")
			.get("/NotSoSecureBank/webresources/user/test@test.com")
			.headers(headers_1),
            http("request_13")
			.get("/NotSoSecureBank/webresources/user/test@test.com/transaction")
			.headers(headers_1)))
		.pause(1)
		.exec(http("request_14")
			.get("/NotSoSecureBank/webresources/session")
			.headers(headers_1)
			.resources(http("request_15")
			.get("/NotSoSecureBank/webresources/user/test@test.com")
			.headers(headers_1)))
		.pause(3)
		.exec(http("request_16")
			.delete("/NotSoSecureBank/webresources/session")
			.headers(headers_16))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}