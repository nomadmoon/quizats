package ru.nomadmoon.quizats

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var quefrag = questionFrag()
    var fragMan: FragmentManager = fragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val qdarr: ArrayList<quizdata> = ArrayList()
        qdarr.add(quizdata(1, arrayOf("Answer 1","Answer 2","Answer 3")))
        qdarr.add(quizdata(2, arrayOf("XXXSome Answer 1","Some Answer 2","Some Answer 3")))
    //    qdarr.add(quizdata(3, arrayOf("XXXПингвины котики котики котики котики котики котики ","Слоны котики котики котики котики котики котики ","Котики котики котики котики котики котики котики ")))
   //     qdarr.add(quizdata(2, arrayOf("XXXSome Answer 4","Some Answer 4","Some Answer 4")))
   //     qdarr.add(quizdata(2, arrayOf("XXXSome Answer 5","Some Answer 5","Some Answer 5")))
    ///    qdarr.add(quizdata(2, arrayOf("XXXSome Answer 6","Some Answer 6","Some Answer 6")))
    //    qdarr.add(quizdata(2, arrayOf("XXXSome Answer 7","Some Answer 7","Some Answer 7")))
    //    qdarr.add(quizdata(2, arrayOf("XXXSome Answer 8","Some Answer 8","Some Answer 8")))


        val ft = fragMan.beginTransaction()

        ft.replace(R.id.fragmentMy, quefrag)
        ft.commit()

        var test_BUT: Button = Button(this)
        test_BUT.text = "Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo Booo "

        var butParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100)

        //var test_FL = findViewById<LinearLayout>(R.id.fragmentLayout)
       // test_FL.addView(test_BUT)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            //quefrag.quizButton1.text="1111"
            //Log.d("Zzz", quefrag.quizButton1.height.toString())
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

      quefrag.setQuizArr(qdarr)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
