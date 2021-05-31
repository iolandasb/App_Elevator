package com.example.exercicio_aula_09

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var andarAtual: TextView
    lateinit var pessoasNoElevador: TextView
    lateinit var digitarAndar: EditText
    lateinit var irParaAndar: Button
    lateinit var entrar: Button
    lateinit var sair: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        andarAtual = findViewById(R.id.edtAndar)
        pessoasNoElevador = findViewById(R.id.edtPessoasElevador)
        digitarAndar = findViewById(R.id.edtDigitarAndar)
        irParaAndar = findViewById(R.id.btnIrAndar)
        entrar = findViewById(R.id.btnEntrar)
        sair = findViewById(R.id.btnSair)

        var elevador = Elevador() //Aqui se está chamando a classe "Elevador"

        entrar.setOnClickListener { //Ao clicar no botão "entrar", executar o que segue.
            var adicionar = elevador.adicionarPessoa() //Aqui se colocou o RESULTADO da função (e não a função em si) "adicionarPessoa()" da classe "Elevador" dentro de uma variável.
            if (adicionar != -1) { //Aqui se está dizendo "se a função "adicionarPessoa()" (da classe "Elevador") for diferente do erro que ela gera (aqui representado por "-1", que é qualquer coisa acima do número máximo de pessoas ("5")), então..."
                pessoasNoElevador.setText(adicionar.toString() + "/5 \n pessoas no elevador") //"Colocar dentro da caixa de texto "pessoasNoElevador" o resultado da função "adicionarPessoa()" (transformada em string) e adicionar o texto subsequente.
            } else {
                Toast.makeText( //Aqui se criou um popup para avisar que o elevador está cheio, caso a condicional do "if" não ocorra.
                    this,
                    "Não cabem mais pessoas no elevador!",
                    Toast.LENGTH_SHORT).show()
            }
        }

        sair.setOnClickListener { //Ao clicar no botão "sair", executar o que segue.
            var excluir = elevador.excluirPessoa() //Aqui se colocou o RESULTADO da função (e não a função em si) "excluirPessoa()" da classe "Elevador" dentro de uma variável.
            if (excluir != -1) { //Aqui se está dizendo "se a função "excluirPessoa()" (da classe "Elevador") for diferente do erro que ela gera (aqui representado por "-1", que é qualquer coisa abaixo do número mínimo de pessoas ("1")), então..."
                pessoasNoElevador.setText(excluir.toString() + "/5 \n pessoas no elevador") //"Colocar dentro da caixa de texto "pessoasNoElevador" o resultado da função "excluirPessoa()" (transformada em string) e adicionar o texto subsequente.
            } else {
                Toast.makeText( //Aqui se criou um popup para avisar que o elevador está vazio, caso a condicional do "if" não ocorra.
                    this,
                    "Não tem mais ninguém no elevador!",
                    Toast.LENGTH_SHORT).show()
            }
        }

        irParaAndar.setOnClickListener { //Ao clicar no botão "sair", executar o que segue.
            val x = digitarAndar.text.toString() //Aqui se está transformando o resultado da caixa de texto "digitarAndar" em string.
            val andar = x.toIntOrNull() //Aqui se está transformando o resultado da caixa de texto "digitarAndar" em número inteiro ou nulo.
            when (andar) { //"Quando algo for digitado em "andar"..."
               in 1..12 -> {
                   andarAtual.text = x + "˚ Andar" //"Se o número for entre "1" e "12" (considerando aqui que se determinou um prédiod e 12 andares), colocar o andar digitado dentro da caixa de texto "andarAtual", junto com o texto subsequente. andarAtual.text = null
                   digitarAndar.text = null //Esse comando limpa a caixa de texto do andar.
               }
                   else -> { //"Em caso diferente..."
                    digitarAndar.error = "Andar inválido!" //"Criar um erro na caixa de texto "digitarAndar" informando que o andar é inválido.
                }
            }
        }

    }
}