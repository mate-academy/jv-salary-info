package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
            throws IllegalDateParametersException {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        if (!endDate.isAfter(startDate)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder salaryInfo = new StringBuilder("Отчёт за период "
                + dateFrom + " - " + dateTo + "\n");
        List<Employee> employees = new ArrayList<>();

        for (String name : names) {
            Employee employee = new Employee(name);
            employees.add(employee);
            for (String i : data) {
                String[] dataInformation = i.split(" ");
                LocalDate currentDate = LocalDate.parse(dataInformation[0], FORMATTER);
                if (isCorrectDate(currentDate, startDate, endDate)
                        && employee.getName().equals(dataInformation[1])) {
                    int hours = Integer.parseInt(dataInformation[2]);
                    int rate = Integer.parseInt(dataInformation[3]);
                    employee.calcSalary(hours, rate);
                }
            }
            salaryInfo.append(employee.getName())
                    .append(" - ")
                    .append(employee.getSalary())
                    .append("\n");
        }
        return salaryInfo.delete(salaryInfo.length() - 1, salaryInfo.length()).toString();
    }

    public boolean isCorrectDate(LocalDate currentDate, LocalDate startDate, LocalDate endDate) {
        return ((currentDate.isAfter(startDate) && currentDate.isBefore(endDate))
                || currentDate.equals(startDate) || currentDate.equals(endDate));
    }
}
