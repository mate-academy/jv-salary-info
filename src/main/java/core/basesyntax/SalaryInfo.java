package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * <p>
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * <p>
     * names:
     * Сергей
     * Андрей
     * София
     * <p>
     * data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * <p>
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        if (localDateTo.isBefore(LocalDate.parse(dateFrom, formatter))) {
            return null;
        }
        String statement = "Отчёт за период " + dateFrom + " - " + dateTo + "\n";
        for (String name : names) {
            int salary = 0;
            LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
            while (localDateFrom.isBefore(localDateTo.plusDays(1))) {
                salary += getSalary(name, data, localDateFrom.format(formatter));
                localDateFrom = localDateFrom.plusDays(1);
            }
            statement += name + " - " + salary + "\n";
        }
        return statement;
    }

    private int getSalary(String name, String[] data, String localDateFrom) {
        int salaryResult = 0;
        for (String entry : data) {
            String[] parts = entry.split(" ");
            if (parts[0].equals(localDateFrom) && parts[1].equals(name)) {
                salaryResult += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
            }
        }
        return salaryResult;
    }
}
