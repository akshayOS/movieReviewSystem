package mrs.repositories;

import mrs.models.entity.MovieEntity;

public interface MovieRepositoryService {

    MovieEntity addMovie(MovieEntity movieEntity);

    MovieEntity getMovie(String movieName);
}
