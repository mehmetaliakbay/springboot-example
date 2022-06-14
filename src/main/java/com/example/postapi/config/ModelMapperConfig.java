package com.example.postapi.config;

import com.example.postapi.dto.request.UpdatePostRequest;
import com.example.postapi.entity.Post;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private static final Converter<UpdatePostRequest, Post> UPDATE_POST_REQUEST_POST_CONVERTER =
            context -> {
                var post = context.getDestination();
                var updatePost = context.getSource();
                post.setAuthor(updatePost.getAuthor());
                post.setTitle(updatePost.getTitle());
                post.setText(updatePost.getText());
                post.setCategory(updatePost.getCategory());
                return post;
            };


    @Bean("standardModelMapper")
    ModelMapper createModelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.addConverter(UPDATE_POST_REQUEST_POST_CONVERTER, UpdatePostRequest.class, Post.class);
        return modelMapper;
    }
}
