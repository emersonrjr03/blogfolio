package br.com.herms.blogfolio.service;

import br.com.herms.blogfolio.model.Post;

import java.util.List;

public interface BlogService<T> {

    List<T> findAll();

    T findById(Long id);

    T save(T post);
}
