package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SalaryInfo {
    private static final int POSITION_OF_DATE = 0;
    private static final int POSITION_OF_NAME = 1;
    private static final int POSITION_OF_WORKED_HOURS = 2;
    private static final int POSITION_OF_SALARY_PER_HOURS = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] dataSplit;
        String[] dataOutput = new String[names.length];
        int j = 0;
        for (String name : names) {
            int sum = 0;
            for (String oneData : data) {
                dataSplit = oneData.split(" ");
                if (dataSplit[POSITION_OF_NAME].equals(name)) {
                    if (getDate(dataSplit[POSITION_OF_DATE]).getTime()
                            >= getDate(dateFrom).getTime()) {
                        if (getDate(dataSplit[POSITION_OF_DATE]).getTime()
                                <= getDate(dateTo).getTime()) {
                            sum += Integer.parseInt(dataSplit[POSITION_OF_WORKED_HOURS])
                                    * Integer.parseInt(dataSplit[POSITION_OF_SALARY_PER_HOURS]);
                        }
                    }
                }
            }
            dataOutput[j] = names[j] + " - " + sum;
            j++;
        }
        return "Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator() + getStringFromNewLine(dataOutput);
    }

    private Date getDate(String date) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date dateFormat;
        try {
            return dateFormat = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid format date!");
        }
    }

    private String getStringFromNewLine(String[] strings) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length - 1; i++) {
            builder.append(strings[i]).append(System.lineSeparator());
        }
        return builder.append(strings[strings.length - 1]).toString();
    }
}
