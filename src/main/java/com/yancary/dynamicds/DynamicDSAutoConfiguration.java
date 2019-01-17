package com.yancary.dynamicds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DynamicDatasourceConfigProperties.class)
public class DynamicDSAutoConfiguration {

    @Autowired
    private DynamicDatasourceConfigProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(SwitchDynamicDatasource.class)
    DataSource dataSource() {
        SwitchDynamicDatasource ds = new SwitchDynamicDatasource();
        ds.setDsProperties(properties);
        return ds;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(SwitchDynamicDatasource.class)
    OrgCodeInterceptor orgCodeInterceptor() {
        OrgCodeInterceptor interceptor = new OrgCodeInterceptor();
        interceptor.setOrgCodeHeaderName(properties.getOrgCodeHeader());
        interceptor.setValidOrgCodes(properties.getTenants().keySet());
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(SwitchDynamicDatasource.class)
    InterceptorRegister interceptorRegister() {
        InterceptorRegister interceptorRegister = new InterceptorRegister();
        return interceptorRegister;
    }
}
