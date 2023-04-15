package by.tms.utils.predicates;

import by.tms.models.Show;

import java.util.function.Predicate;

public class PredicateBetweenTwoRates implements Predicate<Show> {
    private final double ratesBegin;
    private final double ratesEnd;

    public PredicateBetweenTwoRates(String query) {
        String[] tmpArray = query.split(", ");
        this.ratesBegin = Double.parseDouble(tmpArray[0]);
        this.ratesEnd = Double.parseDouble(tmpArray[1]);
    }

    @Override
    public boolean test(Show show) {
        return show.getRating() >= ratesBegin && show.getRating() <= ratesEnd;
    }
}
