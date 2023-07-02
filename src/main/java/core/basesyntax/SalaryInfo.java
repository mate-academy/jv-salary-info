package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String REPORT_HEADER_FORMAT = "Report for period %s - %s&n";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        int[] salaries = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            String[] recordData = data[i].split(" ");
            LocalDate recordDate = LocalDate.parse(recordData[0], DATE_FORMAT);
            if (recordDate.compareTo(fromDate) >= 0 && recordDate.compareTo(toDate) <= 0) {
                String name = recordData[1];
                int hours = Integer.parseInt(recordData[2]);
                int rate = Integer.parseInt(recordData[3]);

                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(name)) {
                        salaries[j] += hours * rate;
                        break;
                    }
                }
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(REPORT_HEADER_FORMAT).append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salaries[i]);
        }
        return reportBuilder.toString();
    }
}
