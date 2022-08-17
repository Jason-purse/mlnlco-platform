package club.smileboy.mlnlco.service.cache;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 21:40
 * @description 基于application 上下文容器管理的 缓存注册机 ...
 **/
@Service
public class SimpleBaseBeanFactoryCacheRegistry implements CacheRegistry<CacheContainer<?, ?>>, ApplicationContextAware {

    private GenericApplicationContext applicationContext;

    private BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

    private final Map<Serializable, CacheContainer<?, ?>> containerMap = new ConcurrentHashMap<>(4);

    private final Object monitor = new Object();

    @Autowired
    public void setCacheContainer(List<CacheContainer<?,?>> cacheContainers) {
       synchronized (monitor) {
           // full lifecycle bean
           for (CacheContainer<?, ?> cacheContainer : cacheContainers) {
                containerMap.put(cacheContainer.getIdentify(),cacheContainer);
           }
       }
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        if(applicationContext instanceof GenericApplicationContext) {
            this.applicationContext = ((GenericApplicationContext) applicationContext);
        }
        throw new IllegalArgumentException("application must be ConfigurableApplicationContext !!!");
    }


    @Override
    public  void registry(CacheContainer<?, ?> cacheContainer, boolean ifDuplicateOverride) {
        if(containerMap.containsKey(cacheContainer.getIdentify())) {
            // 表示已经存在,此时判断
            if (!ifDuplicateOverride) {
                throw new UnsupportedOperationException("the cacheContainer that cache identify is duplicate, so can't register it !!!");
            }
            else {
                synchronized (monitor) {
                    // 进行 bean 生命周期 ...
                    Class<?> userClass = ClassUtils.getUserClass(cacheContainer.getClass());
                    String beanName = beanNameGenerator.generateBeanName(new RootBeanDefinition(userClass), applicationContext) + "$$" + cacheContainer.getIdentify();
                    Object initializeBean = applicationContext.getAutowireCapableBeanFactory().initializeBean(cacheContainer, beanName);
                    applicationContext.getAutowireCapableBeanFactory().configureBean(initializeBean,beanName);
                    containerMap.put(cacheContainer.getIdentify(),cacheContainer);
                }
            }
        }
    }

    @Override
    public List<CacheContainer<?, ?>> list(Class<CacheContainer<?, ?>> containerClass) {
        return containerMap.values().stream().filter(ele -> ele.getClass() == containerClass).collect(Collectors.toList());
    }

    @Override
    public CacheContainer<?, ?> first(Class<CacheContainer<?, ?>> containerClass) {

        for (CacheContainer<?, ?> value : containerMap.values()) {
            if (value.getClass() == containerClass) {
                return value;
            }
        }
        throw new UnsupportedOperationException("can't find a CacheContainer that class type match " + containerClass.getName());
    }

    @Override
    public CacheContainer<?, ?> get(Serializable identify) {
        return Optional.ofNullable( containerMap.getOrDefault(identify,null)).orElseThrow(() -> new UnsupportedOperationException("can't find a CacheContainer that identify match " + identify));
    }

    public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
        if (beanNameGenerator != null) {
            this.beanNameGenerator = beanNameGenerator;
        }
    }
}
