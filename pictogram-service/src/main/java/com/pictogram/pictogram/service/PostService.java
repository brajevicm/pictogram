package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.Post;

import java.util.List;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */

public interface PostService {
  void save(Post post);

  Post findOne(Long id);

  List<Post> findAllByType(String type, int page, int size);

  List<Post> findAllByUser(Long userId, int page, int size);
}
