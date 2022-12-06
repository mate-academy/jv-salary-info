package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalaryInfo {
    private static final String pattern = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        List<String> namesList = Arrays.asList(names);
        int[] salaries = new int[names.length];
        for (String dataRow : data) {
            String[] dataArr = dataRow.split(" ");
            if (namesList.contains(dataArr[1]) && isDateInRange(dataArr[0], dateFrom, dateTo)) {
                int hours = Integer.parseInt(dataArr[2]);
                int amount = Integer.parseInt(dataArr[3]);
                int index = namesList.indexOf(String.valueOf(dataArr[1]));
                salaries[index] += hours * amount;
            }
        }
        StringBuilder sb = new StringBuilder("Report for period ");
        sb.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < namesList.size(); i++) {
            sb.append(System.lineSeparator());
            sb.append(namesList.get(i)).append(" - ").append(salaries[i]);
        }
        return sb.toString();
    }

    private boolean isDateInRange(String dateToCheck, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern(pattern);
        try {
            Date from = dateFormat.parse(dateFrom);
            Date to = dateFormat.parse(dateTo);
            Date date = dateFormat.parse(dateToCheck);
            return date.getTime() >= from.getTime() && date.getTime() <= to.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Date doesn't match " + pattern + " pattern ", e);
        }
    }
}
