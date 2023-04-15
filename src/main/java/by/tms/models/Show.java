package by.tms.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public abstract class Show {

    private String showTitle;
    private int releaseYear;
    private String countryCode;
    private double rating;
    private int ratesCounter;

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatesCounter() {
        return ratesCounter;
    }

    public void setRatesCounter(int ratesCounter) {
        this.ratesCounter = ratesCounter;
    }

    @Override
    public String toString() {
        return "Show{" +
                "showTitle='" + showTitle + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", rating=" + rating +
                ", ratesCounter=" + ratesCounter +
                '}';
    }

    private static class CompareByName implements Comparator<Show> {

        @Override
        public int compare(Show show1, Show show2) {
            return show1.getShowTitle().compareTo(show2.getShowTitle());
        }
    }

    private static class CompareByCountry implements Comparator<Show> {

        @Override
        public int compare(Show show1, Show show2) {
            return show1.getCountryCode().compareTo(show2.getCountryCode());
        }
    }

    private static class CompareByYear implements Comparator<Show> {

        @Override
        public int compare(Show show1, Show show2) {
            return Integer.compare(show2.getReleaseYear(), show1.getReleaseYear());
        }
    }

    private static class CompareByRating implements Comparator<Show> {

        @Override
        public int compare(Show show1, Show show2) {
            return Double.compare(show1.getRating(), show2.getRating());
        }
    }

    private static class CompareByRatesCounter implements Comparator<Show> {

        @Override
        public int compare(Show show1, Show show2) {
            return Integer.compare(show1.getRatesCounter(), show2.getRatesCounter());
        }
    }

    public static final Comparator<Show> COMPARE_BY_NAME = new CompareByName();
    public static final Comparator<Show> COMPARE_BY_COUNTRY = new CompareByCountry();
    public static final Comparator<Show> COMPARE_BY_YEAR = new CompareByYear();
    public static final Comparator<Show> COMPARE_BY_RATING = new CompareByRating();
    public static final Comparator<Show> COMPARE_BY_RATES_COUNTER = new CompareByRatesCounter();
    public static List<Comparator<Show>> getComparators() {

        List<Comparator<Show>> comparators = new ArrayList<>();

        comparators.add(COMPARE_BY_NAME);
        comparators.add(COMPARE_BY_COUNTRY);
        comparators.add(COMPARE_BY_YEAR);
        comparators.add(COMPARE_BY_RATING);
        comparators.add(COMPARE_BY_RATES_COUNTER);

        return comparators;
    }

}
