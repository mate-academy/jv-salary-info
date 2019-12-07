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
     * Создать класс-ошибку IllegalDateParametersException и сделать так, чтобы
     * метод getSalaryInfo выбрасывал IllegalDateParametersException,
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

        Employe[] report = new Employe[data.length];

        if (LocalDate.parse(dateFrom, FORMATTER).isAfter(LocalDate.parse(dateTo, FORMATTER))) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder builder
                = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + "\n");

        String[] buffer;
        for (int i = 0; i < data.length; ++i) {
            buffer = data[i].split(" ");
            report[i] = new Employe(LocalDate.parse(buffer[0], FORMATTER), buffer[1],
                    Integer.parseInt(buffer[2]), Integer.parseInt(buffer[3]));
        }
        for (String name : names) {
            int finishSalary = 0;

            for (int i = 0; i < report.length; ++i) {
                if (name.equals(report[i].getName())
                        && ((report[i].getDate().isEqual(LocalDate.parse(dateFrom, FORMATTER)))
                        || (report[i].getDate().isEqual(LocalDate.parse(dateTo, FORMATTER)))
                        || (report[i].getDate().isAfter(LocalDate.parse(dateFrom, FORMATTER))
                        && report[i].getDate().isBefore(LocalDate.parse(dateTo, FORMATTER))))) {
                    finishSalary += report[i].getHours() * report[i].getSalary();
                }
            }
            builder.append(name).append(" - ").append(finishSalary).append("\n");
        }
        return builder.toString();
    }
}
