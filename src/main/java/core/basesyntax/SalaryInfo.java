package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    final byte DATE_INDEX = 0;
    final byte NAME_INDEX = 1;
    final byte WORKING_HOUR_INDEX = 2;
    final byte INCOME_PER_HOUR_INDEX = 3;
    final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            salaryInfo.append(name)
                    .append(" - ")
                    .append(getSalaryAmount(name, data, dateFrom, dateTo))
                    .append(System.lineSeparator());
        }
        salaryInfo.setLength(salaryInfo.length() - System.lineSeparator().length());
        return String.valueOf(salaryInfo);
    }

    private String getSalaryAmount(String name, String[] data, String dateFrom, String dateTo) {
        int salaryAmount = 0;
        for (String dataForProcessing : data) {
            if (dataForProcessing.contains(name)) {
                salaryAmount += payForCheckedDay(dataForProcessing, dateFrom, dateTo);
            }
        }
        return String.valueOf(salaryAmount);
    }

    private int payForCheckedDay(String dataForProcessing, String dateFrom, String dateTo) {
        String[] delimitedString = dataForProcessing.split(" ");
        int payForCheckedDay = 0;
        SimpleDateFormat parseDate = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date startDate = parseDate.parse(dateFrom);
            Date endDate = parseDate.parse(dateTo);
            Date checkedDate = parseDate.parse(delimitedString[DATE_INDEX]);
            if ((checkedDate.equals(startDate) || checkedDate.after(startDate))
                    && (checkedDate.before(endDate) || checkedDate.equals(endDate))) {
                payForCheckedDay = Integer.parseInt(delimitedString[WORKING_HOUR_INDEX])
                        * Integer.parseInt(delimitedString[INCOME_PER_HOUR_INDEX]);
            }
        } catch (ParseException e) {
            throw new RuntimeException("The date format is incorrect. " +
                    "The date must be in dd.MM.yyyy format");
        }
        return payForCheckedDay;
    }
}
