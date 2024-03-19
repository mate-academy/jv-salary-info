package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final String START_TEXT = "Report for period";
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder(START_TEXT)
                .append(" ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            salaryInfo
                    .append("\n")
                    .append(name)
                    .append(" - ")
                    .append(getSalaryByName(name, data, dateFrom, dateTo));
        }
        return salaryInfo.toString();
    }

    private int getSalaryByName(String name, String[] data, String dateFrom, String dateTo) {
        int sumSalary = 0;

        for (String itemData : data) {
            String[] employerData = itemData.split(" ");
            if (isDateInRange(employerData[0], dateFrom, dateTo) && name.equals(employerData[1])) {
                sumSalary += Integer.parseInt(employerData[2]) * Integer.parseInt(employerData[3]);
            }
        }
        return sumSalary;
    }

    private Date getDateFromString(String date) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_FORMAT);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isDateInRange(String date, String dateFrom, String dateTo) {
        return (getDateFromString(date).after(getDateFromString(dateFrom))
                || getDateFromString(date).equals(getDateFromString(dateFrom)))
                && (getDateFromString(date).before(getDateFromString(dateTo))
                || getDateFromString(date).equals(getDateFromString(dateTo)));
    }
}
