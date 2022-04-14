package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_RATE_INDEX = 3;
    public static final int TOTAL_PARAMETERS = 4;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDate1 = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDate2 = LocalDate.parse(dateTo, dateTimeFormatter);
        int[] salaryTotal = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            String date = data[i].split(" ")[DATE_INDEX];
            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
            String name = data[i].split(" ")[NAME_INDEX];
            String hours = data[i].split(" ")[HOURS_INDEX];
            String salary = data[i].split(" ")[SALARY_RATE_INDEX];
            for (int j = 0; j < names.length; j++) {
                if (names[j].equals(name)
                        && localDate.isAfter(localDate1)
                        && (localDate.isBefore(localDate2) || (localDate.equals(localDate2)))) {
                    salaryTotal[j] += Integer.parseInt(hours) * Integer.parseInt(salary);
                }
            }
        }

        //      Creating resulting StringBuilder
        StringBuilder stringBuilderNames = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            stringBuilderNames.append(names[i]);
            stringBuilderNames.append(" - ");
            stringBuilderNames.append(salaryTotal[i]);
            if (i != names.length - 1) {
                stringBuilderNames.append("\n");
            }
        }

        StringBuilder stringBuilderResult = new StringBuilder("Report for period ");
        stringBuilderResult.append(dateFrom);
        stringBuilderResult.append(" - ");
        stringBuilderResult.append(dateTo);
        stringBuilderResult.append(System.lineSeparator());
        stringBuilderResult.append(stringBuilderNames);

        return stringBuilderResult.toString();
    }
}
