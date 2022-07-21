package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builderListOfWorkers = new StringBuilder();
        builderListOfWorkers.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        int index = 0;
        for (String name : names) {
            int salaryTotalAtWorkPeriod = 0;
            for (String dataRow : data) {
                String[] dataArrayRow = dataRow.split(" ");
                LocalDate workDate = getFormatDate(dataArrayRow[0]);
                String nameOfWorker = dataArrayRow[1];
                int hoursPerDay = Integer.parseInt(dataArrayRow[2]);
                int salaryPerHour = Integer.parseInt(dataArrayRow[3]);
                boolean atWorkPeriod = workDate.isAfter(getFormatDate(dateFrom).minusDays(1))
                        & workDate.isBefore(getFormatDate(dateTo).plusDays(1));
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

    private LocalDate getFormatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }
}
