package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final byte DATE_LENGTH = 3;
    private static final byte YEAR_ARRAY_INDEX = 0;
    private static final byte WORK_HOURS_ARRAY_INDEX = 2;
    private static final byte SALARY_PERHOUR_ARRAY_INDEX = 3;
    private static final byte EQUAL_YEAR_CONDITION = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate date1 = convertStringToDate(dateFrom);
        LocalDate date2 = convertStringToDate(dateTo);

        for (int i = 0; i < names.length; i++) {
            int totalSalary = 0;
            for (int k = 0; k < data.length; k++) {
                if (data[k].contains(names[i])) {
                    String[] separateData = data[k].split(" ");
                    LocalDate date = convertStringToDate(separateData[YEAR_ARRAY_INDEX]);
                    if (date.compareTo(date1) >= EQUAL_YEAR_CONDITION
                            && date.compareTo(date2) <= EQUAL_YEAR_CONDITION) {
                        totalSalary += Integer.parseInt(separateData[WORK_HOURS_ARRAY_INDEX])
                                * Integer.parseInt(separateData[SALARY_PERHOUR_ARRAY_INDEX]);
                    }
                }
            }
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(totalSalary);
        }
        return result.toString();

    }

    private static final LocalDate convertStringToDate(String date) {
        String[] dateSeparateArray = date.split("\\.");
        int[] dateNumber = new int[dateSeparateArray.length];
        for (int i = 0; i < DATE_LENGTH; i++) {
            dateNumber[i] = Integer.parseInt(dateSeparateArray[i]);
        }
        return LocalDate.of(dateNumber[2], dateNumber[1], dateNumber[0]);
    }
}
