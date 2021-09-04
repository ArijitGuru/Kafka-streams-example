package com.arijit.domaincrawler;

import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DomainCrawlerService {


    private KafkaTemplate<String, Domain> kafkaTemplate;
    private static final String KAFKA_TOPIC = "web-domains";

    //Constructor Injection - Spring boot will inject the KafkaTemplate bean
    public DomainCrawlerService(KafkaTemplate<String, Domain> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void crawl (String name){
        Mono<DomainList> domainListMono = WebClient.create()
                .get()
                .uri("https://api.domainsdb.info/v1/domains/search?domain="+name+"&zone=com")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DomainList.class);

        domainListMono.subscribe(domainList -> {
            domainList.domains.forEach(domain -> {
                kafkaTemplate.send(KAFKA_TOPIC,domain);
                System.out.println("Domain message --> " + domain.getDomain());
            });
        });

        //Note: bodyToMono vs bodyFlux
        /*If you are retrieving a single item, use bodyToMono. It emits 0-1 items
        For multiple items, use bodyToFlux. It emits 0-N items.*/



    }
}
