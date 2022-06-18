package com.gmail.bogumilmecel2.produkty.common.data.api

import com.gmail.bogumilmecel2.produkty.BuildConfig
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Data
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ItemsApi {

    @GET("/oauth/token")
    suspend fun logInUser(
        @Query("password") password:String,
        @Query("username") username:String,
        @Query("client_secret") clientSecret:String = BuildConfig.CLIENT_SECRET,
        @Query("client_id") clientId:String = BuildConfig.CLIENT_ID,
        @Query("grant_type") grantType:String = "password"
    ): AccessToken

    @GET("api/v3/{organizationId}/items")
    suspend fun getItems(
        @Path("organizationId") organizationId:String = BuildConfig.ORGANIZATION_ID,
        @Query("include") include:String = "tax",
        @Query("access_token") accessToken: String
    ):Data

    @POST("/oauth/token")
    suspend fun refreshToken(
        @Query("client_secret") clientSecret:String = BuildConfig.CLIENT_SECRET,
        @Query("client_id") clientId:String = BuildConfig.CLIENT_ID,
        @Query("grant_type") grantType:String = "refresh",
        @Query("refresh_token") refreshToken:String
    )
}