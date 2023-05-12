package by.tms.repository;

import by.tms.models.Film;
import by.tms.models.Series;
import by.tms.models.Show;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class TVShowRepositoryImpl implements TVShowRepository {

    private static final Path SERIES_FILE_NAME = Path.of("series.csv");
    private static final Path FILMS_FILE_NAME = Path.of("films.csv");

    @Override
    public List<Show> getDataFromFile() {
        List<Show> preparedList;
        try (Stream<String> csvFilmsLines = Files.lines(FILMS_FILE_NAME, StandardCharsets.UTF_8);
             Stream<String> csvSeriesLines = Files.lines(SERIES_FILE_NAME, StandardCharsets.UTF_8)) {

            preparedList = Stream.concat(
                            csvFilmsLines.map(film -> film.split(","))
                                    .map(ar -> new Film(ar[0], Integer.parseInt(ar[1]), ar[2], Double.parseDouble(ar[3]), Integer.parseInt(ar[4]))),
                            csvSeriesLines.map(series -> series.split(","))
                                    .map(se -> {
                                        Series series = new Series();
                                        series.setShowTitle(se[0]);
                                        series.setReleaseYear(Integer.parseInt(se[1]));
                                        series.setLastEpisode(se[2]);
                                        series.setCountryCode(se[3]);
                                        series.setNumberOfSeasons(Integer.parseInt(se[4]));
                                        series.setNumberOfEpisodes(Integer.parseInt(se[5]));
                                        series.setRating(Double.parseDouble(se[6]));
                                        series.setRatesCounter(Integer.parseInt(se[7]));
                                        return series;
                                    }))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return preparedList;
    }
}
