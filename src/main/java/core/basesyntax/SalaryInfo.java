package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportForPeriod = new StringBuilder("Report for period ");
        reportForPeriod.append(dateFrom).append(" - ").append(dateTo);
        LocalDate tempDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate tempDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int totalIncome = 0;
            for (String dataInfo : data) {
                String[] splittedData = dataInfo.split(" ");
                LocalDate tempDate = LocalDate.parse(splittedData[DATE_INDEX], FORMATTER);
                if (dataInfo.contains(name)
                        && (tempDate.isAfter(tempDateFrom) || tempDate.isEqual(tempDateFrom))
                        && (tempDate.isBefore(tempDateTo) || tempDate.isEqual(tempDateTo))) {
                    totalIncome += Integer.parseInt(splittedData[WORK_HOURS_INDEX])
                            * Integer.parseInt(splittedData[INCOME_PER_HOUR_INDEX]);
                }
            }
            reportForPeriod.append("\n").append(name).append(" - ").append(totalIncome);
        }
        return reportForPeriod.toString();
    }
}
