package mrs.repositories;

import mrs.models.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieDetailsRepository extends JpaRepository<MovieEntity, Integer> {

    Optional<MovieEntity> findFirstByMovieName(final String name);
}
