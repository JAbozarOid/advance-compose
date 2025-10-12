package com.example.advancecompose.feature.interview.algoritm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import java.io.IOException

fun main() = runBlocking {

    launch {
        println(
            "buffering ${
                bufferingProgress().flowOn(Dispatchers.IO).collect {
                    print("collected buffered $it")
                }
            }}")
    }
    launch {
        println(
            "Combine flow ${
                combineVideoData(
                    videoFlow = flowOf(
                        Video(
                            id = "13",
                            timestamp = 1741170298,
                            title = "DD",
                            watchCounts = 100,
                            category = "live",
                            duration = 150000,
                            subCategory = listOf("main")
                        ), Video(
                            id = "12",
                            timestamp = 1743848698,
                            title = "CC",
                            watchCounts = 12,
                            category = "documentary",
                            duration = 50000,
                            subCategory = listOf("main")
                        )
                    ),
                    subtitlesFlow = flowOf(
                        Subtitles("sub 1"),
                        Subtitles("sub 2"),
                        Subtitles("sub 3")
                    ),
                    analyticsFlow = flowOf(Analytics(5), Analytics(15), Analytics(35))
                ).flowOn(Dispatchers.IO).collect {
                    print("collected combine flow $it")
                }

            }")
    }


    println(
        "search query ${
            searchVideos(queryFlow = flowOf("DDABCD")).flowOn(
                Dispatchers.IO
            ).collect { it }
        }"
    )

    val list1 = mutableListOf<Video>()
    list1.add(
        Video(
            id = "1",
            timestamp = 1759659898,
            title = "A",
            watchCounts = 10,
            category = "unknown",
            duration = 600000,
            subCategory = listOf("unknown")
        )
    )
    list1.add(
        Video(
            id = "2",
            timestamp = 1757067898,
            title = "B",
            watchCounts = 10,
            category = "drama",
            duration = 1000,
            subCategory = listOf("adult")
        )
    )
    list1.add(
        Video(
            id = "3",
            timestamp = 1754389498,
            title = "C",
            watchCounts = 210,
            category = "live",
            duration = 10,
            subCategory = listOf("main")
        )
    )
    list1.add(
        Video(
            id = "4",
            timestamp = 1751711098,
            title = "D",
            watchCounts = 1,
            category = "fiction",
            duration = 100000,
            subCategory = listOf("children")
        )
    )


    val list2 = mutableListOf<Video>()
    list2.add(
        Video(
            id = "10",
            timestamp = 1749119098,
            title = "AA",
            watchCounts = 0,
            category = "fiction",
            duration = 20000,
            subCategory = listOf("children")
        )
    )
    list2.add(
        Video(
            id = "11",
            timestamp = 1746440698,
            title = "BB",
            watchCounts = 17,
            category = "drama",
            duration = 100000,
            subCategory = listOf("adult")
        )
    )
    list2.add(
        Video(
            id = "12",
            timestamp = 1743848698,
            title = "CC",
            watchCounts = 12,
            category = "documentary",
            duration = 50000,
            subCategory = listOf("main")
        )
    )
    list2.add(
        Video(
            id = "13",
            timestamp = 1741170298,
            title = "DD",
            watchCounts = 100,
            category = "live",
            duration = 150000,
            subCategory = listOf("main")
        )
    )

    println("merged list is : ${mergeSortedVideos(list1 = list1, list2 = list2)}")
    //print(topKWatchedVideos(list = list1, watchCounts = mapOf(), k = 100))
    println(analyzedVideosByCategory(mergeSortedVideos(list1 = list1, list2 = list2)))

    println(
        "target timestamp is : ${
            findVideoByTimestamp(
                videos = list1,
                targetTimestamp = 1759659898
            )
        }"
    )

    println(
        "filtered videos by the greater than timestamp with their titles ${
            processLargeVideoList(
                mergeSortedVideos(list1, list2)
            )
        }"
    )
}

data class Video(
    val id: String = "",
    val timestamp: Long = 0L,
    val title: String = "",
    val watchCounts: Int = 0,
    val category: String = "",
    val duration: Long = 0L,
    val subCategory: List<String> = listOf()
)

data class CategoryStats(val count: Int, val totalDuration: Long, val averageDuration: Double)

