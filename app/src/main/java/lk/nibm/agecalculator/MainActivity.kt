package lk.nibm.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var btnDatePicker: Button
    lateinit var tvSelectedDate : TextView
    lateinit var tvAgeinminutes : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeinminutes = findViewById(R.id.tvAgeinminutes)

        btnDatePicker.setOnClickListener{view ->
            clickDatePicker(view)
//            Toast.makeText(this,"Button wroked",Toast.LENGTH_LONG).show()
        }


    }
    fun clickDatePicker(view: View){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, year, month,dayOfMonth ->
            Toast.makeText(this,"The chosen year is $year ,the month is $month and the day is $dayOfMonth"
                ,Toast.LENGTH_LONG).show()

            val selectedDate = "$dayOfMonth/${month+1}/$year"
            tvSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time/60000

            val currentDate =sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDatetominutes = currentDate!!.time / 60000

            val differenceInminutes = currentDatetominutes - selectedDateInMinutes
            tvAgeinminutes.setText(differenceInminutes.toString())
        }
            ,year,month,day).show()
    }
}