package com.pictogram.pictogram.rest.service;

import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.dto.PostDto;


/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */


public interface PostService {
  void save(PostDto postDto);

  Post findOne(Long id);




  }
