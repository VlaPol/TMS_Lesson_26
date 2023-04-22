package by.tms.service;

import by.tms.models.Film;
import by.tms.models.Series;
import by.tms.models.Show;
import by.tms.repository.TVShowRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowServiceImplTest {

    TVShowRepository repository;
    ShowService service;

    @BeforeEach
    void setRepository(){
        repository = Mockito.mock(TVShowRepository.class);
        service = new ShowServiceImpl(repository);
    }

    @Test
    void shouldReturnUnsortedListIfEmptyComporatorsList(){
        List<Comparator<Show>> compList = new ArrayList<>();
        List<Show> shows = List.of(
                new Series("Во все тяжкие", 2008, "US", 8.9, 533325, "2013",5,62),
                new Film("Тайна Коко", 2017, "US",8.7,620689),
                new Film("Властелин колец: Возвращение короля", 2003,"NZ", 8.7, 551487)
        );
        Mockito.when(repository.getDataFromFile())
                .thenReturn(shows);

        List<Show> returnedList = service.getSortedListByChainOfRules(compList);

        assertEquals(shows, returnedList);
    }

    @Test
    void getSortedListByChainOfRules() {
    }

    @Test
    void getFiltredListByChainOfRules() {
    }
}