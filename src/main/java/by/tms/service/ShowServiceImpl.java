package by.tms.service;

import by.tms.models.Show;
import by.tms.repository.TVShowRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ShowServiceImpl implements ShowService {

    TVShowRepository tvShowRepository;
    public ShowServiceImpl(TVShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }


    @Override
    public List<Show> getSortedList() throws IOException {
        List<Show> showList = tvShowRepository.getDataFromFile();
        List<Comparator<Show>> comparators = Show.getComparators();

        showList.sort(comparators.get(0).thenComparing(comparators.get(1)).thenComparing(comparators.get(2)));

        return showList;
    }
}
