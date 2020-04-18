package com.example.kinopoisk

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.telecom.Call
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Response

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

      //  apiClient.getApiService().login(LoginRequest(email = "s@sample.com", password = "mypassword"))
           // .enqueue(object : Call.Callback<LoginResponse> {
             //   override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Error logging in
                }

               // override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    //val loginResponse = response.body()

                   /// if (loginResponse?.statusCode == 200 && loginResponse.user != null) {
                   //     sessionManager.saveAuthToken(loginResponse.authToken)
                  //  } else {
                  //      // Error logging in
                    }
                //
          //  })

    //}
//}