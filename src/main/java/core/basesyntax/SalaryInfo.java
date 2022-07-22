package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_WORK_DATE = 0;
    private static final int DATA_NAME_OF_WORKER = 1;
    private static final int DATA_HOURS_PER_DAY = 2;
    private static final int DATA_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builderListOfWorkers = new StringBuilder();
        builderListOfWorkers.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        int index = 0;
        for (String name : names) {
            int salaryTotalAtWorkPeriod = 0;
            for (String dataRow : data) {
                String[] dataArrayRow = dataRow.split(" ");
                LocalDate workDate = LocalDate.parse(dataArrayRow[DATA_WORK_DATE], FORMATTER);
                String nameOfWorker = dataArrayRow[DATA_NAME_OF_WORKER];
                int hoursPerDay = Integer.parseInt(dataArrayRow[DATA_HOURS_PER_DAY]);
                int salaryPerHour = Integer.parseInt(dataArrayRow[DATA_SALARY_PER_HOUR]);
                boolean atWorkPeriod = workDate.isAfter(LocalDate.parse(dateFrom, FORMATTER)
                        .minusDays(1)) && workDate.isBefore(LocalDate.parse(dateTo, FORMATTER)
                        .plusDays(1));
                if (name.equals(nameOfWorker) && atWorkPeriod) {
                    salaryTotalAtWorkPeriod += hoursPerDay * salaryPerHour;
                }
            }
            builderListOfWorkers.append(System.lineSeparator()).append(names[index])
                    .append(" - ").append(salaryTotalAtWorkPeriod);
            index++;
        }
        return builderListOfWorkers.toString();
    }
}
