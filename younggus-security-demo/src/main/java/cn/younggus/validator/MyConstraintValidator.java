package cn.younggus.validator;

import cn.younggus.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Glenn.Zheng
 * @date 2018/5/7 23:31
 */

/**
 * 注解验证逻辑编写
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private TestService testService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        // singleton
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(testService.greeting("glenn"));
        System.out.println(value);
        return false;
    }
}
