//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//  MQTT message relayer, subscribes to messages on one topic and
//  then re-publishes them on another.
//  Based on the Eclipse paho MQTT package defined here:
//  https://www.eclipse.org/paho/files/javadoc/org/eclipse/paho/client/mqttv3/package-tree.html
//
package org.clulab.asist


// coordinator for the Mosquitto subscriber and publisher
import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


object DialogAgent extends App {


    val brokerUrl = "tcp://127.0.0.1:1883"
    val clientId = "TomcatClient"
    val qos = 2
    val topic = "example_topic"
    val persistence = new MemoryPersistence()

    val subscriber = new MqttSubscribe(brokerUrl, clientId, qos, persistence)

    override def main(args:Array[String]): Unit = {
        println("DialogAgent main")
    }

}


