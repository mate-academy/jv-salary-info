package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и
     * 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив
     * содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и
     * ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество
     * отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который
     * передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за
     * этот период
     * Создать пакет exception и в нём класс-ошибку
     * IllegalDateParametersException. Сделать так,
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

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo)
            throws IllegalDateParametersException {

        LocalDate startOfPeriod = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endOfPeriod = LocalDate.parse(dateTo, FORMATTER);

        if (startOfPeriod.isAfter(endOfPeriod)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder report = new StringBuilder("Отчёт за период ");
        report.append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] dayInfo = line.split(" ");
                LocalDate date = LocalDate.parse(dayInfo[0], FORMATTER);
                if (dayInfo[1].equals(name)
                        && (date.isBefore(endOfPeriod) || date.isEqual(endOfPeriod))
                        && (date.isAfter(startOfPeriod) || date.isEqual(endOfPeriod))) {
                    salary += Integer.parseInt(dayInfo[2]) * Integer.parseInt(dayInfo[3]);
                }
            }
            report.append("\n").append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
