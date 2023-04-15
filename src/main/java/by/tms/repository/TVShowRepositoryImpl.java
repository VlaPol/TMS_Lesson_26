package by.tms.repository;

import by.tms.models.Film;
import by.tms.models.Series;
import by.tms.models.Show;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TVShowRepositoryImpl implements TVShowRepository {

    final Path SERIES_FILE_NAME = Path.of("series.csv");
    final Path FILMS_FILE_NAME = Path.of("films.csv");

    @Override
    public List<Show> getDataFromFile() throws IOException {

        List<Film> filmList = filmsFileParser(FILMS_FILE_NAME);
        List<Series> seriesList = seriesFileParser(SERIES_FILE_NAME);

        List<Show> showList = new ArrayList<>();
        showList.addAll(filmList);
        showList.addAll(seriesList);

        return showList;
    }

    private static List<Film> filmsFileParser(Path path) throws IOException {

        List<Film> filmsList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path.toString()));
        String line;

        while ((line = br.readLine()) != null) {
            String[] film = line.split(",");
            Film tmpFilm = new Film();
            tmpFilm.setShowTitle(film[0]);
            tmpFilm.setReleaseYear(Integer.parseInt(film[1]));
            tmpFilm.setCountryCode(film[2]);
            tmpFilm.setRating(Double.parseDouble(film[3]));
            tmpFilm.setRatesCounter(Integer.parseInt(film[4]));
            filmsList.add(tmpFilm);
        }


        return filmsList;
    }

    private static List<Series> seriesFileParser(Path path) throws IOException {

        List<Series> filmsList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path.toString()));
        String line;

        while ((line = br.readLine()) != null) {
            String[] series = line.split(",");
            Series tmpSeries = new Series();
            tmpSeries.setShowTitle(series[0]);
            tmpSeries.setReleaseYear(Integer.parseInt(series[1]));
            tmpSeries.setLastEpisode(series[2]);
            tmpSeries.setCountryCode(series[3]);
            tmpSeries.setNumberOfSeasons(Integer.parseInt(series[4]));
            tmpSeries.setNumberOfEpisodes(Integer.parseInt(series[5]));
            tmpSeries.setRating(Double.parseDouble(series[6]));
            tmpSeries.setRatesCounter(Integer.parseInt(series[7]));
            filmsList.add(tmpSeries);
        }


        return filmsList;
    }
}
