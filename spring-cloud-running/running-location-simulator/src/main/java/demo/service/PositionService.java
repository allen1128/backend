package demo.service;

import demo.domain.CurrentPosition;

/**
 * Created by XL on 8/23/2017.
 */
public interface PositionService {
    void processPositionInfo(long id, CurrentPosition currentPosition, boolean sendPositionsToDistributionService);
}
