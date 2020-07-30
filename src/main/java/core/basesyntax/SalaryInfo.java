package core.basesyntax;

import core.basesyntax.core.basesyntax.exception.IllegalDateParametersException;
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
     * Сделать так,
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
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) throws IllegalDateParametersException {
        StringBuilder builder = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo);
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);

        if (dateStart.isAfter(dateFinish)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        for (int i = 0; i < names.length; i++) {
            int fee = 0;
            for (int j = 0; j < data.length; j++) {
                if ((LocalDate.parse(data[j].split(" ")[0], FORMATTER).isAfter(dateStart)
                        || LocalDate.parse(data[j].split(" ")[0], FORMATTER).isEqual(dateStart))
                        && (LocalDate.parse(data[j].split(" ")[0], FORMATTER).isBefore(dateFinish)
                        || LocalDate.parse(data[j].split(" ")[0], FORMATTER).isEqual(dateFinish))
                        && names[i].equals(data[j].split(" ")[1])) {
                    fee += Integer.parseInt(data[j].split(" ")[2])
                            * Integer.parseInt(data[j].split(" ")[3]);
                }
            }
            builder.append("\n").append(names[i] + " - ").append(fee);
        }
        return builder.toString();
    }
}
