package com.arijit.domaincrawler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain")
public class DomainCrawlerController {

    private DomainCrawlerService service;

    //Constructor Injection
    public DomainCrawlerController(DomainCrawlerService service) {
        this.service = service;
    }

    @GetMapping("/lookup/{name}")
    public String lookup(@PathVariable("name") final String name){
        service.crawl(name);
        return "Domain crawler has consumed your data";
    }
}
