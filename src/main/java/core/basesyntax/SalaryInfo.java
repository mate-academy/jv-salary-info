package core.basesyntax;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DELIMITER = " ";
    private static final String SIGN = " - ";
    private static final String REPORT_MESSAGE = "Report for period ";
    private static final int DATE_VALUE = 0;
    private static final int EMPLOYER = 1;
    private static final int HOURS = 2;
    private static final int PRICE = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        try {
            final LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
            final LocalDate toDate = LocalDate.parse(dateTo, formatter);
            if (toDate.compareTo(fromDate) < 0) {
                throw new ParseException("DateFrom occurs after DateTo", 0);
            }
            result.append(REPORT_MESSAGE).append(fromDate.format(formatter))
                    .append(SIGN).append(toDate.format(formatter));
            for (String name: names) {
                int money = 0;
                for (String dat: data) {
                    var record = dat.split(DELIMITER);
                    var dataRecord = LocalDate.parse(record[DATE_VALUE], formatter);
                    if ((fromDate.compareTo(dataRecord) < 0 || fromDate.compareTo(dataRecord) == 0)
                            && (toDate.compareTo(dataRecord) > 0
                            || toDate.compareTo(dataRecord) == 0)
                            && name.trim().equals(record[EMPLOYER].trim())) {
                        money += Integer.parseInt(record[HOURS]) * Integer.parseInt(record[PRICE]);
                    }
                }
                result.append(System.lineSeparator()).append(name.trim())
                        .append(SIGN).append(money);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return result.toString();
    }
}
