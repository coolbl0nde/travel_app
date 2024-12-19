package com.example.travelmap.data.local.prefs

interface TokenProvider{
    fun getToken(): String?
    fun saveToken(token: String)
    fun clearToken()
}