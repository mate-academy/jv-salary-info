package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            stringBuilder.append(calculateTotalEarned(name, data, dateFrom, dateTo))
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }

    private String calculateTotalEarned(String employee,
                                        String[] data,
                                        String dateFrom,
                                        String dateTo) {
        int earned = 0;
        for (String row : data) {
            String[] separatedData = row.split(" ");
            String date = separatedData[0];
            String nameFromArray = separatedData[1];
            int hours = Integer.parseInt(separatedData[2]);
            int rate = Integer.parseInt(separatedData[3]);

            if (isDateInPeriod(date, dateFrom, dateTo) && nameFromArray.equals(employee)) {
                earned += hours * rate;
            }
        }
        return employee + " - " + earned;
    }

    private boolean isDateInPeriod(String checkedDate, String dateFrom, String dateTo) {
        LocalDate checkedDateLD = LocalDate.parse(checkedDate, formatter);
        LocalDate dateFromLD = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLD = LocalDate.parse(dateTo, formatter);

        return checkedDateLD.isEqual(dateFromLD)
                || checkedDateLD.isEqual(dateToLD)
                || (checkedDateLD.isAfter(dateFromLD)
                && checkedDateLD.isBefore(dateToLD));
    }
}
