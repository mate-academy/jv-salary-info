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
     * ставка за 1 час. Метод должен вернуть отчё т за период, который передали в метод
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
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        final DateTimeFormatter FR = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, FR);
        LocalDate to = LocalDate.parse(dateTo, FR);
        if (from.isAfter(to)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        int[] salary = new int[names.length];
        for (String tempData : data) {
            String[] temp = tempData.split("\\s");
            LocalDate temp0 = (LocalDate.parse(temp[0], FR));
            for (int i = 0; i < names.length; i++) {
                if ((temp0.isAfter(from) || temp0.isEqual(from))
                        & (temp0.isBefore(to) || temp0.isEqual(to))
                        & names[i].equals(temp[1])) {
                    salary[i] += Integer.parseInt(temp[2])
                            * Integer.parseInt(temp[3]);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Отчёт за период ")
                .append(from.format(FR)).append(" - ")
                .append(to.format(FR)).append("\n")
                .append(names[0] + " - " + salary[0] + "\n")
                .append(names[1] + " - " + salary[1] + "\n")
                .append(names[2] + " - " + salary[2] + "\n");
        return stringBuilder.toString();
    }
}
