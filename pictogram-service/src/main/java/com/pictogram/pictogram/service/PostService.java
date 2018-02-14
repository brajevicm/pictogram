package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */

public interface PostService {
  Boolean delete(Post post);

  Post save(Post post);

  Post findOne(Long id);

  List<Post> findAllByType(String type, Pageable pageable);

  List<Post> findAllByUser(User user, Pageable pageable);

  List<Post> findAllByFollows(User user, Pageable pageable);
}
