//  DialogSubscriber
//
//  Author:  Joseph Astier
//  Date:  2020 November
//
//  Asynchronous event-driven Mosquitto message relayer, can also be used
//  as a standalone subscriber or publisher.
//  Based on the Eclipse Paho Mosquitto API: www.eclipse.org/paho/files/javadoc
//
package org.clulab.asist

import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

// this class allows a test of the Relayer 
object DialogAgent extends App {

    val host    = "127.0.0.1"
    val port    = 1883
    val topic   = "observations/chat"
    val message = "Hello Mosquitto!"
    val relayer = new DialogRelayer(host, port)

    println("Entering relayer startup ...")
    if(relayer.startUp()) {
        println("Startup completed successfully.")
        relayer.publish (topic, message)
        relayer.publish ("foo", message)
    } else
        println("Startup failed.")
}


// Mosquitto topic relayer.
class DialogRelayer (host: String, port: Int)extends MqttCallback { 
    val id         = "Relayer"
    val subId      = "Subscriber"
    val pubId      = "Publisher"
    val uri        = "tcp://%s:%d".format(host, port)
    val relaySrc   = "observations/chat"
    val relayDst   = "agent/tomcat_chatbot"
    val qos        = 2
    val verbose    = true  // debugging
    val subscriber = new MqttAsyncClient(uri, subId, new MemoryPersistence())
    val publisher  = new MqttClient(uri, pubId, new MemoryPersistence())

    subscriber.setCallback(this)

    // optional progress reports
    private def report(msg: String): Unit = if(verbose) print(id +": " + msg)

    // publish a string
    def publish(topic: String, text: String): Boolean = publish(
        topic, text.getBytes)

    // publish any byte array
    def publish(topic: String, payload: Array[Byte]): Boolean = publish(
        topic, new MqttMessage(payload))

    // publish Mosquitto message
    def publish(topic: String, msg: MqttMessage): Boolean = try {
        if(publisher.isConnected) { 
            publisher.publish(topic, msg)
            report("Published '%s' to '%s'\n".format(msg.toString, topic))
            true
        } else {
            report("Can't publish '%s' to '%s'. Not conected.n".format(
                msg.toString, topic))
            false
        }
    } catch {
        case e: Exception => { 
            report("Can't publish '%s' to '%s'. %s\n".format(
                msg.toString, topic, e.toString))
            false
        }
    }

    // subscribe to any topic
    def subscribe(topic: String): Boolean  = try {
        if(subscriber.isConnected) {
            subscriber.subscribe(topic, qos)
            report("Subscribed to '%s'\n".format(topic))
            true
        } else {
            report("Can't subscribe '%s', not connected\n".format(topic))
            false
        }
    } catch {
        case e: Exception => {
            report("Exception during subscribe to " + topic + ", " + e)
            false
        }
    }

    def isConnected(): Boolean = {
        subscriber.isConnected && publisher.isConnected
    }

    def connectOptions(): MqttConnectOptions = { 
        val co = new MqttConnectOptions
        co.setCleanSession(true)
        co
    }

    def connectSubscriber(): Boolean = {
        report("Connecting subscriber to %s ...\n".format(uri))
        subscriber.connect(connectOptions).waitForCompletion 
        if(subscriber.isConnected) {
            report("subscriber is connected to %s\n".format(uri))
            true
        } else {
            report("subscriber is not connected to %s\n".format(uri))
            false
        }
    }


    def connectPublisher(): Boolean = {
        report("Connecting publisher to %s ...\n".format(uri))
        publisher.connect(connectOptions) 
        if(publisher.isConnected) {
            report("publisher is connected to %s\n".format(uri))
            true
        } else {
            report("publisher is not connected to %s\n".format(uri))
            false
        }
    }


    def startUp(): Boolean = (connectPublisher 
        && connectSubscriber
        && subscribe(relayDst) 
        && subscribe(relaySrc))


    override def connectionLost(cause: Throwable): Unit = 
        report("%s\n".format(cause.toString))


    override def deliveryComplete(token: IMqttDeliveryToken): Unit = 
        report("deliveryComplete: " + token.getMessage)


    override def messageArrived(topic: String, msg: MqttMessage): Unit = { 
        report("Read '%s' on '%s'\n".format(msg.toString, topic))
        if(topic == relaySrc) {
            report(" RELAY '%s' from '%s' to '%s'\n".format(
                msg.toString, relaySrc, relayDst))
            publish(relayDst, msg)
        }
    }
}
