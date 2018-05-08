package cn.younggus.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Glenn.Zheng
 * @date 2018/5/7 23:29
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint  {

    String message() default "{javax.validation.constraints.MyConstraint.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