fun mergeSortedVideos(list1: List<Video>, list2: List<Video>): List<Video> {
    val result = mutableListOf<Video>()
    var i = 0
    var j = 0


    while (i < list1.size && j < list2.size) {
        if (list1[i].timestamp <= list2[j].timestamp) {
            result.add(list1[i++])
        } else {
            result.add(list2[j++])
        }
    }

    while (i < list1.size) result.add(list1[i++])
    while (j < list2.size) result.add(list2[j++])

    return result
}

fun topKWatchedVideos(list: List<Video>, watchCounts: Map<String, Int>, k: Int): List<Video> {
    return list.sortedByDescending { watchCounts[it.id] ?: 0 }.take(k)
}

/**
 * Challenge: Group videos by category and find duration sum
 * Demonstrate groupBy, sumOf, mapValues
 */
fun analyzedVideosByCategory(list: List<Video>): Map<String, CategoryStats> {
    return list.groupBy {
        it.category
    }.mapValues { (_, categoryVideo) ->
        CategoryStats(
            count = categoryVideo.size,
            totalDuration = categoryVideo.sumOf { it.duration },
            averageDuration = categoryVideo.map { it.duration }.average()
        )
    }
}

/**
 * Challenge: Remove duplicates while maintaining order
 * Use distinctBy for complex objects
 */
fun removeDuplicate(list: List<Video>): List<Video> {
    return list.distinctBy {
        it.id
    }
}


// Challenge: Sliding window - Find max views in time window
// Useful for analytics in streaming apps
fun maxViewsInWindow(viewTimestamps: List<Long>, windowSizeMs: Long): Int {
    if (viewTimestamps.isEmpty()) return 0

    val sorted = viewTimestamps.sorted()
    var maxViews = 0
    var left = 0

    for (right in sorted.indices) {
        while (sorted[right] - sorted[left] > windowSizeMs) {
            left++
        }
        maxViews = maxOf(maxViews, right - left + 1)
    }

    return maxViews
}

/**
 * Challenge: Chunking for pagination
 * Common in video list loading
 */
fun paginateVideos(list: List<Video>, pagingSize: Int): List<List<Video>> {
    return list.chunked(pagingSize)
}

/**
 * flatten nested categories
 */
fun flattenCategories(list: List<Video>): List<String> {
    return list.flatMap { it.subCategory }
}

/**
 * COLD FLOW: Video buffering progress
 * Each collector gets independent stream
 */
suspend fun bufferingProgress(): Flow<Int> = flow {
    for (progress in 0..100 step 10) {
        delay(100)
        emit(progress)
    }
}.stateIn(CoroutineScope(Dispatchers.IO))

/**
 * HOT FLOW: Shared video player state
 * Using StateFlow - all collectors see the same state
 */
class VideoPlayerViewModel {
    private val _playbackState = MutableStateFlow<PlaybackState>(PlaybackState.Idle)
    val playbackState: StateFlow<PlaybackState> = _playbackState.asStateFlow()

    private val _bufferProgress = MutableStateFlow(0)
    val bufferProgress: StateFlow<Int> = _bufferProgress.asStateFlow()

    fun play() {
        _playbackState.value = PlaybackState.Playing
    }

    fun pause() {
        _playbackState.value = PlaybackState.Paused
    }
}

suspend fun test() {
    val videoPlayerViewModel = VideoPlayerViewModel()


    videoPlayerViewModel.playbackState.collect {
        when (it) {
            is PlaybackState.Buffering -> TODO()
            is PlaybackState.Error -> TODO()
            PlaybackState.Idle -> TODO()
            PlaybackState.Paused -> TODO()
            PlaybackState.Playing -> TODO()
        }
    }
}

sealed class PlaybackState {
    object Idle : PlaybackState()
    object Playing : PlaybackState()
    object Paused : PlaybackState()
    data class Buffering(val progress: Int) : PlaybackState()
    data class Error(val message: String) : PlaybackState()
}

/**
 * event broadcasting using shared flow
 * one-time events like errors, notification
 */

class VideoEventBroadcaster {
    // replay : don't replay events to new subscribers
    // extraBufferingCapacity : buffer up to 10 events
    private val _events = MutableSharedFlow<VideoEvent>(replay = 0, extraBufferCapacity = 10)
    val event: SharedFlow<VideoEvent> = _events.asSharedFlow()

    suspend fun sendEvent(event: VideoEvent) {
        _events.emit(event)
    }
}

