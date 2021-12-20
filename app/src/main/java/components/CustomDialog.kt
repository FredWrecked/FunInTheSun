package components

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.example.funinthesun.R

class CustomDialog(
    private val applicationContext: Context,
    private val title: String = "",
    private val message: String = "",
    private val positive: String = applicationContext.getString(R.string.yes),
    private val negative: String = applicationContext.getString(R.string.no),
    private val neutral: String = ""
) {

    fun show(
        ){
        val builder = AlertDialog.Builder(applicationContext)

        if(title != "") builder.setTitle(title)
        if(title != "")builder.setMessage(message)

        builder.setPositiveButton(positive) { dialog, which ->
            Toast.makeText(applicationContext,
                positive, Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton(negative) { dialog, which ->
            Toast.makeText(applicationContext,
                negative, Toast.LENGTH_SHORT).show()
        }


        if (neutral != ""){
            builder.setNeutralButton(neutral) { dialog, which ->
                Toast.makeText(applicationContext,
                    neutral, Toast.LENGTH_SHORT).show()
            }
        }

        builder.show()
    }


}