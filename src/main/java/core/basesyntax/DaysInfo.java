package core.basesyntax;

import java.util.ArrayList;
import java.util.List;

public class DaysInfo {

    public String[] getDaysFromTheRange(String[] data, String dateFrom, String dateTo) {

        List<String> daysFromTheRange = new ArrayList<>();

        String[] numberFrom = dateFrom.split("\\.");
        int fromDay = Integer.parseInt(numberFrom[0]);
        int fromMonth = Integer.parseInt(numberFrom[1]);
        int fromYear = Integer.parseInt(numberFrom[2]);

        String[] numberTo = dateTo.split("\\.");
        int toDay = Integer.parseInt(numberTo[0]);
        int toMonth = Integer.parseInt(numberTo[1]);
        int toYear = Integer.parseInt(numberTo[2]);

        for (String days : data) {
            if (days.length() < 10) {
                System.out.println("Invalid date format: " + days);
                continue;
            }

            String dayData = days.substring(0, 2);
            String monthData = days.substring(3, 5);
            String yearData = days.substring(6, 10);

            int day = 0;
            int month = 0;
            int year = 0;

            try {
                day = Integer.parseInt(dayData);
                month = Integer.parseInt(monthData);
                year = Integer.parseInt(yearData);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format: " + dayData + ", "
                        + monthData + ", " + yearData);
                continue;
            }

            int fromDate = fromYear * 10000 + fromMonth * 100 + fromDay;
            int toDate = toYear * 10000 + toMonth * 100 + toDay;
            int currentDate = year * 10000 + month * 100 + day;

            if (currentDate >= fromDate && currentDate <= toDate) {
                daysFromTheRange.add(days);
            }
        }

        String[] result = new String[daysFromTheRange.size()];
        daysFromTheRange.toArray(result);

        return result;
    }
}
