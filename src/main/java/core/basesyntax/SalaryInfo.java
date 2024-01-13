package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_IN_DATA = 0;
    private static final int NAME_IN_DATA = 1;
    private static final int VORKING_HOUR_IN_DATA = 2;
    private static final int INCOME_PER_HOUR_IN_DATA = 3;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder employeInfrmation = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int moneyEarned = 0;
            for (String basicData : data) {
                String[] currentInfo = basicData.split(" ");
                LocalDate entryDate = LocalDate.parse(currentInfo[DATE_IN_DATA], formatter);
                if ((entryDate.isEqual(localDateFrom)
                        || entryDate.isAfter(localDateFrom))
                            && (entryDate.isEqual(localDateTo)
                        || entryDate.isBefore(localDateTo))
                            && currentInfo[NAME_IN_DATA].equals(name)) {
                    moneyEarned += Integer.parseInt(currentInfo[VORKING_HOUR_IN_DATA])
                            * Integer.parseInt(currentInfo[INCOME_PER_HOUR_IN_DATA]);
                }
            }
            employeInfrmation.append(System.lineSeparator())
                    .append(name).append(" - ").append(moneyEarned);
        }
        return employeInfrmation.toString();
    }
}
