package com.mercateo.kitchenapp.db.mongo.offers;

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
import com.mercateo.kitchenapp.data.Offer;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class OffersToMongoTransformer0Test {

    private OffersToMongoTransformer uut;

    @Before
    public void init() {
        initMocks(this);
        uut = new OffersToMongoTransformer();
    }

    @Test
    public void testApply() {

        // given
        Set<String> meals = Sets.newHashSet("a", "b");
        LocalDate day = LocalDate.of(2016, Month.OCTOBER, 7);
        Offer offer = new Offer(day, meals);

        // when
        DBObject dbo = uut.apply(offer);

        // then
        assertThat(dbo.get(MongoDbOffersConstants.DAY)).isEqualTo("2016-10-07");
        assertThat((BasicDBList) dbo.get(MongoDbOffersConstants.MEALS)) //
                .flatExtracting(new TitleExtractor()) //
                .containsOnly("a", "b");

    }

    public class TitleExtractor implements Extractor<Object, List<String>> {
        @Override
        public List<String> extract(Object input) {
            String title = (String) ((BasicDBObject) input).get(MongoDbOffersConstants.TITLE);
            return Collections.singletonList(title);
        }
    }

}
