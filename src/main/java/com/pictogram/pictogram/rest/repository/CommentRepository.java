package com.pictogram.pictogram.rest.repository;

import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
  Page<Comment> findAllByUser(User user, Pageable pageable);

  Page<Comment> findAllByPostOrderByUpvoteCommentsDesc(Post post, Pageable pageable);
}
