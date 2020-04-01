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
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        if (localDateFrom.compareTo(localDateTo) >= 0) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder result = new StringBuilder();
        result.append(String.format("Отчёт за период %s - %s\n", dateFrom, dateTo));
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int salary = 0;
            for (String dataCase : data) {
                String[] dataArr = dataCase.split("[ ]");
                if (name.equals(dataArr[1])
                        && LocalDate.parse(dataArr[0], dateTimeFormatter)
                        .compareTo(localDateFrom) >= 0
                        && LocalDate.parse(dataArr[0], dateTimeFormatter)
                        .compareTo(localDateTo) <= 0) {
                    salary = salary + Integer.parseInt(dataArr[2]) * Integer.parseInt(dataArr[3]);
                }
            }
            result.append(String.format("%s - %d", name, salary));
            if (i != names.length - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
