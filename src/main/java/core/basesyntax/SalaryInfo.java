package core.basesyntax;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

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
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        if (from.compareTo(to) > 0) {
            throw new core.basesyntax.exception.IllegalDateParametersException("Wrong parameters");
        }

        HashMap<String, Integer> listName = new HashMap<>();
        for (String name : names) {
            listName.put(name, 0);
        }
        StringBuilder result = new StringBuilder();
        result.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo);
        result.append("\n");

        for (String i: data) {
            String[] nameInfo = i.split(" ");
            LocalDate nameData = LocalDate.parse(nameInfo[0], FORMATTER);
            if ((nameData.compareTo(from) >= 0) && (nameData.compareTo(to) <= 0)) {
                int hour = Integer.parseInt(nameInfo[2]);
                int perDay = Integer.parseInt(nameInfo[3]);
                listName.replace(nameInfo[1], listName.get(nameInfo[1]) + (perDay * hour));
            }
        }
        for (String i: names) {
            result.append(i).append(" - ").append(listName.get(i)).append("\n");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}

