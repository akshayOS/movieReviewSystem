package mrs.repositories;

import mrs.models.entity.MovieReviewEntity;

import java.util.List;

public interface MovieReviewRepositoryService {

    MovieReviewEntity addReview(MovieReviewEntity movieReviewEntity);

    List<MovieReviewEntity> getMoviesByReviewScoreByReviewerAndGenre(String reviewer, String genre, Integer sizeOfList);

    List<MovieReviewEntity> getMoviesByReviewScoreByReviewerAndReleaseYear(String reviewer, Integer releaseYear, Integer sizeOfList);

    Double getAverageReviewScoreInYear(Integer year);

    Double getAverageReviewScoreInGenre(String genre);

    Double getAverageReviewScoreOfMovie(String movieName);

    Boolean ifReviewExist(String userName, String movieName);
}
