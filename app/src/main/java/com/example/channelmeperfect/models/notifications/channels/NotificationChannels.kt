package com.example.channelmeperfect.models.notifications.channels

enum class NotificationChannels(
    val channelName: String,
    val channelDescription: String,
    val clickAction: String
) {
    NEW_EPISODE_AVAILABLE(
        "New Episode Available",
        "Get notified when a new episode of your favorite TV show is released.",
        "com.example.channelmeperfect.MainActivity"
    ),
    RECOMMENDED_MOVIES(
        "Recommended Movies",
        "Discover personalized movie recommendations based on your viewing history.",
        "com.example.channelmeperfect.MainActivity"
    ),
    UPCOMING_RELEASES(
        "Upcoming Releases",
        "Stay informed about the latest movie and TV show releases.",
        "com.example.channelmeperfect.MainActivity"
    ),
    WATCHLIST_UPDATES(
        "Watchlist Updates",
        "Receive notifications when items are added or removed from your watchlist.",
        "com.example.channelmeperfect.MainActivity"
    ),
    TRENDING_CONTENT(
        "Trending Content",
        "Discover the most popular shows and movies right now.",
        "com.example.channelmeperfect.MainActivity"
    ),
    ACTOR_NEWS(
        "Actor News",
        "Stay up-to-date on the latest news about your favorite actors and actresses.",
        "com.example.channelmeperfect.MainActivity"
    ),
    FESTIVAL_HIGHLIGHTS(
        "Festival Highlights",
        "Get a recap of the best moments from major film festivals.",
        "com.example.channelmeperfect.MainActivity"
    ),
    BOX_OFFICE_HITS(
        "Box Office Hits",
        "See which movies are dominating the box office this week.",
        "com.example.channelmeperfect.MainActivity"
    ),
    CRITIC_REVIEWS(
        "Critic Reviews",
        "Read expert opinions on the latest films and TV shows.",
        "com.example.channelmeperfect.MainActivity"
    ),
    INDUSTRY_INSIGHTS(
        "Industry Insights",
        "Get behind-the-scenes news and analysis from the entertainment industry.",
        "com.example.channelmeperfect.MainActivity"
    );

    companion object {
        fun fromString(name: String): NotificationChannels? {
            return entries.find { it.name == name }
        }
    }
}