package com.example.mypa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.mypa.databinding.MainPageBinding
import com.example.mypa.databinding.WelcomeActivityBinding
import java.util.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private lateinit var mActivity: WelcomeActivityBinding


    lateinit var name: String
    lateinit var lastname: String
    lateinit var email: String
    var gender = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = WelcomeActivityBinding.inflate(layoutInflater)


        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        var cursor: Cursor = db.rawQuery("select count(*) from reg",null)
        //var i = db.execSQL("SELECT count(*) FROM REG").toString()
        cursor.moveToFirst()
        var i = cursor.getInt(0)


        if(i == 0 )
            setContentView(mActivity.root)
        else {
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }


        mActivity.btnReg.setOnClickListener {

            var check = 0

            name = mActivity.txtName.text.toString()
            lastname = mActivity.txtLastname.text.toString()
            email = mActivity.txtEmail.text.toString()
     //       date = mActivity.birthDate.text.toString()

            val pomGndr = mActivity.gndr.checkedRadioButtonId

            if (pomGndr == R.id.gndrFemale)
                gender = 1
            else
                gender = 0


            if (name == "") {
                mActivity.txtName.setBackgroundResource(R.drawable.bg_wrong)
            } else {
                mActivity.txtName.setBackgroundResource(R.drawable.bg_fields)
                check += 1
            }
            if (lastname == "")
                mActivity.txtLastname.setBackgroundResource(R.drawable.bg_wrong)
            else {
                check += 1
                mActivity.txtLastname.setBackgroundResource(R.drawable.bg_fields)
            }
            if (email == "")
                mActivity.txtEmail.setBackgroundResource(R.drawable.bg_wrong)
            else {
                check += 1
                mActivity.txtEmail.setBackgroundResource(R.drawable.bg_fields)
            }
  /*         if (date == "")
                mActivity.txtName.setBackgroundResource(R.drawable.bg_wrong)
            else {
                mActivity.txtName.setBackgroundResource(R.drawable.bg_fields)
            }
   */         if (check ==3 ) {
                helper.insertData(name,lastname,email)

                val intent = Intent(this, MainPageActivity::class.java)
                startActivity(intent)
            }

        }

    }
}