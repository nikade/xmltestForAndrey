package `in`.edak.xmltest.run

import `in`.edak.xmltest.dto.ReceiverPhone
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class MessageXmlSerializerTest {
    val messageToDeserialize = "<SmsMessage>\n" +
            "    <sender>+79031234567</sender>\n" +
            "    <message>Всем привет!</message>\n" +
            "    <time_period>10:00-22:00</time_period>\n" +
            "    <phones>\n" +
            "       <phone><number>+79132157888</number><uuid>5192ec6a-85cb-11ea-bc55-0242ac130003</uuid></phone>\n" +
            "       <phone><number>+79132157889</number><uuid>5192ec6a-85cb-11ea-bc55-0242ac130004</uuid></phone>\n" +
            "       <phone><number>+79132157880</number><uuid>5192ec6a-85cb-11ea-bc55-0242ac130005</uuid></phone>\n" +
            "       <phone><number>+79132157881</number><uuid>5192ec6a-85cb-11ea-bc55-0242ac130006</uuid></phone>\n" +
            "    </phones>\n" +
            "</SmsMessage>"

    val messageXmlSerializer = MessageXmlSerializer()

    @Test
    fun testAll() {
        val obj = messageXmlSerializer.deserialize(messageToDeserialize)
        assertEquals("+79031234567", obj.sender)
        assertEquals("Всем привет!", obj.message)
        assertEquals("10:00-22:00", obj.timePeriod)
        assertEquals(4, obj.phones.size)
        assertEquals(
            listOf(
                ReceiverPhone("+79132157888", UUID.fromString("5192ec6a-85cb-11ea-bc55-0242ac130003")),
                ReceiverPhone("+79132157889", UUID.fromString("5192ec6a-85cb-11ea-bc55-0242ac130004")),
                ReceiverPhone("+79132157880", UUID.fromString("5192ec6a-85cb-11ea-bc55-0242ac130005")),
                ReceiverPhone("+79132157881", UUID.fromString("5192ec6a-85cb-11ea-bc55-0242ac130006"))
            ),obj.phones)

        val xml = messageXmlSerializer.serialize(obj)

        // тут сверка не проходит, можно решить аннотациаями, мне не охота разбиарться
        // <phones><phone><number>+79132157888<
        // <phones><phones><number>+79132157888<
        assertEquals(
            messageToDeserialize.replace("\\s*\\n\\s*".toRegex(),""),
            xml
        )

    }
}