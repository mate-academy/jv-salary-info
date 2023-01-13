package core.basesyntax;

import java.time.LocalDate;
import java.util.Arrays;

public class SalaryInfo {
    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        int[] salary = new int[names.length];
        for (String i : data) {
            String[] generalInfo = i.split(" ");
            String[] dateMain = generalInfo[0].split("\\.");
            String[] dateStarted = dateFrom.split("\\.");
            String[] dateFinished = dateTo.split("\\.");
            LocalDate mainDate = LocalDate.of(Integer.parseInt(dateMain[2]),
                    Integer.parseInt(dateMain[1]), Integer.parseInt(dateMain[0]));
            LocalDate startedDate = LocalDate.of(Integer.parseInt(dateStarted[2]),
                    Integer.parseInt(dateStarted[1]), Integer.parseInt(dateStarted[0]));
            LocalDate finishedDate = LocalDate.of(Integer.parseInt(dateFinished[2]),
                    Integer.parseInt(dateFinished[1]), Integer.parseInt(dateFinished[0]));
            for (String name : names) {
                if (name.equals(generalInfo[1]) && ((mainDate.isAfter(startedDate)
                        && mainDate.isBefore(finishedDate)) || mainDate.equals(startedDate)
                        || mainDate.equals(finishedDate))) {
                    salary[Arrays.asList(names).indexOf(name)]
                            += Integer.parseInt(generalInfo[2]) * Integer.parseInt(generalInfo[3]);
                }
            }

        }
        for (String i : names) {
            stringBuilder.append(System.lineSeparator()).append(i).append(" - ")
                    .append(salary[Arrays.asList(names).indexOf(i)]);
        }
        return stringBuilder.toString();
    }
}
