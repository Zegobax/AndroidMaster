package com.biodan997.androidmaster.Apps.IMCalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.biodan997.androidmaster.Apps.IMCalculator.IMCalculator_Activity.Companion.GCT_KEY
import com.biodan997.androidmaster.Apps.IMCalculator.IMCalculator_Activity.Companion.IMC_KEY
import com.biodan997.androidmaster.Apps.IMCalculator.IMCalculator_Activity.Companion.WFD_KEY
import com.biodan997.androidmaster.R



class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvWFD: TextView
    private lateinit var tvGCT: TextView
    private lateinit var btnRecalculate: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val resultIMC: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        val resultWFD: Double = intent.extras?.getDouble(WFD_KEY) ?: -1.0

        val resultGCT: Double = intent.extras?.getDouble(GCT_KEY)?: -1.0



        initComponent()
        initUImc(resultIMC)
        changeWFDtext(resultWFD)
        changeGCTtext(resultGCT)
        initListenners()

    }

    private fun initListenners(){
        btnRecalculate.setOnClickListener { onBackPressed() }
    }


    private fun initComponent(){

        tvIMC=findViewById(R.id.tvIMC)
        tvResult=findViewById(R.id.tvResult)
        tvDescription=findViewById(R.id.tvDescription)
        tvWFD=findViewById(R.id.tvWFD)
        tvGCT=findViewById(R.id.tvGCT)
        btnRecalculate=findViewById(R.id.btnRecalculate)


    }

    private fun initUImc(resultIMC: Double) {

        tvIMC.text=resultIMC.toString()
        when(resultIMC){
            in 0.00..18.50->   {//Bajo Peso
                tvResult.text=getString(R.string.title_low_weight)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.low_weight))
                tvDescription.text=getString(R.string.descrip_low_weight)
            }
            in 18.51..24.99->{//Peso Normal
                tvResult.text=getString(R.string.title_normal_weight)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.normal_weight))
                tvDescription.text=getString(R.string.descrip_normal_weight)

            }
            in 25.00..29.99->{//Sobrepeso
                tvResult.text=getString(R.string.title_over_weight)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.over_weight))
                tvDescription.text=getString(R.string.descrip_over_weight)

            }
            in 30.00..99.00-> {//Obesidad
                tvResult.text=getString(R.string.title_obesity)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesity))
                tvDescription.text=getString(R.string.descrip_obesity)

            }
            else->{//error
                tvIMC.text=getString(R.string.error)
                tvResult.text=getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesity))
                tvDescription.text=getString(R.string.error)
            }
        }


    }

    private fun changeWFDtext(resultWFD:Double){

        tvWFD.text=resultWFD.toString()
        tvWFD.setTextColor(ContextCompat.getColor(this,R.color.water))
    }

    private fun changeGCTtext(resultGCT:Double){

        tvGCT.text=resultGCT.toString()
        tvGCT.setTextColor(ContextCompat.getColor(this,R.color.calories))
    }



}