```
src/main/java/itau/worker/queue/
├─ QueueApplication.java
├─ application/
│  ├─ service/
│  │  └─ PagamentoWorkerService.java        # Lógica de processamento
│  └─ port/
│     └─ in/
│        └─ PagamentoWorkerUseCase.java    # UseCase que service implementa
├─ domain/
│  └─ model/
│     └─ pagamento/
│        ├─ PagamentoMessage.java          # Payload recebido do RabbitMQ
├─ infrastructure/
│  ├─ messaging/
│     ├─ PaymentListener.java             # Escuta fila RabbitMQ
│     └─ RabbitMQConfig.java              # Configuração de filas e exchanges
```

## Responsabilidades

**Camada**	**Responsabilidade** 

- application.port.in:	Define os casos de uso da API Worker (UseCases)

- application.service:	Implementa a lógica de negócio: validações, regras, fluxo de pagamento

- domain.model:	Contém DTOs e enums: PagamentoMessage, PagamentoResultMessage, StatusPagamento

- infrastructure.messaging:	Configura RabbitMQ, escuta filas e publica mensagens

- infrastructure.adapter.out:	Caso precise persistir logs ou status dos pagamentos