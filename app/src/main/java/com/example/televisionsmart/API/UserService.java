package com.example.televisionsmart.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("auth/login")
    Call<LoginResponce> userLogin(@Body LoginRequest loginRequest);
}
