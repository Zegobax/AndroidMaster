package com.biodan997.androidmaster.Apps.IMCalculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.biodan997.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat


class IMCalculator_Activity : AppCompatActivity() {

    //-------------------Variables----------------------//
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var radioSelect: Float = 0.0f
    private var currentWeight: Int = 60
    private var currentAge: Int = 15
    private var currentHeight: Int = 120
    private var maleValue: Double = 0.0
    private var femaleValue: Double=0.0

    //---------------------Lateinit Variables--------------------------------------//

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rgHeight: RangeSlider
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnCalculate: Button
    private lateinit var rGroupSelect: RadioGroup



    companion object{
        const val IMC_KEY="resultA";
        const val WFD_KEY="resultB";
        const val GCT_KEY="resultC"
    }


//-------------------------Override-----------------------------//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcalculator)
        initComponent()
        initListeners()
        initUI()
    }

    //---------------------Components------------------------//

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rgHeight = findViewById(R.id.rgHeight)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeightValue)
        tvAge = findViewById(R.id.tvAge)
        btnSubstractAge = findViewById(R.id.btnSubstractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnCalculate = findViewById(R.id.btnCalculate)
        rGroupSelect = findViewById(R.id.rGroup)

    }

   //--------------------------------Listeners-----------------------------------//

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
            maleValue=66.5


        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
            femaleValue=665.0

        }
        rgHeight.addOnChangeListener { _, value, _ ->

            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubstractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnSubstractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        rGroupSelect.setOnCheckedChangeListener { Group, i ->
            when (i) {
                R.id.RBtnLow -> {
                    radioSelect = 1.5f
                }

                R.id.RBtnMedium -> {
                    radioSelect = 1.84f
                }

                R.id.RBtnHard -> {
                    radioSelect = 2.2f
                }

            }
        }
        btnCalculate.setOnClickListener {
            val resultIMC=calculateIMC()
            Log.i("biodan997", "Log IMC en btnCalculate $resultIMC")

            val resultWFD=calculateWaterforDay ()
            Log.i("biodan997", "Log IMC en btnCalculate $resultWFD")

            val resultGCT= if(maleValue==66.5) {

                val df = DecimalFormat("#.##")
                val maleTMB=(maleValue+(currentWeight*13.8)+(5*currentHeight)-(6.8*currentAge))*radioSelect
                df.format(maleTMB).toDouble()

            }else{
                val df = DecimalFormat("#.##")
                val femaleTMB=(femaleValue+(currentWeight*9.6)+(1.8*currentHeight)-(4.7*currentAge))*radioSelect
                df.format(femaleTMB).toDouble()
                //femaleValue=0.0



            }
            Log.i("biodan997", "Log resultGCT fuera del condicional $resultGCT")
            maleValue=0.0
            femaleValue=0.0

            navigate(resultIMC, resultWFD,resultGCT)


        }

    }


//-----------------------------Intent Class----------------------------------//

    private fun navigate(resultIMC: Double,resultWFD: Double, resultGCT: Double){

        navigateResults(resultIMC, resultWFD, resultGCT)

    }


    private fun navigateResults(resultIMC:Double,resultWFD:Double,resultGCT:Double){

        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, resultIMC)
        intent.putExtra(WFD_KEY, resultWFD)
        intent.putExtra(GCT_KEY, resultGCT)
        Log.d("biodan997", "IMC enviado a ResultActivity: $resultIMC")
        Log.d("biodan997", "WFD enviado a ResultActivity: $resultWFD")
        Log.d("biodan997", "GCT enviado a ResultActivity: $resultGCT")
        startActivity(intent)
    }



//--------------------------------Calculates--------------------------------------//

    private fun calculateIMC():Double {
        val df = DecimalFormat("#.##")
        val imc =currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()

    }

    
    private fun calculateWaterforDay():Double{

        val glassWater=currentWeight/7
        return glassWater*250.toDouble()

    }



    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference = if (isSelectedComponent) {
            R.color.background_cardView_selected
        } else {
            R.color.background_cardView

        }

        return ContextCompat.getColor(this, colorReference)

    }
//----------------------------UI-----------------------------//

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }


}



