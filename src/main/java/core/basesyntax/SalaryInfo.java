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
        StringBuilder sb = new StringBuilder();
        int salary;
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateLast = LocalDate.parse(dateTo, FORMATTER);
        String[] resultToReport = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (String dataElement : data) {
                if (dataElement.contains(names[i])) {
                    LocalDate currentDate = LocalDate
                            .parse(dataElement.substring(START_OF_RANGE, DATE_OF_PAY), FORMATTER);
                    sb.delete(START_OF_RANGE, sb.length());
                    if (!dateLast.isBefore(currentDate) && !dateStart.isAfter(currentDate)) {
                        String[] splittedData = dataElement.split(SPACE);
                        salary += Integer.parseInt(splittedData[HOURS_WORKED])
                                * Integer.parseInt(splittedData[SALARY_PER_HOUR]);
                    }
                    resultToReport[i] = sb.append(System.lineSeparator()).append(names[i])
                            .append(SEPARATE).append(salary).toString();
                }
            }
        }

        String result = REPORT_MESSAGE + dateFrom + SEPARATE + dateTo;
        for (String res : resultToReport) {
            result += res;
        }


        return result;
    }
}
