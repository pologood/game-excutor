package com.snowcattle.game.excutor.event.asyncevent;

import com.snowcattle.game.excutor.event.CycleEvent;
import com.snowcattle.game.excutor.event.EventBus;
import com.snowcattle.game.excutor.event.EventParam;
import com.snowcattle.game.excutor.event.async.IntegerUpdate;
import com.snowcattle.game.excutor.event.impl.DispatchCreateEventListener;
import com.snowcattle.game.excutor.event.impl.DispatchFinishEventListener;
import com.snowcattle.game.excutor.event.impl.DispatchUpdateEventListener;
import com.snowcattle.game.excutor.pool.UpdateEventExcutorService;
import com.snowcattle.game.excutor.service.UpdateService;
import com.snowcattle.game.excutor.thread.LockSupportEventDisptachThread;
import com.snowcattle.game.excutor.utils.Constants;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by jwp on 2017/3/28.
 */
public class AsyncNotifyEventTest {
    public static void main(String[] args) throws Exception {
        testEvent();
    }

    public static void testEvent() throws Exception {
        EventBus updateEventBus = new EventBus();
//        int maxSize = 10000;
//        int corePoolSize = 100;
        int maxSize = 2;
        int corePoolSize = 2;
        long keepAliveTime = 60;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        UpdateEventExcutorService updateEventExcutorService = new UpdateEventExcutorService(corePoolSize);
        int cycleSleepTime = 1000 / Constants.cycle.cycleSize;
        LockSupportEventDisptachThread dispatchThread = new LockSupportEventDisptachThread(updateEventBus, updateEventExcutorService
                , cycleSleepTime, cycleSleepTime*1000);
        updateEventExcutorService.setDispatchThread(dispatchThread);
        UpdateService updateService = new UpdateService(dispatchThread, updateEventExcutorService);
        updateEventBus.addEventListener(new DispatchCreateEventListener(dispatchThread, updateService));
        updateEventBus.addEventListener(new DispatchUpdateEventListener(dispatchThread, updateService));
        updateEventBus.addEventListener(new DispatchFinishEventListener(dispatchThread, updateService));

        updateService.notifyStart();
//        while (true) {
//            Thread.currentThread().sleep(100);
//            LockSupport.unpark(dispatchThread);
//
//            LockSupport.park(dispatchThread);
//            updateService.notifyRun();
//            break;
//        }


        for (long i = 0; i < maxSize; i++) {
            IntegerUpdate integerUpdate = new IntegerUpdate(i);
            EventParam<IntegerUpdate> param = new EventParam<IntegerUpdate>(integerUpdate);
            CycleEvent cycleEvent = new CycleEvent(Constants.EventTypeConstans.readyCreateEventType, integerUpdate.getId(), param);
            updateService.addReadyCreateEvent(cycleEvent);
        }


//        updateService.shutDown();
        Timer timer = new Timer();
        timer.schedule(new NotifyTask(updateService), 0, 10);
        while (true) {
            Thread.currentThread().sleep(100);
            updateService.toString();
        }
    }
}

