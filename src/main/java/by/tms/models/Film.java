package by.tms.models;

public class Film extends Show {

    public Film(String showTitle, int releaseYear, String countryCode, double rating, int ratesCounter) {
        super(showTitle, releaseYear, countryCode, rating, ratesCounter);
    }

    public Film() {
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
