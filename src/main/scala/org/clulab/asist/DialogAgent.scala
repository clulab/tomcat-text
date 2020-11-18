package org.clulab.asist

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence



// coordinator for the Mosquitto subscriber and publisher

object DialogAgent extends App {


    override def main(args:Array[String]): Unit = {
        println("DialogAgent main")
        MqttPublish
    }

}


