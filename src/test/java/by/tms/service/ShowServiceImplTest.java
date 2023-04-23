package by.tms.service;

import by.tms.models.Film;
import by.tms.models.Series;
import by.tms.models.Show;
import by.tms.repository.TVShowRepository;
import by.tms.utils.predicates.PredicateBetweenTwoRates;
import by.tms.utils.predicates.PredicateByTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShowServiceImplTest {

    TVShowRepository repository;
    ShowService service;

    @BeforeEach
    void setRepository(){
        repository = mock(TVShowRepository.class);
        service = new ShowServiceImpl(repository);
    }

    @Test
    void shouldReturnUnsortedListIfEmptyComporatorsList(){
        List<Comparator<Show>> compList = new ArrayList<>();
        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);

        assertEquals(shows, returnedList);
    }

    @Test
    void shouldReturnSortedByTitleList(){
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_NAME);

        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);

        assertEquals(shows, returnedList);
    }

    @Test
    void shouldReturnSortedByYearList(){
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_YEAR);

        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);

        assertEquals(shows, returnedList);
    }

    @Test
    void shouldReturnSortedByCountryCodeList(){
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_COUNTRY);

        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);

        assertEquals(shows, returnedList);
    }

    @Test
    void shouldReturnSortedByRatingList(){
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_RATING);

        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);

        assertEquals(shows, returnedList);
    }

    @Test
    void shouldReturnSortedByRatesCounterYearList(){
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_RATES_COUNTER);

        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);

        assertEquals(shows, returnedList);
    }

    @Test
    void shouldReturnSortedFirstByTitleSecondByYearList(){
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_NAME);
        compList.add(Show.COMPARE_BY_YEAR);

        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);

        assertEquals(shows, returnedList);
    }

    @Test
    void shouldReturnSortedFirstByAllComparatorsList(){
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_NAME);
        compList.add(Show.COMPARE_BY_YEAR);
        compList.add(Show.COMPARE_BY_RATES_COUNTER);
        compList.add(Show.COMPARE_BY_RATING);
        compList.add(Show.COMPARE_BY_COUNTRY);

        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);

        assertEquals(shows, returnedList);
    }

    @Test
    void shouldReturnUnfilteredListIfEmptyPredicateList(){
        List<Predicate<Show>> predList = new ArrayList<>();
        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getFiltredListByChainOfRules(predList);

        assertEquals(shows, returnedList);
    }

    @Test
    void shouldReturnFilteredByTitleList(){
        List<Predicate<Show>> predList = new ArrayList<>();
        String inputTitle = "ко";
        predList.add(new PredicateByTitle(inputTitle));

        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getFiltredListByChainOfRules(predList);
        List<Show> waitList =new ArrayList<>();
        waitList.add(shows.get(1));
        waitList.add(shows.get(2));

        assertIterableEquals(returnedList, waitList);
    }

    @Test
    void shouldReturnFilteredByTitleAndRatingBetweenToDoubleNumbersList(){
        List<Predicate<Show>> predList = new ArrayList<>();
        String inputTitle = "ко";
        double from = 8.8;
        double to = 9.2;

        predList.add(new PredicateByTitle(inputTitle));
        predList.add(new PredicateBetweenTwoRates(from, to));

        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.9, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getFiltredListByChainOfRules(predList);
        List<Show> waitList =new ArrayList<>();
        waitList.add(shows.get(2));

        assertIterableEquals(returnedList, waitList);
    }

    @Test
    void shouldReturnEmptyListIfTitleDoesntConsistInputTitle(){
        List<Predicate<Show>> predList = new ArrayList<>();
        String inputTitle = "zzzzzzzzzzzzzzzzzzzzzzzzzzz";
        predList.add(new PredicateByTitle(inputTitle));

        List<Show> shows = new ArrayList<>();
        shows.add(new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62));
        shows.add(new Film("Тайна Коко", 2017, "US",8.7,620689));
        shows.add(new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487));
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getFiltredListByChainOfRules(predList);
        List<Show> waitList = new ArrayList<>();


        assertEquals(waitList, returnedList);
    }
}