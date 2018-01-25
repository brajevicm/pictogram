package com.pictogram.pictogram.service;

import com.pictogram.pictogram.domain.PostDomain;

import java.util.List;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */

public interface PostService {
  void save(PostDomain post);

  PostDomain findOne(Long id);

  List<PostDomain> findAllByType(String type, int page, int size);

  List<PostDomain> findAllByUser(Long userId, int page, int size);
}
