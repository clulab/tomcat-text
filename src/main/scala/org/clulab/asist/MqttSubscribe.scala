//  MqttSubscribe
//
//  Author:  Joseph Astier
//  Date:  2020 November
//
//  Asynchronous event-driven Mosquitto message subscriber
//  Based on the Eclipse paho MQTT package defined here:
//  https://www.eclipse.org/paho/files/javadoc/org/eclipse/paho/client/mqttv3/package-tree.html
//
package org.clulab.asist

import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MqttSubscribe (
        brokerUrl: String, 
        clientID: String, 
        qos: Int,
        persistence: MemoryPersistence) extends MqttCallback {

    val connectOptions = new MqttConnectOptions()
    val client:MqttAsyncClient = new MqttAsyncClient(
        brokerUrl, clientID, persistence)

    connectOptions.setCleanSession(true)
    client.setCallback(this)

    val conToken: IMqttToken = client.connect(connectOptions, null, null)


    def report(msg: String): Unit = {
        println("MqttSubscribe:" + msg)
    }


    override def connectionLost(cause: Throwable): Unit = { 
        report("connectionLost with: " + cause.toString())
    }

    override def deliveryComplete(token: IMqttDeliveryToken): Unit = {
        try {
            report("deliveryComplete: " + token.getMessage())
        } catch {
            case ex: Exception => {
                println(ex.toString())
            }
        }
    }

    override def messageArrived(topic: String, message: MqttMessage): Unit = { 
        try {
            report("messageArrived " + topic + ":" + message.toString())
        } catch {
            case ex: Exception => {
                report("messageArrived: Exception caught")
                report("messageArrived: " + ex.toString())
            }
        }
    }
}


