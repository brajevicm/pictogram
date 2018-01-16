package com.pictogram.pictogram.rest.repository;

import com.pictogram.pictogram.rest.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
