package cf.jrozen.po.warehouse.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

public class JsonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    static final String PATTERN_DATE_TIME = "dd-MM-yyyy HH:mm:ss";

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.format(ofPattern(PATTERN_DATE_TIME)));
    }

}
