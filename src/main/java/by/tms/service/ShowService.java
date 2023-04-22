package by.tms.service;

import by.tms.models.Show;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface ShowService {
    List<Show> getSortedListByChainOfRules(List<Comparator<Show>> action);
    List<Show> getFiltredListByChainOfRules(List<Predicate<Show>> predicateList);

}
