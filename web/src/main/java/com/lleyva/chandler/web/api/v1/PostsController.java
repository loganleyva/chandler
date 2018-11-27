package com.lleyva.chandler.web.api.v1;

import com.lleyva.chandler.data.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/posts")
public class PostsController extends ApiV1CrudControllerBase<Post> { }
