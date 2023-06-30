package com.tsa.injector.service;

import com.tsa.injector.dao.BaseDao;
import com.tsa.injector.domain.*;

import java.util.List;

public class MovieService implements BaseService<MovieDto> {
    private final BaseDao<MovieDto> movieDao;
    private final BaseDao<String> countryDao;
    private final BaseDao<GenreDto> genreDao;
    private final BaseDao<RatingDto> ratingDao;
    private final BaseDao<UserDto> userDao;
    private final BaseDao<TwoNumberHolder> movieCountryDao;
    private final BaseDao<TwoNumberHolder> movieGenreDao;

    public MovieService(BaseDao<MovieDto> movieDao,
                        BaseDao<String> countryDao,
                        BaseDao<GenreDto> genreDao,
                        BaseDao<RatingDto> ratingDao,
                        BaseDao<UserDto> userDao,
                        BaseDao<TwoNumberHolder> movieCountryDao,
                        BaseDao<TwoNumberHolder> movieGenreDao) {
        this.movieDao = movieDao;
        this.countryDao = countryDao;
        this.genreDao = genreDao;
        this.ratingDao = ratingDao;
        this.userDao = userDao;
        this.movieCountryDao = movieCountryDao;
        this.movieGenreDao = movieGenreDao;
    }

    @Override
    public boolean insert(List<MovieDto> movies) {
        int adminId = getAdminId();

        for (MovieDto movieDto : movies) {
            if (movieDao.notPresent(movieDto)) {
                movieDao.insert(movieDto);
                int movieId = movieDao.getId(movieDto);
                assignGenres(movieDto, movieId);
                insertCountries(movieDto);
                assignCountries(movieDto, movieId);
                assignRating(adminId, movieDto, movieId);
            }
        }
        return true;
    }

    private int getAdminId() {
        UserDto adminUser = createAdminUser();
        return userDao.getId(adminUser);
    }

    private UserDto createAdminUser() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("ADMIN");
        userDto.setLastName("ADMIN");
        userDto.setEmail("admin@admin.com");
        return userDto;
    }

    private void assignGenres(MovieDto movieDto, int movieId) {
        List<GenreDto> genres = movieDto.getGenres();
        genres.forEach(genre -> {
            int genreId = genreDao.getId(genre);
            if (genreId == 0) {
                movieDao.deleteBy(movieDto);
                throw new RuntimeException("Provided GENRE: [%s] is not present in Database".formatted(genre));
            }
            TwoNumberHolder twoNumberHolder = new TwoNumberHolder(movieId, genreId);
            if (movieGenreDao.notPresent(twoNumberHolder)) {
                movieGenreDao.insert(twoNumberHolder);
            }
        });
    }

    private void insertCountries(MovieDto movieDto) {
        List<String> countries = movieDto.getCountries();
        countries.stream().filter(countryDao::notPresent).forEach(countryDao::insert);
    }

    private void assignCountries(MovieDto movieDto, int movieId) {
        List<String> countries = movieDto.getCountries();
        countries.forEach(country -> {
            TwoNumberHolder twoNumberHolder = new TwoNumberHolder(movieId, countryDao.getId(country));
            if (movieCountryDao.notPresent(twoNumberHolder)) {
                movieCountryDao.insert(twoNumberHolder);
            }
        });
    }

    private void assignRating(int adminId, MovieDto movieDto, int movieId) {
        RatingDto ratingDto = new RatingDto(movieId, adminId, movieDto.getRating());
        if (ratingDao.notPresent(ratingDto)) {
            ratingDao.insert(ratingDto);
        }
    }
}
