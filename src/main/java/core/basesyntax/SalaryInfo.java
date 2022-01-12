package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate parseDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parseDateTo = LocalDate.parse(dateTo, formatter);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int income = 0;
            for (String datum : data) {
                String[] getInfoWithDate = datum.split(" ");
                if (checkDate(name, getInfoWithDate[1], getInfoWithDate[0],
                        parseDateFrom, parseDateTo)) {
                    if (name.equals(getInfoWithDate[1])) {
                        income = income + (Integer.parseInt(getInfoWithDate[2])
                                * Integer.parseInt(getInfoWithDate[3]));
                    }
                }
            }
            if (report.length() == 1) {
                report.append(name).append(" - ").append(income);
            } else {
                report.append("\n").append(name).append(" - ").append(income);
            }
        }
        return report.toString();
    }

    private boolean checkDate(String name, String nameDate, String date,
                              LocalDate dateFrom, LocalDate dateTo) {
        LocalDate parseDate = LocalDate.parse(date, formatter);

        if (parseDate.isAfter(dateFrom) && parseDate.isBefore(dateTo)
                || parseDate.equals(dateTo) && name.equals(nameDate)) {
            return true;
        } else {
            return false;
        }
    }
}
