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