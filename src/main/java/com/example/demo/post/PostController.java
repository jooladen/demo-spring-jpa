package com.example.demo.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> list() {
        return postRepository.findAll();
    } 

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post req) {
        //Post saved = postRepository.save(new Post(req.getTitle(), req.getContent()));
        //return ResponseEntity.created(URI.create("/posts/" + saved.getId())).body(saved);

        Post saved = postRepository.save(new Post(req.getTitle(), req.getContent()));
        return ResponseEntity.ok(saved);
    }
}
