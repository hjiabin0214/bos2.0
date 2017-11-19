package cn.hjiabin.bos.mq;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Service;

@Service("smsConsumer")
public class SmsConsumer implements MessageListener {

	@Override
	public void onMessage(Message message) {
		MapMessage mapMessage = (MapMessage) message;
		try {
			System.out.println("发送短信成功,手机号："
					+ mapMessage.getString("telephone") + "，验证码："
					+ mapMessage.getString("msg"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
