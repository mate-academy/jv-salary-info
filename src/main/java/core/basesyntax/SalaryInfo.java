package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NUMBER_OF_ELEMENT_BY_DATE = 0;
    private static final int NUMBER_OF_ELEMENT_BY_EMPLOYEE = 1;
    private static final int NUMBER_OF_ELEMENT_BY_HOUSE_OF_WORK = 2;
    private static final int NUMBER_OF_ELEMENT_BY_SALARY_OF_HOUR = 3;
    private static final String REPORT_FOR_PERIOD = "Report for period ";
    private static final String SPACES_AND_DASH = " - ";
    private static final String SPACE = " ";

    public String getSalaryInfo(String[] names,
                                       String[] data,
                                       String dateFrom,
                                       String dateTo) {

        LocalDate fromDate = convertDateToRightFormat(dateFrom);
        LocalDate toDate = convertDateToRightFormat(dateTo);
        StringBuilder sb = new StringBuilder().append(REPORT_FOR_PERIOD)
                .append(dateFrom)
                .append(SPACES_AND_DASH)
                .append(dateTo);

        String[] logicalInfo;
        for (String employee :
                names) {
            int salary = 0;
            for (String element :
                    data) {
                logicalInfo = element.split(SPACE);
                LocalDate date = convertDateToRightFormat(logicalInfo[NUMBER_OF_ELEMENT_BY_DATE]);
                int hoursOfWork = Integer.parseInt(
                        logicalInfo[NUMBER_OF_ELEMENT_BY_HOUSE_OF_WORK]);
                int salaryByHour = Integer.parseInt(
                        logicalInfo[NUMBER_OF_ELEMENT_BY_SALARY_OF_HOUR]);
                if (fromDate.compareTo(date) <= 0
                        && toDate.compareTo(date) >= 0
                        && logicalInfo[NUMBER_OF_ELEMENT_BY_EMPLOYEE].equals(employee)) {
                    salary += hoursOfWork * salaryByHour;
                }
            }
            sb.append(System.lineSeparator())
                    .append(employee)
                    .append(SPACES_AND_DASH)
                    .append(salary);
        }

        return sb.toString();
    }

    public LocalDate convertDateToRightFormat(String date) {
        return LocalDate.parse(date, DATE_FORMAT);
    }
}
