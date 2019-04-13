package ru.nomadmoon.quizats

import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.result_card_view.view.*

class ResultRecyclerViewAdapter  (private val questions: ArrayList<quizdata>, private val answers: ArrayList<quizresult>, private val currentquizdir: String):  RecyclerView.Adapter<ResultRecyclerViewAdapter.ViewHolder>() {



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rightAnsw.text=questions[position].answers[answers[position].right_answer]
        holder.userAnsw.text=questions[position].answers[answers[position].answer]

        holder.resImg.setImageBitmap(BitmapFactory.decodeFile( holder.resImg.context.filesDir.toString()+"/quizes/"+currentquizdir+"/"+(position+1)+".jpg"))


        holder.rightAnsw.setBackgroundColor(Color.parseColor("#00AA00"))

        if (answers[position].right_answer == answers[position].answer)
        {
            holder.userAnsw.setBackgroundColor(Color.parseColor("#00AA00"))
        }
        else
        {
            holder.userAnsw.setBackgroundColor(Color.parseColor("#AA0000"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.result_card_view, parent, false)
        return ViewHolder(view)
    }



    override fun getItemCount(): Int = answers.count()

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val rightAnsw = mView.ResultTextViewRightAnswer
        val userAnsw = mView.ResultTextViewAnswer
        val resImg = mView.resultImageView
    }
}