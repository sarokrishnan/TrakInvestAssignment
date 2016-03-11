package com.trakinvest.actors


import akka.actor.{Actor, ActorLogging, ActorRef}
import com.trakinvest.protocol.ChildActor1Protocol.{ askActor1ToInitiate}
import com.trakinvest.protocol.ChildActor2Protocol.exchangeRateInsertRequest
import com.typesafe.config.ConfigFactory
import spray.client.pipelining._
import spray.http._

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}
/**
  * Created by sbalakrishnan on 3/9/16.
  */
class ChildActor1 (childActor2Ref:ActorRef) extends Actor with ActorLogging {

  val conf = ConfigFactory.load()

  var responseFromAPI : String =""

  //STORY 2 : As the 1st child I should be able to request HTTP (http://api.fixer.io/latest) endpoint every XX minutes and get results
  //On receive from Main Actor invoke the tasks
  def receive = {

    //tasks for child actor 1
    case askActor1ToInitiate()=> {

      log.info("Calling API and sending the response to child actor 2 every 15 seconds")
      //childactor 1 invokes the rest api call using SPRAY-CLIENT framework every 1 minute
      //use the system reference from Main actor
      val system = context.system
      import system.dispatcher
      system.scheduler.schedule(initialDelay = 0 seconds, interval = 5 seconds) {

        try{
              val pipeline: HttpRequest => Future[HttpResponse] = sendReceive
              val responseFuture: Future[HttpResponse] = pipeline(Get(conf.getString("apiToCall")))

              responseFuture onComplete {
                case Success(res) =>

                  //after successfull response send this message to Child Actor 2 to insert into mongoDB
                  //STORY 3 : As the 1st child I should be able to pass each result as a message to the 2nd child
                  responseFromAPI = res.entity.data.asString
                  log.info("Response received from API");
                  log.info(responseFromAPI);

                  //sending message to child actor 2
                  childActor2Ref ! exchangeRateInsertRequest(responseFromAPI)

                case Failure(error) =>
                  log.info("error" + error.getMessage)
              }

          }catch{
            case e:Exception => log.info("Error getting response back from API call")
          }

      }
    }


  }

}
