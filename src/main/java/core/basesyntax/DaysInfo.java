package core.basesyntax;

public class DaysInfo {

    public DaysInfo() {
    }

    public String[] getDaysFromTheRange(String[] data, String dateFrom, String dateTo) {

        String[] daysFromTheRange = new String[data.length];
        int index = 0;

        String[] numberFrom = dateFrom.split("\\.");
        int fromDay = Integer.parseInt(numberFrom[0]);
        int fromMonth = Integer.parseInt(numberFrom[1]);
        int fromYear = Integer.parseInt(numberFrom[2]);

        String[] numberTo = dateTo.split("\\.");
        int toDay = Integer.parseInt(numberTo[0]);
        int toMonth = Integer.parseInt(numberTo[1]);
        int toYear = Integer.parseInt(numberTo[2]);

        for (String days : data) {
            String dayData = days.substring(0, 2);
            String monthData = days.substring(3, 5);
            String yearData = days.substring(6, 10);

            int day = Integer.parseInt(dayData);
            int month = Integer.parseInt(monthData);
            int year = Integer.parseInt(yearData);

            int fromDate = fromYear * 10000 + fromMonth * 100 + fromDay;
            int toDate = toYear * 10000 + toMonth * 100 + toDay;
            int currentDate = year * 10000 + month * 100 + day;

            if (currentDate >= fromDate && currentDate <= toDate) {
                daysFromTheRange[index] = days;
                index++;
            }
        }

        String[] result = new String[index];
        for (int i = 0; i < index; i++) {
            result[i] = daysFromTheRange[i];
        }

        return result;
    }
}
