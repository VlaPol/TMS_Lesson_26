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

            List<Film> filmsList = csvFilmsLines.map(film -> film.split(","))
                    .map(ar -> new Film(ar[0], Integer.parseInt(ar[1]), ar[2], Double.parseDouble(ar[3]), Integer.parseInt(ar[4])))
                    .toList();


            List<Series> seriesList = csvSeriesLines
                    .map(series -> series.split(","))
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
                    })
                    .toList();

            preparedList = Stream.concat(filmsList.stream(), seriesList.stream())
                    .toList();
            System.out.println();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return preparedList;
//        List<Film> filmList = filmsFileParser(FILMS_FILE_NAME);
//        List<Series> seriesList = seriesFileParser(SERIES_FILE_NAME);
//
//        List<Show> showList = new ArrayList<>();
//        showList.addAll(filmList);
//        showList.addAll(seriesList);

    }

//    private static List<Film> filmsFileParser(Path path) {
//
//        List<Film> filmsList = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                String[] film = line.split(",");
//                Film tmpFilm = new Film();
//                tmpFilm.setShowTitle(film[0]);
//                tmpFilm.setReleaseYear(Integer.parseInt(film[1]));
//                tmpFilm.setCountryCode(film[2]);
//                tmpFilm.setRating(Double.parseDouble(film[3]));
//                tmpFilm.setRatesCounter(Integer.parseInt(film[4]));
//                filmsList.add(tmpFilm);
//            }
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        }
//
//
//        return filmsList;
//    }

//    private static List<Series> seriesFileParser(Path path) {
//

//        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                String[] series = line.split(",");
//                Series tmpSeries = new Series();
//                tmpSeries.setShowTitle(series[0]);
//                tmpSeries.setReleaseYear(Integer.parseInt(series[1]));
//                tmpSeries.setLastEpisode(series[2]);
//                tmpSeries.setCountryCode(series[3]);
//                tmpSeries.setNumberOfSeasons(Integer.parseInt(series[4]));
//                tmpSeries.setNumberOfEpisodes(Integer.parseInt(series[5]));
//                tmpSeries.setRating(Double.parseDouble(series[6]));
//                tmpSeries.setRatesCounter(Integer.parseInt(series[7]));
//                filmsList.add(tmpSeries);
//            }
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        }
//
//
//        return preparedList;
//    }
}
