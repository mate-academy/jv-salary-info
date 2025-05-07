package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int DAYS_AMOUNT_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryStatement = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate dateFromParsed = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateToParsed = LocalDate.parse(dateTo, DATE_FORMATTER);
        for (String name : names) {
            int earnedMoney = 0;
            for (String info : data) {
                String[] splitData = info.split(" ");
                LocalDate workingDate = LocalDate.parse(splitData[WORKING_DAY_INDEX],
                        DATE_FORMATTER);
                String currentName = splitData[NAME_INDEX];
                int daysAmount = Integer.parseInt(splitData[DAYS_AMOUNT_INDEX]);
                int salaryPerDay = Integer.parseInt(splitData[SALARY_INDEX]);
                if (currentName.equals(name) && !workingDate.isAfter(dateToParsed)
                        && !workingDate.isBefore(dateFromParsed)) {
                    earnedMoney += daysAmount * salaryPerDay;
                }
            }
            salaryStatement.append(System.lineSeparator()).append(name)
                .append(" - ").append(earnedMoney);
        }
        return salaryStatement.toString();
    }
}
