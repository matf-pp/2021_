package com.example.mypa

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.theme_choice.*
import kotlinx.android.synthetic.main.welcome_activity.*
import java.util.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private var registered = false
    lateinit var name: String
    lateinit var lastname: String
    lateinit var email: String
    lateinit var date : String
    var gender = 1
    var check = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)

        if(check == 4){
            setContentView(R.layout.main_page)
        }
        else{

        /*txtName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                txtLastname.setBackgroundColor(Color.parseColor("#FFF"))
            }

        })
*/
        /*
        txtLastname.setOnClickListener {
            txtLastname.setBackgroundColor(Color.parseColor("#FFF"))
        }

        txtEmail.setOnClickListener {
            txtEmail.setBackgroundColor(Color.parseColor("#FFF"))
        }

        birthDate.setOnClickListener {
            birthDate.setBackgroundColor(Color.parseColor("#FFF"))
        }

        */

        btnReg.setOnClickListener {

            name = txtName.text.toString()
            lastname = txtLastname.text.toString()
            email = txtEmail.text.toString()
            date = birthDate.text.toString()


            val pomGndr = gndr.checkedRadioButtonId

            if (pomGndr == R.id.gndrFemale)
                gender = 1
            else
                gender = 0


            if (name == "")
                txtName.setBackgroundColor(Color.parseColor("#cd432e"))
            else

                check += 1

            if (lastname == "")
                txtLastname.setBackgroundColor(Color.parseColor("#cd432e"))
            else
                check += 1

            if (email == "")
                txtEmail.setBackgroundColor(Color.parseColor("#cd432e"))
            else
                check += 1


            if (date == "")
                birthDate.setBackgroundColor(Color.parseColor("#cd432e"))
            else
                check += 1

            if (check == 4)
                setContentView(R.layout.to_do)

        }
        }
    }

}