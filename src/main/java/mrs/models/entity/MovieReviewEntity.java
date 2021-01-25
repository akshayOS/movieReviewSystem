package mrs.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "movie_review_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "review_score")
    private Integer reviewScore;

    @Column(name = "review_capability")
    private String reviewerCapability;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "movie_genre")
    private String movieGenre;

    @Column(name = "user_name")
    private String userName;
}
