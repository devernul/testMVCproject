package edu.devernul.project.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	private static final Logger logger = LoggerFactory.getLogger(MyBeanPostProcessor.class);


	public Object postProcessAfterInitialization(Object object, String name) throws BeansException {
		logger.info("postProcessAfterInitialization(): {}" + object);
		return object;
	}


	public Object postProcessBeforeInitialization(Object object, String name) throws BeansException {
		return object;
	}

}
