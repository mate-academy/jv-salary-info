package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder reportBuilder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        String[] dataArray;
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                dataArray = line.split(" ");
                LocalDate currentDate = LocalDate.parse(dataArray[DATE_INDEX], formatter);
                if (((localDateFrom.isBefore(currentDate)) || localDateFrom.isEqual(currentDate))
                        && ((localDateTo.isAfter(currentDate)) || localDateTo.isEqual(currentDate))
                        && (dataArray[NAME_INDEX].equals(name))) {
                    salary += Integer.parseInt(dataArray[HOURS_INDEX])
                            * Integer.parseInt(dataArray[SALARY_PER_HOUR_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
