package by.tms.service;

import by.tms.models.Show;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface ShowService {
    List<Show> getSortedList(String action) throws IOException;
    List<Show> getSortedListByChainOfRules(List<Comparator<Show>> action) throws IOException;
    List<Show> getFiltredListByChainOfRules(List<Predicate<Show>> predicateList) throws IOException;
    List<Show> getShowList() throws IOException;
    List<Show> getFiltredList(String action, String query) throws IOException;
}
