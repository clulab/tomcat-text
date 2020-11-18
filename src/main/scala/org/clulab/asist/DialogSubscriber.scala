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

class DialogSubscriber (brokerUrl: String, qos: Int) extends MqttCallback {

    val clientId: String = "tomcat_subscriber"
    val persistence: MemoryPersistence = new MemoryPersistence()
    val connectOptions: MqttConnectOptions = new MqttConnectOptions()
    val client: MqttAsyncClient = new MqttAsyncClient(
        brokerUrl, clientId, persistence)

    connectOptions.setCleanSession(true)
    client.setCallback(this)


    private def report(msg: String): Unit = {
        println(clientId +":" + msg)
    }


    def subscribe(topic: String): Unit = try {
        report("subscribe:  subscribing to: " + topic + "...")
        client.subscribe(topic, qos)
        report("subscribe:  subscribed to: " + topic)
    } catch {
        case ex: Exception => {
            report("subscribe:  Exception caught")
            report("subscribe:  " + ex.toString())
        }
    }

    def connect(): Unit = try {
        report("connect: Connecting to broker: "+brokerUrl + " ...")
        client.connect(connectOptions)
        report("connect: Connected")
    }
    catch {
        case e: Exception => {
            report("connect: Caught an Exception: " + e)
        }
    }


    override def connectionLost(cause: Throwable): Unit = { 
        report("connectionLost with: " + cause)
    }


    override def deliveryComplete(token: IMqttDeliveryToken): Unit = try {
        report("deliveryComplete: " + token.getMessage())
    } catch {
        case e: Exception => {
            report("deliveryComplete: Caught an Exception: " + e)
        }
    }


    override def messageArrived(topic: String, msg: MqttMessage): Unit = try { 
        report("messageArrived: topic   = " + topic)
        report("messageArrived: message = " + msg)

    } catch {
        case e: Exception => {
            report("messageArrived: Caught an Exception: " + e)
        }
    }
}


