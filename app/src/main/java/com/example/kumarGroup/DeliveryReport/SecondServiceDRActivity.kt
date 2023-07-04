package com.example.kumarGroup.DeliveryReport

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kumarGroup.R

class SecondServiceDRActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_service_d_r)



        progressDialog = ProgressDialog(this@SecondServiceDRActivity)
        progressDialog!!.show()
        progressDialog!!.setContentView(R.layout.progress_dialog)
        progressDialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)


        val Rclv_SecondService = findViewById<RecyclerView>(R.id.Rclv_SecondService);
        Rclv_SecondService.setHasFixedSize(true)
        Rclv_SecondService.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)





    }
}