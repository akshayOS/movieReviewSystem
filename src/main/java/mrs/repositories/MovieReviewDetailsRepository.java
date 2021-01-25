package mrs.repositories;

import mrs.models.entity.MovieReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieReviewDetailsRepository extends JpaRepository<MovieReviewEntity, Integer> {

    List<MovieReviewEntity> findAllByReviewerCapabilityAndMovieGenreOrderByReviewScoreDesc(String reviewerCapability, String genre);

    List<MovieReviewEntity> findAllByReviewerCapabilityAndReleaseYearOrderByReviewScoreDesc(String reviewerCapability, Integer releaseYear);

    @Query("SELECT AVG(e.reviewScore) FROM MovieReviewEntity e WHERE e.releaseYear = :releaseYear")
    Double findAverageReviewScoreByReleaseYear(Integer releaseYear);

    @Query("SELECT AVG(e.reviewScore) FROM MovieReviewEntity e WHERE e.movieGenre = :movieGenre")
    Double findAverageReviewScoreByMovieGenre(String movieGenre);

    @Query("SELECT AVG(e.reviewScore) FROM MovieReviewEntity e WHERE e.movieName = :movieName")
    Double findAverageReviewScoreByMovieName(String movieName);

    Optional<MovieReviewEntity> findFirstByUserNameAndMovieName(String userName, String movieName);



}
