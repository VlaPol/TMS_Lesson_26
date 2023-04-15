package by.tms;

import by.tms.models.Show;
import by.tms.repository.TVShowRepository;
import by.tms.repository.TVShowRepositoryImpl;
import by.tms.service.ShowService;
import by.tms.service.ShowServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        LOG.info("Приложение запущено");

        Scanner scanner = new Scanner(System.in);
        TVShowRepository tvShowRepository = new TVShowRepositoryImpl();
        ShowService tvShowService = new ShowServiceImpl(tvShowRepository);

        do {
            System.out.println("*********** MENU *************");
            System.out.println("1. Сортировка");
            System.out.println("2. Фильтрация");
            System.out.println("0. Exit");
            System.out.println("******************************");
            System.out.print("Input task number (0 for exit): ");

            int key = scanner.nextInt();
            List<Show> showList = new ArrayList<>();

            switch (key) {
                case 1 -> {
                    System.out.println("Выберите способ сортировки: ");
                    boolean flag = true;
                    do {

                        System.out.println("Сортировка по: \n");
                        System.out.println("***** 11. Названию");
                        System.out.println("***** 12. Стране производства");
                        System.out.println("***** 13. Году выпуска");
                        System.out.println("***** 14. Рейтингу");
                        System.out.println("***** 15. Количеству проголосовавших");
                        System.out.println("0. Exit");

                        int subKey = Integer.parseInt(scanner.nextLine());
                        switch (subKey) {
                            case 11 -> {
                                showList = tvShowService.getSortedList("byTitle");
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 12 -> {
                                showList = tvShowService.getSortedList("byCountry");
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 13 -> {
                                showList = tvShowService.getSortedList("byYear");
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 14 -> {
                                showList = tvShowService.getSortedList("byRating");
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 15 -> {
                                showList = tvShowService.getSortedList("byCounters");
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 0 -> flag = false;
                            default -> System.out.println("Нет такой сортировки!!!");
                        }
                    }
                    while (flag);
                }
                case 2 -> {
                    System.out.println("Выберите способ сортировки: ");
                    boolean flag = true;
                    do {
                        System.out.println("Фильтр по: \n");
                        System.out.println("***** 21. Названию");
                        System.out.println("***** 22. Стране производства");
                        System.out.println("***** 23. Году выпуска");
                        System.out.println("***** 24. Рейтинг в заданном промежутке");
                        System.out.println("***** 25. Количество проголосовавших в заданном промежутке");
                        System.out.println("0. Exit");

                        int subKey = Integer.parseInt(scanner.nextLine());
                        switch (subKey) {
                            case 21 -> {
                                System.out.println("Введите интересующее название");
                                String title = scanner.nextLine();
                                showList = tvShowService.getFiltredList("byTitle", title);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 22 -> {
                                System.out.println("Введите интересующую страну (код - две буквы)");
                                String code = scanner.nextLine();
                                showList = tvShowService.getFiltredList("byCountry", code);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 23 -> {
                                System.out.println("Введите интересующий год");
                                String year = scanner.nextLine();
                                showList = tvShowService.getFiltredList("byYear", year);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 24 -> {
                                System.out.println("Введите через запятую интересующий промежуток оценок");
                                String rates = scanner.nextLine();
                                showList = tvShowService.getFiltredList("betweenTwoRates", rates);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 25 -> {
                                System.out.println("Введите через запятую интересующее количество проголосовавших");
                                String counters = scanner.nextLine();
                                showList = tvShowService.getFiltredList("betweenTwoRatesCounters", counters);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 0 -> flag = false;
                            default -> System.out.println("Нет такого фильтра!!!");
                        }
                    }
                    while (flag);
                }
                case 0 -> {
                    System.out.println("До встречи!!!");
                    return;
                }
                default -> System.out.println("Нет такого ключа!!!");
            }
        } while (true);
    }


//        try {
//            //        List<Show> list = tvShowRepository.getDataFromFile();
//            List<Show> list = tvShowService.getSortedList();
//            for (Show itm : list) {
//                System.out.println(itm.toString());
//            }
//        } catch (Exception e) {
//            System.out.println("Ошибка при работе с файлом");
//        }
//    }
}