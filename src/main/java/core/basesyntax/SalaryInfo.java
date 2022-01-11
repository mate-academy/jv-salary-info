package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int income;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            income = 0;
            for (String datum : data) {
                String[] getInfoWithDate = datum.split(" ");
                if (checkDate(name, getInfoWithDate[1], getInfoWithDate[0], dateFrom, dateTo)) {
                    if (name.equals(getInfoWithDate[1])) {
                        income = income + (Integer.parseInt(getInfoWithDate[2])
                                * Integer.parseInt(getInfoWithDate[3]));
                    }
                }
            }
            if (stringBuilder.length() == 1) {
                stringBuilder.append(name).append(" - ").append(income);
            } else {
                stringBuilder.append("\n").append(name).append(" - ").append(income);
            }
        }
        return stringBuilder.toString();
    }

    protected boolean checkDate(String name, String nameDate, String date,
                                String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate parseDate = LocalDate.parse(date, formatter);
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parseDateTo = LocalDate.parse(dateTo, formatter);

        if (parseDate.isAfter(parseDateFrom) && parseDate.isBefore(parseDateTo)
                || parseDate.equals(parseDateTo) && name.equals(nameDate)) {
            return true;
        } else {
            return false;
        }
    }
}