suspend fun testEvent() {
    val videoEventBroadcaster: VideoEventBroadcaster = VideoEventBroadcaster()

    videoEventBroadcaster.sendEvent(VideoEvent.QualityChanged("240"))

    videoEventBroadcaster.event.collect { event ->
    }
}

sealed class VideoEvent {
    data class QualityChanged(val quality: String) : VideoEvent()
    data class VideoEnded(val videoId: String) : VideoEvent()
    data object NetworkLost : VideoEvent()
}

/**
 * Challenge: Combine multiple flows
 * Useful for combining video metadata, subtitles, and analytics
 */
data class Subtitles(val text: String)
data class Analytics(val views: Int)
data class VideoData(val video: Video, val subtitles: Subtitles, val analytics: Analytics)

fun combineVideoData(
    videoFlow: Flow<Video>,
    subtitlesFlow: Flow<Subtitles>,
    analyticsFlow: Flow<Analytics>
): Flow<VideoData> =
    combine(videoFlow, subtitlesFlow, analyticsFlow) { video, subtitle, analytics ->
        VideoData(video = video, subtitles = subtitle, analytics = analytics)
    }

/**
 * Challenge: Debounce search queries
 * Essential for search functionality in video apps
 */

fun searchVideos(queryFlow: Flow<String>): Flow<List<Video>> {
    return queryFlow.debounce(300).distinctUntilChanged().filter { it.length >= 2 }
        .flatMapLatest { query ->
            flow {
                delay(500)
                emit(searchDatabase(query))
            }
        }.flowOn(Dispatchers.IO)
}

fun searchDatabase(query: String): List<Video> {
    return listOf(Video(title = query))
}

/**
 * Challenge: Retry with exponential backoff
 * Important for network requests in streaming
 */
fun fetchVideoWithRetry(videoId: String): Flow<Video> = flow {
    emit(fetchVideo(videoId))
}.retry(3) { cause ->
    delay(1000)
    cause is IOException
}

// With exponential backoff
fun fetchVideoWithExponentialBackoff(videoId: String): Flow<Video> = flow {
    emit(fetchVideo(videoId))
}.retryWhen { cause, attempt ->
    if (cause is IOException && attempt < 3) {
        //delay(1000 * (1 shl attempt.toInt())) // 1s, 2s, 4s
        true
    } else {
        false
    }
}

suspend fun fetchVideo(videoId: String): Video {
    // Simulated API call
    delay(100)
    return Video(videoId, System.currentTimeMillis(), "Sample Video")
}

/**
 * Challenge: Transform cold flow to hot
 * Share network requests among multiple subscribers
 */
fun liveVideoStream(): Flow<VideoFrame> = flow {
    while (true) {
        delay(33) // ~30 fps
        emit(VideoFrame(System.currentTimeMillis()))
    }
}.shareIn(
    scope = CoroutineScope(Dispatchers.IO),
    started = SharingStarted.WhileSubscribed(5000),
    replay = 1
)

data class VideoFrame(val timestamp: Long)

/**
 * Challenge: Buffer overflow strategies
 * Critical for video streaming to prevent memory issues
 */
fun videoFramesWithBuffer(): Flow<VideoFrame> = flow {
    repeat(1000) {
        emit(VideoFrame(System.currentTimeMillis()))
    }
}.buffer(capacity = 50, onBufferOverflow = BufferOverflow.DROP_OLDEST)

// ============================================
// 3. ALGORITHM CHALLENGES
// ============================================

/**
 * Challenge: LRU Cache for video thumbnails
 * Essential for memory-efficient apps
 */
class LRUCache<K, V>(private val capacity: Int) {
    private val cache = LinkedHashMap<K, V>(capacity, 0.75f, true)

    fun get(key: K): V? = cache[key]

    fun put(key: K, value: V) {
        if (cache.size >= capacity) {
            val firstKey = cache.keys.first()
            cache.remove(firstKey)
        }
        cache[key] = value
    }
}

/**
 * Challenge: Find median buffering time
 * Useful for performance analytics
 */
fun findMedianBufferingTime(times: List<Long>): Double {
    val sorted = times.sorted()
    val size = sorted.size
    return if (size % 2 == 0) {
        (sorted[size / 2 - 1] + sorted[size / 2]) / 2.0
    } else {
        sorted[size / 2].toDouble()
    }
}

