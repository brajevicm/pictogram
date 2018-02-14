package com.pictogram.pictogram.config;

import com.pictogram.pictogram.ServicePackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Configuration
@ComponentScan(basePackageClasses = ServicePackage.class)
public class ServiceConfig {
}
