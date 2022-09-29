package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INFO_FORMAT_INDEX_OF_NAME = 0;
    private static final int INFO_FORMAT_INDEX_OF_WORKING_HOURS = 2;
    private static final int INFO_FORMAT_INDEX_OF_PAYMENT = 3;
    private static final String INFO_FORMAT_DEFAULT_SPLITTER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period " + dateFrom + " - " + dateTo);

        LocalDate beginDate;
        LocalDate endDate;
        beginDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        endDate = LocalDate.parse(dateTo, DATE_FORMAT);

        for (String name : names) {
            sb.append(System.lineSeparator());
            int salary = 0;
            for (String s : data) {
                String[] info = s.split(INFO_FORMAT_DEFAULT_SPLITTER);

                LocalDate workDay;
                workDay = LocalDate.parse(info[INFO_FORMAT_INDEX_OF_NAME], DATE_FORMAT);

                if (info[1].equals(name)
                        && (workDay.compareTo(beginDate) >= 0 && workDay.compareTo(endDate) <= 0)) {
                    int hours = Integer.parseInt(info[INFO_FORMAT_INDEX_OF_WORKING_HOURS]);
                    int paymentPerHour = Integer.parseInt(info[INFO_FORMAT_INDEX_OF_PAYMENT]);
                    salary += hours * paymentPerHour;
                }
            }
            sb.append(name + " - " + salary);
        }
        return sb.toString();
    }
}
