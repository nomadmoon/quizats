package ru.nomadmoon.quizats


import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ResultFragment : Fragment() {
    override fun onStart() {
        super.onStart()
        countRightAnswers()
    }


    var currentquizdir: String = "-1"
    var questions: ArrayList<quizdata> = arrayListOf(quizdata(-1, arrayOf("")))
    var answers: ArrayList<quizresult> = arrayListOf(quizresult(-1,-1))


    var resultText: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inflated: View = inflater.inflate(R.layout.fragment_result, container, false)

        countRightAnswers()

        val tv = inflated.findViewById<TextView>(R.id.resultTextView)
        val recV = inflated.findViewById<RecyclerView>(R.id.resultRecyclerView)
        recV.adapter = ResultRecyclerViewAdapter(questions, answers, currentquizdir)
        recV.layoutManager=LinearLayoutManager(context)

        tv.text=resultText

        return inflated
    }

    fun setQandA(ques: ArrayList<quizdata>, answ: ArrayList<quizresult>)
    {
        questions=ques
        answers=answ
    }



    fun countRightAnswers()
    {
        var rightAnwrsCount = 0

        for (answr in answers)
        {
            if (answr.answer==answr.right_answer) rightAnwrsCount++
        }

        resultText="Количество правильных ответов: "+rightAnwrsCount

    }

}
