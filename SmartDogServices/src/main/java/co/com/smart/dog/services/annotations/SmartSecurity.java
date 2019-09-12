package co.com.smart.dog.services.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

/**
 * SmartSecurity @interface annotation
 * @author SmartJungle S.A.S
 *
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface SmartSecurity {
	/**
	 * implemetacion para validar si aplica o no seguridad JWT
	 * @return
	 */
	public boolean apply() default true;
}
