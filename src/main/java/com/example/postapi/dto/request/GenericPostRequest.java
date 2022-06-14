package com.example.postapi.dto.request;

import com.example.postapi.entity.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericPostRequest {
    @NotBlank
    private String author;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotNull
    private PostCategory category;
}
