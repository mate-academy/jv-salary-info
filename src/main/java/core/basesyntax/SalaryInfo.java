package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORK_DATE_INDEX = 0;
    private static final int NAME_OF_WORKER_INDEX = 1;
    private static final int HOURS_PER_DAY_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int salaryTotalAtWorkPeriod = 0;
            for (String dataRow : data) {
                String[] dataArrayRow = dataRow.split(" ");
                LocalDate workDate = LocalDate.parse(dataArrayRow[WORK_DATE_INDEX], FORMATTER);
                String nameOfWorker = dataArrayRow[NAME_OF_WORKER_INDEX];
                int hoursPerDay = Integer.parseInt(dataArrayRow[HOURS_PER_DAY_INDEX]);
                int salaryPerHour = Integer.parseInt(dataArrayRow[SALARY_PER_HOUR_INDEX]);
                boolean atWorkPeriod = workDate.isAfter(LocalDate.parse(dateFrom, FORMATTER)
                        .minusDays(1)) && workDate.isBefore(LocalDate.parse(dateTo, FORMATTER)
                        .plusDays(1));
                if (name.equals(nameOfWorker) && atWorkPeriod) {
                    salaryTotalAtWorkPeriod += hoursPerDay * salaryPerHour;
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salaryTotalAtWorkPeriod);
        }
        return reportBuilder.toString();
    }
}
