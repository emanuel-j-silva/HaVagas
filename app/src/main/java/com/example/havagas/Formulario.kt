package com.example.havagas

data class Formulario(
    val nome: String,
    val email: String,
    val recebeEmails: Boolean,
    val telefone: String,
    val tipoTelefone: String,
    val celular: String?,
    val sexo: String,
    val dataNascimento: String,
    val formacao: String,
    val anoFormacao: String?,
    val anoConclusao: String?,
    val instituicao: String?,
    val monografia: String?,
    val orientador: String?
)
