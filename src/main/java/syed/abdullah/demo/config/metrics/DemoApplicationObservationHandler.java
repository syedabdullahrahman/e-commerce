package syed.abdullah.demo.config.metrics;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoApplicationObservationHandler implements ObservationHandler<Observation.Context> {

    @Override
    public void onStart(Observation.Context context) {
        log.debug("Observation started with context: {}", context);
    }

    @Override
    public void onStop(Observation.Context context) {
        log.debug("Observation stopped with context: {}", context);
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}
