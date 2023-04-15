package by.tms.repository;

import by.tms.models.Show;

import java.io.IOException;
import java.util.List;

public interface TVShowRepository {

    List<Show> getDataFromFile() throws IOException;
}
