package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private int findEmployeesIndex(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])) {
                return i;
            }
        }
        return -1;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] dailyDataInfo = new String[4];
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salary = new int[names.length];
        for (String dailyData : data) {
            dailyDataInfo = dailyData.split(" ",4);
            LocalDate dailyDate = LocalDate.parse(dailyDataInfo[0], FORMATTER);
            int employeesIndex = findEmployeesIndex(names, dailyDataInfo[1]);
            if (!dailyDate.isAfter(toDate)
                    && !dailyDate.isBefore(fromDate)
                    && employeesIndex != -1) {
                salary[employeesIndex]
                        += Integer.parseInt(dailyDataInfo[2])
                        * Integer.parseInt(dailyDataInfo[3]);
            }
        }
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary[i]);
        }
        return salaryInfo.toString();
    }
}
