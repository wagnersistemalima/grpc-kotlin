package br.com.zup

import com.google.protobuf.Timestamp
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId

//Subindo servidor gRPC
fun main () {

    // criando o servidor

    val server = ServerBuilder
        .forPort(50051)             // servidor escultando nesta porta
        .addService(FuncionarioEndPoint())

        .build()

    // instanciando o servidor
    server.start()

    // segurando a tred para que o servidor fique em execução
    server.awaitTermination()
}

class FuncionarioEndPoint: FuncionarioServiceGrpc.FuncionarioServiceImplBase() {

    // metodo cadastrar da interface

    override fun cadastrar(request: FuncionarioRequest?, responseObserver: StreamObserver<FuncionarioResponse>?) {

        val nome = request?.nome
        val instant = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()
        val criadoEm = Timestamp.newBuilder()
                .setSeconds(instant.epochSecond)
                .setNanos(instant.nano)
                .build()

        val response = FuncionarioResponse.newBuilder()
            .setNome(nome)
            .setCriadoEm(criadoEm)

            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }

}