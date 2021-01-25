package mrs.services;

import mrs.models.entity.MovieEntity;

import java.util.List;

public interface MovieService {

    Boolean addMovie(String movieName, Integer releaseYear, List<String> genreList);

    MovieEntity getMovie(String movieName);
}
