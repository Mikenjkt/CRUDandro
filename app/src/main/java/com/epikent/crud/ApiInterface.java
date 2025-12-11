package com.epikent.crud;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    // We no longer use the POST login/register for the new logic
    // We only need GET users

    @GET("api/users?page=1") // Get the first page of sample users
    Call<UserListResponse> getUsers();
}

