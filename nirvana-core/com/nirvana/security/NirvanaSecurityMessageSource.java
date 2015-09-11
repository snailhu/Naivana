package com.nirvana.security;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

public class NirvanaSecurityMessageSource extends ResourceBundleMessageSource {
	// ~ Constructors
	// ===================================================================================================

	public NirvanaSecurityMessageSource() {
		setBasename("org.springframework.security.messages");
	}

	// ~ Methods
	// ========================================================================================================

	public static MessageSourceAccessor getAccessor() {
		return new MessageSourceAccessor(new NirvanaSecurityMessageSource());
	}
}
