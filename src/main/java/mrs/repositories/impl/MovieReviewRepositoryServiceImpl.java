package mrs.repositories.impl;

import mrs.models.entity.MovieReviewEntity;
import mrs.repositories.MovieReviewDetailsRepository;
import mrs.repositories.MovieReviewRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieReviewRepositoryServiceImpl implements MovieReviewRepositoryService {

    private final MovieReviewDetailsRepository movieReviewDetailsRepository;

    @Autowired
    public MovieReviewRepositoryServiceImpl(
            MovieReviewDetailsRepository movieReviewDetailsRepository) {
        this.movieReviewDetailsRepository = movieReviewDetailsRepository;
    }

    @Override
    public MovieReviewEntity addReview(MovieReviewEntity movieReviewEntity) {
        return movieReviewDetailsRepository.save(movieReviewEntity);
    }

    @Override
    public List<MovieReviewEntity> getMoviesByReviewScoreByReviewerAndGenre(String reviewer, String genre,
                                                                            Integer sizeOfList) {
        List<MovieReviewEntity> movieReviewEntityList =
                movieReviewDetailsRepository.findAllByReviewerCapabilityAndMovieGenreOrderByReviewScoreDesc(reviewer,
                                                                                                            genre);
       if(movieReviewEntityList.size() > sizeOfList)
           return movieReviewEntityList.subList(0,sizeOfList);
        return movieReviewEntityList;
    }

    @Override
    public List<MovieReviewEntity> getMoviesByReviewScoreByReviewerAndReleaseYear(String reviewer, Integer releaseYear,
                                                                                  Integer sizeOfList) {

        List<MovieReviewEntity> movieReviewEntityList =
                movieReviewDetailsRepository.findAllByReviewerCapabilityAndReleaseYearOrderByReviewScoreDesc(reviewer,
                                                                                                            releaseYear);
        if(movieReviewEntityList.size() > sizeOfList)
            return movieReviewEntityList.subList(0,sizeOfList);
        return movieReviewEntityList;
    }

    @Override
    public Double getAverageReviewScoreInYear(Integer year) {
        return movieReviewDetailsRepository.findAverageReviewScoreByReleaseYear(year);
    }

    @Override
    public Double getAverageReviewScoreInGenre(String genre) {
    return movieReviewDetailsRepository.findAverageReviewScoreByMovieGenre(genre);
    }

    @Override
    public Double getAverageReviewScoreOfMovie(String movieName) {
    return movieReviewDetailsRepository.findAverageReviewScoreByMovieName(movieName);
    }
}
