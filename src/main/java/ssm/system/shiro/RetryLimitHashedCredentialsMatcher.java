package ssm.system.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

public class RetryLimitHashedCredentialsMatcher extends  
SimpleCredentialsMatcher {  
    
private Cache<String, AtomicInteger> passwordRetryCache;  

public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
    //获取passwordRetryCache缓存
    passwordRetryCache = cacheManager.getCache("passwordRetryCache");  
}  

@Override  
public boolean doCredentialsMatch(AuthenticationToken token,  
        AuthenticationInfo info) {  
    String loginName = (String) token.getPrincipal();  
    // retry count + 1  
    AtomicInteger retryCount = passwordRetryCache.get(loginName);  
    if (retryCount == null) {  
        retryCount = new AtomicInteger(0);  
        passwordRetryCache.put(loginName, retryCount);  
    }  
    if (retryCount.incrementAndGet() > 5) {  
        // if retry count > 5 throw  
        throw new ExcessiveAttemptsException();  
    }  

    boolean matches = super.doCredentialsMatch(token, info);  
    if (matches) {  
        // clear retry count  
        passwordRetryCache.remove(loginName);  
    }  
    return matches;  
}  
}  
