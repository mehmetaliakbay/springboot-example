package com.example.postapi.service;

import com.example.postapi.dto.request.GenericPostRequest;
import com.example.postapi.dto.request.UpdatePostRequest;
import com.example.postapi.dto.response.GenericPostResponse;
import com.example.postapi.dto.response.UpdatePostResponse;
import com.example.postapi.entity.PostCategory;

import java.util.Collection;

public interface PostService {

    GenericPostResponse findPostById(Long id);

    Collection<GenericPostResponse> findAll();

    GenericPostResponse createPost(GenericPostRequest postRequest);

    UpdatePostResponse updatePost(Long id, UpdatePostRequest updatePostRequest);

    GenericPostResponse deletePost(Long id);

    Collection<GenericPostResponse> findPostsByAuthor(String author);

    Collection<GenericPostResponse> findPostsByCategory(PostCategory category);

    Collection<GenericPostResponse> getLastThreePost();

}
