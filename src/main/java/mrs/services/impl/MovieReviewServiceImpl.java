package mrs.services.impl;

import mrs.constants.UserConstants;
import mrs.models.entity.MovieEntity;
import mrs.models.entity.MovieReviewEntity;
import mrs.models.entity.UserEntity;
import mrs.repositories.MovieReviewRepositoryService;
import mrs.services.MovieReviewService;
import mrs.services.MovieService;
import mrs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieReviewServiceImpl implements MovieReviewService {

    private final UserService userService;

    private final MovieReviewRepositoryService movieReviewRepositoryService;

    private final MovieService movieService;

    @Autowired
    public MovieReviewServiceImpl(
            MovieReviewRepositoryService movieReviewRepositoryService, UserService userService,
            MovieService movieService) {
        this.movieReviewRepositoryService = movieReviewRepositoryService;
        this.userService = userService;
        this.movieService = movieService;
    }

    @Override
    public MovieReviewEntity addReview(String userName, String movieName, Integer rating) {
        UserEntity userEntity = userService.getUserByName(userName);
        MovieEntity movieEntity = movieService.getMovie(movieName);
        if (userEntity.getCapability().equals(UserConstants.USER_CAPABILITY_CRITIC))
            rating = rating * 2;
        MovieReviewEntity movieReviewEntity = movieReviewRepositoryService.addReview(
                buildMovieReviewRequest(userName, movieName, rating, userEntity.getCapability(), movieEntity.getGenre(),
                                        movieEntity.getReleaseYear()));
        if (userEntity.getReviewCount() + 1 == UserConstants.USER_THRESHOLD_COUNT_FOR_CRITIC)
            userService.upgradeUser(UserEntity.builder()
                                              .id(userEntity.getId())
                                              .capability(UserConstants.USER_CAPABILITY_CRITIC)
                                              .reviewCount(userEntity.getReviewCount() + 1)
                                              .build());
        else
            userService.upgradeUser(UserEntity.builder()
                                              .id(userEntity.getId())
                                              .reviewCount(userEntity.getReviewCount() + 1)
                                              .build());

        return movieReviewEntity;

    }

    private MovieReviewEntity buildMovieReviewRequest(String userName, String movieName, Integer rating,
                                                      String capability, String genre, Integer releaseYear) {

        return MovieReviewEntity.builder()
                                .userName(userName)
                                .movieName(movieName)
                                .reviewScore(rating)
                                .reviewerCapability(capability)
                                .movieGenre(genre)
                                .releaseYear(releaseYear)
                                .build();
    }

    @Override
    public List<MovieReviewEntity> getMoviesByReviewScoreByViewerWithGenre(String genre,
                                                                  Integer sizeOfList) {
        return movieReviewRepositoryService.getMoviesByReviewScoreByReviewerAndGenre(UserConstants.USER_CAPABILITY_VIEWER, genre, sizeOfList);
    }

    @Override
    public List<MovieReviewEntity> getMoviesByReviewScoreByViewerWithReleaseYear(Integer releaseYear,
                                                                  Integer sizeOfList) {
        return movieReviewRepositoryService.getMoviesByReviewScoreByReviewerAndReleaseYear(UserConstants.USER_CAPABILITY_VIEWER, releaseYear, sizeOfList);
    }

    @Override
    public List<MovieReviewEntity> getMoviesByReviewScoreByCriticWithGenre(String genre,
                                                                           Integer sizeOfList) {
        return movieReviewRepositoryService.getMoviesByReviewScoreByReviewerAndGenre(UserConstants.USER_CAPABILITY_CRITIC, genre, sizeOfList);
    }

    @Override
    public List<MovieReviewEntity> getMoviesByReviewScoreByCriticWithReleaseYear(Integer releaseYear,
                                                                                 Integer sizeOfList) {
        return movieReviewRepositoryService.getMoviesByReviewScoreByReviewerAndReleaseYear(UserConstants.USER_CAPABILITY_CRITIC, releaseYear, sizeOfList);
    }

    @Override
    public Double getAverageReviewScoreInYear(Integer year) {
    return movieReviewRepositoryService.getAverageReviewScoreInYear(year);
    }

    @Override
    public Double getAverageReviewScoreInGenre(String genre) {
    return movieReviewRepositoryService.getAverageReviewScoreInGenre(genre);
    }

    @Override
    public Double getAverageReviewScoreOfMovie(String movieName) {
    return movieReviewRepositoryService.getAverageReviewScoreOfMovie(movieName);
    }
}
