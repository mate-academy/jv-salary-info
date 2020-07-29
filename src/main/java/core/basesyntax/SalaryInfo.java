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
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToLocal = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salary = new StringBuilder("Отчёт за период ")
                .append(dateFrom).append(" - ").append(dateTo);

        if (dateToLocal.isBefore(dateFromLocal)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        for (int i = 0; i < names.length; i++) {
            int sum = 0;
            for (int j = 0; j < data.length; j++) {
                String[] nameData = data[j].split(" ");
                LocalDate localDate = LocalDate.parse(nameData[0], FORMATTER);
                if ((localDate.isBefore(dateToLocal) || localDate.isEqual(dateToLocal))
                        && (localDate.isAfter(dateFromLocal) || localDate.isEqual(dateFromLocal))) {
                    if (names[i].equals(nameData[1])) {
                        sum += (Integer.parseInt(nameData[2])
                                * Integer.parseInt(nameData[3]));
                    }
                }
            }
            salary.append("\n").append(names[i]).append(" - ").append(sum);
        }
        return salary.toString();
    }
}
