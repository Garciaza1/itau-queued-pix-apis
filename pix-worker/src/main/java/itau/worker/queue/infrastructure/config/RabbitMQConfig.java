package itau.worker.queue.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import itau.pix.commons.messaging.RabbitMQConstants;

@Configuration
@EnableRabbit
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
    public Queue successPaymentQueue() {
        return new Queue(RabbitMQConstants.FILA_PAGAMENTO_SUCESSO, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public CachingConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory(RabbitMQConstants.RABBIT_HOST);
        factory.setUsername(RabbitMQConstants.RABBIT_USER);
        factory.setPassword(RabbitMQConstants.RABBIT_PASSWORD);
        factory.setPort(5672);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);

        template.setConfirmCallback((correlationData, ack, cause) -> {
            String id = (correlationData != null) ? correlationData.getId() : "N/A";
            if (ack) {
                System.out.println("✅ Message confirmed by RabbitMQ. ID: " + id);
            } else {
                System.err.println("❌ Message NOT confirmed! ID: " + id + ", Cause: " + cause);
            }
        });

        template.setReturnsCallback(returned -> {
            System.err.println("❌ Message could not be routed. ReplyCode: " + returned.getReplyCode()
                    + ", ReplyText: " + returned.getReplyText()
                    + ", Exchange: " + returned.getExchange()
                    + ", RoutingKey: " + returned.getRoutingKey());
        });

        return template;
    }

}
