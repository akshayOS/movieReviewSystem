package mrs.services.impl;

import mrs.models.entity.MovieEntity;
import mrs.repositories.MovieRepositoryService;
import mrs.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepositoryService movieRepositoryService;

    @Autowired
    public MovieServiceImpl(MovieRepositoryService movieRepositoryService) {
        this.movieRepositoryService = movieRepositoryService;
    }

    public Boolean addMovie(final String movieName, final Integer releaseYear, final List<String> genre) {
        try {
            genre.parallelStream()
                 .forEach(genreItem -> movieRepositoryService.addMovie(
                         buildMovieEntity(movieName, releaseYear, genreItem)));
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private MovieEntity buildMovieEntity(String movieName, Integer releaseYear, String genreItem) {

        return MovieEntity.builder()
                          .movieName(movieName)
                          .genre(genreItem)
                          .releaseYear(releaseYear)
                          .build();
    }

    public MovieEntity getMovie(final String movieName) {
        return movieRepositoryService.getMovie(movieName);
    }

}
