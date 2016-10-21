package com.mercateo.kitchenapp.db.mongo.offer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.iterable.Extractor;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Offer;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class OfferToMongoTransformer0Test {

    private OfferToMongoTransformer uut;

    @Before
    public void init() {
        initMocks(this);
        uut = new OfferToMongoTransformer();
    }

    @Test
    public void testApply() {

        // given
        Set<String> meals = Sets.newHashSet("a", "b");
        LocalDate day = LocalDate.of(2016, Month.OCTOBER, 7);
        Set<Email> subscribed = Sets.newHashSet(Email.of("e1"), Email.of("e2"));
        Offer offer = new Offer(day, meals, subscribed);

        // when
        DBObject dbo = uut.apply(offer);

        // then
        assertThat(dbo.get(MongoDbOfferConstants.DAY)).isEqualTo("2016-10-07");
        assertThat((BasicDBList) dbo.get(MongoDbOfferConstants.MEALS)) //
                .flatExtracting(new TitleExtractor()) //
                .containsOnly("a", "b");
        assertThat((BasicDBList) dbo.get(MongoDbOfferConstants.SUBSCRIBED)) //
                .flatExtracting(new EmailExtractor()) //
                .containsOnly("e1", "e2");

    }

    public class TitleExtractor implements Extractor<Object, List<String>> {
        @Override
        public List<String> extract(Object input) {
            String title = (String) ((BasicDBObject) input).get(MongoDbOfferConstants.TITLE);
            return Collections.singletonList(title);
        }
    }

    public class EmailExtractor implements Extractor<Object, List<String>> {
        @Override
        public List<String> extract(Object input) {
            String title = (String) ((BasicDBObject) input).get(MongoDbOfferConstants.SUBSCRIBED);
            return Collections.singletonList(title);
        }
    }

}
