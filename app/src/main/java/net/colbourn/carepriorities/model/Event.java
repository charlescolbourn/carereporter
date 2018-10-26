/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.ZonedDateTime;


class Event implements Serializable
{
    private ZonedDateTime time;
    private EventType eventType;
    private String name;
    private Duration eventDuration;
    private Duration reoccurence;

    //icon (cached & transient?)
}