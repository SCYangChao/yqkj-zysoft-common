package com.yqkj.zysoft.weixin.spring.core;

import com.yqkj.zysoft.common.collection.CollectionToole;
import com.yqkj.zysoft.common.string.StringUtil;
import com.yqkj.zysoft.common.system.PropertyTool;
import com.yqkj.zysoft.weixin.common.enums.ProxyEnum;
import com.yqkj.zysoft.weixin.spring.anotation.EnableWeiXinSpring;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName WeiXinBeanDefinitionRegistrar
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2020/2/18 10:19
 * @Version 1.0
 **/
public class WeiXinBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar , ResourceLoaderAware {


    private  ResourceLoader resourceLoader;

    /**
     * Register bean definitions as necessary based on the given annotation metadata of
     * the importing {@code @Configuration} class.
     * <p>Note that {@link BeanDefinitionRegistryPostProcessor} types may <em>not</em> be
     * registered here, due to lifecycle constraints related to {@code @Configuration}
     * class processing.
     * <p>The default implementation is empty.
     *
     * @param importingClassMetadata annotation metadata of the importing class
     * @param registry               current bean definition registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableWeiXinSpring.class.getName()));
        String[] basePackage = annotationAttributes.getStringArray("basePackage");
        ProxyEnum proxyEnum = annotationAttributes.getEnum("proxyType");
        String baseUrl = annotationAttributes.getString("baseUrl");
        if(CollectionToole.isNull(basePackage)){
            basePackage = PropertyTool.getBasePackage();
        }

        if(!CollectionToole.isNull((basePackage))) {
            WeiXinClassPathBeanDefinitionScanner weiXinClassPathBeanDefinitionScanner = new WeiXinClassPathBeanDefinitionScanner(registry);
            weiXinClassPathBeanDefinitionScanner.registerFilters();
            weiXinClassPathBeanDefinitionScanner.setResourceLoader(resourceLoader);
            weiXinClassPathBeanDefinitionScanner.setProxyEnum(proxyEnum);
            weiXinClassPathBeanDefinitionScanner.setBaseUrl(baseUrl);
            weiXinClassPathBeanDefinitionScanner.doScan(basePackage);
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
