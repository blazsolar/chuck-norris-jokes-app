package com.github.blazsolar.chuck.integration.data.api;

import retrofit.http.GET;
import rx.Observable;
import com.github.blazsolar.chuck.integration.data.api.model.Joke;
import com.github.blazsolar.chuck.integration.data.api.model.Response;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public interface JokesService {

    @GET("/jokes/random")
    Observable<Response<Joke>> randomJoke();

}
