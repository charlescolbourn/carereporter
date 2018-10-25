/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.model;

import java.time.Duration;
import java.time.ZonedDateTime;


class Event
{
    private ZonedDateTime time;
    private EventType eventType;
    private String name;
    private Duration eventDuration;
}