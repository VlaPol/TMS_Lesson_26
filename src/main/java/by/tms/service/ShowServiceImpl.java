package by.tms.service;

import by.tms.models.Show;
import by.tms.repository.TVShowRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class ShowServiceImpl implements ShowService {

    TVShowRepository tvShowRepository;

    public ShowServiceImpl(TVShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }


    @Override
    public List<Show> getSortedList(String action) {

        Comparator<Show> tmpComparator = switch (action) {
            case "byCountry" -> Show.COMPARE_BY_COUNTRY;
            case "byYear" -> Show.COMPARE_BY_YEAR;
            case "byTitle" -> Show.COMPARE_BY_NAME;
            case "byRating" -> Show.COMPARE_BY_RATING;
            case "byCounters" -> Show.COMPARE_BY_RATES_COUNTER;
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };
        return getSortedShows(tmpComparator);

    }

    @Override
    public List<Show> getSortedListByChainOfRules(List<Comparator<Show>> action) {

        List<Show> sourceList = getShowList();
        if (action.isEmpty()) {
            return sourceList;
        }

        Iterator<Comparator<Show>> iterator = action.iterator();
        Comparator<Show> comparatorChain = iterator.next();

        while (iterator.hasNext()) {
            Comparator<Show> next = iterator.next();
            comparatorChain = comparatorChain.thenComparing(next);
        }

        sourceList.sort(comparatorChain);

        return sourceList;

    }

    @Override
    public List<Show> getFiltredListByChainOfRules(List<Predicate<Show>> action) {

        List<Show> sourceList = getShowList();

        if (action.isEmpty()) {
            return sourceList;
        }

        Iterator<Predicate<Show>> iterator = action.iterator();
        Predicate<Show> predicateChain = iterator.next();

        while (iterator.hasNext()) {
            Predicate<Show> next = iterator.next();
            predicateChain = predicateChain.and(next);
        }

        List<Show> returnedList = new ArrayList<>();

        for (Show itm : sourceList) {
            if (predicateChain.test(itm)) {
                returnedList.add(itm);
            }
        }

        return returnedList;

    }

    @Override
    public List<Show> getShowList() {
        return tvShowRepository.getDataFromFile();
    }

    private List<Show> getSortedShows(Comparator<Show> comparator) {

        List<Show> showList = getShowList();
        showList.sort(comparator);
        return showList;
    }


}
