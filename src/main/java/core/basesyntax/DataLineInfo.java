package core.basesyntax;

public class DataLineInfo {
    String date;
    String name;
    int hours;
    int payment;

    public DataLineInfo(String data) {
        this.date = data.split(" ")[0];
        this.name = data.split(" ")[1];
        this.hours = Integer.parseInt(data.split(" ")[3]);
        this.payment = Integer.parseInt(data.split(" ")[2]);
    }
}

