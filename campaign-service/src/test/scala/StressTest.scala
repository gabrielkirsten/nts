import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class StressTest extends Simulation {
  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("*/*")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("100 requests")
    .exec(http("Get all campaigns in 1 second")
      .get("/campaign"))
    .pause(1)
    .exec(http("Get all campaigns in 1 second(2)")
      .get("/campaign"))
    .pause(1)
    .exec(http("Get all campaigns in 1 second(3)")
      .get("/campaign"))
    .pause(1)
    .exec(http("Get all campaigns in 1 second(4)")
      .get("/campaign"))
    .pause(1)
    .exec(http("Get all campaigns in 1 second(5)")
      .get("/campaign"))
    .pause(1)
    .exec(http("Get all campaigns in 1 second(5)")
      .get("/campaign"))
    .pause(1)
    .exec(http("Get all campaigns in 1 second(6)")
      .get("/campaign"))
    .pause(1)
    .exec(http("Get all campaigns in 1 second(7)")
      .get("/campaign"))
    .pause(1)
    .exec(http("Get all campaigns in 1 second(8)")
      .get("/campaign"))
    .pause(1)


  setUp(scn.inject(atOnceUsers(1000)).protocols(httpProtocol))
}