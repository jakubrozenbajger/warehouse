package cf.jrozen.po.warehouse.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

import static cf.jrozen.po.warehouse.utils.JsonLocalDateTimeSerializer.PATTERN_DATE_TIME;
import static java.time.format.DateTimeFormatter.ofPattern;

public class JsonLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return  LocalDateTime.parse(parser.getText(), ofPattern(PATTERN_DATE_TIME));
    }
}
