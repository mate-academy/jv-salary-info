package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate localDateFrom = getParsedDate(dateFrom);
        LocalDate localDateTo = getParsedDate(dateTo);

        report.append(String.format("Report for period %s - %s", dateFrom, dateTo));
        for (String name : names) {
            int totalIncome = 0;
            for (String row : data) {
                String[] rowSeparatedData = row.split(" ");
                LocalDate parsedDate = getParsedDate(rowSeparatedData[DATE_INDEX]);

                if (name.equals(rowSeparatedData[NAME_INDEX])) {
                    if ((parsedDate.isEqual(localDateFrom)
                            || parsedDate.isAfter(localDateFrom))
                            && (parsedDate.isEqual(localDateTo)
                            || parsedDate.isBefore(localDateTo))) {
                        totalIncome += Integer.parseInt(rowSeparatedData[WORK_HOURS_INDEX])
                                * Integer.parseInt(rowSeparatedData[INCOME_PER_HOUR_INDEX]);
                    }
                }
            }
            report.append(System.lineSeparator())
                    .append(String.format("%s - %d", name, totalIncome));
        }
        return report.toString();
    }

    private LocalDate getParsedDate(String date) {
        return LocalDate.parse(date, formatter);
    }
}
