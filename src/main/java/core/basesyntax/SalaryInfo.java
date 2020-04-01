package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        StringBuilder str = new StringBuilder("");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        int workinHours;
        int rate;
        String name = "";
        LocalDate checkDate = null;
        int salary = 0;
        String result = "Отчёт за период " + dateFrom + " - " + dateTo + "\n";
        if (fromDate.isAfter(toDate)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        fromDate = fromDate.minusDays(1);
        toDate = toDate.plusDays(1);
        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < data.length; i++) {
                str = new StringBuilder(data[i]);
                checkDate = LocalDate.parse(str.substring(0, str.indexOf(" ")), formatter);
                str = str.delete(0, str.indexOf(" ") + 1);
                name = str.substring(0, str.indexOf(" "));
                str = str.delete(0, str.indexOf(" ") + 1);
                workinHours = Integer.valueOf(str.substring(0, str.indexOf(" ")));
                str = str.delete(0, str.indexOf(" ") + 1);
                rate = Integer.valueOf(str.toString());
                if (names[j].equals(name)
                        && checkDate.isBefore(toDate)
                        && checkDate.isAfter(fromDate)) {
                    salary += workinHours * rate;
                }
            }
            result += names[j] + " - " + salary + "\n";
            salary = 0;
        }
        return result.trim();
    }
}
