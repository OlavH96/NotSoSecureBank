
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class LoginAndSendMoneySimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("application/json, text/javascript, */*; q=0.01")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:55.0) Gecko/20100101 Firefox/55.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_3 = Map("Accept" -> "*/*")

	val headers_6 = Map(
		"Accept" -> "*/*",
		"Pragma" -> "no-cache")

	val headers_7 = Map(
		"Accept" -> "*/*",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_8 = Map(
		"Content-Type" -> "application/json; charset=utf-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_9 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_13 = Map(
		"Accept" -> "*/*",
		"Content-Type" -> "application/json; charset=utf-8",
		"X-Requested-With" -> "XMLHttpRequest")

    val uri1 = "http://localhost:8080/NotSoSecureBank"
    val uri2 = "http://detectportal.firefox.com/success.txt"

	val scn = scenario("LoginAndSendMoneySimulation")
		.exec(http("request_0")
			.get("/NotSoSecureBank/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/NotSoSecureBank/css/bootstrap.min.css")
			.headers(headers_1),
            http("request_2")
			.get("/NotSoSecureBank/css/navbar.css")
			.headers(headers_1),
            http("request_3")
			.get("/NotSoSecureBank/js/bootstrap.min.js")
			.headers(headers_3),
            http("request_4")
			.get("/NotSoSecureBank/index.js")
			.headers(headers_3),
            http("request_5")
			.get("/NotSoSecureBank/css/signin.css")
			.headers(headers_1),
            http("request_6")
			.get(uri2 + "")
			.headers(headers_6)))
		.pause(5)
		.exec(http("request_7")
			.delete("/NotSoSecureBank/webresources/session")
			.headers(headers_7))
		.pause(4)
		.exec(http("request_8")
			.post("/NotSoSecureBank/webresources/session")
			.headers(headers_8)
			.body(RawFileBody("LoginAndSendMoneySimulation_0008_request.txt"))
			.resources(http("request_9")
			.get("/NotSoSecureBank/webresources/session")
			.headers(headers_9),
            http("request_10")
			.get("/NotSoSecureBank/webresources/user/test@test.com")
			.headers(headers_9)))
		.pause(1)
		.exec(http("request_11")
			.get("/NotSoSecureBank/webresources/session")
			.headers(headers_9)
			.resources(http("request_12")
			.get("/NotSoSecureBank/webresources/user/test@test.com")
			.headers(headers_9)))
		.pause(10)
		.exec(http("request_13")
			.post("/NotSoSecureBank/webresources/user/test@test.com/transaction")
			.headers(headers_13)
			.body(RawFileBody("LoginAndSendMoneySimulation_0013_request.txt"))
			.resources(http("request_14")
			.get("/NotSoSecureBank/webresources/session")
			.headers(headers_9),
            http("request_15")
			.get("/NotSoSecureBank/webresources/user/test@test.com")
			.headers(headers_9),
            http("request_16")
			.get("/NotSoSecureBank/webresources/user/test@test.com/transaction")
			.headers(headers_9)))
		.pause(6)
		.exec(http("request_17")
			.delete("/NotSoSecureBank/webresources/session")
			.headers(headers_7))

	setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}