package com.example.postapi.repository;

import com.example.postapi.entity.Post;
import com.example.postapi.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Collection<Post> findPostsByAuthor(String author);

    Collection<Post> findPostsByCategory(PostCategory category);

}
