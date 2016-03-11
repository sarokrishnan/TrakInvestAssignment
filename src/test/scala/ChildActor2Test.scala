
import akka.actor.ActorSystem
import akka.testkit._
import com.trakinvest.actors.{ChildActor1, ChildActor2}
import com.trakinvest.protocol.ChildActor1Protocol.askActor1ToInitiate
import com.trakinvest.protocol.ChildActor2Protocol.exchangeRateInsertRequest
import org.scalatest.{MustMatchers, WordSpecLike}

/**
  * Created by sbalakrishnan on 3/9/16.
  */


 class ChildActor2Test extends TestKit(ActorSystem("TestTrakInvestAssignment"))
    with WordSpecLike
    with MustMatchers{

        "A child actor 1" must {
          val childRef2 = TestActorRef[ChildActor2]
          val childRef1 = TestActorRef(new ChildActor1(childRef2))

          "make an api call and gets response" in {

            childRef1 ! askActor1ToInitiate()

            childRef1.underlyingActor.responseFromAPI.indexOf("base")

          }
        }

        "A child actor 1" must {
          val childRef2 = TestActorRef[ChildActor2]
          val childRef1 = TestActorRef(new ChildActor1(childRef2))

          "sends message to child actor 2" in {

            childRef1 ! askActor1ToInitiate()

            childRef2.underlyingActor.exRate.indexOf("base")

          }
        }


        "A child actor 2" must {

        val actorRef = TestActorRef[ChildActor2]

          "receive messages" in {

            actorRef ! exchangeRateInsertRequest("{\n\"base\": \"EUR\",\n\"date\": \"2016-03-10\",\n\"rates\": {\n\"AUD\": 1.4522,\n\"BGN\": 1.9558,\n\"BRL\": 3.972,\n\"CAD\": 1.4424,\n\"CHF\": 1.0941,\n\"CNY\": 7.0787,\n\"CZK\": 27.037,\n\"DKK\": 7.4587,\n\"GBP\": 0.76728,\n\"HKD\": 8.4299,\n\"HRK\": 7.567,\n\"HUF\": 310.01,\n\"IDR\": 14168.19,\n\"ILS\": 4.2357,\n\"INR\": 72.6645,\n\"JPY\": 123.87,\n\"KRW\": 1302.89,\n\"MXN\": 19.1827,\n\"MYR\": 4.4438,\n\"NOK\": 9.2926,\n\"NZD\": 1.6277,\n\"PHP\": 50.581,\n\"PLN\": 4.2921,\n\"RON\": 4.4585,\n\"RUB\": 76.437,\n\"SEK\": 9.2361,\n\"SGD\": 1.5013,\n\"THB\": 38.314,\n\"TRY\": 3.1292,\n\"USD\": 1.0857,\n\"ZAR\": 16.364\n}\n}")

            actorRef.underlyingActor.exRate must equal("{\n\"base\": \"EUR\",\n\"date\": \"2016-03-10\",\n\"rates\": {\n\"AUD\": 1.4522,\n\"BGN\": 1.9558,\n\"BRL\": 3.972,\n\"CAD\": 1.4424,\n\"CHF\": 1.0941,\n\"CNY\": 7.0787,\n\"CZK\": 27.037,\n\"DKK\": 7.4587,\n\"GBP\": 0.76728,\n\"HKD\": 8.4299,\n\"HRK\": 7.567,\n\"HUF\": 310.01,\n\"IDR\": 14168.19,\n\"ILS\": 4.2357,\n\"INR\": 72.6645,\n\"JPY\": 123.87,\n\"KRW\": 1302.89,\n\"MXN\": 19.1827,\n\"MYR\": 4.4438,\n\"NOK\": 9.2926,\n\"NZD\": 1.6277,\n\"PHP\": 50.581,\n\"PLN\": 4.2921,\n\"RON\": 4.4585,\n\"RUB\": 76.437,\n\"SEK\": 9.2361,\n\"SGD\": 1.5013,\n\"THB\": 38.314,\n\"TRY\": 3.1292,\n\"USD\": 1.0857,\n\"ZAR\": 16.364\n}\n}")

          }
        }

      "A child actor 2" must {

        val actorRef = TestActorRef[ChildActor2]

        "insert record to mongodb " in {

          actorRef ! exchangeRateInsertRequest("{\n\"base\": \"EUR\",\n\"date\": \"2016-03-10\",\n\"rates\": {\n\"AUD\": 1.4522,\n\"BGN\": 1.9558,\n\"BRL\": 3.972,\n\"CAD\": 1.4424,\n\"CHF\": 1.0941,\n\"CNY\": 7.0787,\n\"CZK\": 27.037,\n\"DKK\": 7.4587,\n\"GBP\": 0.76728,\n\"HKD\": 8.4299,\n\"HRK\": 7.567,\n\"HUF\": 310.01,\n\"IDR\": 14168.19,\n\"ILS\": 4.2357,\n\"INR\": 72.6645,\n\"JPY\": 123.87,\n\"KRW\": 1302.89,\n\"MXN\": 19.1827,\n\"MYR\": 4.4438,\n\"NOK\": 9.2926,\n\"NZD\": 1.6277,\n\"PHP\": 50.581,\n\"PLN\": 4.2921,\n\"RON\": 4.4585,\n\"RUB\": 76.437,\n\"SEK\": 9.2361,\n\"SGD\": 1.5013,\n\"THB\": 38.314,\n\"TRY\": 3.1292,\n\"USD\": 1.0857,\n\"ZAR\": 16.364\n}\n}")
          actorRef.underlyingActor.dbMessage must equal("WriteResult{, n=0, updateOfExisting=false, upsertedId=null}")
        }
      }



}