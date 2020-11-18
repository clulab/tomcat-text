package org.clulab.asist


// coordinator for the Mosquitto subscriber and publisher

object DialogAgent extends App {


    override def main(args:Array[String]): Unit = {
        println("DialogAgent main")
        MqttPublish
    }

}


