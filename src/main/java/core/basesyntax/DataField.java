package core.basesyntax;

public class DataField {

    private final int workingHour;
    private final int incomePerHour;
    private final String date;
    private final String employeeName;

    public DataField(String data) {
        String[] datas = data.split(" ");
        date = datas[0];
        employeeName = datas[1];
        workingHour = Integer.parseInt(datas[2]);
        incomePerHour = Integer.parseInt(datas[3]);

    }

    public int getWorkingHour() {
        return workingHour;
    }

    public int getIncomePerHour() {
        return incomePerHour;
    }

    public String getDate() {
        return date;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}
