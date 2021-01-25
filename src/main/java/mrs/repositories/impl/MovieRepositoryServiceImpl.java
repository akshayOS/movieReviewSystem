package mrs.repositories.impl;

import lombok.extern.log4j.Log4j2;
import mrs.models.entity.MovieEntity;
import mrs.models.entity.UserEntity;
import mrs.repositories.MovieDetailsRepository;
import mrs.repositories.MovieRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class MovieRepositoryServiceImpl implements MovieRepositoryService {

    private final MovieDetailsRepository movieDetailsRepository;

    @Autowired
    public MovieRepositoryServiceImpl(
            MovieDetailsRepository movieDetailsRepository) {this.movieDetailsRepository = movieDetailsRepository;}

    public MovieEntity addMovie(final MovieEntity movieEntity){
        MovieEntity entity =  movieDetailsRepository.save(movieEntity);
        System.out.println(entity);
        return entity;
    }


    public MovieEntity getMovie(final String name){
        Optional<MovieEntity> movieEntityOptional = movieDetailsRepository.findFirstByMovieName(name);
        System.out.println(movieEntityOptional.get());
        if(movieEntityOptional.isPresent())
            return movieEntityOptional.get();
        else {
            log.error("movie not found");
            return new MovieEntity();
        }

    }
}

