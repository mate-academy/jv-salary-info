package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NUMBER_OF_MONTH = 2;
    private static final int SALARY = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder nameAndSalarySum = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            int salarySum = 0;
            for (String record : data) {
                String[] splitData = record.split(" ");
                if (dateRange(dateFrom, splitData[DATE], dateTo) == true
                        && splitData[1].equals(names[i])) {
                    salarySum += getMonthSalary(splitData[NUMBER_OF_MONTH], splitData[SALARY]);
                }
            }
            if (i == names.length - 1) {
                nameAndSalarySum.append(names[i]).append(" - ").append(salarySum);
            } else {
                nameAndSalarySum.append(names[i]).append(" - ").append(salarySum).append("\n");
            }
        }
        return String.format("Report for period %s - %s\n%s", dateFrom, dateTo, nameAndSalarySum);
    }

    private LocalDate getDateFormat(String stringDate) {
        return LocalDate.parse(stringDate, FORMATTER);
    }

    private boolean dateRange(String dateFrom, String correctDate, String dateTo) {
        return !getDateFormat(correctDate).isBefore(getDateFormat(dateFrom))
                && !getDateFormat(correctDate).isAfter(getDateFormat(dateTo));
    }

    private int getMonthSalary(String splitData2, String splitData3) {
        return Integer.parseInt(splitData2) * Integer.parseInt(splitData3);
    }
}
