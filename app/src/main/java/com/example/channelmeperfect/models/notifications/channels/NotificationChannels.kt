package com.example.channelmeperfect.models.notifications.channels

enum class NotificationChannels(
    val channelName: String,
    val channelDescription: String,
    val clickAction: String
) {
    NEW_EPISODE_AVAILABLE(
        channelName = "New Episode Available",
        channelDescription = "Get notified when a new episode of your favorite TV show is released.",
        clickAction = "com.example.channelmeperfect.MainActivity"
    ),
    RECOMMENDED_MOVIES(
        channelName = "Recommended Movies",
        channelDescription = "Discover personalized movie recommendations based on your viewing history.",
        clickAction = "com.example.channelmeperfect.MainActivity"
    ),
    UPCOMING_RELEASES(
        channelName = "Upcoming Releases",
        channelDescription = "Stay informed about the latest movie and TV show releases.",
        clickAction = "com.example.channelmeperfect.MainActivity"
    ),
    WATCHLIST_UPDATES(
        channelName = "Watchlist Updates",
        channelDescription = "Receive notifications when items are added or removed from your watchlist.",
        clickAction = "com.example.channelmeperfect.MainActivity"
    ),
    TRENDING_CONTENT(
        channelName = "Trending Content",
        channelDescription = "Discover the most popular shows and movies right now.",
        clickAction = "com.example.channelmeperfect.MainActivity"
    ),
    ACTOR_NEWS(
        channelName = "Actor News",
        channelDescription = "Stay up-to-date on the latest news about your favorite actors and actresses.",
        clickAction = "com.example.channelmeperfect.MainActivity"
    ),
    FESTIVAL_HIGHLIGHTS(
        channelName = "Festival Highlights",
        channelDescription = "Get a recap of the best moments from major film festivals.",
        clickAction = "com.example.channelmeperfect.MainActivity"
    ),
    BOX_OFFICE_HITS(
        channelName = "Box Office Hits",
        channelDescription = "See which movies are dominating the box office this week.",
        clickAction = "com.example.channelmeperfect.MainActivity"
    ),
    CRITIC_REVIEWS(
        channelName = "Critic Reviews",
        channelDescription = "Read expert opinions on the latest films and TV shows.",
        clickAction = "com.example.channelmeperfect.MainActivity"
    ),
    INDUSTRY_INSIGHTS(
        channelName = "Industry Insights",
        channelDescription = "Get behind-the-scenes news and analysis from the entertainment industry.",
        clickAction = "com.example.channelmeperfect.MainActivity"
    );

    companion object {
        fun fromString(name: String): NotificationChannels? {
            return entries.find { it.name == name }
        }
    }
}