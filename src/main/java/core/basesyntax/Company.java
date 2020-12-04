package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Company {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private Employee[] employees;

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public static WorkingDay parseWorkingDays(String data) {
        String[] splitFields = data.split(" ");
        WorkingDay day = new WorkingDay(Company.parseDate(splitFields[0]));
        day.setHoursWorked(Integer.parseInt(splitFields[2]));
        day.setHourIncome(Integer.parseInt(splitFields[3]));
        return day;
    }

    public static void fillWorkingDays(Employee[] employees, String[] data) {
        for (String dataLine : data) {
            String[] fields = dataLine.split(" ");
            for (Employee employee : employees) {
                if (employee.getName().equals(fields[1])) {
                    employee.addWorkingDay(parseWorkingDays(dataLine));
                }
            }
        }
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }
}
