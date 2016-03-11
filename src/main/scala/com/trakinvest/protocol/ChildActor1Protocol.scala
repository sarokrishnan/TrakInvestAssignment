package com.trakinvest.protocol

/**
  * Created by sbalakrishnan on 3/9/16.
  */
object ChildActor1Protocol {


  /*
    * the main actor issues a signal to childactor1 and
    * child actor 1 calls an exchange api every few seconds and sends the response to child actor 2
    * child actor 2 inserts the received response into mongodb
    */
  case class askActor1ToInitiate()

}
