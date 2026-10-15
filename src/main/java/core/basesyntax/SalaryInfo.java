package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom.trim())
                .append(" - ")
                .append(dateTo.trim());
        LocalDate dateBegin = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);
        for (String name : names) {
            int totalSalary = 0;
            String[] workDataArray;
            for (String workData : data) {
                workDataArray = workData.split("\\s+");
                if (workDataArray[NAME_INDEX].equals(name)) {
                    LocalDate paymentDate = LocalDate.parse(
                            workDataArray[DATE_INDEX],
                            DATE_FORMATTER);
                    if (!paymentDate.isBefore(dateBegin) && !paymentDate.isAfter(dateEnd)) {
                        totalSalary += Integer.parseInt(workDataArray[HOURS_INDEX])
                                * Integer.parseInt(workDataArray[RATE_INDEX]);
                    }
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return result.toString();
    }
}
