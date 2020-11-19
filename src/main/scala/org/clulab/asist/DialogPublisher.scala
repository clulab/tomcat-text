//  DialogPublisher
//
//  Author:  Joseph Astier
//  Date:  2020 November
//
//  Asynchronous Mosquitto message publisher
//  Based on the Eclipse Paho Mosquitto package:
//  https://www.eclipse.org/paho/files/javadoc/org/eclipse/paho/client/mqttv3/package-tree.html
//
package org.clulab.asist
import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


class DialogPublisher (clientId: String, brokerUri: String, qos: Int) {

    val persistence = new MemoryPersistence()
    val connectOptions = new MqttConnectOptions()
    val client = new MqttClient(brokerUri, clientId, persistence)

    connectOptions.setCleanSession(true)


    // Tag any output with this classname
    private def report(msg: String): Unit = {
        println(clientId + ":" + msg)
    }


    // Send a
    def publish(topic: String, payload: String): Unit = try {
        val message = new MqttMessage(payload.getBytes())
        message.setQos(qos)
        client.publish(topic, message)
        report("Published message: \"" + payload + "\" on topic \"" + topic + "\"")
    }
    catch {
        case e: Exception =>{
            report("publish: Caught an Exception: " + e.toString())
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


    def disconnect(): Unit = try {
        client.disconnect()
        report("disconnected from " + brokerUri)
    }
    catch {
        case e: Exception =>{
            report("Exception during disconnect from " + brokerUri + ": " + e.toString())
        }
    }
}
