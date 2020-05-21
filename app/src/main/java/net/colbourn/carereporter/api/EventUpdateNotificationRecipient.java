package net.colbourn.carereporter.api;

public interface EventUpdateNotificationRecipient {

    NewsPostResult postNews(NewsUpdateItem updateItem);
    boolean checkForResponse(String postId);

}

