package com.pictogram.pictogram.rest.projections;

import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.security.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Project: pictogram
 * Date: 16-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Projection(name = "userPosts", types = {User.class})
public interface UserPosts {
  String getUsername();

  @Value("#{target.posts}")
  List<Post> getPosts();

  @Value("#{target.comments}")
  List<Comment> getComments();
}
