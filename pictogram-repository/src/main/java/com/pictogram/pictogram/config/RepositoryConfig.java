package com.pictogram.pictogram.config;

import com.pictogram.pictogram.RepositoryPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Configuration
@ComponentScan(basePackageClasses = RepositoryPackage.class)
public class RepositoryConfig {
}
