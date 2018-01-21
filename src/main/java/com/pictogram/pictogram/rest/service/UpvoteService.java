package com.pictogram.pictogram.rest.service;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface UpvoteService {
  void savePost(Long postId);

  void saveComment(Long comment);
}
