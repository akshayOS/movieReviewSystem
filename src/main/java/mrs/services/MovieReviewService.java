package mrs.services;

import mrs.models.entity.MovieReviewEntity;

import java.util.List;

public interface MovieReviewService {

    MovieReviewEntity addReview(String userName, String movieName, Integer rating);

    List<MovieReviewEntity> getMoviesByReviewScoreByViewerWithGenre(String genre, Integer sizeOfList);

    List<MovieReviewEntity> getMoviesByReviewScoreByViewerWithReleaseYear(Integer releaseYear, Integer sizeOfList);

    List<MovieReviewEntity> getMoviesByReviewScoreByCriticWithGenre(String genre, Integer sizeOfList);

    List<MovieReviewEntity> getMoviesByReviewScoreByCriticWithReleaseYear(Integer releaseYear, Integer sizeOfList);

    Double getAverageReviewScoreInYear(Integer year);

    Double getAverageReviewScoreInGenre(String genre);

    Double getAverageReviewScoreOfMovie(String movieName);
}
