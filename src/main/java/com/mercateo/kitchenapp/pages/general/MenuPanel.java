/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.mercateo.kitchenapp.pages.general;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.pages.PagesRegistry;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;

class MenuPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @Inject
    private PagesRegistry pages;

    MenuPanel(String id) {
        super(id);

        add(link("subscription", pages.getOffersPage()));
        add(link("profile", pages.getProfilePage()));
        add(link("admin", pages.getAdminPage()));
        add(link("meals", pages.getMealsPage()));
        if (AuthenticatedWebSession.get().isSignedIn()) {
            add(new Link<User>("logOut") {
                @Override
                public void onClick() {
                    AuthenticatedWebSession.get().invalidate();
                    setResponsePage(pages.getHomePage());
                }
            });
        } else {
            add(new Label("logOut"));
        }

    }

    private <T extends WebPage> Link<T> link(String id, Class<T> page) {
        return new Link<T>(id) {
            @Override
            public void onClick() {
                setResponsePage(page);
            }
        };
    }

}
