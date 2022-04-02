package core.basesyntax.support;

import core.basesyntax.model.Employee;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeSupport {
    private Employee[] employees;
    private SimpleDateFormat simpleDateFormat;

    public EmployeeSupport() {
        this.simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    public Employee[] calculateSalaryForPeriod(String[] names, String[] data,
                                               String dateFrom, String dateTo) {
        employees = calculateStatisticOfEmployee(names, data);
        try {
            Date startDate = simpleDateFormat.parse(dateFrom);
            Date endDate = simpleDateFormat.parse(dateTo);
            Date spentWorkDay;
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < employees.length; j++) {
                    if (employees[j].getWorkday()[i] == null) {
                        continue;
                    }
                    spentWorkDay = simpleDateFormat.parse(employees[j]
                            .getWorkday()[i]);
                    if (isWithinPeriod(spentWorkDay, startDate, endDate)) {
                        employees[j]
                                .setMoneyEarnedForCertainPeriod(employees[j]
                                        .getMoneyEarnedForCertainPeriod()
                                        + (Integer.parseInt(employees[j]
                                        .getHoursWork()[i])
                                        * Integer.parseInt(employees[j]
                                        .getSalaryPerHour()[i])));
                    }
                }
            }
        } catch (ParseException e) {
            System.out.println("Wrong date format, need to use \"dd.MM.yyyy\"");
        }
        return employees;
    }

    private Employee[] calculateStatisticOfEmployee(String[] names, String[]
            data) {
        employees = new Employee[names.length];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(names[i], data.length, 0);
        }
        String[] statistic;
        for (int i = 0; i < data.length; i++) {
            statistic = data[i].split(" ");
            // statistic[0] = day;
            // statistic[1] = name;
            // statistic[2] = working hours;
            // statistic[3] = salary per hour;
            for (int j = 0; j < employees.length; j++) {
                if (employees[j].getName().equals(statistic[1])) {
                    int index = emptyIndex(employees[j].getHoursWork());
                    employees[j].setWorkday(statistic[0], index);
                    employees[j].setHoursWork(statistic[2], index);
                    employees[j].setSalaryPerHour(statistic[3], index);
                }
            }
        }
        return employees;
    }

    private int emptyIndex(String[] date) {
        int counter = 0;
        for (int i = 0; i < date.length; i++) {
            if (date[i] != null) {
                counter++;
            }
        }
        return counter;
    }

    private boolean isWithinPeriod(Date testDate, Date
            startDate, Date endDate) {
        return testDate.getTime() >= startDate.getTime()
                && testDate.getTime() <= endDate.getTime();
    }
}
