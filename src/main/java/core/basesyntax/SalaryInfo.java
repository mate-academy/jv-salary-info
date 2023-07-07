package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int NUMBER_OF_WORKED_HOURS_INDEX = 2;
    private static final int AMOUNT_OF_PAYMENT_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom,
                formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo,
                formatter);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        LocalDate parsedDateFrom = LocalDate.parse(dateFrom,
                formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo,
                formatter);

        for (String name : names) {
            int salary = 0;
            for (String dataSplit : data) {
                String[] foundData = dataSplit.split(" ");
                LocalDate localDateWorkDay = LocalDate.parse(foundData[DATE_INDEX],
                        formatter);

                if ((localDateWorkDay.isAfter(localDateFrom)
                        || localDateWorkDay.isEqual(localDateFrom))
                        && (localDateWorkDay.isBefore(localDateTo)
                        || localDateWorkDay.isEqual(localDateTo))
                        && foundData[NAME_INDEX].equals(name)) {
                    salary += Integer.parseInt(foundData[NUMBER_OF_WORKED_HOURS_INDEX])
                            * Integer.parseInt(foundData[AMOUNT_OF_PAYMENT_PER_HOUR_INDEX]);

                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
