package net.colbourn.carepriorities.api;

public interface EventUpdateNotificationRecipient {

    NewsPostResult postNews(NewsUpdateItem updateItem);
    boolean checkForResponse(String postId);

}

