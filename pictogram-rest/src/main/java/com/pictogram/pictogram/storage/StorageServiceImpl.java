package com.pictogram.pictogram.storage;

import com.pictogram.pictogram.exception.storage.StorageException;
import com.pictogram.pictogram.exception.storage.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Service
public class StorageServiceImpl implements StorageService {
  private final Path rootLocation = Paths.get("/opt/uploads/");

  @Autowired
  private HttpServletRequest httpServletRequest;

  @Override
  public String store(MultipartFile file) {
    String filename = StringUtils.cleanPath(file.getOriginalFilename());
    String storageDir = "uploads";
    String delimiter = "/";
    String url = httpServletRequest.getRequestURL().toString();
    String baseURL = url.substring(0, url.length() - httpServletRequest.getRequestURI().length())
      + httpServletRequest.getContextPath() + delimiter;
    String fullPath = baseURL + storageDir + delimiter + filename;

    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file " + filename);
      }
      if (filename.contains("src/main")) {
        // This is a security check
        throw new StorageException(
          "Cannot store file with relative path outside current directory "
            + filename);
      }
      Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
        StandardCopyOption.REPLACE_EXISTING);
      return fullPath;
    } catch (IOException e) {
      throw new StorageException("Failed to store file " + filename, e);
    }
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.rootLocation, 1)
        .filter(path -> !path.equals(this.rootLocation))
        .map(this.rootLocation::relativize);
    } catch (IOException e) {
      throw new StorageException("Failed to read stored files", e);
    }

  }

  @Override
  public Path load(String filename) {
    return rootLocation.resolve(filename);
  }

  @Override
  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new StorageFileNotFoundException(
          "Could not read file: " + filename);

      }
    } catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }

  @Override
  public void init() {
    try {
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new StorageException("Could not initialize storage", e);
    }
  }
}
