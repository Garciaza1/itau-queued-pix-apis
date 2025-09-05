package itau.worker.queue.infrastructure.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import itau.pix.commons.messaging.RabbitMQConstants;

@Configuration
public class RabbitMQConfig {


    @Bean
    public Queue paymentQueue() {
        return new Queue(RabbitMQConstants.FILA_PAGAMENTO, true);
    }

    @Bean
    public Queue failedPaymentQueue() {
        return new Queue(RabbitMQConstants.FILA_PAGAMENTO_FALHOU, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
