package by.tms.utils.predicates;

import by.tms.models.Show;

import java.util.function.Predicate;

public class PredicateByCountry implements Predicate<Show> {

    private final String countryCode;

    public PredicateByCountry(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean test(Show show) {
        return show.getCountryCode().equals(countryCode);
    }
}
