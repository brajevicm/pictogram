package com.pictogram.pictogram.rest.repository;

import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Project: pictogram
 * Date: 14-Jan-18a
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
  Page<Post> findAllByUser(User user, Pageable pageable);

  @Query(value = "SELECT posts FROM Post posts ORDER BY posts.comments.size DESC")
  Page<Post> findAllByCommentsCount(Pageable pageable);

  @Query(value = "SELECT posts FROM Post posts ORDER BY posts.upvotePosts.size DESC")
  Page<Post> findAllByUpvotePostsCount(Pageable pageable);
}
