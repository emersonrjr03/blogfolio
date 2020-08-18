package br.com.herms.blogfolio.repository;

import br.com.herms.blogfolio.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
