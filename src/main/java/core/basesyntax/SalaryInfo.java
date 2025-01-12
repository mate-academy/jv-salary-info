package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] employeeSalaries = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            String[] infoFromData = data[i].split(" ");
            Employee employee = new Employee();
            employee.setName(infoFromData[1]);

            try {
                if (infoFromData.length >= 4) {
                    String dateString = infoFromData[0];
                    if (!isDateInRange(dateString, dateFrom, dateTo)) {
                        continue;
                    }

                    int hours = Integer.parseInt(infoFromData[2]);
                    int salaryPerHour = Integer.parseInt(infoFromData[3]);
                    int salary = hours * salaryPerHour;
                    employee.setSalary(salary);

                    for (int j = 0; j < names.length; j++) {
                        if (names[j].equals(employee.getName())) {
                            employeeSalaries[j] += salary;
                            break;
                        }
                    }
                } else {
                    System.out.println("Wrong data format at index " + i);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format at index " + i);
            } catch (DateTimeException e1) {
                System.out.println("Invalid date format at index " + i + ". Should be dd.mm.yyyy");
            }
        }
        StringBuilder result = new StringBuilder();
        int totalSalary = 0;
        for (int i = 0; i < names.length; i++) {
            int salary = employeeSalaries[i];
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
            totalSalary += employeeSalaries[i];
        }
        return "Report for period " + (dateFrom) + (" - ") + (dateTo) + result;
    }

    public boolean isDateInRange(String date, String dateFrom, String dateTo) {
        LocalDate actual = parseDate(date);
        LocalDate fromDate = parseDate(dateFrom);
        LocalDate toDate = parseDate(dateTo);
        return fromDate.isBefore(actual) && toDate.isAfter(actual)
                || toDate.isEqual(actual) || fromDate.isEqual(actual);

    }

    public LocalDate parseDate(String date) {
        String[] parsedDate = date.split("[\\\\.]");
        if (parsedDate.length != 3) {
            throw new ArrayIndexOutOfBoundsException("Wrong date format. Got: "
                    + date + " Should be dd.mm.yyyy format.");
        }
        int day = Integer.parseInt(parsedDate[0]);
        int month = Integer.parseInt(parsedDate[1]);
        int year = Integer.parseInt(parsedDate[2]);
        return LocalDate.of(year, month, day);
    }
}
