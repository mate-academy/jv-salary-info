package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date fromDate = null;
        try {
            fromDate = dateFormat.parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date toDate = null;
        try {
            toDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Map<String, Integer> nameSalaryMap = new HashMap<>();
        for (String name : names) {
            nameSalaryMap.put(name, 0);
        }

        for (String oneDataString : data) {
            String[] dataSplitFormat = oneDataString.split(" ");
            Date dateFromData = null;
            try {
                dateFromData = dateFormat.parse(dataSplitFormat[0]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            if ((dateFromData.compareTo(fromDate) >= 0)
                    && (dateFromData.compareTo(toDate) <= 0)) {
                if (nameSalaryMap.containsKey(dataSplitFormat[1])) {
                    Integer oldValue = nameSalaryMap.get(dataSplitFormat[1]);
                    nameSalaryMap.put(dataSplitFormat[1], oldValue
                            + Integer.parseInt(dataSplitFormat[2])
                            * Integer.parseInt(dataSplitFormat[3]));
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
}
