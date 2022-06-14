package com.example.postapi.controller;

import com.example.postapi.dto.request.GenericPostRequest;
import com.example.postapi.dto.request.UpdatePostRequest;
import com.example.postapi.dto.response.GenericPostResponse;
import com.example.postapi.dto.response.UpdatePostResponse;
import com.example.postapi.entity.PostCategory;
import com.example.postapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Collection;

@RestController
@RequestScope
@RequestMapping("/posts")
@CrossOrigin
@RequiredArgsConstructor
public class PostRestController {
    private final PostService postService;

    @GetMapping("{id}")
    public GenericPostResponse findPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    @GetMapping("/author")
    public Collection<GenericPostResponse> findPostByAuthor(@RequestParam(value = "author") String author) {
        return postService.findPostsByAuthor(author);
    }

    @GetMapping("/category")
    public Collection<GenericPostResponse> findPostByCategory(@RequestParam(name = "category") PostCategory category) {
        return postService.findPostsByCategory(category);
    }

    @GetMapping("/lastThreePosts")
    public Collection<GenericPostResponse> getLastThreePost() {
        return postService.getLastThreePost();
    }

    @GetMapping("/findAllPosts")
    public Collection<GenericPostResponse> findAll() {
        return postService.findAll();
    }

    @PostMapping
    public GenericPostResponse createPost(@RequestBody GenericPostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @PutMapping(value = "{id}")
    public UpdatePostResponse updatePost(@PathVariable Long id, @RequestBody UpdatePostRequest postRequest) {
        return postService.updatePost(id, postRequest);
    }

}
