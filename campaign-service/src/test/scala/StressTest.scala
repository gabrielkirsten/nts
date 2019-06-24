import io.gatling.core.Predef._
import io.gatling.http.Predef._

class StressTest extends Simulation {
  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("*/*")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("100 requests - campaign")
    .exec(http("Post Campaign")
      .post("/campaigns")
      .body(StringBody("""{"name": "Campaign x", "startDate": "2019-06-22T14:05:43.333", "endDate": "2020-06-26T14:05:43.333", "favouriteTeam": "86c63b0c-90c2-4d6d-b4e8-921fba7099e8"}""")).asJson
    )

    .exec(http("Get all campaigns in 1 second")
      .get("/campaigns"))
    .pause(1)


  setUp(scn.inject(atOnceUsers(100)).protocols(httpProtocol))
}