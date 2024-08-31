package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final int FIRST_SALARY_SPLIT_INDEX = 2;
    private static final int SECOND_SALARY_SPLIT_INDEX = 3;
    private static final int NAME_SPLIT_INDEX = 1;
    private static final int DATE_SPLIT_INDEX = 0;
    private static final String WHITESPACE = " ";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period " + dateFrom + " - " + dateTo);
        int[] salary = new int[names.length];
        try {
            Date firstDate = SIMPLE_DATE_FORMAT.parse(dateFrom);
            Date lastDate = SIMPLE_DATE_FORMAT.parse(dateTo);
            for (int i = 0; i < names.length; i++) {
                for (String datum : data) {
                    Date currentDate = splitDate(datum);

                    if (currentDate.after(lastDate)) {
                        continue;
                    }

                    if ((currentDate.after(firstDate) || currentDate.equals(firstDate))
                            && (currentDate.before(lastDate) || currentDate.equals(lastDate))
                            && names[i].equals(splitName(datum))) {
                        salary[i] += salaryCounter(datum);
                    }
                }
                builder.append(System.lineSeparator() + names[i]).append(" - " + salary[i]);
            }
        } catch (ParseException e) {
            System.out.println("Failed to parse date");
        }
        return builder.toString();
    }

    private int salaryCounter(String data) {
        String[] dataSplit = data.split(WHITESPACE);
        return Integer.valueOf(dataSplit[FIRST_SALARY_SPLIT_INDEX])
                * Integer.valueOf(dataSplit[SECOND_SALARY_SPLIT_INDEX]);
    }

    private String splitName(String data) {
        String[] dataSplit = data.split(WHITESPACE);
        return dataSplit[NAME_SPLIT_INDEX];
    }

    private Date splitDate(String data) {
        String[] dataSplit = data.split(WHITESPACE);
        try {
            return SIMPLE_DATE_FORMAT.parse(dataSplit[DATE_SPLIT_INDEX]);
        } catch (ParseException e) {
            throw new DateParseException("Failed to parse date from input: " + data, e);
        }
    }
}
