package core.basesyntax;

import static java.time.LocalDate.parse;

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
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        LocalDate startingDate = parse(dateFrom, FORMATTER);
        LocalDate endingDate = parse(dateTo, FORMATTER);
        if (!startingDate.isBefore(endingDate)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder report =
                new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + "\n");
        for (String name: names) {
            int sum = 0;
            for (String datas: data) {
                String[] split = datas.split(" ");
                if (name.equals(split[1])
                        && ((LocalDate.parse(split[0], FORMATTER).isAfter(startingDate)
                        && LocalDate.parse(split[0], FORMATTER).isBefore(endingDate))
                        || (LocalDate.parse(split[0], FORMATTER).equals(startingDate)
                        | LocalDate.parse(split[0], FORMATTER).equals(endingDate)))) {
                    sum += Integer.parseInt(split[2]) * Integer.parseInt(split[3]);
                }
            }
            report.append(name).append(" - ").append(sum).append("\n");
        }

        return report.toString().trim();
    }
}
