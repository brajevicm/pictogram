package com.pictogram.pictogram.rest.service;

import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */


public interface PostService {
  void save(PostDto postDto);

  Post findOne(Long id);

  Page<Post> findAllByPage(Pageable pageable);

}
