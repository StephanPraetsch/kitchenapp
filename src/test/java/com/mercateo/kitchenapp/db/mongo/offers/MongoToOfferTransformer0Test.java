package com.mercateo.kitchenapp.db.mongo.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.mercateo.kitchenapp.data.Offer;
import com.mongodb.DBObject;

public class MongoToOfferTransformer0Test {

    private final OffersToMongoTransformer transformer = new OffersToMongoTransformer();

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
        Offer offer = new Offer(day, meals);

        DBObject dbObject = transformer.apply(offer);

        // when
        Offer mapped = uut.apply(dbObject);

        // then
        assertThat(mapped).isEqualTo(offer);

    }

}
