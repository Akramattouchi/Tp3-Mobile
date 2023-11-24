package com.gl4.tp1

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var nomInput: TextInputEditText
    lateinit var prenomInput: TextInputEditText
    lateinit var adresseInput: TextInputEditText
    lateinit var miniRadioButton: RadioButton
    lateinit var moyenneRadioButton: RadioButton
    lateinit var maxiRadioButton: RadioButton
    lateinit var fromageChip: Chip
    lateinit var tomateChip: Chip
    lateinit var champignonChip: Chip
    lateinit var commanderBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nomInput = findViewById(R.id.textFieldNom)
        prenomInput = findViewById(R.id.textFieldPrenom)
        adresseInput = findViewById(R.id.textFieldAdresse)

        miniRadioButton = findViewById(R.id.radio_button_Mini)
        moyenneRadioButton = findViewById(R.id.radio_button_Moyenne)
        maxiRadioButton = findViewById(R.id.radio_button_Maxi)

        fromageChip = findViewById(R.id.chipFromage)
        champignonChip = findViewById(R.id.chipChampignon)
        tomateChip = findViewById(R.id.chipTomate)

        commanderBtn = findViewById(R.id.btnCommander)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), 111)

        }


        commanderBtn.setOnClickListener {

            if((!miniRadioButton.isChecked && !maxiRadioButton.isChecked && !moyenneRadioButton.isChecked)
                || nomInput.text.toString().isEmpty()
                || prenomInput.text.toString().isEmpty()
                || adresseInput.text.toString().isEmpty()){
                val toast = Toast.makeText(applicationContext, "Tous les champs sont obligatoires", Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                var message : String = "Nom et prénom : ${nomInput.text.toString()} ${prenomInput.text.toString()} \n" +
                        "Adresse : ${adresseInput.text.toString()} \n" +
                        "Taille : ${taillePizza()} \n" +
                        "Ingredients : ${ingredientsPizza()}"
                //Put your phone number here
                sendMessage("+216???", message)

                val toast = Toast.makeText(applicationContext, "Commande envoyée", Toast.LENGTH_LONG)
                toast.show()
                val intent = Intent(this, SplashActivity::class.java)
                startActivity(intent)
            }


        }
    }
}