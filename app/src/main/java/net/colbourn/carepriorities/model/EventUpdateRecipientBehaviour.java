/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.model;

import java.io.Serializable;

class EventUpdateRecipientBehaviour implements Serializable
{
    private Boolean shareable;
    private Boolean autoShare;
    private Boolean promptToAutoshare;
    // TODO this needs more work - should be share/autoshare/prompt
}
