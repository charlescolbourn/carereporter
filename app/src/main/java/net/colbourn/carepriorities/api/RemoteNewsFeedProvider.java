package net.colbourn.carepriorities.api;

public interface RemoteNewsFeedProvider {

    NewsPostResult postNews(NewsUpdateItem updateItem);
    boolean checkForResponse(String postId);

}

