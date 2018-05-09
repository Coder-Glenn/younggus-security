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


