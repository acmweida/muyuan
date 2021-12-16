package com.muyuan.gateway.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.discovery.GatewayDiscoveryClientAutoConfiguration;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GatewayDiscoveryClientConfig
 * Description GatewayDiscoveryClientConfig
 * @Author 2456910384
 * @Date 2021/12/16 17:19
 * @Version 1.0
 */
@Configuration
public class GatewayDiscoveryClientConfig extends GatewayDiscoveryClientAutoConfiguration {

    @Value("${spring.cloud.gateway.api-prefix:/api}")
    private String prefix;

    @Bean
    public DiscoveryLocatorProperties discoveryLocatorProperties() {
        DiscoveryLocatorProperties properties = new DiscoveryLocatorProperties();
        properties.setPredicates(initPrefixPredicates());
        properties.setFilters(initPrefixFilters());
        return properties;
    }

    public  List<PredicateDefinition> initPrefixPredicates() {
        ArrayList<PredicateDefinition> definitions = new ArrayList();
        PredicateDefinition predicate = new PredicateDefinition();
        predicate.setName(NameUtils.normalizeRoutePredicateName(PathRoutePredicateFactory.class));
        predicate.addArg("pattern","'/"+ prefix+"/'+serviceId+'/**'");
        definitions.add(predicate);
        return definitions;
    }

    public  List<FilterDefinition> initPrefixFilters() {
        ArrayList<FilterDefinition> definitions = new ArrayList();
        FilterDefinition filter = new FilterDefinition();
        filter.setName(NameUtils.normalizeFilterFactoryName(RewritePathGatewayFilterFactory.class));
        String regex ="'"+ prefix+"/' + serviceId + '/?(?<remaining>.*)'";
        String replacement = "'/${remaining}'";
        filter.addArg("regexp", regex);
        filter.addArg("replacement", replacement);
        definitions.add(filter);
        return definitions;
    }

}
