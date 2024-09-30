package dev.mouhieddine.blog;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.thrift.THttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  private static Server newServer(int port) throws Exception {
    final THttpService tHttpService =
        THttpService.builder()
            .addService(new BlogServiceImpl())
            .build();

    return Server.builder()
        .http(port)
        .service("/blog", tHttpService)
        .build();
  }

  public static void main(String[] args) throws Exception {
    final Server server = newServer(8080);

    server.closeOnJvmShutdown().thenRun(() -> {
      logger.info("Server has been stopped.");
    });

    server.start().join();
  }
}