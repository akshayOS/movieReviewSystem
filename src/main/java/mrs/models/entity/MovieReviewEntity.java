package mrs.models.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "movie_review_details")
@Data
@Builder
public class MovieReviewEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer reviewScore;

    private String reviewerCapability;

    private String movieName;

    private Integer releaseYear;

    private String movieGenre;

    private String userName;
}
