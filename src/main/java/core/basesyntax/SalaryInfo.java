package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] checkLine;
        StringBuilder finalReport = new StringBuilder();
        finalReport.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append("\r");

        for (String name: names) {
            finalReport.append("\n").append(name).append(" - ");
            int salary = 0;

            for (String dateString : data) {
                checkLine = splitString(dateString, " ");
                if (name.equals(checkLine[1])) {
                    if (isDateInRange(dateFrom, dateTo, checkLine[0])) {
                        salary = salary
                                + (Integer.parseInt(checkLine[2]) * Integer.parseInt(checkLine[3]));
                    }
                }
            }

            if (!name.equals(names[names.length - 1])) {
                finalReport.append(salary).append("\r");
            } else {
                finalReport.append(salary);
            }

        }

        return finalReport.toString();
    }

    public boolean isDateInRange(String dateRangeBegin, String dateRangeEnd, String dataSalary) {
        try {
            Date date1 = format.parse(dateRangeBegin);
            Date date2 = format.parse(dateRangeEnd);
            Date date3 = format.parse(dataSalary);

            if (date1.before(date3) && date2.after(date3)
                    || date1.equals(date3) || date2.equals(date3)) {
                return true;
            }
        } catch (ParseException e) {
            System.out.println("Format date is not right");;
        }

        return false;
    }

    public String[] splitString(String textForSplit, String splitSymbol) {
        return textForSplit.split(splitSymbol);
    }
}
