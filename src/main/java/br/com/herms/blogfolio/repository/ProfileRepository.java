package br.com.herms.blogfolio.repository;

import br.com.herms.blogfolio.model.Post;
import br.com.herms.blogfolio.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
