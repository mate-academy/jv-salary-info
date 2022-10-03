package core.basesyntax;

import java.util.Date;

public class SalaryInfo {
    private static final int FIRST_ARRAY_ELEMENT = 0;
    private static final int SECOND_ARRAY_ELEMENT = 1;
    private static final int THIRD_ARRAY_ELEMENT = 2;
    private static final int FOURTH_ARRAY_ELEMENT = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        String[] date = dateFrom.split("\\.");
        String[] dateA = dateTo.split("\\.");
        Date dateBefore = new Date(Integer.parseInt(date[THIRD_ARRAY_ELEMENT]),
                Integer.parseInt(date[SECOND_ARRAY_ELEMENT]),
                Integer.parseInt(date[FIRST_ARRAY_ELEMENT]));
        Date dateAfter = new Date(Integer.parseInt(dateA[THIRD_ARRAY_ELEMENT]),
                Integer.parseInt(dateA[SECOND_ARRAY_ELEMENT]),
                Integer.parseInt(dateA[FIRST_ARRAY_ELEMENT]));

        for (String name:names) {
            int sumSalary = 0;
            for (String dataLine:data) {
                String[] dataLineParts = dataLine.split(" ");
                String[] dataLinePartWithDate = dataLineParts[FIRST_ARRAY_ELEMENT].split("\\.");
                Date workDate = new Date(Integer
                        .parseInt(dataLinePartWithDate[THIRD_ARRAY_ELEMENT]),
                        Integer.parseInt(dataLinePartWithDate[SECOND_ARRAY_ELEMENT]),
                        Integer.parseInt(dataLinePartWithDate[FIRST_ARRAY_ELEMENT]));
                if (dataLineParts[SECOND_ARRAY_ELEMENT].equals(name)
                        && dateBefore.getTime() < workDate.getTime()
                        && dateAfter.getTime() >= workDate.getTime()) {
                    sumSalary = sumSalary + Integer.parseInt(dataLineParts[THIRD_ARRAY_ELEMENT])
                            * Integer.parseInt(dataLineParts[FOURTH_ARRAY_ELEMENT]);
                }
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(sumSalary);
        }
        return sb.toString();
    }
}
