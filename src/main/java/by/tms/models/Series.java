package by.tms.models;

import java.util.Objects;

public class Series extends Show {

    private String lastEpisode;
    private int numberOfSeasons;
    private int numberOfEpisodes;

    public Series(String showTitle, int releaseYear, String countryCode, double rating, int ratesCounter, String lastEpisode, int numberOfSeasons, int numberOfEpisodes) {
        super(showTitle, releaseYear, countryCode, rating, ratesCounter);
        this.lastEpisode = lastEpisode;
        this.numberOfSeasons = numberOfSeasons;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Series() {
    }

    public void setLastEpisode(String lastEpisode) {
        this.lastEpisode = lastEpisode;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return numberOfSeasons == series.numberOfSeasons && numberOfEpisodes == series.numberOfEpisodes && Objects.equals(lastEpisode, series.lastEpisode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastEpisode, numberOfSeasons, numberOfEpisodes);
    }

    @Override
    public String toString() {
        return "Название: " + super.getShowTitle() +
                ", Год выпуска: " + super.getReleaseYear() +
                ", Последний сезон: " + lastEpisode +
                ", Страна производства: " + super.getCountryCode() +
                ", Количество сезонов: " + numberOfSeasons +
                ", Количество серий: " + numberOfEpisodes +
                ", Рейтинг фильма: " + super.getRating() +
                ", Количество проголосовавших: " + super.getRatesCounter();
    }
}
