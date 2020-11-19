//  DialogSubscriber
//
//  Author:  Joseph Astier
//  Date:  2020 November
//
//  Asynchronous event-driven Mosquitto message subscriber
//  Based on the Eclipse Paho Mosquitto package:
//  https://www.eclipse.org/paho/files/javadoc/org/eclipse/paho/client/mqttv3/package-tree.html
//
package org.clulab.asist

import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class DialogSubscriber (
        clientId: String, 
        brokerUri: String,
        qos: Int) extends MqttCallback {

    val persistence: MemoryPersistence = new MemoryPersistence()
    val connectOptions: MqttConnectOptions = new MqttConnectOptions()
    val client: MqttAsyncClient = new MqttAsyncClient(
        brokerUri, clientId, persistence)

    connectOptions.setCleanSession(true)
    client.setCallback(this)

    private def report(msg: String): Unit = {
        println(clientId +":" + msg)
    }


    def subscribe(topic: String): Unit = try {
        client.subscribe(topic, qos)
        report("subscribed to: " + topic)
    } catch {
        case e: Exception => {
            report("Exception during subscribe to " + topic + ", " + e)
        }
    }


    def connect(): Unit = try {
        client.connect(connectOptions)
        report("connected to " + brokerUri)
    }
    catch {
        case e: Exception => {
            report("Exception during connection to " + brokerUri + ", " + e)
        }
    }


    override def connectionLost(cause: Throwable): Unit = { 
        report("connectionLost with: " + cause)
    }


    override def deliveryComplete(token: IMqttDeliveryToken): Unit = {
        report("deliveryComplete: " + token.getMessage())
    }


    override def messageArrived(topic: String, msg: MqttMessage): Unit = { 
        report("messageArrived: " + topic + " -> " + msg)
    }
}


