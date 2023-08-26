package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate localDateFrom = getParsedDate(dateFrom);
        LocalDate localDateTo = getParsedDate(dateTo);

        report.append(String.format("Report for period %s - %s", dateFrom, dateTo));
        for (String name : names) {
            int totalIncome = 0;
            for (String row : data) {
                String[] rowSeparatedData = row.split(" ");
                LocalDate parsedDate = getParsedDate(rowSeparatedData[0]);

                if (name.equals(rowSeparatedData[1])) {
                    if ((parsedDate.isEqual(localDateFrom)
                            || parsedDate.isAfter(localDateFrom))
                            && (parsedDate.isEqual(localDateTo)
                            || parsedDate.isBefore(localDateTo))) {
                        totalIncome += Integer.parseInt(rowSeparatedData[2])
                                * Integer.parseInt(rowSeparatedData[3]);
                    }
                }
            }
            report.append(System.lineSeparator())
                    .append(String.format("%s - %d", name, totalIncome));
        }
        return report.toString();
    }

    private LocalDate getParsedDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}
