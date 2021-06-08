package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String DATA_SEPARATOR = " ";
    public static final int DATA_SEPARATOR_LENGTH = DATA_SEPARATOR.length();

    public String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromWithFormatter = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateToWithFormatter = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder salaryInfo = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            int earnedPerName = 0;
            for (String element : data) {
                if (name.equals(element.substring(element.indexOf(DATA_SEPARATOR, 0)
                                + DATA_SEPARATOR_LENGTH,
                        element.indexOf(DATA_SEPARATOR, element.indexOf(DATA_SEPARATOR, 0)
                                + DATA_SEPARATOR_LENGTH)))
                        && isDateInPeriod(element.substring(0,element.indexOf(DATA_SEPARATOR, 0)),
                        dateTimeFormatter,dateFromWithFormatter, dateToWithFormatter)) {
                    earnedPerName += Integer.parseInt(element.substring(element
                            .indexOf(DATA_SEPARATOR,
                                    element.indexOf(DATA_SEPARATOR, 0)
                                            + DATA_SEPARATOR_LENGTH) + DATA_SEPARATOR_LENGTH,
                            element.lastIndexOf(DATA_SEPARATOR)))
                            * Integer.parseInt(element.substring(element.lastIndexOf(DATA_SEPARATOR)
                            + DATA_SEPARATOR_LENGTH, element.length()));
                    System.out.println(earnedPerName);
                }
            }
            salaryInfo = salaryInfo.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(earnedPerName);
        }
        return salaryInfo.toString();
    }

    private boolean isDateInPeriod(String date,
                                   DateTimeFormatter dateTimeFormatter,
                                   LocalDate dateFromWithFormatter,
                                   LocalDate dateToWithFormatter) {
        return (LocalDate.parse(date, dateTimeFormatter).isAfter(dateFromWithFormatter)
                || LocalDate.parse(date, dateTimeFormatter).equals(dateToWithFormatter))
                && (LocalDate.parse(date, dateTimeFormatter).isBefore(dateToWithFormatter)
                || LocalDate.parse(date, dateTimeFormatter).equals(dateToWithFormatter));
    }
}
