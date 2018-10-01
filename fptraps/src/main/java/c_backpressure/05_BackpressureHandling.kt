package c_backpressure

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.github.com/"

fun main(vararg args: String) {

    val api = createApi()
    val userName = "romstr"

    Observable.intervalRange(1, userName.length.toLong(), 0, 250, TimeUnit.MILLISECONDS)
            .map { userName.subSequence(0, it.toInt()) }
            .doOnNext { println("User entered: $it") }
//            .debounce(300, TimeUnit.MILLISECONDS)
            .doOnNext { println("Searching for: $it") }
            .observeOn(Schedulers.newThread())
            .flatMapSingle { api.getUser(it.toString()) }
            .doOnNext { println(it) }
            .filter { it.repos > 0 }
            .flatMapSingle { user -> api.getRepos(user.login).map { repos -> user to repos } }
            .blockingSubscribe({ println(it) }, Throwable::printStackTrace)
}

fun createApi(): GitHubApi {
    val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    return retrofit.create(GitHubApi::class.java)
}

data class User(
        val login: String,

        @SerializedName("public_repos")
        val repos: Int,
        @SerializedName("avatar_url")
        val avatar: String)

data class Repo(
        val name: String,
        val language: String,
        @SerializedName("watchers_count")
        val watchers: Int)

interface GitHubApi {

    @GET("users/{username}")
    fun getUser(@Path(value = "username") userName: String): Single<User>

    @GET("users/{username}/repos")
    fun getRepos(@Path(value = "username") userName: String): Single<List<Repo>>
}