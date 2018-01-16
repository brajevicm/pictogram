package com.pictogram.pictogram.rest.repository;

import com.pictogram.pictogram.rest.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
