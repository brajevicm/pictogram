package com.pictogram.pictogram.repository;

import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.model.User;
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

  @Query(value = "SELECT post FROM Post post WHERE post.enabled = true ORDER BY post.comments.size DESC")
  Page<Post> findAllByCommentsCount(Pageable pageable);

  @Query(value = "SELECT post FROM Post post WHERE post.enabled = true ORDER BY post.upvotePosts.size DESC")
  Page<Post> findAllByUpvotePostsCount(Pageable pageable);

  @Query(value = "SELECT post FROM Post post ORDER BY post.reportPosts.size DESC")
  Page<Post> findAllReportedPosts(Pageable pageable);
}
