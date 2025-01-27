package com.example.rest.domain.post.post.controller;

import com.example.rest.domain.post.post.entity.Post;
import com.example.rest.domain.post.post.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> getItems() {
        return postService.getItems();
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable long id) {
        Post post = postService.getItem(id).get();
        postService.delete(post);

        Map<String, Object> rsData = new HashMap<>();
        rsData.put("code", "200-1");
        rsData.put("msg", "%d번 글 삭제가 완료되었습니다.".formatted(id));

        return rsData;
    }

    @AllArgsConstructor
    @Getter
    public static class ModifyForm {
        private String title;
        private String content;
    }

    @PutMapping("{id}")
    public Map<String, Object> modify(@PathVariable long id, @RequestBody ModifyForm form) {
        Post post = postService.getItem(id).get();
        postService.modify(post, form.getTitle(), form.getContent());

        Map<String, Object> rsData = new HashMap<>();
        rsData.put("code", "200-1");
        rsData.put("msg", "%d번 글 수정이 완료되었습니다.".formatted(id));

        return rsData;
    }
}
