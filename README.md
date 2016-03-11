Stories:

As an Actor I should be able to create 2 child actors
As the 1st child I should be able to request HTTP (http://api.fixer.io/latest) endpoint every XX minutes and get results
As the 1st child I should be able to pass each result as a message to the 2nd child
As the 2nd child I should store each result to MongoDB

Technology used : Scala, Akka actor, spray-client, scalatest, akka-scheduler, SBT , MongoDB as datastore

Note : I used the main actor as an app(extends app) to create Actor System, and child actors 
Also, this is my first scala/akka coding, coded after reading few hours scala and akka. Interesting to learn and play with this.
Please let me know your feedback, will make it better eventually as I learn.

Download the project and import to Intellij and 
run MainActor.scala 
and for unit test case run, ChildActorTest.scala

If you encounter "Empty Test Suite" , please goto Run -> Edit Configuration and change Test Kind to All.
