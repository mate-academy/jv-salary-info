package core.basesyntax;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DELIMITER = " ";
    private static final String SIGN = " - ";
    private static final String REPORT_MESSAGE = "Report for period ";
    private static final int DATE_INDEX = 0;
    private static final int EMPLOYER_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PRICE_INDEX = 3;
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
                for (String line : data) {
                    var record = line.split(DELIMITER);
                    var dataRecord = LocalDate.parse(record[DATE_INDEX], formatter);
                    if ((fromDate.compareTo(dataRecord) < 0 || fromDate.compareTo(dataRecord) == 0)
                            && (toDate.compareTo(dataRecord) > 0
                            || toDate.compareTo(dataRecord) == 0)
                            && name.equals(record[EMPLOYER_INDEX].trim())) {
                        money += Integer.parseInt(record[HOURS_INDEX])
                                * Integer.parseInt(record[PRICE_INDEX]);
                    }
                }
                result.append(System.lineSeparator()).append(name)
                        .append(SIGN).append(money);
            }
        } catch (NumberFormatException e) {
            System.out.println(
                    "One of the numeric fields have non numeric representation in record");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return result.toString();
    }
}
