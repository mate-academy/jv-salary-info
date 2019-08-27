package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SalaryInfo {

    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты, определяющие
     * период за который надо вычислить зарплату, первый массив содержит имена сотрудников
     * организации, второй массив информацию о рабочих часах и ставке. Формат данных второго массива
     * следующий: дата, имя сотрудника, количество отработанных часов, ставка за 1 час. Метод должен
     * вернуть отчёт за период, который передали в метод (обе даты включительно) составленный по
     * следующей форме: Отчёт за период #дата_1# - #дата_2# Имя сотрудника - сумма заработанных
     * средств за этот период
     * <p>
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * <p>
     * names: Сергей Андрей София
     * <p>
     * data: 26.04.2019 Сергей 60 50 26.04.2019 Андрей 3 200 26.04.2019 Сергей 7 100 26.04.2018
     * София 9 100 26.04.2019 Сергей 11 50 26.04.2019 Андрей 3 200 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100 26.04.2019 Сергей 11 50
     * <p>
     * Пример вывода: Отчёт за период 01.04.2019  - 30.04.2019 Сергей - 1550 Андрей - 600 София -
     * 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("");


        String[] workers;

        try {
            if (inDate(dateFrom).after(inDate(dateTo))) {
                return null;
            }
            for (String name : names) {
                int salary = 0;
                for (int i = 0; i < data.length; i++) {
                    workers = data[i].split(" ");
                    try {
                        if (inDate(workers[0]).after(inDate(dateTo))) {
                            break;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        break;
                    }
                    if (name.equals(workers[1])) {
                        salary += Integer.parseInt(workers[2]) * Integer.parseInt(workers[3]);
                    }
                }
                report.append(name + " - " + salary + "\n");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String headOfReport = "Отчёт за период " + dateFrom + " - " + dateTo + "\n";
        return headOfReport + report;
    }

    private Date inDate(String string) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.mm.yyyy", Locale.ENGLISH);
        return format.parse(string);
    }
}
