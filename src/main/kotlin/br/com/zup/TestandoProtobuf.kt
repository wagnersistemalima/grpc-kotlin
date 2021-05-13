package br.com.zup

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {

    //Transformamos o request em um formato que será trafegado na rede
    val request = FuncionarioRequest.newBuilder()
        .setNome("Wagner Lima")
        .setCpf("111.111.111-11")
        .setSalario(2000.0)
        .setAtivo(true)
        .setCargo(Cargo.DEV)             // cargo default
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
                .setLogradouro("Rua nova")
                .setCep("12345-000")
                .setComplemento("Perto da budega")
                .build()
        )

        .build()

    println(request)


    //Escrevemos o objeto
    request.writeTo(FileOutputStream("funcionario-request.bin"))

    //Lemos o objeto
    val request2 = FuncionarioRequest.newBuilder()
        .mergeFrom(FileInputStream("funcionario-request.bin"))

    request2.setCargo(Cargo.GERENTE)


    println("----------------------------------------------------")
    println(request2)

}