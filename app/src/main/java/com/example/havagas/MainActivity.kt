package com.example.havagas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.limparBt.setOnClickListener{
            clear()
        }

        amb.salvarBt.setOnClickListener{
            val formulario = Formulario(
                nome = amb.nomeEt.text.toString(),
                email = amb.emailEt.text.toString(),
                recebeEmails = amb.checkEmail.isChecked,
                telefone = amb.telefoneEt.text.toString(),
                tipoTelefone = when(amb.telefoneRg.checkedRadioButtonId){
                    R.id.comercialRb -> "Comercial"
                    R.id.residencialRb -> "Residencial"
                    else -> "Não informado"
                },
                celular = if (amb.celularRg.checkedRadioButtonId == R.id.simRb) amb.celularEt.text.toString() else null,
                sexo = when (amb.sexoRg.checkedRadioButtonId) {
                    R.id.masculinoRb -> "Masculino"
                    R.id.femininoRb -> "Feminino"
                    else -> "Não informado"
                },
                dataNascimento = amb.dataNascEt.text.toString(),
                formacao = amb.formacaoSp.selectedItem.toString(),
                anoFormacao = if (amb.formacaoSp.selectedItem == "Ensino Fundamental" || amb.formacaoSp.selectedItem == "Ensino Médio") amb.anoFormacaoEt.text.toString() else null,
                anoConclusao = if (amb.formacaoSp.selectedItem == "Graduação" || amb.formacaoSp.selectedItem == "Especialização" || amb.formacaoSp.selectedItem == "Mestrado" || amb.formacaoSp.selectedItem == "Doutorado") amb.anoConclusaoEt.text.toString() else null,
                instituicao = if (amb.formacaoSp.selectedItem == "Graduação" || amb.formacaoSp.selectedItem == "Especialização" || amb.formacaoSp.selectedItem == "Mestrado" || amb.formacaoSp.selectedItem == "Doutorado") amb.instituicaoEt.text.toString() else null,
                monografia = if (amb.formacaoSp.selectedItem == "Mestrado" || amb.formacaoSp.selectedItem == "Doutorado") amb.monografiaEt.text.toString() else null,
                orientador = if (amb.formacaoSp.selectedItem == "Mestrado" || amb.formacaoSp.selectedItem == "Doutorado") amb.orientadorEt.text.toString() else null
            )

            showPopup(formulario)
        }

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

    private fun showPopup(formulario: Formulario) {
        val message = """
        Nome: ${formulario.nome}
        Email: ${formulario.email}
        Receber Emails: ${if (formulario.recebeEmails) "Sim" else "Não"}
        Telefone: ${formulario.telefone}
        Tipo de telefone: ${formulario.tipoTelefone}
        ${formulario.celular?.let { "Celular: $it" } ?: ""}
        Sexo: ${formulario.sexo}
        Data de Nascimento: ${formulario.dataNascimento}
        Formação: ${formulario.formacao}
        ${formulario.anoFormacao?.let { "Ano de Formação: $it" } ?: ""}
        ${formulario.anoConclusao?.let { "Ano de Conclusão: $it" } ?: ""}
        ${formulario.instituicao?.let { "Instituição: $it" } ?: ""}
        ${formulario.monografia?.let { "Título de Monografia: $it" } ?: ""}
        ${formulario.orientador?.let { "Orientador: $it" } ?: ""}
    """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Informações do Formulário")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun clear() {
        amb.nomeEt.text.clear()
        amb.emailEt.text.clear()
        amb.checkEmail.isChecked = false
        amb.telefoneEt.text.clear()
        amb.telefoneRg.check(R.id.comercialRb)
        amb.celularRg.check(R.id.simRb)
        amb.celularEt.text.clear()
        amb.sexoRg.check(R.id.masculinoRb)
        amb.dataNascEt.text.clear()
        amb.formacaoSp.setSelection(0)
        amb.anoFormacaoEt.text.clear()
        amb.anoConclusaoEt.text.clear()
        amb.instituicaoEt.text.clear()
        amb.monografiaEt.text.clear()
        amb.orientadorEt.text.clear()

        amb.anoConclusaoEt.visibility = View.GONE
        amb.instituicaoEt.visibility = View.GONE
        amb.monografiaEt.visibility = View.GONE
        amb.orientadorEt.visibility = View.GONE

        amb.interesseEt.text.clear()
    }
}