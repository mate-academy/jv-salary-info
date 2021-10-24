package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws ParseException {
        StringBuilder result = new StringBuilder().append("Report for period ")
                               .append(dateFrom).append(" - ").append(dateTo);
        Date dateFromInDate = DATE_FORMATTER.parse(dateFrom);
        Date dateToInDate = DATE_FORMATTER.parse(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String stringOfData : data) {
                Date salaryDate = DATE_FORMATTER.parse(stringOfData);
                if (stringOfData.contains(name)
                        && salaryDate.compareTo(dateFromInDate) >= 0
                        && salaryDate.compareTo(dateToInDate) <= 0) {
                    Integer hoursPerDay = Integer.parseInt(stringOfData.substring(
                            stringOfData.lastIndexOf(name) + name.length() + 1,
                            stringOfData.lastIndexOf(" ")));
                    Integer incomePerHour =
                            Integer.parseInt(stringOfData.substring(stringOfData
                                    .lastIndexOf(" ") + 1));
                    totalSalary += hoursPerDay * incomePerHour;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return result.toString();
    }
}
