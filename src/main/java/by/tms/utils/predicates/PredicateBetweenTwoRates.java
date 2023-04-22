package by.tms.utils.predicates;

import by.tms.models.Show;

import java.util.function.Predicate;

public class PredicateBetweenTwoRates implements Predicate<Show> {
    private final double ratesBegin;
    private final double ratesEnd;

    public PredicateBetweenTwoRates(double from, double to) {
        this.ratesBegin = from;
        this.ratesEnd = to;
    }

    @Override
    public boolean test(Show show) {
        return show.getRating() >= ratesBegin && show.getRating() <= ratesEnd;
    }
}
