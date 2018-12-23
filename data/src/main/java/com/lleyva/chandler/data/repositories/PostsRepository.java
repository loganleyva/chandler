package com.lleyva.chandler.data.repositories;

import com.lleyva.chandler.data.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends RepositoryBase<Post> { }
