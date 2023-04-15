package by.tms.service;

import by.tms.models.Show;

import java.io.IOException;
import java.util.List;

public interface ShowService {
    List<Show> getSortedList() throws IOException;
}
