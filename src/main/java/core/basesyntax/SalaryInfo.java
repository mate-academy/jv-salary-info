package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_PAYMENT = 3;
    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder("Report for period ");
        reportBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            reportBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(getSalaryFromPerson(name, data, dateFrom, dateTo));

        }
        return reportBuilder.toString().trim();
    }

    private int getSalaryFromPerson(String name, String[] data, String dateFrom, String dateTo) {
        int totalScoreOfPerson = 0;
        for (String infoFromData : data) {
            String[] info = infoFromData.split(" ");
            if (name.equals(info[INDEX_OF_NAME])) {
                LocalDate currentDate = LocalDate.parse(info[INDEX_OF_DATE], formatter);
                LocalDate workFrom = LocalDate.parse(dateFrom, formatter);
                LocalDate workTo = LocalDate.parse(dateTo, formatter);

                if ((currentDate.isBefore(workTo) || currentDate.isEqual(workTo))
                        && (currentDate.isAfter(workFrom)) || currentDate.isEqual(workFrom)) {
                    totalScoreOfPerson += (Integer.parseInt(info[INDEX_OF_HOURS])
                            * Integer.parseInt(info[INDEX_OF_PAYMENT]));
                }
            }
        }
        return totalScoreOfPerson;
    }
}
