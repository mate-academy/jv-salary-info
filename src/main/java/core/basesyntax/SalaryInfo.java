package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATA_SEPARATOR = " ";
    private static final byte DATA_DATE_SLOT = 0;
    private static final byte DATA_NAME_SLOT = 1;
    private static final byte DATA_WORK_HOURS_SLOT = 2;
    private static final byte DATA_MONEY_PER_HOUR_SLOT = 3;

    private final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int sumForThisName = 0;
            for (String lineData : data) {
                String[] recordData = lineData.split(DATA_SEPARATOR);
                if (name.equals(recordData[DATA_NAME_SLOT])
                        && datesMatch(recordData[DATA_DATE_SLOT],dateFrom,dateTo)) {
                    int toAdd = Integer.parseInt(recordData[DATA_WORK_HOURS_SLOT])
                            * Integer.parseInt(recordData[DATA_MONEY_PER_HOUR_SLOT]);
                    sumForThisName += toAdd;
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(sumForThisName);
        }
        return stringBuilder.toString();
    }

    private boolean datesMatch(String date, String dateFrom, String dateTo) {
        LocalDate dateNow = LocalDate.parse(date, dateFormatter);
        LocalDate compareFrom = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate compareTo = LocalDate.parse(dateTo, dateFormatter);
        return dateNow.isAfter(compareFrom)
                && compareTo.isAfter(dateNow) || compareTo.isEqual(dateNow);
    }
}
