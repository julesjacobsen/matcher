package org.jacobsen.api;

import org.jacobsen.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by jules on 19/11/2016.
 */
@RestController
public class MatchController {

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final ExternalNodeMatchClient externalNodeMatchClient;

    public MatchController(ExternalNodeMatchClient externalNodeMatchClient) {
        this.externalNodeMatchClient = externalNodeMatchClient;
    }

    @GetMapping("/hello")
    public Person hello(@RequestParam(defaultValue = "") String first, @RequestParam(defaultValue = "") String last) {
        logger.info("Hello {} {}", first, last);
        return Person.builder().first(first).last(last).build();
    }

    @PostMapping("/match")
    public Person match(@RequestBody Person person) {
        logger.info("Match {}", person);
        return person;
    }

    @PostMapping("/match/external")
    public List<Person> matchExternal(@RequestBody Person person) {
        logger.info("Match external {}", person);
        return externalNodeMatchClient.requestMatch(person);
    }
}
