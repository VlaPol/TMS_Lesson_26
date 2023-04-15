package by.tms.models;

public class Series extends Show {

    private String lastEpisode;
    private int numberOfSeasons;
    private int numberOfEpisodes;

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
