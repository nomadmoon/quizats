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
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.io.File
import java.io.InputStream
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.content_main.*
import java.util.zip.ZipFile
import android.content.Intent
import ru.nomadmoon.quizats.R.id.textView
import android.R.attr.data
import android.app.Activity
import java.io.FileOutputStream


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var quefrag = questionFrag()
    var fragMan: FragmentManager = fragmentManager
    var qdarr: ArrayList<quizdata> = ArrayList()
    var qmd = quizmetadata("Тест не выбран", "Загрузите файл с тестом")
    val gson = GsonBuilder().setPrettyPrinting().create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        qdarr.add(quizdata(1, arrayOf("Answer 1","Answer 2","Answer 3")))
        qdarr.add(quizdata(2, arrayOf("XXXSome Answer 1","Some Answer 2","Some Answer 3")))
        qdarr.add(quizdata(3, arrayOf("XXXПингвины котики котики котики котики котики котики ","Слоны котики котики котики котики котики котики ","Котики котики котики котики котики котики котики ")))
        qdarr.add(quizdata(2, arrayOf("XXXSome Answer 4","Some Answer 4","Some Answer 4")))
     //   qdarr.add(quizdata(2, arrayOf("XXXSome Answer 5","Some Answer 5","Some Answer 5")))
    ///    qdarr.add(quizdata(2, arrayOf("XXXSome Answer 6","Some Answer 6","Some Answer 6")))
    //    qdarr.add(quizdata(2, arrayOf("XXXSome Answer 7","Some Answer 7","Some Answer 7")))
    //    qdarr.add(quizdata(2, arrayOf("XXXSome Answer 8","Some Answer 8","Some Answer 8")))

        val quizesDir = File(filesDir.toString()+"/quizes")
        if (!quizesDir.exists()) quizesDir.mkdir()
        val quizesCount = File(filesDir.toString()+"/quizes/counter.txt")
        if (!quizesCount.exists())
        {
            quizesCount.createNewFile()
            quizesCount.writeText("0")
        }


                // loadFromZip()


        val quizQuestionsJSON = File(filesDir.toString().plus("/quizes/1/quiz_questions.txt")).readText()


        val collectionType = object : TypeToken<ArrayList<quizdata>>() {}.type
        var qdarr_file: ArrayList<quizdata> = ArrayList()

        try {
            qdarr_file = gson.fromJson(quizQuestionsJSON, collectionType)
        }
        catch (e: JsonParseException)
        {
           Snackbar.make(findViewById(R.id.rootView), "Ошибка разбора JSON файла с вопросами (quiz_questions.txt)", Snackbar.LENGTH_LONG).show()
        }








        //return
       // var qdarr_json = gson.toJson(qmd).toString()

      //  Log.d("Aaaaa", qdarr_json)

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

      quefrag.setQuizArr(qdarr_file)
    }

    fun loadFromZip()
    {


        // TEMPORARY!!!!
        val dirtodel = File(filesDir.toString().plus("/quizes/1"))
        if (dirtodel.exists()) dirtodel.deleteRecursively()

        val fzip = ZipFile(filesDir.toString().plus("/test.zip"))
        var zipentries = fzip.entries().iterator()

       // var meta_exists: Boolean = false
       // var questions_exists: Boolean = false

    //    for (zipentry in zipentries)
     //   {
            // val outfile = File(filesDir.toString()+"/test_unzip/"+iii.name).outputStream()
            // fzip.getInputStream(iii).copyTo(outfile)
     //       if (zipentry.name=="quiz_questions.txt") questions_exists=true
          //  if (zipentry.name=="quiz_metadata.txt") meta_exists=true

      //  }

        if (fzip.getEntry("quiz_questions.txt")==null) {
            Snackbar.make(findViewById(R.id.rootView), "Отсутствует файл с вопросами (quiz_questions.txt)", Snackbar.LENGTH_LONG).show()
            return
        }
        if (fzip.getEntry("quiz_metadata.txt")==null) {
            Snackbar.make(findViewById(R.id.rootView), "Отсутствует файл с метаданными (quiz_metadata.txt)", Snackbar.LENGTH_LONG).show()
            return
        }

        var quizQuestionsFile = fzip.getInputStream(fzip.getEntry("quiz_questions.txt"))
        var quizQuestionsJSON = quizQuestionsFile.bufferedReader().readText()

        val collectionType = object : TypeToken<ArrayList<quizdata>>() {}.type
        var qdarr_test: ArrayList<quizdata> = ArrayList()

        try {
            qdarr_test = gson.fromJson(quizQuestionsJSON, collectionType)
        }
        catch (e: JsonParseException)
        {
            Snackbar.make(findViewById(R.id.rootView), "Ошибка разбора JSON файла с вопросами (quiz_questions.txt)", Snackbar.LENGTH_LONG).show()
        }


        var quizMetaFile = fzip.getInputStream(fzip.getEntry("quiz_metadata.txt"))
        var quizMetaJSON = quizMetaFile.bufferedReader().readText()

        var quizMetaTest =  quizmetadata("Тест не выбран", "Загрузите файл с тестом")

        try {
            quizMetaTest = gson.fromJson(quizMetaJSON, quizmetadata::class.java)
        }
        catch (e: JsonParseException)
        {
            Snackbar.make(findViewById(R.id.rootView), "Ошибка разбора JSON файла с метаданными (quiz_metadata.txt)", Snackbar.LENGTH_LONG).show()
        }

        for (qdarr_item in qdarr_test)
        {
            if (fzip.getEntry(qdarr_item.img_num_id.toString()+".jpg")==null)
            {
                Snackbar.make(findViewById(R.id.rootView), "Не найден файл "+qdarr_item.img_num_id.toString()+".jpg", Snackbar.LENGTH_LONG).show()
            }
        }

         zipentries = fzip.entries().iterator()

        val quizesCount = File(filesDir.toString()+"/quizes/counter.txt")
        //var cc = quizesCount.readText().toInt()
        //cc=cc+1
        //quizesCount.writeText(cc.toString())

        //var quizesDirCC = 1
        //val quizesDirCC = File(filesDir.toString()+"/quizes/"+cc)
        val quizesDirCC = File(filesDir.toString()+"/quizes/1")

        if (!quizesDirCC.exists()) quizesDirCC.mkdir()

        for (iii in zipentries)
        {
            val outfile = File(quizesDirCC.toString()+"/"+iii.name).outputStream()
            fzip.getInputStream(iii).copyTo(outfile)
            // Log.d("Aaaaa", iii.name)
        }

        File(filesDir.toString()+"/test.zip").delete()

        }



    fun loadFromZipStream()
    {
        val fzip = ZipFile(filesDir.toString().plus("/test.zip"))
        var zipentries = fzip.entries().iterator()



        if (fzip.getEntry("quiz_questions.txt")==null) {
            Snackbar.make(findViewById(R.id.rootView), "Отсутствует файл с вопросами (quiz_questions.txt)", Snackbar.LENGTH_LONG).show()
            return
        }
        if (fzip.getEntry("quiz_metadata.txt")==null) {
            Snackbar.make(findViewById(R.id.rootView), "Отсутствует файл с метаданными (quiz_metadata.txt)", Snackbar.LENGTH_LONG).show()
            return
        }

        var quizQuestionsFile = fzip.getInputStream(fzip.getEntry("quiz_questions.txt"))
        var quizQuestionsJSON = quizQuestionsFile.bufferedReader().readText()

        val collectionType = object : TypeToken<ArrayList<quizdata>>() {}.type
        var qdarr_test: ArrayList<quizdata> = ArrayList()

        try {
            qdarr_test = gson.fromJson(quizQuestionsJSON, collectionType)
        }
        catch (e: JsonParseException)
        {
            Snackbar.make(findViewById(R.id.rootView), "Ошибка разбора JSON файла с вопросами (quiz_questions.txt)", Snackbar.LENGTH_LONG).show()
        }


        var quizMetaFile = fzip.getInputStream(fzip.getEntry("quiz_metadata.txt"))
        var quizMetaJSON = quizMetaFile.bufferedReader().readText()

        var quizMetaTest =  quizmetadata("Тест не выбран", "Загрузите файл с тестом")

        try {
            quizMetaTest = gson.fromJson(quizMetaJSON, quizmetadata::class.java)
        }
        catch (e: JsonParseException)
        {
            Snackbar.make(findViewById(R.id.rootView), "Ошибка разбора JSON файла с метаданными (quiz_metadata.txt)", Snackbar.LENGTH_LONG).show()
        }

        for (qdarr_item in qdarr_test)
        {
            if (fzip.getEntry(qdarr_item.img_num_id.toString()+".jpg")==null)
            {
                Snackbar.make(findViewById(R.id.rootView), "Не найден файл "+qdarr_item.img_num_id.toString()+".jpg", Snackbar.LENGTH_LONG).show()
            }
        }

        zipentries = fzip.entries().iterator()

        val quizesCount = File(filesDir.toString()+"/quizes/counter.txt")
        var cc = quizesCount.readText().toInt()
        cc=cc+1
        quizesCount.writeText(cc.toString())

        var quizesDirCC = 1
        //    val quizesDirCC = File(filesDir.toString()+"/quizes/"+cc)
        //   if (!quizesDirCC.exists()) quizesDirCC.mkdir()

        for (iii in zipentries)
        {
            val outfile = File(quizesDirCC.toString()+"/"+iii.name).outputStream()
            fzip.getInputStream(iii).copyTo(outfile)
            // Log.d("Aaaaa", iii.name)
        }



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
            R.id.nav_load_file -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "*/*"
                startActivityForResult(intent, 10510)

            }
            R.id.nav_preferences -> {

            }
            R.id.nav_exit -> {

            }
            R.id.nav_hlp -> {

            }
            R.id.nav_about -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            10510 -> if (resultCode === Activity.RESULT_OK) {
                //val FilePath = data.getData().path
                //textView.setText(FilePath)
                var filetodel = File(filesDir.toString()+"/test.zip")
                if (filetodel.exists()) filetodel.delete()

                val inpstr = getContentResolver().openInputStream(data?.data)

                try {
                    val out = FileOutputStream(filesDir.toString()+"/test.zip")
                    try {
                        inpstr.copyTo(out)
                    } finally {
                        out.close()
                    }
                } finally {
                    inpstr.close()
                }

                loadFromZip()

                Snackbar.make(findViewById(R.id.rootView), "RESULT_OK", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}

// https://ufile.io/4uqkv
// https://ufile.io/kf7sz