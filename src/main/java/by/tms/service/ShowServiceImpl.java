package by.tms.service;

import by.tms.models.Show;
import by.tms.repository.TVShowRepository;
import by.tms.utils.predicates.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ShowServiceImpl implements ShowService {

    TVShowRepository tvShowRepository;

    public ShowServiceImpl(TVShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }


    @Override
    public List<Show> getSortedList(String action) throws IOException {

        Comparator<Show> tmpComparator = null;

        switch (action) {
            case "byCountry" -> tmpComparator = Show.COMPARE_BY_COUNTRY;
            case "byYear" -> tmpComparator = Show.COMPARE_BY_YEAR;
            case "byTitle" -> tmpComparator = Show.COMPARE_BY_NAME;
            case "byRating" -> tmpComparator = Show.COMPARE_BY_RATING;
            case "byCounters" -> tmpComparator = Show.COMPARE_BY_RATES_COUNTER;
        }
        return getSortedShows(tmpComparator);

    }

    @Override
    public List<Show> getSortedListByChainOfRules(List<Comparator<Show>> action) throws IOException {

        List<Show> sourceList = getShowList();
        Comparator<Show> comparatorChain = action.get(0);

        int numOfComparators = action.size();

        if (numOfComparators == 2) {
            comparatorChain = comparatorChain.thenComparing(action.get(1));
        }
        if (numOfComparators == 3) {
            comparatorChain = comparatorChain.thenComparing(action.get(2));
        }
        if (numOfComparators == 4) {
            comparatorChain = comparatorChain.thenComparing(action.get(3));
        }

        sourceList.sort(comparatorChain);

        return sourceList;

    }

    @Override
    public List<Show> getFiltredListByChainOfRules(List<Predicate<Show>> action) throws IOException {

        List<Show> sourceList = getShowList();
        int numOfPredicates = action.size();


        Predicate<Show> predicateChain = action.get(0);

        if (numOfPredicates >= 2) {
            predicateChain = predicateChain.and(action.get(1));
        }
        if (numOfPredicates >= 3) {
            predicateChain = predicateChain.and(action.get(2));
        }
        if (numOfPredicates >= 4) {
            predicateChain = predicateChain.and(action.get(3));
        }

        List<Show> returnedList = new ArrayList<>();

        for (Show itm: sourceList){
            if(predicateChain.test(itm)){
                returnedList.add(itm);
            }
        }

        return returnedList;

    }

    @Override
    public List<Show> getShowList() throws IOException {
        return tvShowRepository.getDataFromFile();
    }

    //        через stream API
    //        return showList.stream().filter(show -> show.getCountryCode()
    //                .equals(query)).collect(Collectors.toList());
    @Override
    public List<Show> getFiltredList(String action, String query) throws IOException {

        Predicate<Show> tempPredicate = null;

        switch (action) {
            case "byCountry" -> tempPredicate = new PredicateByCountry(query.toUpperCase());
            case "byYear" -> tempPredicate = new PredicateByYear(Integer.parseInt(query));
            case "byTitle" -> tempPredicate = new PredicateByTitle(query);
            case "betweenTwoRates" -> tempPredicate = new PredicateBetweenTwoRates(query);
            case "betweenTwoRatesCounters" -> tempPredicate = new PredicateBetweenTwoRatesCounters(query);
        }
        return getFiltredShows(tempPredicate);
    }

    private List<Show> getSortedShows(Comparator<Show> comparator) throws IOException {

        List<Show> showList = getShowList();
        showList.sort(comparator);
        return showList;
    }

    private List<Show> getFiltredShows(Predicate<Show> predicate) throws IOException {

        List<Show> showList = getShowList();

        List<Show> returnedList = new ArrayList<>();

        for (Show itm : showList) {
            if (predicate.test(itm)) {
                returnedList.add(itm);
            }
        }

        return returnedList;
    }

}
