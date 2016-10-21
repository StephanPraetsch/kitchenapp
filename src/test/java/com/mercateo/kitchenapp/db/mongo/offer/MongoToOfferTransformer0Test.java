package com.mercateo.kitchenapp.db.mongo.offer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Offer;
import com.mongodb.DBObject;

public class MongoToOfferTransformer0Test {

    private final OfferToMongoTransformer transformer = new OfferToMongoTransformer();

    private MongoToOfferTransformer uut;

    @Before
    public void init() {
        initMocks(this);
        uut = new MongoToOfferTransformer();
    }

    @Test
    public void testApply() throws Exception {

        // given
        Set<String> meals = Sets.newHashSet("a", "b");
        LocalDate day = LocalDate.of(2016, Month.OCTOBER, 7);
        Set<Email> subscribed = Sets.newHashSet(Email.of("a"), Email.of("b"), Email.of("c"));
        Offer offer = new Offer(day, meals, subscribed);

        DBObject dbObject = transformer.apply(offer);

        // when
        Offer mapped = uut.apply(dbObject);

        // then
        assertThat(mapped).isEqualTo(offer);

    }

}
