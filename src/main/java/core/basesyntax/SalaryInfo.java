package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromWithFormatter = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateToWithFormatter = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder salaryInfo = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        String dataSeparator = " ";
        StringBuilder date = new StringBuilder();
        StringBuilder name = new StringBuilder();
        StringBuilder hour = new StringBuilder();
        StringBuilder salaryPerHour = new StringBuilder();
        int earnedPerName = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                date.delete(0, date.length());
                date = date.append(data[j].substring(0, data[j].indexOf(dataSeparator, 0)));
                name.delete(0, name.length());
                name = name.append(data[j].substring(data[j].indexOf(dataSeparator, 0) + 1,
                        data[j].indexOf(dataSeparator, data[j].indexOf(dataSeparator, 0) + 1)));
                if (names[i].equals(name.toString())
                        && isDateInPeriod(date, dateTimeFormatter,
                        dateFromWithFormatter, dateToWithFormatter)) {
                    hour.delete(0, hour.length());
                    hour = hour.append(data[j].substring(
                            data[j].indexOf(dataSeparator,
                                    data[j].indexOf(dataSeparator, 0) + 1) + 1,
                            data[j].lastIndexOf(dataSeparator)));
                    salaryPerHour.delete(0, salaryPerHour.length());
                    salaryPerHour = salaryPerHour.append(
                            data[j].substring(data[j].lastIndexOf(dataSeparator) + 1,
                                    data[j].length()));
                    earnedPerName += Integer.parseInt(hour.toString())
                            * Integer.parseInt(salaryPerHour.toString());
                }
            }
            salaryInfo = salaryInfo.append("\n")
                    .append(names[i])
                    .append(" - ")
                    .append(earnedPerName);
            earnedPerName = 0;
        }
        return salaryInfo.toString();
    }

    private boolean isDateInPeriod(StringBuilder date,
                                   DateTimeFormatter dateTimeFormatter,
                                   LocalDate dateFromWithFormatter,
                                   LocalDate dateToWithFormatter) {
        return (LocalDate.parse(date, dateTimeFormatter).isAfter(dateFromWithFormatter)
                || LocalDate.parse(date, dateTimeFormatter).equals(dateToWithFormatter))
                && (LocalDate.parse(date, dateTimeFormatter).isBefore(dateToWithFormatter)
                || LocalDate.parse(date, dateTimeFormatter).equals(dateToWithFormatter));
    }
}
