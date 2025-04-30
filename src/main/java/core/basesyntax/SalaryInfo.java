package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate salaryDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate salaryDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] tempData = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(tempData[DATE_INDEX], formatter);

                if (!currentDate.isBefore(salaryDateFrom)
                        && !currentDate.isAfter(salaryDateTo)) {
                    if (name.equals(tempData[NAME_INDEX])) {
                        totalSalary += Integer.parseInt(tempData[HOUR_INDEX])
                                * Integer.parseInt(tempData[INCOME_INDEX]);
                    }
                }
            }
            reportBuilder.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return reportBuilder.toString();
    }
}
