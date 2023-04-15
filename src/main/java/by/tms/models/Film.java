package by.tms.models;

public class Film extends Show {

    @Override
    public String toString() {
        return "Название: " + super.getShowTitle() +
                ", Год выпуска: " + super.getReleaseYear() +
                ", Страна производства: " + super.getCountryCode() +
                ", Рейтинг фильма: " + super.getRating() +
                ", Количество проголосовавших: " + super.getRatesCounter();

    }
}
