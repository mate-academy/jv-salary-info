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
            throws IllegalDateParametersException {

        StringBuilder builder = new StringBuilder();
        builder.append("Отчёт за период ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append("\n");

        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);

        if (parsedDateFrom.isAfter(parsedDateTo)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        for (String name : names) {
            builder.append(name).append(" - ");
            int result = 0;
            for (String dataString : data) {
                String[] parsedData = dataString.split(" ");

                LocalDate currentDate = LocalDate.parse(parsedData[0], FORMATTER);
                if ((parsedData[1].equals(name)) && (currentDate.isAfter(parsedDateFrom)
                        || currentDate.isEqual(parsedDateFrom))
                        && (currentDate.isBefore(parsedDateTo)
                        || currentDate.isEqual(parsedDateTo))) {
                    result += Integer.parseInt(parsedData[2]) * Integer.parseInt(parsedData[3]);
                }

            }
            builder.append(result).append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
