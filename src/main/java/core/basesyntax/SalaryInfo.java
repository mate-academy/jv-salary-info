package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private Date fromDate;
    private Date toDate;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        fromDate = getDateFromString(dateFrom);
        toDate = getDateFromString(dateTo);

        Map<String, Integer> nameSalaryMap = new HashMap<>();
        for (String name : names) {
            nameSalaryMap.put(name, 0);
        }

        for (String oneDataString : data) {
            String[] dataSplitFormat = oneDataString.split(" ");
            Date dateFromData = getDateFromString(dataSplitFormat[0]);
            String dataName = dataSplitFormat[1];

            if (dateFromData.compareTo(fromDate) >= 0 && dateFromData.compareTo(toDate) <= 0) {
                if (nameSalaryMap.containsKey(dataName)) {
                    Integer oldValue = nameSalaryMap.get(dataSplitFormat[1]);
                    Integer dataHour = Integer.parseInt(dataSplitFormat[2]);
                    Integer dataSalary = Integer.parseInt(dataSplitFormat[3]);

                    nameSalaryMap.put(dataName, oldValue + dataHour * dataSalary);
                }

            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            result.append("\n").append(name).append(" - ").append(nameSalaryMap.get(name));
        }

        return result.toString();
    }

    private Date getDateFromString(String strDate) {
        Date date = null;
        try {
            date = DATE_FORMAT.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
