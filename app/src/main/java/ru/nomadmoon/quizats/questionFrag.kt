package ru.nomadmoon.quizats


import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.graphics.BitmapFactory
import android.support.design.widget.Snackbar
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class questionFrag : Fragment(), View.OnClickListener {

    override fun onClick(p0: View) {
        //Log.d("Zzz", p0.tag)
        if (p0.tag==1) {
            Snackbar.make(view, "Правильный ответ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        else
        {
            Snackbar.make(view, "Неправильный ответ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

        }
    }

    lateinit var quizLayout: LinearLayout
    lateinit var quizImage: ImageView
    lateinit var quizButton1: Button
    lateinit var quizButton2: Button
    lateinit var quizButton3: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //context.openFileOutput("test.txt", Context.MODE_PRIVATE).write("Bzzzz".toByteArray())

        var dm: DisplayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(dm)

        var butHeight = dm.heightPixels/8


        quizLayout = LinearLayout(activity)
        quizLayout.orientation=LinearLayout.VERTICAL
        var layParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        layParams.topMargin=30
        quizLayout.layoutParams=layParams

        quizImage = ImageView(activity)


        var imgParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, butHeight*3)
        quizImage.layoutParams = imgParams
        quizImage.setImageBitmap(BitmapFactory.decodeFile(context.filesDir.toString()+"/ping.jpg"))

        quizButton1 = Button(activity)
        quizButton1.text = "Zzzzzzzz b1 Zzzzzzzz "
        quizButton1.tag=0
        quizButton1.setOnClickListener(this)

        quizButton2 = Button(activity)
        quizButton2.text = "Zzzzzzzz b2"
        quizButton2.tag=1
        quizButton2.setOnClickListener(this)


        quizButton3 = Button(activity)
        quizButton3.text = "Zzzzzzzz b3"
        quizButton3.tag=0
        quizButton3.setOnClickListener(this)


        var butParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, butHeight)



        quizButton1.layoutParams=butParams
        quizButton2.layoutParams=butParams
        quizButton3.layoutParams=butParams


        quizLayout.addView(quizImage)
        quizLayout.addView(quizButton1)
        quizLayout.addView(quizButton2)
        quizLayout.addView(quizButton3)

        return quizLayout

    }


}
