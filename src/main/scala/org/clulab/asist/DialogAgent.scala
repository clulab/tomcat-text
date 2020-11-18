package org.clulab.asist


// coordinator for the Mosquitto subscriber and publisher

object DialogAgent overrides App{


    override def main(args:Array[String]): Unit = {
        println("DialogAgent main")
        MqttPublish
    }

}


