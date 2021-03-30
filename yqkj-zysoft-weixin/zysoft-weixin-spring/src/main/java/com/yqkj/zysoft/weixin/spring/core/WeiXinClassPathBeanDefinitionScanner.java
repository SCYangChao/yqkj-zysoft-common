package com.yqkj.zysoft.weixin.spring.core;

import com.yqkj.zysoft.common.collection.CollectionToole;
import com.yqkj.zysoft.weixin.common.annotation.WeiXinClient;
import com.yqkj.zysoft.weixin.common.enums.ProxyEnum;
import com.yqkj.zysoft.weixin.spring.factory.WeiXinFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @ClassName WeiXinClassPathBeanDefinitionScanner
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/2/18 10:47
 * @Version 1.0
 **/
public class WeiXinClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    private ProxyEnum proxyEnum;

    private String baseUrl;

    public WeiXinClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 设置 包含的注解
     */
    protected void registerFilters() {
        addIncludeFilter(new AnnotationTypeFilter(WeiXinClient.class));
    }
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if (!CollectionToole.isNull(beanDefinitionHolders)) {
            GenericBeanDefinition definition;
           for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
               definition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
               definition.getPropertyValues().add("interfaceBean", definition.getBeanClassName());
               definition.getPropertyValues().add("proxyEnum", proxyEnum);
               definition.getPropertyValues().add("baseUrl", baseUrl);
               definition.setBeanClass(WeiXinFactoryBean.class);
           }

        }
        return  beanDefinitionHolders;
    }
    /**
     * Determine whether the given class does not match any exclude filter
     * and does match at least one include filter.
     *
     * @param beanDefinition the ASM ClassReader for the class
     * @return whether the class qualifies as a candidate component
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ProxyEnum getProxyEnum() {
        return proxyEnum;
    }

    public void setProxyEnum(ProxyEnum proxyEnum) {
        this.proxyEnum = proxyEnum;
    }
}
