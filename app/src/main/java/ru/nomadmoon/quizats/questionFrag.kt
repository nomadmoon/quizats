package ru.nomadmoon.quizats


import android.app.Application
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
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class questionFrag : Fragment(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        quizButton1 = Button(activity)
        quizButton1.text = "Zzzzzzzz b1 Zzzzzzzz "
        quizButton1.tag=0
        quizButton1.setOnClickListener(this)

        quizButtons.add(quizButton1)
   //     quizButtons[0]=quizButton1



        quizButton2 = Button(activity)
        quizButton2.text = "Zzzzzzzz b2"
        quizButton2.tag=1
        quizButton2.setOnClickListener(this)

        quizButtons.add(quizButton2)
    //    quizButtons[1]=quizButton2


        quizButton3 = Button(activity)
        quizButton3.text = "Zzzzzzzz b3"
        quizButton3.tag=2
        quizButton3.setOnClickListener(this)

        quizButtons.add(quizButton3)
    //    quizButtons[2]=quizButton3

    }

    var innerquizdata: ArrayList<quizdata> = arrayListOf(quizdata(0, arrayOf("a1", "a2", "a3")))
    var currentquizdata: quizdata = quizdata(0, arrayOf("a1", "a2", "a3"))
    var quiznumlist: ArrayList<Int> = arrayListOf(0)
    lateinit var quizLayout: LinearLayout
    lateinit var quizImage: ImageView
    lateinit var quizButton1: Button
    lateinit var quizButton2: Button
    lateinit var quizButton3: Button
    var rint = 0
    var rightAnswer = -1

    var quizButtons: ArrayList<Button> = ArrayList()


    override fun onClick(p0: View) {
        //Log.d("Zzz", p0.tag)
        if (p0.tag==rightAnswer)
            {
                  Snackbar.make(view, "Правильный ответ", Snackbar.LENGTH_LONG).show()
            }
        else
            {
               Snackbar.make(view, "Неправильный ответ", Snackbar.LENGTH_LONG).show()
            }

        Log.d("Zzzz befo", quiznumlist.toString())
        when (quiznumlist.count()) {
            0->{
                Log.d("Zzzz last", "quiznumlist.count() = 0")
                //val ma = this.activity as MainActivity
                //ma.fragMan.beginTransaction()
                //ma.fragMan.popBackStack()
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show()
                return
            }
            1-> {
//                val ma = this.activity as MainActivity
                // ma.fragMan.beginTransaction()
                // ma.fragMan.popBackStack()
                rint=0
                Log.d("Zzzz last", "quiznumlist.count() = 1")

            }
        //   2 -> {
        //        Log.d("Zzzz last", "quiznumlist.count() = 2")

        //    }
            else -> {

                //quiznumlist.
                Log.d("Zzzz aft", quiznumlist.toString())
                rint = Random().nextInt(quiznumlist.count() - 1)
            }//rint=0
        }


        Log.d("Zzzz rint", rint.toString())
        Log.d("Zzzz quiznumlist[rint-1]", quiznumlist[rint].toString())
        displayQuiz(rint)
        quiznumlist.removeAt(rint)
    }

    fun setQuizArr(indata: ArrayList<quizdata>)
    {
        innerquizdata = indata
        quiznumlist.clear()
        for (i in 1..indata.count()) {
            quiznumlist.add(i)
        }
        //Log.d("Zzz", "setFields")
        //quizButton1.text = indata.answer1
        //quizButton2.text = indata.answer2
        //quizButton3.text = indata.answer3
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.d("Zzz", "onCreateView")
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

  //      quizButton1 = Button(activity)
   //     quizButton1.text = "Zzzzzzzz b1 Zzzzzzzz "
  //      quizButton1.tag=0
  //      quizButton1.setOnClickListener(this)

  //      quizButtons.add(quizButton1)
  //      quizButtons[0]=quizButton1



  //      quizButton2 = Button(activity)
   //     quizButton2.text = "Zzzzzzzz b2"
  //      quizButton2.tag=1
   //     quizButton2.setOnClickListener(this)

    //    quizButtons.add(quizButton2)
   //    quizButtons[1]=quizButton2


   //     quizButton3 = Button(activity)
   //     quizButton3.text = "Zzzzzzzz b3"
  //      quizButton3.tag=2
  //      quizButton3.setOnClickListener(this)

  //      quizButtons.add(quizButton3)
   //     quizButtons[2]=quizButton3



        var butParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, butHeight)



        quizButton1.layoutParams=butParams
        quizButton2.layoutParams=butParams
        quizButton3.layoutParams=butParams

        rint = Random().nextInt(quiznumlist.count())
        Log.d("Zzzz", rint.toString())
        Log.d("Zzzz", quiznumlist.toString())
        displayQuiz(rint)
        quiznumlist.removeAt(rint)


        quizLayout.addView(quizImage)
        quizLayout.addView(quizButton1)
        quizLayout.addView(quizButton2)
        quizLayout.addView(quizButton3)

        return quizLayout

    }

    fun displayQuiz(quiznumber: Int)
    {
        currentquizdata=innerquizdata[quiznumlist[rint]-1]

        quizImage.setImageBitmap(BitmapFactory.decodeFile(context.filesDir.toString()+"/quizes/1/"+currentquizdata.img_num_id+".jpg"))

        for (z in 0..2) {
            if (currentquizdata.answers[z].substring(0,3)=="QQQ")
            {

                quizButtons[z].text=currentquizdata.answers[z].drop(3)
                rightAnswer = z
            }
            else
            {
                quizButtons[z].text=currentquizdata.answers[z]
            }

        }

    }

}
