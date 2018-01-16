package com.pictogram.pictogram.config;

import com.pictogram.pictogram.rest.projection.PostUsersProjection;
import com.pictogram.pictogram.rest.projection.UserCommentsProjection;
import com.pictogram.pictogram.rest.projection.UserPostsProjection;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Configuration
public class ProjectionConfig extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.getProjectionConfiguration()
      .addProjection(UserCommentsProjection.class)
      .addProjection(UserPostsProjection.class)
      .addProjection(PostUsersProjection.class);
  }
}
