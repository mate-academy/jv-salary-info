package core.basesyntax;

import java.time.LocalDate;
import java.util.Arrays;

public class SalaryInfo {
    private static final int INDEX_OF_DAY = 0;
    private static final int INDEX_OF_MONTH = 1;
    private static final int INDEX_OF_YEAR = 2;
    private static final int INDEX_OF_DATA = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORKED_HOURES = 2;
    private static final int INDEX_OF_SALARY = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        int[] salary = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            String[] generalInfo = data[i].split(" ");
            String[] dateMain = generalInfo[INDEX_OF_DATA].split("\\.");
            String[] dateStarted = dateFrom.split("\\.");
            String[] dateFinished = dateTo.split("\\.");
            LocalDate mainDate = LocalDate.of(Integer.parseInt(dateMain[INDEX_OF_YEAR]),
                    Integer.parseInt(dateMain[INDEX_OF_MONTH]),
                    Integer.parseInt(dateMain[INDEX_OF_DAY]));
            LocalDate startedDate = LocalDate.of(Integer.parseInt(dateStarted[INDEX_OF_YEAR]),
                    Integer.parseInt(dateStarted[INDEX_OF_MONTH]),
                    Integer.parseInt(dateStarted[INDEX_OF_DAY]));
            LocalDate finishedDate = LocalDate.of(Integer.parseInt(dateFinished[INDEX_OF_YEAR]),
                    Integer.parseInt(dateFinished[INDEX_OF_MONTH]),
                    Integer.parseInt(dateFinished[INDEX_OF_DAY]));
            for (String name : names) {
                if (name.equals(generalInfo[INDEX_OF_NAME]) && ((mainDate.isAfter(startedDate)
                        && mainDate.isBefore(finishedDate)) || mainDate.equals(startedDate)
                        || mainDate.equals(finishedDate))) {
                    salary[Arrays.asList(names).indexOf(name)]
                            += Integer.parseInt(generalInfo[INDEX_OF_WORKED_HOURES])
                            * Integer.parseInt(generalInfo[INDEX_OF_SALARY]);
                }
                if (i == data.length - 1) {
                    stringBuilder.append(System.lineSeparator()).append(name).append(" - ")
                            .append(salary[Arrays.asList(names).indexOf(name)]);
                }
            }
        }
        return stringBuilder.toString();
    }
}
