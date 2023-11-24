package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORK_DATE = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORK_HOUR = 2;
    private static final int RATE = 3;
    private static final int SALARY_FOR_PERIOD = 1;
    private static final String DATE_PATTERN = "d.MM.yyyy";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] reportData = new String[names.length];
        int freeIndex = 0;

        for (String employee: data) {
            String[] employeeData = employee.split(" ");
            if (indexOf(names, employeeData[EMPLOYEE_NAME]) >= 0
                    && isInDateRange(employeeData[WORK_DATE], dateFrom, dateTo)
            ) {
                boolean isNewReportItem = indexOf(reportData, employeeData[EMPLOYEE_NAME]) < 0;

                int daySalary = Integer.parseInt(employeeData[WORK_HOUR])
                        * Integer.parseInt(employeeData[RATE]);

                setReportData(reportData, employeeData[EMPLOYEE_NAME], daySalary, freeIndex);

                if (isNewReportItem) {
                    freeIndex++;
                }
            } else {
                setReportData(reportData, employeeData[EMPLOYEE_NAME], 0, freeIndex);
                freeIndex++;
            }
        }

        return createReport(reportData, dateFrom, dateTo);
    }

    private boolean isInDateRange(String checkedDate, String dateFrom, String dateTo) {
        LocalDate date = LocalDate.parse(checkedDate, formatter);
        LocalDate start = LocalDate.parse(dateFrom, formatter);
        LocalDate end = LocalDate.parse(dateTo, formatter);

        return ((date.isAfter(start) || date.isEqual(start))
                && (date.isBefore(end) || date.isEqual(end)));
    }

    private int indexOf(String[] array, String name) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return -1;
            }
            if (array[i].startsWith(name)) {
                return i;
            }
        }

        return -1;
    }

    private void setReportData(String[] reportData, String employee, int salary, int freeIndex) {
        int employeeIndex = indexOf(reportData, employee);

        if (employeeIndex >= 0) {
            String[] employeeData = reportData[employeeIndex].split(" - ");
            int totalSalary = Integer.parseInt(employeeData[SALARY_FOR_PERIOD]) + salary;
            reportData[employeeIndex] = employee + " - " + totalSalary;
        } else {
            reportData[freeIndex] = employee + " - " + salary;
        }
    }

    private String createReport(String[] reportData, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < reportData.length; i++) {
            stringBuilder.append(reportData[i]);
            if (i != reportData.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }

        return stringBuilder.toString();
    }
}
