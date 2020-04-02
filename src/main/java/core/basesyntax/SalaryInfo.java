package core.basesyntax;
import core.basesyntax.exception.IllegalDateParametersException;

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
     * Создать пакет exception и в нём класс-ошибку IllegalDateParametersException. Сделать так,
     * чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
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
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate = formatter.parse(dateFrom);
        Date endDate = formatter.parse(dateTo);
        if (startDate.compareTo(endDate) > 0) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder stringBuilderResult = new StringBuilder();
        stringBuilderResult.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo).append("\n");
        String[] dataSplit;

        for (String workerName : names) {
            int salary = 0;
            stringBuilderResult.append(workerName).append(" - ");

            for (String workersData : data) {
                dataSplit = workersData.split(" ");
                Date workerDays = formatter.parse(dataSplit[0]);

                if (!(workerDays.before(startDate) || workerDays.after(endDate)) && dataSplit[1].equals(workerName)) {
                    salary += Integer.parseInt(dataSplit[2])*Integer.parseInt(dataSplit[3]);
                }
            }
            stringBuilderResult.append(salary).append("\n");
        }

        stringBuilderResult.deleteCharAt(stringBuilderResult.length()-1);
        return stringBuilderResult.toString();

    }
}
