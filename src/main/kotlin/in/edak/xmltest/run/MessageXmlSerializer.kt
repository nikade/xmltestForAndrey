package `in`.edak.xmltest.run

import `in`.edak.xmltest.dto.SmsMessage
import com.fasterxml.jackson.dataformat.xml.XmlMapper

class MessageXmlSerializer {
    val xmlMapper = XmlMapper()

    fun deserialize(xml: String): SmsMessage {
        return xmlMapper.readValue(xml,SmsMessage::class.java)
    }

    fun serialize(message: SmsMessage): String {
        return xmlMapper.writeValueAsString(message)
    }
}