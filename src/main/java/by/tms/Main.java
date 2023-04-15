package by.tms;

import by.tms.models.Show;
import by.tms.repository.TVShowRepository;
import by.tms.repository.TVShowRepositoryImpl;
import by.tms.service.ShowService;
import by.tms.service.ShowServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    public static final Logger LOG = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        LOG.info("Приложение запущено");

        TVShowRepository tvShowRepository = new TVShowRepositoryImpl();
        ShowService tvShowService = new ShowServiceImpl(tvShowRepository);
        try {
            //        List<Show> list = tvShowRepository.getDataFromFile();
            List<Show> list = tvShowService.getSortedList();
            for (Show itm : list) {
                System.out.println(itm.toString());
            }
        } catch (Exception e) {
            System.out.println("Ошибка при работе с файлом");
        }
    }
}