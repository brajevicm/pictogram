package com.pictogram.pictogram.rest.repository;

import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Project: pictogram
 * Date: 14-Jan-18a
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
  Page<Post> findAllByUser(User user, Pageable pageable);

  @Query("SELECT posts.id, posts.user_id,\n" +
    "        (SELECT username FROM users WHERE id = posts.user_id) as user,\n" +
    "         (SELECT COUNT(*) FROM comments WHERE post_id = posts.id) as comments\n" +
    "        FROM posts")
  Page<Post> findTopOrderByComments(Pageable pageable);
}