/**
 * Challenge: Two pointers - Find pair with target watch time
 */
fun findVideoPairWithTargetDuration(
    videos: List<Video>,
    targetDuration: Long
): Pair<Video, Video>? {
    val sorted = videos.sortedBy { it.timestamp }
    var left = 0
    var right = sorted.size - 1

    while (left < right) {
        val sum = sorted[left].timestamp + sorted[right].timestamp
        when {
            sum == targetDuration -> return sorted[left] to sorted[right]
            sum < targetDuration -> left++
            else -> right--
        }
    }
    return null
}

/**
 * Challenge: Binary search - Find video by timestamp
 */
fun findVideoByTimestamp(videos: List<Video>, targetTimestamp: Long): Long? {
    var left = 0
    var right = videos.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2
        when {
            videos[mid].timestamp == targetTimestamp -> return videos[mid].timestamp
            videos[mid].timestamp < targetTimestamp -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return 0L

}

/**
 * Challenge: Implement rate limiter
 * Prevent too many API calls
 */
class RateLimiter(private val maxRequests: Int, private val windowMs: Long) {
    private val requests = mutableListOf<Long>()

    fun allowRequest(): Boolean {
        val now = System.currentTimeMillis()
        requests.removeAll { it < now - windowMs }

        return if (requests.size < maxRequests) {
            requests.add(now)
            true
        } else {
            false
        }
    }
}

/**
 * Challenge: Use sealed classes for network result
 */
sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
    data object Loading : NetworkResult<Nothing>()
}

fun List<Video>.filterByDuration(minSecond: Long, maxSecond: Long): List<Video> {
    return filter { it.timestamp in minSecond..maxSecond }
}

/**
 * Challenge: Inline functions for performance
 */
inline fun <T> measureTimeMillis(block: () -> T): Pair<T, Long> {
    val start = System.currentTimeMillis()
    val result = block()
    val duration = System.currentTimeMillis() - start
    return result to duration
}

/**
 * Challenge: Delegates
 */
class VideoPlayer {

    /*var volume : Int by lazy {
        loadVolumeFromPreference()
    }*/

    private fun loadVolumeFromPreference(): Int = 50
}

/**
 * Challenge: Scope functions
 */
data class VideoConfig(
    val videoId: String,
    var quality: String = "SD",
    var autoPlay: Boolean = false,
    var subtitles: Boolean = false
)

fun createVideoConfig(videoId: String): VideoConfig {
    return VideoConfig(videoId = videoId).apply {
        quality = "HD"
        autoPlay = true
        subtitles = true
    }.also {
        print(it)
    }
}

/**
 * Challenge: Sequence for large datasets
 * More memory efficient than lists
 */
// filter the video list and return the list of timestamp is greater than 0 with their titles
fun processLargeVideoList(videos: List<Video>): List<String> {
    return videos.filter {
        it.timestamp > 1749119098
    }.map {
        it.title
    }.take(100).toList()
}

/**
 * Challenge: Parallel execution
 */
suspend fun loadVideoDetails(videoIds: List<String>): List<Video> = coroutineScope {
    videoIds.map { id ->
        async { fetchVideo(videoId = id) }
    }.awaitAll()
}

/**
 * Challenge: Timeout handling
 */
suspend fun loadVideoWithTimeout(videoId: String): Video? {
    return try {
        withTimeout(5000) {
            fetchVideo(videoId)
        }
    } catch (e: TimeoutCancellationException) {
        null
    }
}

/**
 * Challenge: Structured concurrency
 */
suspend fun fetchComments(videoId: String): List<String> {
    delay(100)
    return listOf("Great Video", "thanks for sharing")
}

suspend fun fetchRecommendations(videoId: String): List<Video> {
    delay(100)
    return emptyList()
}

data class CompleteVideoData(
    val video: Video,
    val comments: List<String>,
    val recommendations: List<Video>
)

suspend fun loadVideoDataConcurrently(videoId: String): CompleteVideoData = coroutineScope {
    val comments = async { fetchComments(videoId) }
    val video = async { fetchVideo(videoId) }
    val recommendations = async { fetchRecommendations(videoId) }

    CompleteVideoData(
        video = video.await(),
        comments = comments.await(),
        recommendations = recommendations.await()
    )
}