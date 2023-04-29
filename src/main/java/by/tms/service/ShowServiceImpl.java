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
    public List<Show> getSortedListByChainOfRules(List<Comparator<Show>> action) {

        List<Show> sourceList = new ArrayList<>(getShowList());
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

        List<Show> sourceList = new ArrayList<>(getShowList());

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

    private List<Show> getShowList() {
        return tvShowRepository.getDataFromFile();
    }


}
