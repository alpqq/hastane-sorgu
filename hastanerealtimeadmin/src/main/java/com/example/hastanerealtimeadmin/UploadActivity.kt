package com.example.hastanerealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hastanerealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            val hastaadi = binding.hastaadi.text.toString()
            val hastadoktoru = binding.hastadoktoru.text.toString()
            val hastaodasi = binding.hastaodasi.text.toString()
            val hastanindurumu = binding.hastanindurumu.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Hata İnformation")
            val hataData = HataData(hastadoktoru, hastaadi, hastanindurumu, hastaodasi)
            databaseReference.child(hastadoktoru).setValue(hataData).addOnSuccessListener {
                binding.hastaadi.text.clear()
                binding.hastadoktoru.text.clear()
                binding.hastaodasi.text.clear()
                binding.hastanindurumu.text.clear()

                Toast.makeText(this,"Saved" , Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed" , Toast.LENGTH_SHORT).show()
            }
        }

    }
}