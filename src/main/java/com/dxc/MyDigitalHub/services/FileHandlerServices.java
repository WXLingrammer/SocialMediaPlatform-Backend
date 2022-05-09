package com.dxc.MyDigitalHub.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHandlerServices implements FileServices {
	private final Path root = Paths.get("C:\\SocialMediaProject\\MyDigitalHub\\src\\assets\\uploads");
	@Override
	public void init() {
		Path target;
		Path pwd = Paths.get("").toAbsolutePath();
		try {
			target = Files.createDirectory(root);
			Path relative = pwd.relativize(target);
			System.out.println("target: " + relative);
		} catch (IOException e) {
			throw new RuntimeException("Could not create folder for upload!");
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			System.out.println(this.root.resolve(file.getOriginalFilename()).getRoot().relativize(this.root.resolve(file.getOriginalFilename())));
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file in folder. Error: " + e.getMessage());
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}

}
