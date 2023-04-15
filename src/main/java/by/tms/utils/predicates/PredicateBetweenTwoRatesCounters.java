package by.tms.utils.predicates;

import by.tms.models.Show;

import java.util.function.Predicate;

public class PredicateBetweenTwoRatesCounters implements Predicate<Show> {
    private final double ratingBegin;
    private final double ratingEnd;

    public PredicateBetweenTwoRatesCounters(String query) {
        String[] tmpArray = query.split(", ");
        this.ratingBegin = Integer.parseInt(tmpArray[0]);
        this.ratingEnd = Integer.parseInt(tmpArray[1]);
    }

    @Override
    public boolean test(Show show) {
        return show.getRatesCounter() >= ratingBegin && show.getRatesCounter() <= ratingEnd;
    }
}
