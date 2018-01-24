package com.pictogram.pictogram.service;

import com.pictogram.pictogram.domain.PostDomain;
import org.springframework.data.domain.Page;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */

public interface PostService {
  void save(PostDomain post);

  PostDomain findOne(Long id);

  Page<PostDomain> findAllByType(String type, int page, int size);

  Page<PostDomain> findAllByUser(Long userId, int page, int size);
}
