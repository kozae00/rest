package com.example.rest.domain.post.post.controller;

import com.example.rest.domain.post.post.entity.Post;
import com.example.rest.domain.post.post.service.PostService;
import com.example.rest.global.dto.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> getItems() {
        return postService.getItems();
    }


    @GetMapping("{id}")
    public Post getItem(@PathVariable long id) {
        return postService.getItem(id).get();
    }


    @DeleteMapping("/{id}")
    public RsData delete(@PathVariable long id) {
        Post post = postService.getItem(id).get();
        postService.delete(post);

        return new RsData(
                "200-1",
                "%번 글 삭제 되었습니다.".formatted(id)
        );
    }


    record ModifyReqBody(String title, String content) { }

    @PutMapping("{id}")
    public RsData modify(@PathVariable long id, @RequestBody ModifyReqBody body) {
        Post post = postService.getItem(id).get();
        postService.modify(post, body.title(), body.content());

        return new RsData(
                "200-1",
                "%번 글 수정이 되었습니다.".formatted(id)
        );
    }


    record WriteReqBody(String title, String content) { }

    @PostMapping
    public RsData write(@RequestBody WriteReqBody body) {
        postService.write(body.title(), body.content());

        return new RsData(
                "200-1",
                "글 작성이 완료되었습니다."
        );
    }
}
