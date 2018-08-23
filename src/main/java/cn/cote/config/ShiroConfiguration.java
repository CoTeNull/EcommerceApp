package cn.cote.config;

import cn.cote.shiro.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {

    /**
     * 配置shiro 缓存的一个管理器
     * @return
     */
    @Bean("shiroCacheManager")
    public MemoryConstrainedCacheManager shiroCacheManager(){
        return new MemoryConstrainedCacheManager();
    }
/*=================================================================================================*/


    /**
     * 定义shiroFilter过滤器并注入securityManager
     * @param manager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置securityManager
        bean.setSecurityManager( manager);
        //设置登录页面
        bean.setLoginUrl("/view/index.html");
//        //设置登录成功跳转的页面
//        bean.setSuccessUrl("/pages/index.jsp");
//        //设置未授权跳转的页面
        bean.setUnauthorizedUrl("/403.html");
        //定义过滤器
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();


        filterChainDefinitionMap.put("/**/hello","anon");
        filterChainDefinitionMap.put("/**/user/login","anon");
        filterChainDefinitionMap.put("/**/gt/register1","anon");
        filterChainDefinitionMap.put("/**/gt/ajax-validate1","anon");
        filterChainDefinitionMap.put("/**/main.html","anon");
        filterChainDefinitionMap.put("/**/relogin.html","anon");
        filterChainDefinitionMap.put("/**/register","anon");
        filterChainDefinitionMap.put("/**/shop/getShop","anon");
        filterChainDefinitionMap.put("/**/view/css/**","anon");
        filterChainDefinitionMap.put("/**/view/js/**","anon");
        filterChainDefinitionMap.put("/**/view/img/**","anon");
        filterChainDefinitionMap.put("/**/out","logout");
        //需要登录访问的资源 , 一般将/**放在最下边
        filterChainDefinitionMap.put("/**", "authc");


        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    /**
     * Spring的一个bean , 由Advisor决定对哪些类的方法进行AOP代理 .
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 配置shiro跟spring的关联
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * lifecycleBeanPostProcessor是负责生命周期的 , 初始化和销毁的类
     * (可选)
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        hashedCredentialsMatcher.setHashIterations(1);
//        ？？？？？？？
//        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    @Bean("customRealm")
    @DependsOn("lifecycleBeanPostProcessor")//可选
    public CustomRealm customRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setAuthorizationCachingEnabled(false);
        customRealm.setCredentialsMatcher(matcher);
        return customRealm;
    }
    /**
     * 定义安全管理器securityManager,注入自定义的realm
     * @param customRealm
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("customRealm") CustomRealm customRealm,
                                                     @Qualifier("shiroCacheManager") MemoryConstrainedCacheManager shiroCacheManager,
                                                     @Qualifier("sessionManager") SessionManager sessionManager
    ) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(customRealm);


        //shiro缓存管理器
//        manager.setCacheManager(shiroCacheManager);
        //shiro session管理器
        manager.setSessionManager(sessionManager);

        return manager;
    }

    @Bean("sessionManager")
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }
//分离式开发:
//重写DefaultWebSessionManager获取sessionId的方法
    public class MySessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
//        String token = httpServletRequest.getHeader("token");
        String token = httpServletRequest.getParameter("token");
        System.out.println("token："+token);
        if(!StringUtils.isEmpty(token)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "token");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;

        }else{
            return super.getSessionId(request, response);
        }

    }
}


    /*=======================================================================================================*/
    //sissionDao
//    /**
//     * 配置sessionDAO
//     * @return
//     */
//    @Bean("sessionDAO")
//    public SessionDAO sessionDAO(){
//        MemorySessionDAO memorySessionDAO = new MemorySessionDAO();
//        return memorySessionDAO;
//    }
//    /**
//     * sessionManager
//     * @return
//     */
//    @Bean("sessionManager")
//    public DefaultWebSessionManager sessionManager(@Qualifier("sessionDAO") SessionDAO sessionDAO,
//                                                   @Qualifier("sessionIdCookie") SimpleCookie sessionIdCookie){
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(sessionDAO);
//        sessionManager.setSessionIdCookieEnabled(true);
//        sessionManager.setSessionIdCookie(sessionIdCookie);
//        return sessionManager;
//    }
//
//    /**
//     * 配置cookie(防止多个项目session被覆盖问题)
//     * @return
//     */
//    @Bean("sessionIdCookie")
//    public SimpleCookie sessionIdCookie(){
//        SimpleCookie simpleCookie = new SimpleCookie("SHRIOSESSIONID");
//        return simpleCookie;
//    }
}
