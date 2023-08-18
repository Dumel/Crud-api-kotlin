package br.senai.sp.jandira.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.create


class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //Ação botão GET:
        findViewById<Button>(R.id.btnGET).setOnClickListener {
            getUserByID()
        }

        //Ação botão POST:
        findViewById<Button>(R.id.btnPOST).setOnClickListener {
            createUser()
        }

        //Ação botão UPDATE:
        findViewById<Button>(R.id.btnPUT).setOnClickListener {
            updateUser()
        }

        //Ação botão DELETE:
        findViewById<Button>(R.id.btnDELETE).setOnClickListener {
            deleteUser()
        }
    }

    //Recupera dados de usuario
    private fun getUserByID() {

        lifecycleScope.launch {
            //CHAMADA PARA ENDPOINT
            val result = apiService.getUserByID("4")

            if (result.isSuccessful) {
                Log.e("GETTING-DATA", "${result.body()?.data}")
            } else {
                Log.e("GETTING-DATA", "${result.message()}")
            }
        }

    }

    //Insere dados de usuario
    private fun createUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Melqui")
                addProperty("job", "Developer")
                addProperty("", "")
                addProperty("", "")
            }

            val result = apiService.createUser(body)

            if (result.isSuccessful) {
                Log.e("CREATE-DATA", "${result.body()}")
            } else {
                Log.e("CREATE-DATA", "${result.message()}")
            }

        }
    }

    private fun updateUser(){
        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Melquisedeque")
                addProperty("job", "Developer systems")

            }

            val result = apiService.updateUser("2", body)
            if (result.isSuccessful) {
                Log.e("UPDATE-DATA", "${result.body()}")
            } else {
                Log.e("UPDATE-DATA", "${result.message()}")
            }

        }
    }

    private fun deleteUser(){
        lifecycleScope.launch {
            val result = apiService.deleteUser("2")
            if (result.isSuccessful) {
                Log.e("DELETE-DATA", "${result}")
            } else {
                Log.e("DELETE-DATA", "${result.message()}")
            }

        }
    }
}