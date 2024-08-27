package com.techlab.cloud.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.techlab.cloud.dto.ImageModel;

public interface ImageService {

	ResponseEntity<Map> uploadImage(ImageModel imageModel);

}
