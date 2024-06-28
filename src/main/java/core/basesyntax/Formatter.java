package core.basesyntax;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Formatter {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int SALARY_PER_HOUR_INDEX = 2;
    public static final int AMOUNT_INDEX = 3;
    public static final int YEAR_INDEX = 2;
    public static final int MONTH_INDEX = 1;
    public static final int DAY_INDEX = 0;

    public LocalDate stringToLocalDate(String date) {
        List<Integer> dateList = Arrays.stream(date.split("\\.")).map(Integer::parseInt).toList();

        return LocalDate.of(
                dateList.get(YEAR_INDEX),
                dateList.get(MONTH_INDEX),
                dateList.get(DAY_INDEX));
    }

    public DataSalary stringToDataSalary(String value) {
        String[] valueArray = value.split(" ");
        return new DataSalary(stringToLocalDate(
                valueArray[DATE_INDEX]),
                valueArray[NAME_INDEX],
                Integer.parseInt(valueArray[SALARY_PER_HOUR_INDEX]),
                Integer.parseInt(valueArray[AMOUNT_INDEX]));
    }
}
