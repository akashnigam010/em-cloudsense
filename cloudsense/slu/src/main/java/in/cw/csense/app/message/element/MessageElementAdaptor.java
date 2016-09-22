package in.cw.csense.app.message.element;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MessageElementAdaptor implements JsonSerializer<MessageElement>, JsonDeserializer<MessageElement> {

	public MessageElement deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = element.getAsJsonObject();
		String jsonType = jsonObject.get("type").getAsString();
		JsonElement jsonElement = (jsonObject.get("properties"));

		try {
			return context.deserialize(jsonElement,
					Class.forName("in.cw.csense.app.message.element." + jsonType));
		} catch (ClassNotFoundException exception) {
			throw new JsonParseException("Unknown element type: " + jsonType, exception);
		}

	}

	public JsonElement serialize(MessageElement element, Type type, JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("type",
				new JsonPrimitive(element.getClass().getSimpleName()));
		result.add("properties", context.serialize(element, element.getClass()));
		return result;

	}

}
