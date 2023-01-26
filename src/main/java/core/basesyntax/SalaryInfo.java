package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String sallaryInfo = "";
        int sallary = 0;
        dateFrom = dateFrom.trim();
        dateTo = dateTo.trim();

        for (String name : names) {
            for (String getData : data) {
                if (getData.contains(name) && (dateFrom.contains(getData.substring(0,10))
                            || dateTo.contains(getData.substring(0, 10))
                            || dateFrom.substring(3, 10).contains(getData.substring(3, 10)))) {
                    sallary += Integer.parseInt(getData.substring(getData
                                .lastIndexOf(' ') + 1, getData.length()));
                }
            }
            sallaryInfo = name + " - " + sallary + "\n";
        }
        return "Report of period " + dateFrom + " - " + dateTo + "\n" + sallaryInfo;
    }
}
