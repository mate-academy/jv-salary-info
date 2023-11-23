package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final int ONE_DAY = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);
        LocalDate comparisonDate;
        int[] salaryOfNames = new int[names.length];
        final int numberOfWorkers = names.length;
        final int lastIndexOfData = data.length - 1;
        int countsOfValideDays = 0;
        int nameNumber = 0;

        for (int i = 0; i < data.length; i++) {
            String[] oneLineArray = data[i].split(" ");
            comparisonDate = LocalDate.parse(oneLineArray[DATE_INDEX], formatter);
            if (comparisonDate.isAfter(dateStart.minusDays(ONE_DAY))
                    && comparisonDate.isBefore(dateEnd.plusDays(ONE_DAY))) {
                countsOfValideDays++;
                if (oneLineArray[NAME_INDEX].equals(names[nameNumber])) {
                    salaryOfNames[nameNumber] += (Integer.parseInt(oneLineArray[HOURS_INDEX]))
                            * (Integer.parseInt(oneLineArray[SALARY_PER_HOUR_INDEX]));

                }

            }
            if (i == lastIndexOfData && countsOfValideDays != 0) {
                nameNumber++;
                countsOfValideDays = 0;
                i = 0;
            }
            if (nameNumber >= numberOfWorkers) {
                break;
            }
        }
        nameNumber = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateStart.format(formatter)).append(" - ").append(dateEnd.format(formatter))
                .append(System.lineSeparator());
        for (int i = 0; i < salaryOfNames.length; i++) {

            stringBuilder.append(names[nameNumber]).append(" - ")
                    .append(salaryOfNames[nameNumber]);
            stringBuilder.append(System.lineSeparator());
            nameNumber++;
        }
        return stringBuilder.toString().trim();
    }
}
