package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_EMPLOYEE_INDEX = 1;
    private static final int HOURS_QUANTITY_INDEX = 2;
    private static final int SALARY_HOURLY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder("Report for period ");
        sb.append(dateFrom).append(" - ").append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        int[] summarySalaryArray = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            String[] splitDataRow = data[i].split(" ");
            String rawDate = splitDataRow[DATE_INDEX];
            LocalDate date = LocalDate.parse(rawDate, formatter);

            boolean isIncludesInPeriod = (date.isEqual(fromDate)
                    || date.isAfter(fromDate))
                    && (date.isEqual(toDate)
                    || date.isBefore(toDate));

            if (isIncludesInPeriod) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(splitDataRow[NAME_EMPLOYEE_INDEX])) {
                        summarySalaryArray[j] += getSalaryAtDay(
                                splitDataRow[HOURS_QUANTITY_INDEX],
                                splitDataRow[SALARY_HOURLY_INDEX]);
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            sb.append("\n").append(names[i]).append(" - ").append(summarySalaryArray[i]);
        }

        return sb.toString();
    }

    private int getSalaryAtDay(String hoursQuantity, String salaryHourly) {
        int hours = Integer.parseInt(hoursQuantity);
        int rate = Integer.parseInt(salaryHourly);
        return hours * rate;
    }
}
