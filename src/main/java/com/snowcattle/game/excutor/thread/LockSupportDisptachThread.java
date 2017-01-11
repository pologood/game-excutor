package com.snowcattle.game.excutor.thread;

import com.snowcattle.game.excutor.event.EventBus;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by jiangwenping on 17/1/9.
 * 带预置锁的分配器
 */
public class LockSupportDisptachThread extends DispatchThread{

    public LockSupportDisptachThread(EventBus eventBus) {
        super(eventBus);
    }

    public void run() {
        super.run();
        LockSupport.park();
    }
}