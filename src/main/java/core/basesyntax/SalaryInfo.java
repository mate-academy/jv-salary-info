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

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        int[][] numbers = new int[data.length][];
        LocalDate[] docDate = new LocalDate[data.length];
        LocalDate dateF = LocalDate.parse(dateFrom, formatter);
        LocalDate dateT = LocalDate.parse(dateTo, formatter);

        if (dateF.isAfter(dateT)) {
            throw new IllegalDateParametersException();
        }

        String[][] mydata = new String[data.length][];
        StringBuilder result = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo);
        for (int i = 0; i < data.length; i++) {
            mydata[i] = data[i].split(" ");
            docDate[i] = LocalDate.parse(mydata[i][0], formatter);
            numbers[i] = new int[]{Integer.parseInt(mydata[i][2]), Integer.parseInt(mydata[i][3])};
        }
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {

                if (name.equals(mydata[i][1]) && (docDate[i].isAfter(dateF)
                        || docDate[i].isEqual(dateF))
                        && (docDate[i].isBefore(dateT) || docDate[i].isEqual(dateT))) {
                    salary += numbers[i][0] * numbers[i][1];
                }
            }
            result.append("\n" + name + " - " + salary);
        }
        return result.append("\n").toString();
    }
}

