package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final int SALARY_SPLIT_INDEX = 2;
    private static final int NAME_SPLIT_INDEX = 1;
    private static final int DATE_SPLIT_INDEX = 0;
    private SimpleDateFormat sdformat = new SimpleDateFormat("dd.MM.yyyy");

    private int salaryCounter(String data) {
        String[] dataSplit = data.split(" ");
        return Integer.valueOf(dataSplit[SALARY_SPLIT_INDEX]) * Integer.valueOf(dataSplit[3]);
    }

    private String nameSplit(String data) {
        String[] dataSplit = data.split(" ");
        return dataSplit[NAME_SPLIT_INDEX];
    }

    private Date checkDate(String data) {
        String[] dataSplit = data.split(" ");
        try {
            Date date = sdformat.parse(dataSplit[DATE_SPLIT_INDEX]);
            return date;
        } catch (ParseException e) {
            System.out.println("Failed to parse date");
            return null;
        }
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period " + dateFrom + " - " + dateTo);
        int[] salary = new int[names.length];
        try {
            Date firstDate = sdformat.parse(dateFrom);
            Date lastDate = sdformat.parse(dateTo);
            for (int i = 0; i < names.length; i++) {
                for (String datum : data) {
                    Date currentDate = checkDate(datum);

                    if (currentDate.after(lastDate)) {
                        continue;
                    }

                    if ((currentDate.after(firstDate) || currentDate.equals(firstDate))
                            && (currentDate.before(lastDate) || currentDate.equals(lastDate))
                            && names[i].equals(nameSplit(datum))) {
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
}
