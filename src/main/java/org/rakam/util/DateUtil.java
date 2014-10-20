package org.rakam.util;

import java.time.Clock;
import java.time.ZoneId;

/**
 * Created by buremba <Burak Emre Kabakcı> on 23/09/14 01:52.
 */
public class DateUtil {
    final static Clock clock = Clock.tickSeconds(ZoneId.of("UTC"));

    public static int UTCTime() {
        return (int) (clock.millis()/1000);
    }
}
