package org.jacobsen.api;

import org.jacobsen.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Created by jules on 19/11/2016.
 */
@Component
public class ExternalNodeMatchClient {

    private static final Logger logger = LoggerFactory.getLogger(ExternalNodeMatchClient.class);

    private final List<String> connectedNodes;

    public ExternalNodeMatchClient(List<String> connectedNodes) {
        this.connectedNodes = connectedNodes;
    }

    List<Person> requestMatch(Person person) {
        return connectedNodes.parallelStream()
                .map(matchPerson(person))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    private Function<String, Optional<Person>> matchPerson(Person person) {
        return nodeUrl -> {
            RestTemplate restTemplate = new RestTemplate();
            logger.info("Attempting requestMatch request against {}", nodeUrl);
            ResponseEntity<Person> response = restTemplate.postForEntity(nodeUrl +  "/match", person, Person.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Person matchedPerson = response.getBody();
                logger.info("Got a requestMatch from {}: {}", nodeUrl, matchedPerson);
                return Optional.of(matchedPerson);
            }
            logger.info("No requestMatch from node {}", nodeUrl);
            return Optional.empty();
        };
    }


}
