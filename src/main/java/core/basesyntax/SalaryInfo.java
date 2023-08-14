package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        System.out.println();
        String[] strDateFrom = dateFrom.split("\\.");
        int numDateFrom = Integer.parseInt(strDateFrom[2]) * 10000
                + Integer.parseInt(strDateFrom[1]) * 100
                + Integer.parseInt(strDateFrom[0]);
        String[] strDateTo = dateTo.split("\\.");
        String[] returnStrings = new String[names.length];
        int numDateTo = Integer.parseInt(strDateTo[2]) * 10000
                + Integer.parseInt(strDateTo[1]) * 100
                + Integer.parseInt(strDateTo[0]);
        for (int i = 0; i < names.length; i++) {
            int sum = 0;
            for (String datum : data) {
                String[] array = datum.split(" ");
                String[] strCurrentDate = array[0].split("\\.");
                int numCurrentDate = Integer.parseInt(strCurrentDate[2]) * 10000
                        + Integer.parseInt(strCurrentDate[1]) * 100
                        + Integer.parseInt(strCurrentDate[0]);
                if (names[i].equals(array[1]) && numCurrentDate >= numDateFrom
                        && numCurrentDate <= numDateTo) {
                    sum += Integer.parseInt(array[2]) * Integer.parseInt(array[3]);
                }
            }
            returnStrings[i] = names[i] + " - " + sum;
        }
        StringBuilder returnString = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String string : returnStrings) {
            returnString.append(System.lineSeparator()).append(string);
        }
        return returnString.toString();
    }
}
