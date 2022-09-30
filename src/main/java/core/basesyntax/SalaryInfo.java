package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final String SPLIT_FOR_DATA = " ";
    private static final String SPLIT_FOR_CONVERT_DATE = "\\.";
    private static final int DATE = 0;
    private static final int BOUND_OF_DAYS = 1;
    private static final int NAME = 1;
    private static final int HOURS_OF_WORK = 2;
    private static final int PAYMENT_PER_HOUR = 3;
    private static final String DATE_FORMAT_SEPARATOR = "-";
    private int[] salary;
    private StringBuilder report;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        salaryCalculate(names, data, dateFrom, dateTo);
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
            report.append(names[i]).append(" - ").append(salary[i]).append(System.lineSeparator());
        }
    }

    public LocalDate getDate(String date) {
        String[] dateFormatting = date.split(SPLIT_FOR_CONVERT_DATE);
        StringBuilder rightDateFormat = new StringBuilder(dateFormatting[2]);
        rightDateFormat.append(DATE_FORMAT_SEPARATOR).append(dateFormatting[1]);
        rightDateFormat.append(DATE_FORMAT_SEPARATOR).append(dateFormatting[0]);
        LocalDate localDate = LocalDate.parse(rightDateFormat.toString());
        return localDate;
    }

    private int getIntValue(String data) {
        return Integer.parseInt(data);
    }
}
