package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_OF_PAY = 10;
    private static final int HOURS_WORKED = 2;
    private static final int SALARY_PER_HOUR = 3;
    private static final int START_OF_RANGE = 0;
    private static final String SPACE = " ";
    private static final String REPORT_MESSAGE = "Report for period ";
    private static final String SEPARATE = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder(REPORT_MESSAGE + dateFrom + SEPARATE + dateTo);
        int salary;
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateLast = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            salary = 0;
            for (String dataElement : data) {
                if (dataElement.contains(name)) {
                    LocalDate currentDate = LocalDate
                            .parse(dataElement.substring(START_OF_RANGE, DATE_OF_PAY), FORMATTER);
                    if (!dateLast.isBefore(currentDate) && !dateStart.isAfter(currentDate)) {
                        String[] splittedData = dataElement.split(SPACE);
                        salary += Integer.parseInt(splittedData[HOURS_WORKED])
                                * Integer.parseInt(splittedData[SALARY_PER_HOUR]);
                    }
                }
            }
            sb.append(System.lineSeparator()).append(name)
                    .append(SEPARATE).append(salary);
        }
        return sb.toString();
    }
}
