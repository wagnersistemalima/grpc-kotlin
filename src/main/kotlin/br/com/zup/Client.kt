package br.com.zup

import io.grpc.ManagedChannelBuilder

fun main() {

    // canal de comunicação
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val request = FuncionarioRequest.newBuilder()
        .setNome("Wagner Lima")
        .setCpf("111.111.111-11")
        .setIdade(34)
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

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)

    val response = client.cadastrar(request)
    println(response)
}