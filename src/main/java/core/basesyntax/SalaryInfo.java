package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final String SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder getSalaryInfo = new StringBuilder("Report for period "
                + dateFrom + SEPARATOR + dateTo);
        int[] salariesInfo = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String datum : data) {
                if (isDateInRange(datum.split(" ")[DATE_INDEX], dateFrom, dateTo)
                        && datum.split(" ")[NAME_INDEX].equals(names[i])) {
                    salariesInfo[i] += Integer.parseInt(datum.split(" ")[HOURS_INDEX])
                            * Integer.parseInt(datum.split(" ")[SALARY_INDEX]);
                }
            }
            getSalaryInfo.append(System.lineSeparator()).append(names[i])
                    .append(SEPARATOR).append(salariesInfo[i]);
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
