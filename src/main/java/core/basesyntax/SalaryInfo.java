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
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMATTER);
        int[] totalIncome = new int[names.length];
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String newData : data) {
            String[] salaryInfo = newData.split(" ");
            LocalDate workDate = LocalDate.parse(salaryInfo[WORK_DATE_INDEX], FORMATTER);
            if (!(workDate.isBefore(firstDate)) && !(workDate.isAfter(lastDate))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(salaryInfo[NAME_OF_WORKER_INDEX])) {
                        totalIncome[i] = totalIncome[i]
                                + Integer.parseInt(salaryInfo[HOURS_PER_DAY_INDEX])
                                * Integer.parseInt(salaryInfo[SALARY_PER_HOUR_INDEX]);
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(totalIncome[i]);
        }
        return reportBuilder.toString();
    }
}
