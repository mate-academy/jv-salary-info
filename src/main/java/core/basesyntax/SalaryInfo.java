package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final String DATA_RECORD_SPLIT_REGEX = " ";
    private static final int SALARY_OFFSET = 0;
    private static final int DATE_COLUMN = 0;
    private static final int NAME_COLUMN = 1;
    private static final int WORKING_HOURS_COLUMN = 2;
    private static final int SALARY_RATE_COLUMN = 3;
    private int[] totalSalary = new int[] {};
    private String dataPattern = "dd.MM.yyyy";
    private DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern(dataPattern);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        totalSalary = new int[names.length];
        for (int i = 0; i < totalSalary.length; i++) {
            totalSalary[i] = SALARY_OFFSET;
        }
        for (String dataRecord : data) {
            String[] splitRecord = dataRecord.split(DATA_RECORD_SPLIT_REGEX);
            String requestDate = splitRecord[DATE_COLUMN];
            if (checkIfDateIsInRange(dateFrom, dateTo, requestDate)) {
                int salary = Integer.parseInt(splitRecord[WORKING_HOURS_COLUMN])
                        * Integer.parseInt(splitRecord[SALARY_RATE_COLUMN]);
                totalSalary[indexOf(names,splitRecord[NAME_COLUMN])] += salary;
            }
        }
        return getReportForNames(dateFrom, dateTo, names);
    }

    private String getReportForNames(String dateFrom, String dateTo, String[] names) {
        StringBuilder report = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ")
                    .append(totalSalary[i])
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }

    private boolean checkIfDateIsInRange(String dateFrom, String dateTo, String requestDate) {
        LocalDate dateToCheck = LocalDate.parse(requestDate, simpleDateFormat);
        LocalDate startDate = LocalDate.parse(dateFrom, simpleDateFormat);
        LocalDate endDate = LocalDate.parse(dateTo,simpleDateFormat);
        return (dateToCheck.compareTo(startDate) >= 0) && (dateToCheck.compareTo(endDate) <= 0);
    }

    private int indexOf(Object[] strArray, Object element) {
        return Arrays.asList(strArray).indexOf(element);
    }
}
