<!-- 
                  ,--.    ,--.
                 ((O ))--((O ))
               ,'_`--'____`--'_`.
              _:  ____________  :_
             | | ||::::::::::|| | |
             | | ||::::::::::|| | |
             | | ||::::::::::|| | |
             |_| |/__________\| |_|
               |________________|
            __..-'            `-..__
         .-| : .----------------. : |-.
       ,\ || | |\______________/| | || /.
      /`.\:| | ||  __  __  __  || | |;/,'\
     :`-._\;.| || '--''--''--' || |,:/_.-':
     |    :  | || .----------. || |  :    |
     |    |  | || '----------' || |  |    |
     |    |  | ||   _   _   _  || |  |    |
     :,--.;  | ||  (_) (_) (_) || |  :,--.;
     (`-'|)  | ||______________|| |  (|`-')
      `--'   | |/______________\| |   `--'
             |____________________|
              `.________________,'
               (_______)(_______)
               (_______)(_______)
               (_______)(_______)
               (_______)(_______)
              |        ||        |
              '--------''--------'
-->
**main task: getUserById**
1. @PathVariable 映射url片段到方法的请求参数
2. 在url声明中使用正则表达式
3. @JsonView控制json输出内容

**main task: create user**
1. @RequestBody 映射请求体到方法的参数
2. 日期类型参数的处理
3. @Valid注解和 BindingResult验证请求参数的合法性并处理校验结果

**main task: modify user info and delete user api**
1. 常用的参数验证注解(@Valid @RequestBody User user, BindingResult errors)
2. 自定义错误处理消息(@NotBlank(message = "密码不能为空"))
3. 自定义校验注解(ConstraintValidator<MyConstraint, Object>)

**RESTful API错误处理**
1. Spring Boot中默认的错误处理机制
2. 自定义异常处理(@ControllerAdvice, @ExceptionHandler(UserNotExistException.class))

**RESTful API的拦截**
1. 过滤器(Filter), 由java servlet提供，可以过滤所有的web请求并获取请求和响应的信息，但是无法获取方法的任何信息
2. 拦截器(Interceptor), 由spring提供支持,拦截所有controller的方法调用，可以获取方法名，但无法获取方法的参数信息
3. 切片(Aspect), 由spring提供支持，可以获取controller的参数信息,但无法获取request和response的信息

**Spring Boot中的文件上传下载**
1. 文件上传
2. 文件下载

============================================================================================================

Spring Security
1. 核心功能：认证，授权，攻击防护（防止伪造身份）
2. 自定义用户认证逻辑：
    - 处理用户信息获取逻辑(UserDetailService)
    - 处理用户信息校验逻辑(UserDetail的4个方法)
    - 处理密码加密解密(org.springframework.security.crypto.password.PasswordEncoder)
3. 个性化用户认证流程
    - 自定义登录页面(UsernamePasswordAuthenticationFilter)
    - 自定义登录成功处理(AuthenticationSuccessHandler)
    - 自定义登录失败处理(AuthenticationFailureHandler)
4. 源码解读：
    UsernamePasswordAuthencationProvider中创建UsernamePasswordAuthenticationToken，这个token中包含了未认证的用户的基本信息(username, password等），之后获取authenticationManager进行下一步的认证，authenticationManager的默认实现是ProviderManager，这个manager会拿到所有的provider进行下一步的认证，针对不同的登录方式，有不同的provider，比如用户名密码表单登录，默认是由DaoAuthenticationProvider对usernamepasswordAuthenticationToken进行认证，而针对第三方如微信登录则是由SocialAuthenticaitonProvider对SocialAuthenticationToken进行认证，有不同的provider去做认证。
    DaoAuthenticationProvider继承AbstractUserDetailsAuthenticationProvider，主要的校验逻辑由AbstractUserDetailsAuthenticationProvider的authenticate方法实现，这个方法通过retrieveUser获取了一个UserDetails对象，retrieveUser是一个抽象方法，具体的实现由DaoAuthenticationProvider完成，在DaoAuthenticationProvider中的retrieveUser的方法中，loadedUser=this.getUserDetailsService().loadUserByUsername(username);
    通过得到userDetailService从数据库中获取一个userDeatil对象，这个service可以自己实现。
    AbstractUserDetailsAuthenticationProvider的authenticate方法中，获取完用户信息 之后，
    this.preAuthenticationChecks.check(user);
    this.additionalAuthenticationChecks(user,(UsernamePasswordAuthenticationToken)authentication);
    this.postAuthenticationChecks.check(user);
    这三个方法完成了对用户认证，pre完成了lock, isEnable, accoutExpired的判断
    additionalCheck完成了对密码的验证，这个密码是加密的密码
    
    
    post完成了credentials的验证。Lock, isEnable, accountExpired, credentials都是UserDetails对象的属性。
    最后创建一个新的UsernamePasswordAuthenticationToken，这个token相比于刚开始的token是认证过的，并且保存了用户的权限信息。
    UsernamePasswordAuthencationProvider继承了AbstractAuthenticationProcessingFilter，这个filter中的doFilter方法完成了UsernamePasswordAuthencationProvider中attemptAuthentication的操作。拿到认证过的Authentication以后，
    
    调用了successfulAuthentication方法（图片最后一行），这个方法调用了successHandler。
    同时在认证过程中的任何异常都会在此捕获。
    
    同时在成功认证以后，Authentication被放入到SecurityContext中，再放入到SecrityContextHolder（ThreadLocal的一个封装）以实现authentication认证结果在多个请求中共享。
    
    在UsernamePasswordAuthentication之前还有一个过滤器叫做SecurityContextPersistenceFilter，这个filter是在整个过滤器链的最前端，在请求进来时，首先检查session中是否有securityContext，如果有，那么拿出来放到线程里，在请求结束以后，再检查线程中是否有securityContext，如果有，则放到session里