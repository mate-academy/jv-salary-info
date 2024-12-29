package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate;
        Date endDate;

        try {
            startDate = dateFormat.parse(dateFrom);
            endDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");

            try {
                Date recordDate = dateFormat.parse(parts[DATE_INDEX]);
                String employeeName = parts[NAME_INDEX];
                int hoursWorked = Integer.parseInt(parts[HOURS_INDEX]);
                int ratePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);

                if (!recordDate.before(startDate) && !recordDate.after(endDate)) {
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(employeeName)) {
                            salaries[i] += hoursWorked * ratePerHour;
                        }
                    }
                }
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format");
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salaries[i]);
        }

        return stringBuilder.toString();
    }
}
