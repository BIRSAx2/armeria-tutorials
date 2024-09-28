package dev.mouhieddine.blog;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.docs.DocServiceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Server server = newServer(8080);
        server.start().join();
        logger.info("Server has been started. Serving DocService at http://127.0.0.1:{}/docs", server.activeLocalPort());
    }

    static Server newServer(int port) {
        ServerBuilder sb = Server.builder();
        DocService docService = DocService.builder().exampleRequests(BlogService.class,
                "createBlogPost",
                "{\"title\": \"My first blog post\", \"content\": \"Hello, world!\"}"

        ).build();

        return sb.http(port)
                .annotatedService("/", new BlogService())
                .serviceUnder("/docs", docService)
                .build();
    }
}