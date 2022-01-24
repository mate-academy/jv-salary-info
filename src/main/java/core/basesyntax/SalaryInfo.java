package core.basesyntax;

public class SalaryInfo {

    static String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String [] detail = {};
        int sal = 0;
        String result = "";
        result = "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator();
        for (int j = 0; j < names.length; j++) {
            sal = 0;
            for (int i = 0; i < data.length; i++) {
                detail = data[i].split(" ");
                if ((names[j].equals(detail[1])
                        && ((Integer.parseInt(data[i].substring(3, 5))
                        < Integer.parseInt(dateTo.substring(3, 5)))
                        || (Integer.parseInt(data[i].substring(3, 5))
                        == Integer.parseInt(dateTo.substring(3, 5)))
                        && Integer.parseInt(data[i].substring(0, 2))
                        <= Integer.parseInt(dateTo.substring(0, 2))))
                        && ((Integer.parseInt(data[i].substring(3, 5))
                        > Integer.parseInt(dateFrom.substring(3, 5)))
                        || (Integer.parseInt(data[i].substring(3, 5))
                        == Integer.parseInt(dateFrom.substring(3, 5)))
                        && (Integer.parseInt(data[i].substring(0, 2))
                        >= Integer.parseInt(dateFrom.substring(0, 2))))) {
                    sal = sal + Integer.parseInt(detail[2]) * Integer.parseInt(detail[3]);
                }
            }
            result = (j != names.length - 1) ? result + names [j] + " - "
                    + sal + System.lineSeparator() : result + names [j] + " - " + sal;
        }
        return result;
    }
}
