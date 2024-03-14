package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        String[][] report = new String[names.length][2];

        for (int i = 0; i < names.length; i++) {
            report[i][0] = names[i];
            report[i][1] = "0";
        }

        for (String datas : data) {
            String[] info = datas.split(" ");

            for (int i = 0; i < names.length; i++) {
                if (report[i][0].equals(info[1]) && report[i][1] != null
                        && isDateFit(info[0], dateFrom, dateTo)) {
                    report[i][1] = String.valueOf((Integer.parseInt(info[2])
                            * Integer.parseInt(info[3])) + Integer.parseInt(report[i][1]));
                } else if (report[i][0].equals(info[1]) && report[i][1] == null
                        && isDateFit(info[0], dateFrom, dateTo)) {
                    report[i][1] = String.valueOf((Integer.parseInt(info[2])
                            * Integer.parseInt(info[3])));
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(report[i][1]);
        }

        return salaryInfo.toString();
    }

    public boolean isDateFit(String localDate, String dateFrom, String dateTo) {
        String[] dateSplited = localDate.split("\\.");
        String[] dateFromSplited = dateFrom.split("\\.");
        String[] dateToSplited = dateTo.split("\\.");

        int[] dateI = {
                Integer.parseInt(dateSplited[0]),
                Integer.parseInt(dateSplited[1])
        };

        int[] dateFromI = {
                Integer.parseInt(dateFromSplited[0]),
                Integer.parseInt(dateFromSplited[1])
        };

        int[] dateToI = {
                Integer.parseInt(dateToSplited[0]),
                Integer.parseInt(dateToSplited[1])
        };

        if (dateFromI[1] == dateToI[1]) {
            return dateI[0] >= dateFromI[0] && dateI[0] <= dateToI[0];
        }

        return (dateI[0] <= dateToI[0] && dateI[1] == dateToI[1])
                || (dateI[0] >= dateFromI[0] && dateI[1] == dateFromI[1]);
    }
}
