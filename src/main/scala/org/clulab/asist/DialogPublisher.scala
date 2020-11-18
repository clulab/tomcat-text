//  DialogSubscriber
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


class DialogPublisher (brokerUrl: String, qos: Int) {

    val clientId = "tomcat_publisher"
    val persistence = new MemoryPersistence()
    val connectOptions = new MqttConnectOptions()
    val client = new MqttClient(brokerUrl, clientId, persistence)

    connectOptions.setCleanSession(true)


    // Tag any output with this classname
    private def report(msg: String): Unit = {
        println(clientId + ":" + msg)
    }


    // Send a
    def publish(topic: String, payload: String): Unit = try {
        report("publish:  topic   = "+topic)
        report("publish:  payload = "+payload)
        val message = new MqttMessage(payload.getBytes())
        message.setQos(qos)
        client.publish(topic, message)
    }
    catch {
        case e: Exception =>{
            report("publish: Caught an Exception: " + e.toString())
        }
    }


    def connect(): Unit = try {
        report("connect: Connecting to broker: "+brokerUrl + " ...")
        client.connect(connectOptions)
        report("connect: Connected")
    }
    catch {
        case e: Exception =>{
            report("connect: Caught an Exception: " + e.toString())
        }
    }
    

    def disconnect(): Unit = try {
        report("disconnect: disconnecting...")
        client.disconnect()
        report("disconnect: disonnected")
    }
    catch {
        case e: Exception =>{
            report("disconnect: Caught an Exception: " + e.toString())
        }
    }
}
