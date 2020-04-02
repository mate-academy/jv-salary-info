package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.text.ParseException;
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
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException, ParseException {

        LocalDate date1 = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate date2 = LocalDate.parse(dateTo, FORMATTER);
        if (date1.isAfter(date2)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder res = new StringBuilder();
        res.append("Отчёт за период " + dateFrom + " - " + dateTo + "\n");
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            res.append(names[i] + " - ");
            for (String one : data) {
                String[] temp = one.split(" ");
                LocalDate dateSalary = LocalDate.parse(temp[0], FORMATTER);
                if (dateSalary.isAfter(date1) && dateSalary.isBefore(date2)
                        || dateSalary.isEqual(date1)
                        || dateSalary.isEqual(date2)) {
                    if (temp[1].equals(names[i])) {
                        salary += (Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]));
                    }
                }
            }
            res.append(salary + "\n");
        }
        String answer = res.substring(0,res.length() - 1);
        return answer;
    }
}
