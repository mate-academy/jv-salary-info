package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        validateInput(names, data);

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo);

        String[] uniqueNames = new String[names.length]; // Масив унікальних імен співробітників
        int[] salaries = new int[names.length]; // Масив заробітків співробітників
        int uniqueIndex = 0; // Індекс для унікальних імен

        if (startDate.isEqual(endDate)) {
            buildReportString(reportBuilder, names, uniqueNames, salaries, uniqueIndex);
            return reportBuilder.toString();
        }
        for (String employeeData : data) {
            String[] employeeInfo = employeeData.split(" ");
            LocalDate hireDate = LocalDate.parse(employeeInfo[0], dateFormat);
            String name = employeeInfo[1];
            int hoursWorked = Integer.parseInt(employeeInfo[2]);
            int hourlyRate = Integer.parseInt(employeeInfo[3]);

            if (isWithinDateRange(hireDate, startDate, endDate)) {
                int nameIndex = findNameIndex(uniqueNames, name, uniqueIndex);

                if (nameIndex == -1) {
                    uniqueNames[uniqueIndex] = name;
                    salaries[uniqueIndex] = hoursWorked * hourlyRate;
                    uniqueIndex++;
                } else {
                    salaries[nameIndex] += hoursWorked * hourlyRate;
                }
            }
        }
        buildReportString(reportBuilder, names, uniqueNames, salaries, uniqueIndex);
        return reportBuilder.toString();
    }

    private void validateInput(String[] names, String[] data) {
        if (names == null || data == null) {
            throw new IllegalArgumentException("Масиви імен та даних не можуть бути пустими");
        }
    }

    private LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, dateFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неправильний формат дати: " + e.getMessage(), e);
        }
    }

    private boolean isWithinDateRange(LocalDate hireDate, LocalDate startDate, LocalDate endDate) {
        return hireDate.isEqual(startDate) || hireDate.isEqual(endDate)
                || (hireDate.isAfter(startDate) && hireDate.isBefore(endDate));
    }

    private int findNameIndex(String[] uniqueNames, String name, int uniqueIndex) {
        for (int i = 0; i < uniqueIndex; i++) {
            if (uniqueNames[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private void buildReportString(StringBuilder reportBuilder, String[] names,
                                   String[] uniqueNames, int[] salaries, int uniqueIndex) {
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int nameIndex = findNameIndex(uniqueNames, name, uniqueIndex);
            int salary = (nameIndex != -1) ? salaries[nameIndex] : 0;
            reportBuilder.append(name).append(" - ").append(salary);
            if (i < names.length - 1) {
                reportBuilder.append(System.lineSeparator());
            }
        }
    }
}