package com.myapp.restful;

import javax.ws.rs.ApplicationPath;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("restful")
public class RESTfulApplication extends ResourceConfig
{
	public RESTfulApplication()
	{
		packages(RESTfulService.class.getPackage().getName());
		register(JacksonJsonProvider.class);
	}
}
