package by.tms.utils.predicates;

import by.tms.models.Show;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PredicateByTitle implements Predicate<Show> {
    private final Pattern pattern;

    public PredicateByTitle(String title) {
        this.pattern = Pattern.compile(
                "\\b" + title + "\\w*\\b",
                Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    }

    @Override
    public boolean test(Show show) {
        return pattern.matcher(show.getShowTitle()).find();
    }
}
