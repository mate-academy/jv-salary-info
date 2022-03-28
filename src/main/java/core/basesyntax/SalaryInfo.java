package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int DAY_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        StringBuilder reportBuilder = new StringBuilder("Report for period ");
        reportBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] splittedData = data[i].split(" ");
                LocalDate date = LocalDate.parse(splittedData[DATE_INDEX], formatter);
                if ((localDateFrom.isBefore(date) || localDateFrom.equals(date))
                        && (localDateTo.isAfter(date) || localDateTo.equals(date))
                        && name.equals(splittedData[NAME_INDEX])) {
                    salary += Integer.parseInt(splittedData[DAY_INDEX])
                            * Integer.parseInt(splittedData[INCOME_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
