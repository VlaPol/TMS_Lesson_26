package by.tms;

import by.tms.models.Show;
import by.tms.repository.TVShowRepository;
import by.tms.repository.TVShowRepositoryImpl;
import by.tms.service.ShowService;
import by.tms.service.ShowServiceImpl;
import by.tms.utils.predicates.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final List<String> ACTIONS_FOR_SORTING = List.of("byTitle", "byCountry", "byYear", "byRating", "byCounters");
    private final static List<Comparator<Show>> COMPORATORS_LIST = Show.getComparators();

    public static void main(String[] args) {

        LOG.info("Приложение запущено");

        Scanner scanner = new Scanner(System.in);
        TVShowRepository tvShowRepository = new TVShowRepositoryImpl();
        ShowService tvShowService = new ShowServiceImpl(tvShowRepository);

        do {
            System.out.println("*********** MENU *************");
            System.out.println("1. Сортировка");
            System.out.println("2. Фильтрация");
            System.out.println("0. Выход");
            System.out.println("******************************");
            System.out.print("Выберите пункт меню (0 для выхода): ");

            int key = scanner.nextInt();
            List<Show> showList = new ArrayList<>();

            switch (key) {
                case 1 -> {
                    System.out.println("Выберите способ сортировки: ");
                    String inputString = scanner.nextLine();
                    boolean flag = true;
                    do {

                        System.out.println("Сортировка по:");
                        System.out.println("***** 10. Без сортировок");
                        System.out.println("***** 11. Названию");
                        System.out.println("***** 12. Стране производства");
                        System.out.println("***** 13. Году выпуска");
                        System.out.println("***** 14. Рейтингу");
                        System.out.println("***** 15. Количеству проголосовавших");
                        System.out.println("***** 16. Комбинация предыдущих сортировок");
                        System.out.println("0. Exit");

                        int subKey = Integer.parseInt(scanner.nextLine());
                        switch (subKey) {
                            case 10 -> {
                                showList = tvShowService.getShowList();
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 11 -> {
                                showList = tvShowService.getSortedList(ACTIONS_FOR_SORTING.get(0));
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 12 -> {
                                showList = tvShowService.getSortedList(ACTIONS_FOR_SORTING.get(1));
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 13 -> {
                                showList = tvShowService.getSortedList(ACTIONS_FOR_SORTING.get(2));
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 14 -> {
                                showList = tvShowService.getSortedList(ACTIONS_FOR_SORTING.get(3));
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 15 -> {
                                showList = tvShowService.getSortedList(ACTIONS_FOR_SORTING.get(4));
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 16 -> {
                                System.out.println("Введите пункты сортировок через запятую или 0 при отсутствии выбора");
                                String filterChain = scanner.nextLine();
                                List<Comparator<Show>> userAction = new ArrayList<>();
                                if (!(filterChain.equals("0"))) {
                                    String[] strings = filterChain.split(", ");
                                    for (String string : strings) {
                                        int index = Integer.parseInt(string.replaceFirst("1", ""));
                                        if (index == 0) {
                                            throw new IllegalArgumentException("Выбран пункт 0, который не предусмотрен для цепочки фильтров");
                                        }
                                        userAction.add(COMPORATORS_LIST.get(index - 1));
                                    }
                                }
                                showList = tvShowService.getSortedListByChainOfRules(userAction);
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

                    List<Predicate<Show>> predicateList;
                    int filterNumber;
                    Predicate<Show> tmpPredicate = null;

                    System.out.println("Выберите способ сортировки: ");
                    String inputString = scanner.nextLine();
                    boolean flag = true;
                    do {
                        System.out.println("Фильтр по: \n");
                        System.out.println("***** 20. Без фильтров");
                        System.out.println("***** 21. Названию");
                        System.out.println("***** 22. Стране производства");
                        System.out.println("***** 23. Году выпуска");
                        System.out.println("***** 24. Рейтинг в заданном промежутке");
                        System.out.println("***** 25. Количество проголосовавших в заданном промежутке");
                        System.out.println("***** 26. Комбинация фильтров (условия фильтрации");
                        System.out.println("0. Exit");

                        int subKey = Integer.parseInt(scanner.nextLine());
                        switch (subKey) {
                            case 20 -> {
                                predicateList = new ArrayList<>();
                                showList = tvShowService.getFiltredListByChainOfRules(predicateList);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 21 -> {
                                System.out.println("Введите интересующее название");
                                String title = scanner.nextLine();
                                predicateList = new ArrayList<>();
                                predicateList.add(new PredicateByTitle(title));
                                showList = tvShowService.getFiltredListByChainOfRules(predicateList);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 22 -> {
                                System.out.println("Введите интересующую страну (код - две буквы)");
                                String code = scanner.nextLine();
                                predicateList = new ArrayList<>();
                                predicateList.add(new PredicateByCountry(code));
                                showList = tvShowService.getFiltredListByChainOfRules(predicateList);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 23 -> {
                                System.out.println("Введите интересующий год");
                                int year = Integer.parseInt(scanner.nextLine());
                                predicateList = new ArrayList<>();
                                predicateList.add(new PredicateByYear(year));
                                showList = tvShowService.getFiltredListByChainOfRules(predicateList);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 24 -> {
                                System.out.println("Введите начальную границу промежутка оценок");
                                double from = Double.parseDouble(scanner.nextLine());
                                System.out.println("Введите конечную границу промежутка оценок");
                                double to = Double.parseDouble(scanner.nextLine());
                                predicateList = new ArrayList<>();
                                predicateList.add(new PredicateBetweenTwoRates(from, to));
                                showList = tvShowService.getFiltredListByChainOfRules(predicateList);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 25 -> {
                                System.out.println("Введите начальную границу проголосовавших");
                                double from = Double.parseDouble(scanner.nextLine());
                                System.out.println("Введите конечную границу проголосовавших");
                                double to = Double.parseDouble(scanner.nextLine());
                                predicateList = new ArrayList<>();
                                predicateList.add(new PredicateBetweenTwoRatesCounters(from, to));
                                showList = tvShowService.getFiltredListByChainOfRules(predicateList);
                                for (Show itm : showList) {
                                    System.out.println(itm.toString());
                                }
                            }
                            case 26 -> {
                                predicateList = new ArrayList<>();
                                do {
                                    System.out.println("Выберите пункт фильтрации или 0 при отсутствии выбора:");
                                    filterNumber = Integer.parseInt(scanner.nextLine());
                                    if (filterNumber == 0) {
                                        break;
                                    }
                                    String userFilter = "";
                                    double from = 0;
                                    double to = 0;
                                    if (filterNumber != 24 && filterNumber != 25) {
                                        System.out.println("Введите условие фильтра");
                                        userFilter = scanner.nextLine();
                                    }
                                    if (filterNumber == 24) {
                                        System.out.println("Введите начальную границу проголосовавших");
                                        from = Double.parseDouble(scanner.nextLine());
                                        System.out.println("Введите конечную границу проголосовавших");
                                        to = Double.parseDouble(scanner.nextLine());
                                    }
                                    if (filterNumber == 25) {
                                        System.out.println("Введите начальную границу проголосовавших");
                                        from = Double.parseDouble(scanner.nextLine());
                                        System.out.println("Введите конечную границу проголосовавших");
                                        to = Double.parseDouble(scanner.nextLine());
                                    }
                                    switch (filterNumber) {
                                        case 21 -> tmpPredicate = new PredicateByTitle(userFilter);
                                        case 22 -> tmpPredicate = new PredicateByCountry(userFilter);
                                        case 23 -> tmpPredicate = new PredicateByYear(Integer.parseInt(userFilter));
                                        case 24 -> tmpPredicate = new PredicateBetweenTwoRates(from, to);
                                        case 25 -> tmpPredicate = new PredicateBetweenTwoRatesCounters(from, to);
                                        default -> System.out.println("Нет такого фильтра");
                                    }
                                    predicateList.add(tmpPredicate);
                                    System.out.println("Желаете продолжить? (1 - продолжить, -1 - для выхода)");
                                    filterNumber = Integer.parseInt(scanner.nextLine());

                                } while (filterNumber != -1);

                                showList = tvShowService.getFiltredListByChainOfRules(predicateList);
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

}