package com.mercateo.kitchenapp;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

public class WicketServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        filter("/*").through(WicketFilter.class, createWicketFilterInitParams());
        bind(WebApplication.class).to(KitchenApp.class);
        bind(WicketFilter.class).to(CustomWicketFilter.class).in(Scopes.SINGLETON);
    }

    @Singleton
    private static class CustomWicketFilter extends WicketFilter {

        @Inject
        private Provider<WebApplication> webApplicationProvider;

        @Override
        protected IWebApplicationFactory getApplicationFactory() {
            return new IWebApplicationFactory() {
                @Override
                public WebApplication createApplication(WicketFilter filter) {
                    return webApplicationProvider.get();
                }

                @Override
                public void destroy(WicketFilter filter) {
                    // nothing
                }
            };
        }
    }

    private Map<String, String> createWicketFilterInitParams() {
        Map<String, String> wicketFilterParams = new HashMap<String, String>();
        wicketFilterParams.put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        wicketFilterParams.put("applicationClassName", KitchenApp.class.getName());
        return wicketFilterParams;
    }

}