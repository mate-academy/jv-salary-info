package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int dateIndex = 0;
        final int nameIndex = 1;
        final int hoursIndex = 2;
        final int salaryIndex = 3;
        final String dateSeparator = " - ";

        StringBuilder getSalaryInfo = new StringBuilder("Report for period "
                + dateFrom + dateSeparator + dateTo);
        int[] salariesInfo = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String datum : data) {
                if (isDateInRange(datum.split(" ")[dateIndex], dateFrom, dateTo)
                        && datum.split(" ")[nameIndex].equals(names[i])) {
                    salariesInfo[i] += Integer.parseInt(datum.split(" ")[hoursIndex])
                            * Integer.parseInt(datum.split(" ")[salaryIndex]);
                }
            }
            getSalaryInfo.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salariesInfo[i]);
        }
        return getSalaryInfo.toString();
    }

    private boolean isDateInRange(String dateChecked, String dateFrom, String dateTo) {
        try {
            return !(DATE_FORMAT.parse(dateChecked).before(DATE_FORMAT.parse(dateFrom))
                 || DATE_FORMAT.parse(dateChecked).after(DATE_FORMAT.parse(dateTo)));
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
    }
}
