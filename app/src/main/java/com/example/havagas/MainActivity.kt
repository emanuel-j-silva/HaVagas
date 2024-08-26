package com.example.havagas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.celularRg.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.simRb -> amb.celularEt.visibility = View.VISIBLE
                R.id.naoRb -> amb.celularEt.visibility = View.GONE
            }
        }

        amb.formacaoSp.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {

                val formacao = (view as TextView).text.toString()
                if(formacao == "Ensino Fundamental" || formacao == "Ensino Médio"){
                    amb.run {
                        anoFormacaoEt.visibility = View.VISIBLE

                        anoConclusaoEt.visibility = View.GONE
                        instituicaoEt.visibility = View.GONE
                        monografiaEt.visibility = View.GONE
                        orientadorEt.visibility = View.GONE
                    }
                } else if(formacao == "Graduação" || formacao == "Especialização"){
                    amb.run{
                        anoConclusaoEt.visibility = View.VISIBLE
                        instituicaoEt.visibility = View.VISIBLE

                        anoFormacaoEt.visibility = View.GONE
                        monografiaEt.visibility = View.GONE
                        orientadorEt.visibility = View.GONE
                    }
                }else{
                    amb.run{
                        anoConclusaoEt.visibility = View.VISIBLE
                        instituicaoEt.visibility = View.VISIBLE
                        monografiaEt.visibility = View.VISIBLE
                        orientadorEt.visibility = View.VISIBLE

                        anoFormacaoEt.visibility = View.GONE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //NSA
            }
        }

    }
}