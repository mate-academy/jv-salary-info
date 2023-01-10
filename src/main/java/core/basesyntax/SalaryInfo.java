package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder();
        int[] totalSalary = new int[names.length];
        String[] employeeData;
        LocalDate fromDate = LocalDate.parse(dateFrom, format);
        LocalDate toDate = LocalDate.parse(dateTo, format);
        for (String employee : data
        ) {
            employeeData = employee.split(" ", 4);
            LocalDate currentDate = LocalDate.parse(employeeData[0], format);
            for (int i = 0; i < names.length; i++) {
                if (employeeData[1].equals(names[i])
                        & !currentDate.isBefore(fromDate)
                        & !currentDate.isAfter(toDate)) {
                    totalSalary[i] += (Integer.parseInt(employeeData[2])
                            * Integer.parseInt(employeeData[3]));
                }
            }
        }
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            sb.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(totalSalary[i]);
        }
        return sb.toString();
    }
}
