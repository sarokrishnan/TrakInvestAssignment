package com.trakinvest.actors

import akka.actor.{Actor, ActorLogging}
import com.mongodb.DBObject
import com.mongodb.casbah.MongoClient
import com.mongodb.util.JSON
import com.trakinvest.protocol.ChildActor2Protocol.exchangeRateInsertRequest
import com.typesafe.config.ConfigFactory

/**
  * Created by sbalakrishnan on 3/9/16.
  */
class ChildActor2 extends Actor with ActorLogging{

  val conf = ConfigFactory.load()
  var exRate: String = ""
  var dbMessage : String =""

  def receive = {

    //STORY 4: As the 2nd child I should store each result to MongoDB
    // after receiving message from Child Actor 1 insert into MongoDB
    case exchangeRateInsertRequest(exChangeRate) => {
      exRate = exChangeRate

      log.info("Message received to Child actor 2")
      log.info(exRate)

      try{
        val mongoClient = MongoClient( conf.getString("mongodbhost"), conf.getString("port")toInt )

        val db = mongoClient(conf.getString("db"))

        db.collectionNames

        val coll = db(conf.getString("collection"))

        val doc: DBObject = JSON.parse(exRate).asInstanceOf[DBObject]

        dbMessage = coll.insert( doc).toString

        log.info(dbMessage)

      }catch{

        case e:Exception => log.info("Error inserting document to DB")

      }

    }
  }

}
