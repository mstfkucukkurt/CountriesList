package com.example.country_list.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.country_list.model.Country

@Dao
interface CountryDao {

    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries: Country) : List<Long>
    //Insert -> Insert INTO
    // suspend -> coroutine, pause & resume
    // vararg -> multiple country objects
    // List<Long> -> primary keys

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}