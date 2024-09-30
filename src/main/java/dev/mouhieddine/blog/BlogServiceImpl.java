package dev.mouhieddine.blog;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;


public class BlogServiceImpl implements BlogService.AsyncIface {

  @Override
  public void createBlogPost(CreateBlogPostRequest request,
      AsyncMethodCallback<BlogPost> resultHandler) throws TException {

  }

  public void getBlogPost(GetBlogPostRequest request, AsyncMethodCallback<BlogPost> resultHandler)
      throws TException {

  }

  public void listBlogPosts(ListBlogPostsRequest request,
      AsyncMethodCallback<ListBlogPostsResponse> resultHandler) throws TException {

  }

  public void updateBlogPost(UpdateBlogPostRequest request,
      AsyncMethodCallback<BlogPost> resultHandler) throws TException {

  }

  public void deleteBlogPost(DeleteBlogPostRequest request, AsyncMethodCallback<Void> resultHandler)
      throws TException {

  }
}
