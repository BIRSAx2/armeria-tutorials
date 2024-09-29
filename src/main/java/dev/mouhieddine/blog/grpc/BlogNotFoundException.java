package dev.mouhieddine.blog.grpc;

public class BlogNotFoundException extends IllegalStateException {

  BlogNotFoundException(String s) {
    super(s);
  }
}
