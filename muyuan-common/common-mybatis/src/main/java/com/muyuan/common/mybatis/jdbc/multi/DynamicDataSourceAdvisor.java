//package com.muyuan.common.mybatis.jdbc.multi;
//
//import lombok.Setter;
//import org.aopalliance.aop.Advice;
//import org.springframework.aop.Pointcut;
//import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
//
///**
// * @ClassName DynamicDataSourceAdvisor
// * Description TODO
// * @Author 2456910384
// * @Date 2021/12/9 10:03
// * @Version 1.0
// */
////@Component
//public class DynamicDataSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {
//
//    @Setter
//    private DateSourcePointCut pointCut = new DateSourcePointCut();
//
//    private DynamicDataSourceInterceptor dynamicDataSourceAspect = new DynamicDataSourceInterceptor();
//
//    @Override
//    public Pointcut getPointcut() {
//        return pointCut;
//    }
//
//    @Override
//    public Advice getAdvice() {
//        return dynamicDataSourceAspect;
//    }
//}
