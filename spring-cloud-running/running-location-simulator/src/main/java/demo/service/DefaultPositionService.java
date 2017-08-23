package demo.service;

import demo.domain.CurrentPosition;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by XL on 8/23/2017.
 */

@Slf4j
@Service
public class DefaultPositionService implements PositionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPositionService.class);
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void processPositionInfo(long id, CurrentPosition currentPosition, boolean sendPositionsToDistributionService) {
        String runnerLocationDistribution = "http://running-location-distribution";

        if (sendPositionsToDistributionService){
            log.info(String.format("Thread %d simulator is calling distrituion REST API", Thread.currentThread().getId()));
            this.restTemplate.postForLocation(runnerLocationDistribution + "api/locations", currentPosition);
        }
    }

    public void processPositionInfoFallBack(long id, CurrentPosition currentPosition, boolean sendPositionsToDistributionService){
        LOGGER.error("Hystrix fallback method. Unable to send messages for distribution");
    }
}
