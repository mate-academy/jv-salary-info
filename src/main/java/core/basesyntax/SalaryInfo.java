package core.basesyntax;

import java.util.Date;

public class SalaryInfo {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date dateFromParsed = DateParser.parseDate(dateFrom);
        Date dateToParsed = DateParser.parseDate(dateTo);
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                InputStat inputStat = StatParser.parse(datum);
                if (inputStat.getName().equals(name)
                        && inputStat.getDate().compareTo(dateFromParsed) >= 0
                        && inputStat.getDate().compareTo(dateToParsed) <= 0) {
                    salary += inputStat.getHours() * inputStat.getRate();
                }
            }
            stringBuilder.append(LINE_SEPARATOR + name + " - " + salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + stringBuilder.toString();
    }
}
