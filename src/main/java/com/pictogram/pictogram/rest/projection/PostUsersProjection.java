package com.pictogram.pictogram.rest.projection;

import com.pictogram.pictogram.rest.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Project: pictogram
 * Date: 16-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Projection(name = "postUsersProjection", types = Post.class)
public interface PostUsersProjection {
  String getTitle();

  String getDescription();

  String getPostImage();

  Date getCreatedDate();

  boolean isEnabled();

  @Value("#{target.user.username}")
  String getUser();
}
