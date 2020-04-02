package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    private boolean checkDatesSequence(String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        return toDate.compareTo(fromDate) >= 0;
    }

    private boolean checkDateBelonging(String givenDate, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        LocalDate currentDate = LocalDate.parse(givenDate, FORMATTER);

        return (currentDate.compareTo(fromDate) >= 0) && (currentDate.compareTo(toDate) <= 0);
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {

        if (!checkDatesSequence(dateFrom, dateTo)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            Pattern name = Pattern.compile(names[i]);
            for (String datum : data) {
                Matcher matcher = name.matcher(datum);
                if (matcher.find()) {
                    if (checkDateBelonging(datum.substring(0, 10), dateFrom, dateTo)) {
                        String[] stringArray = datum.split(" ");
                        salary[i] += Integer.parseInt(stringArray[2])
                                * Integer.parseInt(stringArray[3]);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append("\n").append(names[i]).append(" - ").append(salary[i]);
        }

        return result.toString();
    }
}
