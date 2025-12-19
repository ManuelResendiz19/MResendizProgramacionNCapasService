//
//package com.MResendizProgramacionNCapas.Configuration;
//
//import jakarta.jms.ConnectionFactory;
////import org.apache.activemq.broker.BrokerService;
//import java.util.List;
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.core.JmsTemplate;
//
//@Configuration
//@EnableJms
//public class JmsConfig {
//
////     @Bean(initMethod = "start", destroyMethod = "stop")
////    public BrokerService broker() throws Exception {
////        BrokerService broker = new BrokerService();
////        broker.addConnector("tcp://localhost:61616");
////        broker.setPersistent(false); // No guarda mensajes en disco
////        broker.setUseJmx(false);
////        return broker;
////    }
//    
//    @Bean
//    public ActiveMQConnectionFactory connectionFactory() {
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//        connectionFactory.setBrokerURL("tcp://localhost:61616");
//        connectionFactory.setUserName("admin");
//        connectionFactory.setPassword("admin");
//        connectionFactory.setTrustedPackages(List.of("com.MResendizProgramacionNCapas"));
//        return connectionFactory;
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory) {
//        JmsTemplate template = new JmsTemplate();
//        template.setConnectionFactory(connectionFactory);
//        return template;
//    }
//
//}
