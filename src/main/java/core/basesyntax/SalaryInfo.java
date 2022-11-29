package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String NAME_REPORT = "Report for period ";
    private static final String STRING_SEP = "\\s";
    private static final String INFO_DELIMITER = " - ";
    private static final byte DATE_INDEX = 0;
    private static final byte NAME_INDEX = 1;
    private static final byte HOURS_INDEX = 2;
    private static final byte SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder reportBuilder = new StringBuilder(NAME_REPORT);
        String[] personalInfo;
        LocalDate personalDate;
        int personalSalary = 0;

        reportBuilder.append(dateFrom)
                .append(INFO_DELIMITER)
                .append(dateTo);

        for (String name : names) {
            for (String datum : data) {
                personalInfo = datum.split(STRING_SEP);
                if (!name.equals(personalInfo[NAME_INDEX])) {
                    continue;
                }
                personalDate = LocalDate.parse(personalInfo[DATE_INDEX], DATE_FORMAT);
                if (!personalDate.isBefore(startDate)
                        && !personalDate.isAfter(endDate)) {
                    personalSalary += Integer.parseInt(personalInfo[HOURS_INDEX])
                            * Integer.parseInt(personalInfo[SALARY_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(INFO_DELIMITER)
                    .append(personalSalary);
            personalSalary = 0;
        }
        return reportBuilder.toString();
    }
}
