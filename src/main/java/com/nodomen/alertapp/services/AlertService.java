package com.nodomen.alertapp.services;

import com.nodomen.alertapp.models.SendingTime;

import java.util.*;

public class AlertService {

    /* Конвейер по подгрузке времён отправки уведомлений, раздающий
    команды различным отправителям о том, что пора совершать отправку */


    /* Всё попадаемое в buffer изначально находится в bufferUpdater,
    он в свою очередь должен переодически пополняться, обращаясь в базу данных.

    Нужно сконструировать структуру, которая будет управлять временными интервалами,
    уведомления из которых будут пополняться в оперативную память

    Грубо говоря, каждые пол-часа. Нужно сделать то, что будет считать пол часа.

    Timer TimerTask Date Calendar

    Узнать текущее время, высчитать ближайшую метку hh:(кратное 30)

     */

    public Timer timer;

    List<Date> dateList;
    Collection<SendingTime> databaseCleanerBuffer;
    Collection<SendingTime> buffer;
    Collection<SendingTime> bufferUpdater;

    public void main(String[] args) {

        dateList = new LinkedList<Date>();
        Calendar calendar = Calendar.getInstance();

        Date currentTime = calendar.getTime();

        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 00);

        Date newTime = calendar.getTime();

        if(currentTime.after(newTime)) {
            calendar.set(Calendar.HOUR, Calendar.HOUR+1);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        }

        newTime = calendar.getTime();
        System.out.println(currentTime);
        System.out.println(newTime);

        for (int i = 0; i < 10; i++) {
            calendar.add(Calendar.MINUTE, 30);
            Date addableDate = calendar.getTime();
            dateList.add(addableDate);
        }
    }


    public void updateBuffer() {

        /* Как правильно обновить буффер?

        Ясно, что оба буффера будут содержать некоторые одинаковые элементы
        Нужно найти самый первый по времени отправки в buffer, и после этого
        добавить все элементы bufferUpdater, у которых время отправки позже.

        После этого нужно вычистить bufferUpdater */
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

            }
        };

        timer.schedule(task, dateList.get(1), 1000*60*30);
        // мб поэтому не нужно было высчитывать даты ручками.

    }

}
