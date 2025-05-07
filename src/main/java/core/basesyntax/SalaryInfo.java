package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DAY_START_WORK_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURS_OF_WORK_IN_DAY_INDEX = 2;
    static final int SALARY_IN_HOUR_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int moneyEarned = 0;
            for (String personData : data) {
                String[] splitedPersonData = personData.split(" ");
                LocalDate workStartDate =
                        LocalDate.parse(splitedPersonData[DAY_START_WORK_INDEX], DATE_FORMATTER);
                String workerName = splitedPersonData[NAME_INDEX];
                int hoursOfWorkInDay = Integer
                        .parseInt(splitedPersonData[HOURS_OF_WORK_IN_DAY_INDEX]);
                int salaryPerHour = Integer.parseInt(splitedPersonData[SALARY_IN_HOUR_INDEX]);
                if (name.equals(workerName)
                        && (workStartDate.isAfter(localDateFrom)
                        || workStartDate.equals(localDateFrom))
                        && (workStartDate.isBefore(localDateTo)
                        || workStartDate.equals(localDateTo))) {
                    moneyEarned += hoursOfWorkInDay * salaryPerHour;
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(moneyEarned);
        }
        return reportBuilder.toString();
    }
}
