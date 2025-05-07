package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORK_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = DATE_FORMAT.parse(dateFrom);
            toDate = DATE_FORMAT.parse(dateTo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (String itemNames : names) {
            int totalSalary = 0;
            resultBuilder.append(System.lineSeparator()).append(itemNames).append(" - ");
            for (String itemDate : data) {
                String[] arrayElements = itemDate.split(" ");
                Date dateWork = null;
                try {
                    dateWork = DATE_FORMAT.parse(arrayElements[DATE_INDEX]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (arrayElements[NAME_INDEX].equals(itemNames)
                        && dateWork.getTime() <= toDate.getTime()
                        && dateWork.getTime() >= fromDate.getTime()) {
                    int salary = Integer.parseInt(arrayElements[SALARY_INDEX]);
                    int day = Integer.parseInt(arrayElements[HOURS_WORK_INDEX]);
                    totalSalary = totalSalary + salary * day;
                }
            }
            resultBuilder.append(totalSalary);
        }
        return resultBuilder.toString();
    }
}
