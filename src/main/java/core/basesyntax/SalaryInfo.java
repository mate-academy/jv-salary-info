package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INFO_FORMAT_INDEX_OF_NAME = 0;
    private static final int INFO_FORMAT_INDEX_OF_WORKING_HOURS = 2;
    private static final int INFO_FORMAT_INDEX_OF_PAYMENT = 3;
    private static final int INFO_FORMAT_INDEX_OF_ARRAY_NAME = 1;
    private static final String INFO_FORMAT_DEFAULT_SPLITTER = " ";
    private static final String INFO_FORMAT_DATE_SPLITTER = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period " + dateFrom + INFO_FORMAT_DATE_SPLITTER + dateTo);

        for (String name : names) {
            stringBuilder.append(findInfoAboutName(name, data, dateFrom, dateTo));
        }
        return stringBuilder.toString();
    }

    public String findInfoAboutName(String name, String[] data, String dateFrom, String dateTo) {
        int salary = 0;
        for (String s : data) {
            salary += getSalaryForName(name, s, dateFrom, dateTo);
        }
        return System.lineSeparator() + name + INFO_FORMAT_DATE_SPLITTER + salary;
    }

    private int getSalaryForName(String name, String dataLine, String dateFrom, String dateTo) {
        LocalDate beginDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);
        int salary = 0;

        String[] line = dataLine.split(INFO_FORMAT_DEFAULT_SPLITTER);

        LocalDate workDay;
        workDay = LocalDate.parse(line[INFO_FORMAT_INDEX_OF_NAME], DATE_FORMAT);

        if (line[INFO_FORMAT_INDEX_OF_ARRAY_NAME].equals(name)
                && (workDay.compareTo(beginDate) >= 0 && workDay.compareTo(endDate) <= 0)) {
            salary = getSalaryFromLine(line);
        }

        return salary;
    }

    private int getSalaryFromLine(String[] info) {
        int hours = Integer.parseInt(info[INFO_FORMAT_INDEX_OF_WORKING_HOURS]);
        int paymentPerHour = Integer.parseInt(info[INFO_FORMAT_INDEX_OF_PAYMENT]);
        return hours * paymentPerHour;
    }
}
