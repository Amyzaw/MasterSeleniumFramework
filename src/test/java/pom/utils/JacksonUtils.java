package pom.utils;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils{

	public static <T> T deserializeJson(String filename,Class<T> T) throws IOException {
		InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(filename);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(is, T);
	}

}
