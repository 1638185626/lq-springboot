//package com.liuqing.lqactiviti7.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @className: SecurityUtil
// * @description: TODO 类描述
// * @author: qing liu
// * @date: 2022/6/15
// **/
//@Component
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//public class SecurityUtil {
//
//    private final UserDetailsService userDetailsService;
//
//    public void logInAs(String username) {
//
//        UserDetails user = userDetailsService.loadUserByUsername(username);
//        if (user == null) {
//            throw new IllegalStateException("User " + username + " doesn't exist, please provide a valid user");
//        }
//
//        SecurityContextHolder.setContext(new SecurityContextImpl(new Authentication() {
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                return user.getAuthorities();
//            }
//
//            @Override
//            public Object getCredentials() {
//                return user.getPassword();
//            }
//
//            @Override
//            public Object getDetails() {
//                return user;
//            }
//
//            @Override
//            public Object getPrincipal() {
//                return user;
//            }
//
//            @Override
//            public boolean isAuthenticated() {
//                return true;
//            }
//
//            @Override
//            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//
//            }
//
//            @Override
//            public String getName() {
//                return user.getUsername();
//            }
//        }));
//        org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
//    }
//}
