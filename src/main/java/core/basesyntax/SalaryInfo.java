package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int ARRAY_INDEX_FOR_NAME = 1;
    private static final int ARRAY_INDEX_FOR_WORKING_HOURS = 2;
    private static final int ARRAY_INDEX_FOR_INCOME_PER_HOUR = 3;
    private static final int BEGINNING_INDEX_FOR_DAY = 0;
    private static final int ENDING_INDEX_FOR_DAY = 2;
    private static final int BEGINNING_INDEX_FOR_MONTH = 3;
    private static final int ENDING_INDEX_FOR_MONTH = 5;
    private static final int BEGINNING_INDEX_FOR_YEAR = 6;
    private static final int ENDING_INDEX_FOR_YEAR = 10;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary = 0;
        StringBuilder salaryInfo = new StringBuilder();
        try {
            for (String name : names) {
                if (name != null) {
                    for (String datum : data) {
                        String[] dataEntry = datum.split(" ");
                        if (name.equals(dataEntry[ARRAY_INDEX_FOR_NAME])
                                && isDatePass(datum, dateFrom, dateTo)) {
                            salary += Integer.parseInt(dataEntry[ARRAY_INDEX_FOR_WORKING_HOURS])
                                    * Integer.parseInt(dataEntry[ARRAY_INDEX_FOR_INCOME_PER_HOUR]);
                        }
                    }
                    StringBuilder stringBuilder = new StringBuilder(name);
                    if (name.equals(names[names.length - 1])) {
                        salaryInfo.append(stringBuilder.append(" - ").append(salary));
                    } else {
                        salaryInfo.append(stringBuilder.append(" - ").append(salary))
                                .append(System.lineSeparator());
                    }
                    salary = 0;
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: incorrect data entered: " + e.getMessage());
        }
        return "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator()
                + salaryInfo;
    }

    public boolean isDatePass(String data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.of(getYear(dateFrom), getMonth(dateFrom), getDay(dateFrom));
        LocalDate to = LocalDate.of(getYear(dateTo), getMonth(dateTo), getDay(dateTo));
        LocalDate date = LocalDate.of(Integer.parseInt(data.substring(BEGINNING_INDEX_FOR_YEAR,
                ENDING_INDEX_FOR_YEAR)), getMonth(data), getDay(data));

        return from.compareTo(date) <= 0 && to.compareTo(date) >= 0;
    }

    public int getDay(String dateFrom) {
        return Integer.parseInt(dateFrom.substring(BEGINNING_INDEX_FOR_DAY, ENDING_INDEX_FOR_DAY));
    }

    public int getMonth(String dateFrom) {
        return Integer.parseInt(dateFrom.substring(BEGINNING_INDEX_FOR_MONTH,
                ENDING_INDEX_FOR_MONTH));
    }

    public int getYear(String dateFrom) {
        return Integer.parseInt(dateFrom.substring(BEGINNING_INDEX_FOR_YEAR));
    }
}

