package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать пакет exception и в нём класс-ошибку IllegalDateParametersException. Сделать так,
     * чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final String PREFIX_START = "Report for period ";
    private static final String PREFIX_MIDDLE = " - ";
    private static final String SPLIT_REGEX = " ";
    private static final String NEXT_LINE = "\n";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date from;
        Date to;

        try {
            from = DATE_FORMAT.parse(dateFrom);
            to = DATE_FORMAT.parse(dateTo);
        } catch (ParseException exception) {
            throw new RuntimeException("Wrong parameters", exception);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(PREFIX_START).append(dateFrom).append(PREFIX_MIDDLE).append(dateTo);

        for (String name : names) {
            builder.append(NEXT_LINE).append(name).append(PREFIX_MIDDLE);
            int salary = 0;

            for (String datum : data) {
                String[] parsedData = datum.split(SPLIT_REGEX);
                try {
                    if (parsedData[NAME_INDEX].equals(name)
                            && (DATE_FORMAT.parse(parsedData[DATE_INDEX]).after(from)
                            || DATE_FORMAT.parse(parsedData[DATE_INDEX]).equals(from))
                            && (DATE_FORMAT.parse(parsedData[DATE_INDEX]).before(to)
                            || DATE_FORMAT.parse(parsedData[DATE_INDEX]).equals(to))) {
                        salary += Integer.parseInt(parsedData[HOURS_INDEX])
                                * Integer.parseInt(parsedData[SALARY_INDEX]);
                    }
                } catch (ParseException exception) {
                    throw new RuntimeException("Wrong parameters", exception);
                }
            }

            builder.append(salary);
        }

        return builder.toString();
    }
}
