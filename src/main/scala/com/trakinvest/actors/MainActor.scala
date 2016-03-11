package com.trakinvest.actors

import akka.actor.{ActorSystem, Props}
import com.trakinvest.protocol.ChildActor1Protocol.askActor1ToInitiate
import org.slf4j.LoggerFactory

/**
  * Created by sbalakrishnan on 3/9/16.
  */

object MainActor extends App  {

  private val log = LoggerFactory.getLogger(getClass)

  log.info("Creating Actors (Child actor 1 and child actor 2)")
  val system = ActorSystem("TrakInvestGetExchangeRate")

  //STORY #1 : As an Actor I should be able to create 2 child actors
  //child actor 2
  val childActor2Ref = system.actorOf(Props[ChildActor2], "ChildActor2")

  //child actor 1
  //Passing constructor parameter (child actor2) to child actor 1
  val childActor1Ref = system.actorOf(Props(new ChildActor1(childActor2Ref)), "ChildActor1")

  //sending message asking child actor 1 to do the tasks
  childActor1Ref ! askActor1ToInitiate()


}