package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать класс-ошибку IllegalDateParametersException и сделать так, чтобы
     * метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate = null;
        try {
            startDate = dateFormat.parse(dateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (startDate.after(endDate)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        String result = "Отчёт за период " + dateFrom + " - " + dateTo + "\n";
        for (int i = 0; i < names.length; i++) {
            int salaryForPeriod = 0;
            for (int j = 0; j < data.length; j++) {
                String[] oneDay = data[j].split(" ");
                Date thisDay = null;
                try {
                    thisDay = dateFormat.parse(oneDay[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (names[i].equals(oneDay[1]) && thisDay.compareTo(startDate) >= 0
                        && thisDay.compareTo(endDate) <= 0) {
                    salaryForPeriod += Integer.parseInt(oneDay[oneDay.length - 1])
                            * Integer.parseInt(oneDay[oneDay.length - 2]);
                }
            }
            result += names[i] + " - " + salaryForPeriod + "\n";
        }
        return result;
    }
}
