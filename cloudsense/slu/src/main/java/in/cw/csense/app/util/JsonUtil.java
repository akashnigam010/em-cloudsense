package in.cw.csense.app.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.cw.csense.app.message.element.Message;
import in.cw.csense.app.message.element.MessageElement;
import in.cw.csense.app.message.element.MessageElementAdaptor;

public class JsonUtil {

	public static String toJson(Message message) {
		GsonBuilder builder = new GsonBuilder().registerTypeAdapter(MessageElement.class, new MessageElementAdaptor());
		Gson gson = builder.create();
		return gson.toJson(message, Message.class);
	}
}
