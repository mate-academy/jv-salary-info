package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] dataSplit;
        String[] dataOutput = new String[names.length];
        int j = 0;
        for (String name : names) {
            int sum = 0;
            for (String oneData : data) {
                dataSplit = oneData.split(" ");
                if (dataSplit[1].equals(name)) {
                    if (getDate(dataSplit[0]).getTime() >= getDate(dateFrom).getTime()) {
                        if (getDate(dataSplit[0]).getTime() <= getDate(dateTo).getTime()) {
                            sum = sum + Integer.parseInt(dataSplit[2])
                                    * Integer.parseInt(dataSplit[3]);
                        }
                    }
                }
            }
            dataOutput[j] = names[j] + " - " + sum;
            j++;
        }
        System.out.println("Report for period " + dateFrom + " - " + dateTo);
        for (String oneDataOutput : dataOutput) {
            System.out.println(oneDataOutput);
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
