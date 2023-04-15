package by.tms.utils.predicates;

import by.tms.models.Show;

import java.util.function.Predicate;

public class PredicateByYear implements Predicate<Show> {

    private final int releaseYear;

    public PredicateByYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean test(Show show) {
        return show.getReleaseYear() == releaseYear;
    }
}
