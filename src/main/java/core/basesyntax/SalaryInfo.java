package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if ((getDate(dateFrom).isBefore(getDate(dateTo))
                || getDate(dateFrom).equals(getDate(dateTo)))
                && names.length != 0 && data.length != 0
                && dateFrom.length() != 0 && dateTo.length() != 0) {
            StringBuilder builder = new StringBuilder();
            int income = 0;
            builder.append("Report for period " + dateFrom + " - " + dateTo)
                    .append(System.lineSeparator());
            for (int i = 0; i < names.length; i++) {
                for (int j = 0; j < data.length; j++) {
                    if (names[i].equals(splitData(data[j], 1))
                            && (getDate(dateFrom).isBefore(getDate(splitData(data[j], 0)))
                            || getDate(dateFrom).equals(getDate(splitData(data[j], 0))))
                            && (getDate(dateTo).isAfter(getDate(splitData(data[j], 0)))
                            || getDate(dateTo).equals(getDate(splitData(data[j], 0))))) {
                        // we multiply the hours with the salary per hour
                        income += Integer.parseInt(splitData(data[j], 2))
                                * Integer.parseInt(splitData(data[j], 3));
                    }
                }
                builder.append(names[i] + " - " + income).append(System.lineSeparator());
                income = 0;
            }
            return builder.toString().substring(0, builder.length() - 2);
        } else {
            return "Invalid input datas";
        }
    }

    /* returns the value in String from a single row of the date array,
     when i=0 it returns the date, when i=1 it returns the name,
     when i=2 it returns working hours, when i=3 it returns the salary */
    private String splitData(String singularRowData, int i) {
        String[] arrayOfRow = singularRowData.split(" ", 4);
        return arrayOfRow[i];
    }

    // returns LocalDate format from String
    private LocalDate getDate(String stringDate) {
        return LocalDate.parse(stringDate, formatter);
    }
}
