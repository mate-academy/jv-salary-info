package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMAT_DATE = "dd.MM.yyyy";
    private static final int FIRST_MEMBER = 0;
    private static final int SECOND_MEMBER = 1;
    private static final int THIRD_MEMBER = 2;
    private static final int FOURTH_MEMBER = 3;
    private static final int INITIAL_VALUE = 0;
    private static final String DELIMETER = " ";
    private static final String HYPHEN = " - ";
    private static final String SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        LocalDate firstDate = LocalDate.parse(dateFrom, formatter);
        LocalDate lastDate = LocalDate.parse(dateTo, formatter);
        StringBuilder finalResult = new StringBuilder("Report for period "
                + dateFrom + HYPHEN + dateTo);

        for (int i = 0; i < names.length; i++) {
            int salaryInfo = INITIAL_VALUE;
            for (String day : data) {
                String[] dataString = day.split(DELIMETER);
                LocalDate currentDate = LocalDate.parse(dataString[FIRST_MEMBER], formatter);
                if ((currentDate.compareTo(firstDate) >= 0)
                        && (currentDate.compareTo(lastDate) <= 0)) {
                    if (dataString[SECOND_MEMBER].equals(names[i])) {
                        salaryInfo += (Integer.parseInt(dataString[THIRD_MEMBER])
                                * Integer.parseInt(dataString[FOURTH_MEMBER]));
                    }
                }
            }
            finalResult.append(SEPARATOR).append(names[i]).append(HYPHEN).append(salaryInfo);
        }
        return finalResult.toString();
    }
}
