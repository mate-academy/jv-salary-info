package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder sb = new StringBuilder("Report for period " + dateFrom.trim() +
                " - " + dateTo.trim() + "\n");

        int pay = 0;

        String[] dateFromArr = dateFrom.split("\\.");
        int dayFrom = Integer.parseInt(dateFromArr[0]);
        int monthFrom = Integer.parseInt(dateFromArr[1]);
        int yearFrom = Integer.parseInt(dateFromArr[2]);

        String[] dateToArr = dateFrom.split("\\.");
        int dayTo = Integer.parseInt(dateToArr[0]);
        int monthTo = Integer.parseInt(dateToArr[1]);
        int yearTo = Integer.parseInt(dateToArr[2]);

        for (String n : names) {

            for (String d : data) {

                if (d.contains(n)) {

                    String[] dataArr = d.split(" ");
                    int day = Integer.parseInt(dataArr[0]);
                    int month = Integer.parseInt(dataArr[1]);
                    int year = Integer.parseInt(dataArr[2]);
                    int hours = Integer.parseInt(dataArr[4]);
                    int wage = Integer.parseInt(dataArr[5]);

                    if (day >= dayFrom && day <= dayTo &&
                        month >= monthFrom && month <= monthTo &&
                        year >= yearFrom && year <= yearTo) {

                        pay += hours * wage;
                    }
                }
            }

            sb.append(n).append(" - ").append(pay).append("\n");
            pay = 0;
        }

        return sb.toString();
    }
}
