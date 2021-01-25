package mrs.repositories.impl;

import mrs.models.entity.MovieEntity;
import mrs.models.entity.UserEntity;
import mrs.repositories.MovieDetailsRepository;
import mrs.repositories.MovieRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieRepositoryServiceImpl implements MovieRepositoryService {

    private final MovieDetailsRepository movieDetailsRepository;

    @Autowired
    public MovieRepositoryServiceImpl(
            MovieDetailsRepository movieDetailsRepository) {this.movieDetailsRepository = movieDetailsRepository;}

    public MovieEntity addMovie(final MovieEntity movieEntity){
        return movieDetailsRepository.save(movieEntity);
    }


    public MovieEntity getMovie(final String name){
        Optional<MovieEntity> movieEntityOptional = movieDetailsRepository.findByMovieName(name);
        if(movieEntityOptional.isPresent())
            return movieEntityOptional.get();
        else {
            throw new RuntimeException("movie not found");
        }

    }
}

