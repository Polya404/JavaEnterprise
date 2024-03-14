package org.application.springcore.component;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "application.component", havingValue = "one")
public class MyComponentOne implements MyComponent{
}
