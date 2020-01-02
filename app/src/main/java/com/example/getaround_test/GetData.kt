package com.example.getaround_test

import com.example.getaround_test.pojo.Car
import io.reactivex.Observable
import retrofit2.http.GET

interface GetData {
    //Describe the request type and the relative URL
    @GET("drivy/jobs/master/android/api/cars.json")
    fun getData() : Observable<List<Car>>

}