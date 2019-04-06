package ru.nomadmoon.quizats

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.result_card_view.view.*

class ResultRecyclerViewAdapter  (private val questions: ArrayList<quizdata>, private val answers: ArrayList<quizresult>):  RecyclerView.Adapter<ResultRecyclerViewAdapter.ViewHolder>() {

    init {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rightAnsw.text="AAA"
        holder.userAnsw.text="BBB"
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