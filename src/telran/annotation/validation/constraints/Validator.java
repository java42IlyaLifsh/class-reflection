package telran.annotation.validation.constraints;
//Ilyal-HW50
import java.lang.reflect.*;


import java.util.*;


public class Validator {
/**
 * validates the given object against the constraints in the package telran.annotation.validation.constraints
 * @param obj
 * @return list constraint violation messages or empty list if no violations
 */
	public List<String> validate(Object obj) {
		List<String> violations = new LinkedList<>();
		Arrays.stream(obj.getClass().getDeclaredFields()).forEach(field -> validate(field, violations, obj));
		return violations;
	}
	private void validate(Field field, List<String> violations, Object obj) {
		Valid annotation = field.getAnnotation(Valid.class);
		if(annotation==null) {
			regularValidation(field, violations, obj);
			return;
		}
	// nested validation
		
		Object nestedObj;
		try {
			nestedObj = field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return;
		}
		//[YG] just violations.addAll(validate(nestObject)) would be enough
		Arrays.stream(nestedObj.getClass().getDeclaredFields()).forEach(f -> validate(f, violations, nestedObj));
	}
	private void regularValidation(Field field, List<String> violations,
			Object obj)  {
		Max annotationMax = field.getAnnotation(Max.class);
		Min annotationMin = field.getAnnotation(Min.class);
		Patern annotationPatern = field.getAnnotation(Patern.class);
		
		if (annotationMax != null) {String violationMsg = max( annotationMax, field, obj); violations.add(violationMsg);}
		if (annotationMin != null) {String violationMsg = min(annotationMin, field, obj); violations.add(violationMsg);}
		if (annotationPatern != null) {String violationMsg = pattern(annotationPatern, field, obj); violations.add(violationMsg);}
//[YG] a field may contain several annotations, you consider only one
		
/*		I was only able to debug the IF variant. I understand what is right to do through CLASS , 
		   but unfortunately I'm still confused, SORRY. 
		  I will definitely learn how to work properly with CLASS -REFLECTION. 
		  
		    
		Annotation[] annotations= { (Annotation) annotationMax, (Annotation) annotationMin,(Annotation) annotationPatern};
		
		Arrays.stream(annotations).forEach(a -> {
			if (a != null) {
				try {
					Method method = Validator.class
							.getDeclaredMethod(a.getClass().getName(),Annotation.class, Field.class, Object.class);
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (SecurityException e) {
					
					e.printStackTrace();
				}
				method.setAccessible(true);
				String violationMsg = method.invoke( a, field, obj); 
				violations.add(violationMsg);}	
		});
	*/	
		

		
	}
	private String max(Max annotation, Field field, Object obj) {
		
		try {		//[YG] getDoble works only for type double, but min/max might be applied for any numeric type	
				double fieldValue = field.getDouble(obj);
				double annotationValue = annotation.value();
				if(fieldValue > annotationValue) {
					return String.format("Field: %s.  %s : %,.0f is more than %,.0f",
							field.getName(), annotation.message(), fieldValue, annotationValue);
				}
			
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return "";
	}
	private String min(Min annotation, Field field, Object obj) {
		try {			
			double fieldValue = field.getDouble(obj);
			double annotationValue = annotation.value();
			if(fieldValue < annotationValue) {
				return String.format("Field: %s.  %s : %,.0f is less than %,.0f",
						field.getName(), annotation.message(), fieldValue, annotationValue);
			}
		
	} catch(IllegalArgumentException | IllegalAccessException e) {
		e.printStackTrace();
	}
	return "";
	}
	private String pattern(Patern annotation, Field field, Object obj) {
		try {
			
				String name = (String)field.get(obj);
				String value = annotation.value();
				if(!name.matches(value)) {
					return String.format("Field: %s.  %s : %s",
						field.getName(), annotation.message(), name);
				}
			
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return "";
	}
}
