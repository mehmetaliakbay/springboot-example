package com.example.postapi.service.impl;

import com.example.postapi.dto.request.GenericPostRequest;
import com.example.postapi.dto.request.UpdatePostRequest;
import com.example.postapi.dto.response.GenericPostResponse;
import com.example.postapi.dto.response.UpdatePostResponse;
import com.example.postapi.entity.Post;
import com.example.postapi.entity.PostCategory;
import com.example.postapi.exception.NotFoundException;
import com.example.postapi.exception.RestException;
import com.example.postapi.repository.PostRepository;
import com.example.postapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public GenericPostResponse findPostById(Long id) {
        return modelMapper.map(postRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Post not found!")), GenericPostResponse.class);
    }

    @Override
    public Collection<GenericPostResponse> findAll() {
        return postRepository.findAll()
                .stream()
                .map(post -> modelMapper.map(post, GenericPostResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GenericPostResponse createPost(GenericPostRequest postRequest) {
        try {
            var managedPost = postRepository.save(modelMapper.map(postRequest, Post.class));
            return modelMapper.map(managedPost, GenericPostResponse.class);
        } catch (Exception e) {
            throw new RestException(e.getMessage());
        }
    }

    @Override
    public UpdatePostResponse updatePost(Long id, UpdatePostRequest updatePostRequest) {
        var post = postRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Post not found!"));
        modelMapper.map(updatePostRequest, post);
        return modelMapper.map(postRepository.saveAndFlush(post), UpdatePostResponse.class);
    }

    @Override
    public GenericPostResponse deletePost(Long id) {
        var managedPost = postRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Post not found!"));
        postRepository.delete(managedPost);
        return modelMapper.map(managedPost, GenericPostResponse.class);

    }

    @Override
    public Collection<GenericPostResponse> findPostsByAuthor(String author) {

        return postRepository.findPostsByAuthor(author)
                .stream()
                .map(postAuthor -> modelMapper.map(postAuthor, GenericPostResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<GenericPostResponse> findPostsByCategory(PostCategory category) {
        return postRepository.findPostsByCategory(category)
                .stream()
                .map(postCategory -> modelMapper.map(postCategory, GenericPostResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<GenericPostResponse> getLastThreePost() {
        return postRepository.findAll()
                .stream()
                .map(post -> modelMapper.map(post, GenericPostResponse.class))
                .sorted(Comparator.comparing(GenericPostResponse::getCreatedAt).reversed())
                .limit(3)
                .collect(Collectors.toList());

    }
}
