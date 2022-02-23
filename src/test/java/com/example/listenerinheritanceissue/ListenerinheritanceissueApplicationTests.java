package com.example.listenerinheritanceissue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.BeforeTestMethodEvent;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.example.listenerinheritanceissue.ListenerinheritanceissueApplicationTests.ChildConfig;

@SpringBootTest
@Import({ChildConfig.class})
class ListenerinheritanceissueApplicationTests {
    public static int calledByParentConfigBeforeEach = 0;

    @Test
    void parentConfigBeforeTestMethodShouldNotBeCalled() {
        Assertions.assertThat(calledByParentConfigBeforeEach).isEqualTo(0);
    }

    @Configuration
    static class ParentConfig {
        @BeforeTestMethod
        public void beforeEach(BeforeTestMethodEvent e) {
            ListenerinheritanceissueApplicationTests.calledByParentConfigBeforeEach++;
        }

    }

    @Configuration
    static class ChildConfig extends ParentConfig {
        @Override
        public void beforeEach(BeforeTestMethodEvent e) {
			// no call to super so there should be no invocation at all
        }

    }

}
