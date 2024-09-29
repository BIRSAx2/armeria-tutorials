package dev.mouhieddine.blog.grpc;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.grpc.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws Exception {

    final Server server = newServer(8080);

    server.closeOnJvmShutdown().thenRun(() -> {
      logger.info("Server has been stopped");
    });

    server.start().join();
  }

  static Server newServer(int port) throws Exception {
    final GrpcService grpcService =
        GrpcService.builder()
            .addService(new BlogService())
            .exceptionHandler(new GrpcExceptionHandler())
            .build();
    return Server.builder().http(port).service(grpcService).build();
  }
}