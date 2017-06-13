package com.ohb.app.util;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class OhbUtil {

	private static final ObjectMapper jacksonObjectMapper = new ObjectMapper();
	private static final ObjectMapper formattedJacksonObjectMapper = new ObjectMapper();

	static {
		formattedJacksonObjectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		formattedJacksonObjectMapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
//		formattedJacksonObjectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		
		formattedJacksonObjectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		formattedJacksonObjectMapper.getSerializerProvider().setNullKeySerializer(new UXNullKeySerializer());
	}

	public static <T> T convertFromJSON(String json, Class<T> toClass) throws Throwable {
		return jacksonObjectMapper.readValue(json, toClass);
	}
	
	public static <T> T convertFromJSON(InputStream json, Class<T> toClass) throws Throwable {
		return jacksonObjectMapper.readValue(json, toClass);
	}
	
	public static String convertToJSON(Object obj) throws Throwable {
		return jacksonObjectMapper.writeValueAsString(obj);
	}
	public static String convertToJSONWithoutNull(Object obj) throws Throwable {
//		jacksonObjectMapper.disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
		jacksonObjectMapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		jacksonObjectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		
		return jacksonObjectMapper.writeValueAsString(obj);
	}
	public static String convertToPrettyJSON(Object obj) throws Throwable {
		return formattedJacksonObjectMapper.writeValueAsString(obj);
	}

}

class UXNullKeySerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object nullKey, JsonGenerator jsonGenerator, SerializerProvider unused) throws IOException, JsonProcessingException {
		jsonGenerator.writeFieldName("");
	}
}
