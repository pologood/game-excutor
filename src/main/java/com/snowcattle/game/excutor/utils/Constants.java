package com.snowcattle.game.excutor.utils;

import com.snowcattle.game.excutor.event.EventType;
import com.snowcattle.game.excutor.event.EventTypeEnum;

/**
 * Created by jiangwenping on 17/1/9.
 */
public class Constants {

    /**
     * ⌚事件类型常量
     */
    public static class EventTypeConstans{
        public static EventType createEventType = new EventType(EventTypeEnum.CREATE.ordinal());
        public static EventType updateEventType = new EventType(EventTypeEnum.UPDATE.ordinal());
        public static EventType finishEventType = new EventType(EventTypeEnum.FINISH.ordinal());
        public static EventType readyCreateEventType = new EventType(EventTypeEnum.REDAY_CREATE.ordinal());
        public static EventType readyFinishEventType = new EventType(EventTypeEnum.REDAY_FINISH.ordinal());
        public static EventType finishedEventType = new EventType(EventTypeEnum.FINISHED.ordinal());
    }

    /**
     * Thread的名字前缀
     */
    public static class Thread{
        public static final String UPDATE="update";
        public static final String DISPATCH="dispatch";
    }

    /**
     * 循环次数
     */
    public static class cycle{
        /**
         * 每秒循环次数
         */

        public static final int cycleSize = 1;
    }
}
