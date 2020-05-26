package `in`.edak.xmltest.run

import `in`.edak.xmltest.mq.QueueConfig
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.util.*
import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.command.ActiveMQTextMessage
import javax.jms.Session
import javax.jms.TextMessage


class QueueTest {
    val testBroker = "tcp://172.16.3.47:61616"
    val queueName = "testQueue"

    @Before
    fun sendMessage() {
        val connectionFactory = ActiveMQConnectionFactory(testBroker)
        val connection = connectionFactory.createConnection()
        try {
            connection.start()
            val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
            val queue = session.createQueue(queueName)
            val producer = session.createProducer(queue)
            producer.send(session.createTextMessage(MessageXmlSerializerTest.messageToDeserialize))
            session.close()
        } finally {
            connection?.close()
        }
        Thread.sleep(10000)

    }


    @Test
    fun listen() {
        val queueConfig = QueueConfig(testBroker,queueName)
        queueConfig.startListen()
        Thread.sleep(10000)
    }

}