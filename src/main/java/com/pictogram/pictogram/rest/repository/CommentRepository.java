package com.pictogram.pictogram.rest.repository;

import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
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
  Page<Comment> findAllByPost(Post post, Pageable pageable);
}
