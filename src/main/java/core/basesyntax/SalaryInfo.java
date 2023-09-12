package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int WORK_HOUR = 2;
    private static final int INCOME_PER_HOUR = 3;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate localDateFrom = getParsedDate(dateFrom);
        LocalDate localDateTo = getParsedDate(dateTo);

        report.append(String.format("Report for period %s - %s\", dateFrom, dateTo)"));
        for (String name : names) {
            int totalIncome = 0;
            for (String row : data) {
                String[] rowSeparateData = row.split(" ");
                LocalDate parsedDate = getParsedDate(rowSeparateData[DATE]);

                if (name.equals(rowSeparateData[NAME])) {
                    if ((parsedDate.isEqual(localDateFrom)
                    || parsedDate.isAfter(localDateFrom))
                    && (parsedDate.isEqual(localDateTo))
                    || parsedDate.isBefore(localDateTo)) {
                        totalIncome += Integer.parseInt(rowSeparateData[WORK_HOUR])
                                * Integer.parseInt(rowSeparateData[INCOME_PER_HOUR]);
                    }
                }
            }
            report.append(System.lineSeparator()).append(String.format("%s - %d", name, totalIncome));
        }
        return report.toString();

    }
        private LocalDate getParsedDate(String date) {
            return LocalDate.parse(date, formatter);
        }



}
