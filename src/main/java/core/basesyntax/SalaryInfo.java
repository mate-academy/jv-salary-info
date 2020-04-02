package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        if (fromDate.isAfter(toDate)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder sb = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + '\n');
        ArrayList<String[]> dataToArray = new ArrayList<>();
        for (String s : data) {
            if (isDateInRange(s.substring(0,10),fromDate,toDate)) {
                dataToArray.add(s.split(" "));
            }
        }
        int[] money = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String[] dataArray : dataToArray) {
                if (names[i].equals(dataArray[1])) {
                    money[i] += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]).append(" - ").append(money[i]).append('\n');
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public boolean isDateInRange(String givenDate, LocalDate dateFrom, LocalDate dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(givenDate, formatter);
        return ((date.isAfter(dateFrom) || date.isEqual(dateFrom))
                && (date.isBefore(dateTo) || date.isEqual(dateTo)));
    }
}
