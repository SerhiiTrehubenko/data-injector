package com.tsa.injector.datainjector;

import com.tsa.injector.dataparser.ParserFactory;
import com.tsa.injector.domain.*;
import com.tsa.injector.file.FileHolder;
import com.tsa.injector.service.ServiceFactory;

import java.io.File;
import java.util.List;

public class DataInjectorImpl implements DataInjector {
    private final static String USER_FILE_NAME = "user.txt";
    private final static String GENRE_FILE_NAME = "genre.txt";
    private final static String MOVIE_FILE_NAME = "movie.txt";
    private final static String POSTER_FILE_NAME = "poster.txt";
    private final static String REVIEW_FILE_NAME = "review.txt";
    private final FileHolder filesHolder;
    private final ParserFactory parserFactory;
    private final ServiceFactory serviceFactory;

    public DataInjectorImpl(FileHolder filesHolder, ParserFactory parserFactory, ServiceFactory serviceFactory) {
        this.filesHolder = filesHolder;
        this.parserFactory = parserFactory;
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void inject() {
        injectUsers();
        insertGenres();
        injectMovies();
        injectPosters();
        injectReviews();
    }

    private void injectUsers() {
        if (filesHolder.isPresent(USER_FILE_NAME)) {
            File userFile = filesHolder.getFile(USER_FILE_NAME);
            List<UserDto> users = parserFactory.getUserParser().parse(userFile);
            serviceFactory.userService().insert(users);
        }
    }

    private void insertGenres() {
        if (filesHolder.isPresent(GENRE_FILE_NAME)) {
            File genreFile = filesHolder.getFile(GENRE_FILE_NAME);
            List<GenreDto> genres = parserFactory.getGenreParser().parse(genreFile);
            serviceFactory.genreService().insert(genres);
        }
    }

    private void injectMovies() {
        if (filesHolder.isPresent(MOVIE_FILE_NAME)) {
            File movieFile = filesHolder.getFile("movie.txt");
            List<MovieDto> movies = parserFactory.getMovieParser().parse(movieFile);
            serviceFactory.movieService().insert(movies);
        }
    }

    private void injectPosters() {
        if (filesHolder.isPresent(POSTER_FILE_NAME)) {
            File posterFile = filesHolder.getFile(POSTER_FILE_NAME);
            List<PosterDto> posters = parserFactory.getPosterParser().parse(posterFile);
            serviceFactory.posterService().insert(posters);
        }
    }

    private void injectReviews() {
        if (filesHolder.isPresent(REVIEW_FILE_NAME)) {
            File reviewFile = filesHolder.getFile(REVIEW_FILE_NAME);
            List<ReviewDto> reviews = parserFactory.getReviewParser().parse(reviewFile);
            serviceFactory.reviewService().insert(reviews);
        }
    }
}
