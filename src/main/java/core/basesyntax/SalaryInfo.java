package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String LINE = " - ";
    private static final String REPORT_HEADER_FORMAT = "Report for period %s - %s%n";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int RECORD_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        int[] salaries = new int[names.length];
        for (String record : data) {
            String[] recordData = record.split(" ");
            LocalDate recordDate = LocalDate.parse(recordData[RECORD_DATE_INDEX], DATE_FORMAT);
            if (!recordDate.isBefore(fromDate) && !recordDate.isAfter(toDate)) {
                String name = recordData[NAME_INDEX];
                int hours = Integer.parseInt(recordData[HOURS_INDEX]);
                int rate = Integer.parseInt(recordData[RATE_INDEX]);

                int index = getIndex(names, name);
                if (index != -1) {
                    salaries[index] += hours * rate;
                }
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(String.format(REPORT_HEADER_FORMAT, dateFrom, dateTo));
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(names[i]).append(LINE).append(salaries[i]).append(LINE_SEPARATOR);
        }
        return reportBuilder.toString().trim();
    }

    private int getIndex(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
