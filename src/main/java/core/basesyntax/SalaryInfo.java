package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        ArrayList<Employee> employees = new ArrayList<>();
        LocalDate dateF = parseDate(dateFrom);
        LocalDate dateT = parseDate(dateTo);

        if (dateF.isAfter(dateT)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder report = new StringBuilder("Отчёт за период ");
        report.append(dateFrom).append(" - ").append(dateTo).append("\n");
        for (String name : names) {
            employees.add(new Employee(name));
            report.append(getEmployeeSalary(employees.get(employees.size() - 1),
                    data, dateF, dateT))
                    .append("\n");
        }
        return report
                .deleteCharAt(report.length() - 1)
                .toString();
    }

    private String getEmployeeSalary(Employee employee,
                                     String[] data,
                                     LocalDate dateFrom,
                                     LocalDate dateTo) {
        for (String dataOfDay : data) {
            if (dataOfDay.contains(employee.getName())) {
                String[] str = dataOfDay.split(" ");
                LocalDate date = parseDate(str[0]);
                if ((date.isAfter(dateFrom) || date.isEqual(dateFrom))
                        && (date.isBefore(dateTo) || date.isEqual(dateTo))) {
                    employee.addSalary(Integer.parseInt(str[2]) * Integer.parseInt(str[3]));
                }
            }
        }
        return new StringBuilder()
                .append(employee.getName())
                .append(" - ")
                .append(employee.getSalary())
                .toString();
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
