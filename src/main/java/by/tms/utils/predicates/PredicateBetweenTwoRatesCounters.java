package by.tms.utils.predicates;

import by.tms.models.Show;

import java.util.function.Predicate;

public class PredicateBetweenTwoRatesCounters implements Predicate<Show> {
    private final double ratingBegin;
    private final double ratingEnd;

    public PredicateBetweenTwoRatesCounters(double from, double to) {
        this.ratingBegin = from;
        this.ratingEnd = to;
    }

    @Override
    public boolean test(Show show) {
        return show.getRatesCounter() >= ratingBegin && show.getRatesCounter() <= ratingEnd;
    }
}
