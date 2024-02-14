package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_COLUMN_INDEX = 0;
    private static final int NAME_COLUMN_INDEX = 1;
    private static final int HOURS_COLUMN_INDEX = 2;
    private static final int RATE_COLUMN_INDEX = 3;
    private static final String COLUMNS_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = salaryCounter(names, data, dateFrom, dateTo);
        return resultExecutor(names, salary, dateFrom, dateTo);
    }

    public int[] salaryCounter(String[] names, String[] data, String dateFrom, String dateTo) {
        //Hi, in this solution I used the Arrays.asList() method,
        //I know that this is a collection method, and we are not allowed to use collections
        // in this task, but I don’t know how to solve this problem without using collections
        //or less than three loops (and I think the method Arrays.asList().indexOf()
        //actually uses a loop too). If you don't like this solution,
        //you can look at the previous commit (called split method),
        //it does not have collection methods,
        //but it uses 3 loops (2 for counting and 1 for displaying information)
        int[] salary = new int[names.length];
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        for (int i = 0; i < data.length; i++) {
            String[] columns = data[i].split(COLUMNS_SEPARATOR);
            LocalDate localDateCurrent
                    = LocalDate.parse(columns[DATA_COLUMN_INDEX], DATE_FORMATTER);
            if (dateChecker(localDateFrom, localDateTo, localDateCurrent)) {
                int hours = Integer.parseInt(columns[HOURS_COLUMN_INDEX]);
                int rate = Integer.parseInt(columns[RATE_COLUMN_INDEX]);
                int index = Arrays.asList(names).indexOf(columns[NAME_COLUMN_INDEX]);
                if (index != -1) {
                    salary[index] += hours * rate;
                }
            }
        }
        return salary;
    }

    public boolean dateChecker(LocalDate localDateFrom, LocalDate localDateTo,
                               LocalDate localDateCurrent) {
        //I think this way of returning is better
        //than creating two boolean variables and returning them
        return ((localDateFrom.isBefore(localDateCurrent)
                || localDateFrom.isEqual(localDateCurrent))
                && (localDateTo.isAfter(localDateCurrent)
                || localDateTo.isEqual(localDateCurrent)));
    }

    public String resultExecutor(String[] names, int[] salary, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder(String.format("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator()));
        for (int i = 0; i < names.length; i++) {
            builder.append(String.format(names[i] + " - " + salary[i]) + System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
