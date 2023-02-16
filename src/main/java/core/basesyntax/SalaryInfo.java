package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SalaryInfo {
    public static final String EMPTY_SPACE = " ";
    public static final String REPORT_STRING = "Report for period ";
    public static final String DASH = " - ";
    public static final int INDEX_OF_DATE = 0;
    public static final int INDEX_OF_NAME = 1;
    public static final int INDEX_HOUR_AMOUNT = 2;
    public static final int INDEX_SALARY_BY_HOUR = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builderOfResult = new StringBuilder();
        int[] arrayOfSalaries = new int[names.length];
        List<String> listOfNames = List.of(names);
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        for (String dataPerDay : data) {
            String[] split = dataPerDay.split(EMPTY_SPACE);
            LocalDate localDateInData = LocalDate.parse(split[INDEX_OF_DATE], formatter);
            if ((localDateInData.isAfter(startDate) || localDateInData.isEqual(startDate))
                    && (localDateInData.isBefore(endDate) || localDateInData.isEqual(endDate))) {
                int salarySum = Integer.parseInt(split[INDEX_HOUR_AMOUNT])
                        * Integer.parseInt(split[INDEX_SALARY_BY_HOUR]);
                arrayOfSalaries[listOfNames.indexOf(split[INDEX_OF_NAME])] += salarySum;
            }
        }
        builderOfResult.append(REPORT_STRING)
                .append(dateFrom).append(DASH).append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < listOfNames.size(); i++) {
            builderOfResult.append(listOfNames.get(i)).append(" - ")
                    .append(arrayOfSalaries[i]).append(System.lineSeparator());
        }
        return builderOfResult.toString().trim();
    }
}
