package org.apereo.cas.interrupt.webflow;

import org.apereo.cas.interrupt.InterruptResponse;
import org.apereo.cas.services.ServicesManager;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.webflow.test.MockRequestContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This is {@link InterruptSingleSignOnParticipationStrategyTests}.
 *
 * @author Misagh Moayyed
 * @since 5.3.0
 */
@ExtendWith(SpringExtension.class)
public class InterruptSingleSignOnParticipationStrategyTests {
    @Test
    public void verifyStrategyWithoutInterrupt() {
        val s =
            new InterruptSingleSignOnParticipationStrategy(mock(ServicesManager.class), true, true);
        assertTrue(s.isParticipating(new MockRequestContext()));
    }

    @Test
    public void verifyStrategyWithInterruptDisabled() {
        val s =
            new InterruptSingleSignOnParticipationStrategy(mock(ServicesManager.class), true, true);
        val ctx = new MockRequestContext();
        val response = new InterruptResponse();
        response.setSsoEnabled(false);
        InterruptUtils.putInterruptIn(ctx, response);
        assertFalse(s.isParticipating(ctx));
    }
}
