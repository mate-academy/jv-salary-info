package core.basesyntax;

public class SalaryInfo {
    private static final String DATA_SEPARATOR = " ";
    private static final String DATE_SEPARATOR = "\\.";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        System.out.println(System.lineSeparator() + "Starting to look for given names");
        for (String n : names) {
            //for each name
            System.out.println(System.lineSeparator() + "       Searching for " + n);
            int sumForThisName = 0;
            for (String d : data) {
                //name is searched within database and all the results are stored
                String[] recordData = d.split(DATA_SEPARATOR);
                if (n.equals(recordData[1]) && datesMatch(recordData[0],dateFrom,dateTo)) {
                    int toAdd = Integer.parseInt(recordData[2]) * Integer.parseInt(recordData[3]);
                    sumForThisName += toAdd;
                    System.out.println("adding " + toAdd
                            + " for " + n + ", total is now " + sumForThisName);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(n)
                    .append(" - ").append(sumForThisName);
        }
        System.out.println(System.lineSeparator() + "________________________________________");
        return stringBuilder.toString();
    }

    private boolean datesMatch(String date, String dateFrom, String dateTo) {
        int[] intDate = dateToIntArray(date);
        int[] intDateFrom = dateToIntArray(dateFrom);
        int[] intDateTo = dateToIntArray(dateTo);
        boolean afterFrom = compareDate(intDate, intDateFrom);
        boolean beforeTo = compareDate(intDateTo, intDate);
        if (beforeTo || afterFrom) {
            System.out.println(System.lineSeparator());
        }
        System.out.println(afterFrom ? date
                + " is after " + dateFrom : date
                + " isn't after " + dateFrom);
        System.out.println(beforeTo ? date
                + " is before " + dateFrom : date
                + " isn't before " + dateFrom);
        return afterFrom && beforeTo;
    }

    private int[] dateToIntArray(String date) {
        String[] dateParts = date.split(DATE_SEPARATOR);
        for (int i = 0; i < dateParts.length; i++) {
            dateParts[i] = removeZero(dateParts[i]);
        }
        return new int[]{Integer.parseInt(dateParts[0]),
                Integer.parseInt(dateParts[1]),
                Integer.parseInt(dateParts[2])};
    }

    private String removeZero(String str) {
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0') {
            i++;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.replace(0, i, "");
        return sb.toString();
    }

    private boolean compareDate(int[] more, int[] less) {
        return more[2] > less[2] || more[1] > less[1] || more[0] >= less[0];
    }
}
