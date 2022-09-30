package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;

public class SalaryInfo {
    private static final String SPLIT_FOR_DATA = " ";
    private static final String SPLIT_FOR_CONVERT_DATE = "\\.";
    private static final int DATE = 0;
    private static final int BOUND_OF_DAYS = 1;
    private static final int NAME = 1;
    private static final int HOURS_OF_WORK = 2;
    private static final int PAYMENT_PER_HOUR = 3;
    private int[] salary;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        salaryCalculate(names, data, dateFrom, dateTo);
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salary[i]).append(System.lineSeparator());
        }
        return report.toString().trim();
    }

    private void salaryCalculate(String[] names, String[] data, String dateFrom, String dateTo) {
        salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] parseData = data[j].split(SPLIT_FOR_DATA);
                LocalDate dayOfWork = getDate(parseData[DATE]);
                if (dayOfWork.isAfter(getDate(dateFrom).minusDays(BOUND_OF_DAYS))
                        && dayOfWork.isBefore(getDate(dateTo).plusDays(BOUND_OF_DAYS))
                        && parseData[NAME].equals(names[i])) {
                    salary[i] += getIntValue(parseData[HOURS_OF_WORK])
                            * getIntValue(parseData[PAYMENT_PER_HOUR]);
                }
            }
        }
    }

    public LocalDate getDate(String date) {
        String[] dateFormatting = date.split(SPLIT_FOR_CONVERT_DATE);
        StringBuilder rightDateFormat = new StringBuilder(dateFormatting[2]);
        rightDateFormat.append("-").append(dateFormatting[1]);
        rightDateFormat.append("-").append(dateFormatting[0]);
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(rightDateFormat.toString());
        } catch (DateTimeException e) {
            throw new RuntimeException("Can`t parse date", e);
        }
        return localDate;
    }

    private int getIntValue(String data) {
        return Integer.parseInt(data);
    }
}
