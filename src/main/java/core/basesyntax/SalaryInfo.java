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
     * Создать пакет core.basesyntax.exception и в нём класс-ошибку IllegalDateParametersException.
     * Сделать так, чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
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
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        if (LocalDate.parse(dateFrom, DATE_FORMAT).isAfter(LocalDate.parse(dateTo, DATE_FORMAT))) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder report = new StringBuilder();
        report.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo);
        for (String employee : names) {

            int totalSalaryForThePeriod = 0;
            for (String infoAboutSalary : data) {
                String[] arrayInfoAboutSalary = infoAboutSalary.split(" ");
                if (LocalDate.parse(dateFrom, DATE_FORMAT).minusDays(1)
                        .isBefore(LocalDate.parse(arrayInfoAboutSalary[0], DATE_FORMAT))
                        && (LocalDate.parse(arrayInfoAboutSalary[0], DATE_FORMAT)
                        .isBefore(LocalDate.parse(dateTo, DATE_FORMAT).plusDays(1)))
                        && employee.equals(arrayInfoAboutSalary[1])) {
                    totalSalaryForThePeriod +=
                            Integer.parseInt(arrayInfoAboutSalary[2])
                                    * Integer.parseInt(arrayInfoAboutSalary[3]);
                }
            }
            report.append("\n").append(employee).append(" - ").append(totalSalaryForThePeriod);
        }
        return report.toString();
    }
}
