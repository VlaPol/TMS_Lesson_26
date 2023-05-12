package by.tms.models;

import java.util.Objects;

public class Film extends Show {

    public Film(String showTitle, int releaseYear, String countryCode, double rating, int ratesCounter) {
        super(showTitle, releaseYear, countryCode, rating, ratesCounter);
    }

    public Film() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return  Objects.equals(this.getShowTitle(), film.getShowTitle())
                && Objects.equals(this.getCountryCode(), film.getCountryCode())
                && this.getReleaseYear() == film.getReleaseYear()
                && this.getRatesCounter() == film.getRatesCounter()
                && this.getRating() == film.getRating();
    }

    @Override
    public String toString() {
        return "Название: " + super.getShowTitle() +
                ", Год выпуска: " + super.getReleaseYear() +
                ", Страна производства: " + super.getCountryCode() +
                ", Рейтинг фильма: " + super.getRating() +
                ", Количество проголосовавших: " + super.getRatesCounter();

    }
}
