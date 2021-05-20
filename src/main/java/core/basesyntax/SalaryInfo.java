package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        String[] array = new String[names.length];
        try {
            Date firstDate = format.parse(dateFrom);
            Date secondDate = format.parse(dateTo);
            for (int i = 0;i < names.length;i++) {
                int totalAmount = 0;
                for (String row : data) {
                    String[] record = row.trim().split("\\s");
                    Date recordDate = format.parse(record[0]);
                    String recordName = record[1];
                    int amountPerHour = Integer.parseInt(record[3]);
                    int amountHour = Integer.parseInt(record[2]);
                    if (recordDate.compareTo(firstDate) >= 0
                            && recordDate.compareTo(secondDate) <= 0
                            && recordName.equals(names[i])) {
                        totalAmount += amountPerHour * amountHour;
                    }
                }
                array[i] = builder.append(names[i]).append(" - ").append(totalAmount).toString();
                builder.delete(0,builder.length());
            }
            builder.delete(0,builder.length());
            builder.append("Report for period ").append(
                            format.format(firstDate)).append(" - ").append(
                            format.format(secondDate)).append("\n");
        } catch (ParseException e) {
            System.out.println("Wrong format");
        }
        for (int i = 0;i < names.length - 1;i++) {
            builder.append(array[i]).append("\n");
        }
        builder.append(array[array.length - 1]);
        return builder.toString();
    }
}
