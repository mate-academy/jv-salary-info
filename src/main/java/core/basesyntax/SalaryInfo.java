package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate localDateFrom = getParseDate(dateFrom);
        LocalDate localDateTo = getParseDate(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String splitData : data) {
                String[] splitArray = splitData.split(" ");
                LocalDate foundDate = getParseDate(splitArray[DATE_INDEX]);
                if (name.equals(splitArray[NAME_INDEX])
                        && localDateFrom.isBefore(foundDate)
                        && (localDateTo.isAfter(foundDate) || localDateTo.isEqual(foundDate))) {
                    totalSalary += Integer.parseInt(splitArray[WORK_HOURS_INDEX])
                            * Integer.parseInt(splitArray[SALARY_PER_HOUR_INDEX]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(totalSalary);
        }
        return stringBuilder.toString();
    }

    private LocalDate getParseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
