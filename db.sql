CREATE TABLE users (
  id                       BIGINT       NOT NULL AUTO_INCREMENT UNIQUE,
  username                 VARCHAR(32)  NOT NULL,
  password                 VARCHAR(255) NOT NULL,
  first_name               VARCHAR(32)  NOT NULL,
  last_name                VARCHAR(32)  NOT NULL,
  email                    VARCHAR(255) NOT NULL,
  profile_image            VARCHAR(255) NOT NULL,
  enabled                  TINYINT      NOT NULL DEFAULT 1,
  created_date             TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_password_reset_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY (username, email, id),
  PRIMARY KEY (id)
);

CREATE TABLE authorities (
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  UNIQUE KEY (id),
  PRIMARY KEY (id)
);

CREATE TABLE user_authorities (
  id           BIGINT NOT NULL AUTO_INCREMENT,
  user_id      BIGINT NOT NULL,
  authority_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (authority_id) REFERENCES authorities (id),
  UNIQUE KEY (id),
  PRIMARY KEY (id)
);

CREATE TABLE posts (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  title        VARCHAR(32)  NOT NULL,
  description  VARCHAR(255) NOT NULL,
  post_image   VARCHAR(255) NOT NULL,
  created_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  enabled      TINYINT      NOT NULL DEFAULT 1,
  user_id      BIGINT       NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  UNIQUE KEY (id),
  PRIMARY KEY (id)
);

CREATE TABLE comments (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  description  VARCHAR(255) NOT NULL,
  created_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  enabled      TINYINT      NOT NULL DEFAULT 1,
  user_id      BIGINT       NOT NULL,
  post_id      BIGINT       NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (post_id) REFERENCES posts (id),
  UNIQUE KEY (id),
  PRIMARY KEY (id)
);

CREATE TABLE followers (
  id           BIGINT    NOT NULL AUTO_INCREMENT,
  user_id      BIGINT    NOT NULL,
  follow_id    BIGINT    NOT NULL,
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (follow_id) REFERENCES users (id),
  UNIQUE KEY (id),
  PRIMARY KEY (id)
);

CREATE TABLE upvoted_posts (
  id           BIGINT    NOT NULL AUTO_INCREMENT,
  user_id      BIGINT    NOT NULL,
  post_id      BIGINT    NOT NULL,
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  seen         TINYINT   NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (post_id) REFERENCES posts (id),
  UNIQUE KEY (id),
  PRIMARY KEY (id)
);

CREATE TABLE upvoted_comments (
  id           BIGINT    NOT NULL AUTO_INCREMENT,
  user_id      BIGINT    NOT NULL,
  comment_id   BIGINT    NOT NULL,
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  seen         TINYINT   NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (comment_id) REFERENCES comments (id),
  UNIQUE KEY (id),
  PRIMARY KEY (id)
);

CREATE TABLE reported_comments (
  id           BIGINT    NOT NULL AUTO_INCREMENT,
  user_id      BIGINT    NOT NULL,
  comment_id   BIGINT    NOT NULL,
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  seen         TINYINT   NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (comment_id) REFERENCES comments (id),
  UNIQUE KEY (id),
  PRIMARY KEY (id)
);

CREATE TABLE reported_posts (
  id           BIGINT    NOT NULL AUTO_INCREMENT,
  user_id      BIGINT    NOT NULL,
  post_id      BIGINT    NOT NULL,
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  seen         TINYINT   NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (post_id) REFERENCES posts (id),
  UNIQUE KEY (id),
  PRIMARY KEY (id)
);
