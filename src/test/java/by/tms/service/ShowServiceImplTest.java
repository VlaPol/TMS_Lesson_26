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

    public static final Show FIRST_SHOW = new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013", 5, 62);
    public static final Show SECOND_SHOW = new Film("Тайна Коко", 2017, "US", 8.8, 620689);
    public static final Show THIRD_SHOW = new Film("Властелин колец: Возвращение короля", 2003, "NZ", 8.7, 551487);

    @BeforeEach
    void setRepository() {
        repository = mock(TVShowRepository.class);
        service = new ShowServiceImpl(repository);
    }

    @Test
    void shouldReturnUnsortedListIfEmptyComporatorsList() {

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> expectedList = new ArrayList<>();
        expectedList.add(FIRST_SHOW);
        expectedList.add(SECOND_SHOW);
        expectedList.add(THIRD_SHOW);

        assertEquals(shows, expectedList);
    }

    @Test
    void shouldReturnSortedByTitleList() {
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_NAME);

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);
        List<Show> expectedList = new ArrayList<>();
        expectedList.add(THIRD_SHOW);
        expectedList.add(FIRST_SHOW);
        expectedList.add(SECOND_SHOW);

        assertEquals(expectedList, returnedList);
    }

    @Test
    void shouldReturnSortedByYearList() {
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_YEAR);

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);
        List<Show> expectedList = new ArrayList<>();
        expectedList.add(SECOND_SHOW);
        expectedList.add(FIRST_SHOW);
        expectedList.add(THIRD_SHOW);


        assertEquals(expectedList, returnedList);
    }

    @Test
    void shouldReturnSortedByCountryCodeList() {
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_COUNTRY);

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);
        List<Show> expectedList = new ArrayList<>();
        expectedList.add(THIRD_SHOW);
        expectedList.add(FIRST_SHOW);
        expectedList.add(SECOND_SHOW);

        assertEquals(expectedList, returnedList);
    }

    @Test
    void shouldReturnSortedByRatingList() {
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_RATING);

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);
        List<Show> expectedList = new ArrayList<>();
        expectedList.add(FIRST_SHOW);
        expectedList.add(SECOND_SHOW);
        expectedList.add(THIRD_SHOW);

        assertEquals(expectedList, returnedList);
    }

    @Test
    void shouldReturnSortedByRatesCounterYearList() {
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_RATES_COUNTER);

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);
        List<Show> expectedList = new ArrayList<>();
        expectedList.add(SECOND_SHOW);
        expectedList.add(THIRD_SHOW);
        expectedList.add(FIRST_SHOW);

        assertEquals(expectedList, returnedList);
    }

    @Test
    void shouldReturnSortedFirstByTitleSecondByYearList() {
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_NAME);
        compList.add(Show.COMPARE_BY_YEAR);

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);
        List<Show> expectedList = new ArrayList<>();
        expectedList.add(THIRD_SHOW);
        expectedList.add(FIRST_SHOW);
        expectedList.add(SECOND_SHOW);

        assertEquals(expectedList, returnedList);
    }

    @Test
    void shouldReturnSortedFirstByAllComparatorsList() {
        List<Comparator<Show>> compList = new ArrayList<>();
        compList.add(Show.COMPARE_BY_NAME);
        compList.add(Show.COMPARE_BY_YEAR);
        compList.add(Show.COMPARE_BY_RATES_COUNTER);
        compList.add(Show.COMPARE_BY_RATING);
        compList.add(Show.COMPARE_BY_COUNTRY);

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);
        List<Show> expectedList = new ArrayList<>();
        expectedList.add(THIRD_SHOW);
        expectedList.add(FIRST_SHOW);
        expectedList.add(SECOND_SHOW);

        assertEquals(expectedList, returnedList);
    }

    @Test
    void shouldReturnUnfilteredListIfEmptyPredicateList() {
        List<Predicate<Show>> predList = new ArrayList<>();
        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getFiltredListByChainOfRules(predList);
        List<Show> expectedList = new ArrayList<>();
        expectedList.add(FIRST_SHOW);
        expectedList.add(SECOND_SHOW);
        expectedList.add(THIRD_SHOW);

        assertEquals(expectedList, returnedList);
    }

    @Test
    void shouldReturnFilteredByTitleList() {
        List<Predicate<Show>> predList = new ArrayList<>();
        String inputTitle = "ко";
        predList.add(new PredicateByTitle(inputTitle));

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getFiltredListByChainOfRules(predList);
        List<Show> expectedList = new ArrayList<>();
        expectedList.add(SECOND_SHOW);
        expectedList.add(THIRD_SHOW);

        assertIterableEquals(returnedList, expectedList);
    }

    @Test
    void shouldReturnFilteredByTitleAndRatingBetweenToDoubleNumbersList() {
        List<Predicate<Show>> predList = new ArrayList<>();
        String inputTitle = "ко";
        double from = 8.8;
        double to = 9.2;

        predList.add(new PredicateByTitle(inputTitle));
        predList.add(new PredicateBetweenTwoRates(from, to));

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getFiltredListByChainOfRules(predList);
        List<Show> expectedList = new ArrayList<>();
        expectedList.add(SECOND_SHOW);

        assertEquals(returnedList, expectedList);
    }

    @Test
    void shouldReturnEmptyListIfTitleDoesntConsistInputTitle() {
        List<Predicate<Show>> predList = new ArrayList<>();
        String inputTitle = "zzzzzzzzzzzzzzzzzzzzzzzzzzz";
        predList.add(new PredicateByTitle(inputTitle));

        List<Show> shows = new ArrayList<>();
        shows.add(FIRST_SHOW);
        shows.add(SECOND_SHOW);
        shows.add(THIRD_SHOW);
        when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getFiltredListByChainOfRules(predList);
        List<Show> expectedList = new ArrayList<>();


        assertEquals(expectedList, returnedList);
    }
}