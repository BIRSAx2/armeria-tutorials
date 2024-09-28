package dev.mouhieddine.blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.armeria.common.AggregatedHttpRequest;
import com.linecorp.armeria.common.annotation.Nullable;
import com.linecorp.armeria.server.ServiceRequestContext;
import com.linecorp.armeria.server.annotation.RequestConverterFunction;

import java.lang.reflect.ParameterizedType;
import java.util.concurrent.atomic.AtomicInteger;

public class BlogPostRequestConverter implements RequestConverterFunction {
    private static final ObjectMapper mapper = new ObjectMapper();
    private AtomicInteger idGenerator = new AtomicInteger();


    static String stringValue(JsonNode jsonNode, String field) {
        JsonNode node = jsonNode.get(field);
        if (node == null) {
            throw new IllegalArgumentException("Missing field: " + field);
        }
        return node.textValue();
    }

    @Override
    public @Nullable Object convertRequest(ServiceRequestContext ctx, AggregatedHttpRequest request, Class<?> expectedResultType, @Nullable ParameterizedType expectedParameterizedResultType) throws Exception {
        if (expectedResultType != BlogPost.class) {
            return RequestConverterFunction.fallthrough();
        }

        JsonNode jsonNode = mapper.readTree(request.contentUtf8());
        int id = idGenerator.getAndIncrement();
        String title = stringValue(jsonNode, "title");
        String content = stringValue(jsonNode, "content");
        return new BlogPost(id, title, content);
    }
}
