package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final String INFO_SEPARATOR = " ";
    private static final String EXPECTED_SEPARATOR = " - ";

    private final DateParser dateParser = new DateParser();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = dateParser.date(dateFrom);
        LocalDate stopDate = dateParser.date(dateTo);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            int oneDaySalary = 0;
            for (String singleData : data) {
                String[] entries = singleData.split(INFO_SEPARATOR);
                LocalDate currentDate = dateParser.date(entries[DATE_INDEX]);
                if (names[i].equals(entries[NAME_INDEX])
                        && currentDate.compareTo(startDate) >= 0
                        && currentDate.compareTo(stopDate) <= 0) {
                    oneDaySalary += Integer.parseInt(entries[HOURS_INDEX])
                            * Integer.parseInt(entries[SALARY_INDEX]);
                }
            }
            sb.append(names[i]).append(EXPECTED_SEPARATOR).append(oneDaySalary);
            if (i < names.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return "Report for period " + dateFrom + EXPECTED_SEPARATOR + dateTo
                + System.lineSeparator() + sb;
    }
}
