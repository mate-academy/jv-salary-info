package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int FIRST_ARRAY_ELEMENT = 0;
    private static final int SECOND_ARRAY_ELEMENT = 1;
    private static final int THIRD_ARRAY_ELEMENT = 2;
    private static final int FOURTH_ARRAY_ELEMENT = 3;
    private static final int ONE_DAY = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        String[] splitDateFrom = dateFrom.split("\\.");
        String[] splitDateTo = dateTo.split("\\.");
        LocalDate dateBefore = LocalDate.of(Integer.parseInt(splitDateFrom[THIRD_ARRAY_ELEMENT]),
                Integer.parseInt(splitDateFrom[SECOND_ARRAY_ELEMENT]),
                Integer.parseInt(splitDateFrom[FIRST_ARRAY_ELEMENT]));
        LocalDate dateAfter = LocalDate.of(Integer.parseInt(splitDateTo[THIRD_ARRAY_ELEMENT]),
                Integer.parseInt(splitDateTo[SECOND_ARRAY_ELEMENT]),
                Integer.parseInt(splitDateTo[FIRST_ARRAY_ELEMENT]));

        for (String name:names) {
            int sumSalary = 0;
            for (String dataLine:data) {
                String[] dataLineParts = dataLine.split(" ");
                String[] dataLinePartWithDate = dataLineParts[FIRST_ARRAY_ELEMENT].split("\\.");
                LocalDate workDate = LocalDate.of(Integer
                        .parseInt(dataLinePartWithDate[THIRD_ARRAY_ELEMENT]),
                        Integer.parseInt(dataLinePartWithDate[SECOND_ARRAY_ELEMENT]),
                        Integer.parseInt(dataLinePartWithDate[FIRST_ARRAY_ELEMENT]));
                if (dataLineParts[SECOND_ARRAY_ELEMENT].equals(name)
                        && workDate.isAfter(dateBefore)
                        && workDate.isBefore(dateAfter.plusDays(ONE_DAY))) {
                    sumSalary = sumSalary + Integer.parseInt(dataLineParts[THIRD_ARRAY_ELEMENT])
                            * Integer.parseInt(dataLineParts[FOURTH_ARRAY_ELEMENT]);
                }
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(sumSalary);
        }
        return sb.toString();
    }
}
