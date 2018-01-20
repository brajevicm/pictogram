package com.pictogram.pictogram.rest.repository;

import com.pictogram.pictogram.rest.model.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * Project: pictogram
 * Date: 14-Jan-18a
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
