package com.yancary.dynamicds;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import(DynamicDSAutoConfiguration.class)
public @interface EnableDynamicDataSource {

}
