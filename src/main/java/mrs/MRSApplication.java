package mrs;

import mrs.constants.UserConstants;
import mrs.models.entity.UserEntity;
import mrs.repositories.UserDetailsRepository;
import mrs.services.MovieReviewService;
import mrs.services.MovieService;
import mrs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class MRSApplication {

    private final UserDetailsRepository userDetailsRepository;

    private final MovieService movieService;

    private final UserService userService;

    private final MovieReviewService movieReviewService;

    @Autowired
    public MRSApplication(
            UserDetailsRepository userDetailsRepository, MovieService movieService,
            UserService userService, MovieReviewService movieReviewService) {
        this.userDetailsRepository = userDetailsRepository;
        this.movieService = movieService;
        this.userService = userService;
        this.movieReviewService = movieReviewService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MRSApplication.class, args);

    }

    @PostConstruct
    private void initUserDb() {
        System.out.println(userService.addUser("SRK"));
        System.out.println(userService.addUser("Salman"));
        System.out.println(userService.addUser("Deepika"));

        System.out.println(movieService.addMovie("Don", 2006, new ArrayList<>(Arrays.asList("Action", "Comedy"))));
        System.out.println(movieService.addMovie("Tiger", 2008, new ArrayList<>(Arrays.asList("Drama"))));
        System.out.println(  movieService.addMovie("Padmaavat", 2006, new ArrayList<>(Arrays.asList("Comedy"))));
        System.out.println( movieService.addMovie("Lunchbox", 2021, new ArrayList<>(Arrays.asList("Drama"))));
        System.out.println( movieService.addMovie("Guru", 2006, new ArrayList<>(Arrays.asList("Drama"))));
        System.out.println( movieService.addMovie("Metro", 2006, new ArrayList<>(Arrays.asList("Romance"))));

        System.out.println( movieReviewService.addReview("SRK","Don",2));
        System.out.println( movieReviewService.addReview("SRK","Padmaavat",8));
        System.out.println( movieReviewService.addReview("Salman","Don",5));
        System.out.println( movieReviewService.addReview("Deepika","Don",9));
        System.out.println( movieReviewService.addReview("Deepika","Guru",6));
        System.out.println( movieReviewService.addReview("SRK","Don",10));
        System.out.println( movieReviewService.addReview("Deepika","Lunchbox",5));
        System.out.println( movieReviewService.addReview("SRK","Tiger",5));
        System.out.println( movieReviewService.addReview("SRK","Metro",7));

        System.out.println(movieReviewService.getMoviesByReviewScoreByViewerWithReleaseYear(2006,1));
        System.out.println(movieReviewService.getMoviesByReviewScoreByCriticWithReleaseYear(2006,1));
        System.out.println(movieReviewService.getMoviesByReviewScoreByViewerWithGenre("Drama",1));
        System.out.println(movieReviewService.getMoviesByReviewScoreByCriticWithGenre("Drama",1));
        System.out.println(movieReviewService.getAverageReviewScoreInYear(2006));

    }
}
