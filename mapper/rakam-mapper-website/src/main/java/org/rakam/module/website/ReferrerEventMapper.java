package org.rakam.module.website;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.snowplowanalytics.refererparser.CorruptYamlException;
import com.snowplowanalytics.refererparser.Parser;
import com.snowplowanalytics.refererparser.Referer;
import org.rakam.collection.Event;
import org.rakam.collection.FieldType;
import org.rakam.collection.SchemaField;
import org.rakam.collection.event.FieldDependencyBuilder;
import org.rakam.plugin.EventMapper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by buremba <Burak Emre Kabakcı> on 18/07/15 16:37.
 */
public class ReferrerEventMapper implements EventMapper {

    private final Parser parser;

    public ReferrerEventMapper() {
        try {
            parser = new Parser();
        }  catch (IOException | CorruptYamlException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void map(Event event, Iterable<Map.Entry<String, String>> extraProperties, InetAddress sourceAddress) {
        Object referrer = event.properties().get("referrer");
        Object url = event.properties().get("url");
        Referer parse;
        if(referrer != null && url != null) {
            try {
               parse = parser.parse(((String) referrer), ((String) url));
            } catch (URISyntaxException e) {
                return;
            }
            event.properties().put("referrer_medium", parse.medium.toString());
            event.properties().put("referrer_source", parse.source);
            event.properties().put("referrer_term", parse.term);
        }
    }

    @Override
    public void addFieldDependency(FieldDependencyBuilder builder) {
        builder.addFields("referrer_term", ImmutableList.of(
                new SchemaField("referrer_medium", FieldType.STRING, true),
                new SchemaField("referrer_source", FieldType.STRING, true),
                new SchemaField("referrer_term", FieldType.STRING, true)
        ));

    }
}
