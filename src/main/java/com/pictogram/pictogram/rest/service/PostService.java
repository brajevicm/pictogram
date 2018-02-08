package com.pictogram.pictogram.rest.service;

import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.dto.PostDto;
import org.springframework.data.domain.Page;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */

public interface PostService {
  void save(PostDto postDto);

  void delete(Long id);

  Post findOne(Long id);

  Page<Post> findAllByType(String type, int page, int size);

  Page<Post> findAllByUser(Long userId, int page, int size);
}
