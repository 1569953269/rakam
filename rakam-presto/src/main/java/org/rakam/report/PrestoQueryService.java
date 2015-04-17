package org.rakam.report;

import com.facebook.presto.sql.tree.QualifiedName;
import com.facebook.presto.sql.tree.Statement;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.rakam.collection.event.metastore.QueryMetadataStore;
import org.rakam.plugin.AbstractQueryService;
import org.rakam.util.QueryFormatter;

/**
 * Created by buremba <Burak Emre Kabakcı> on 02/04/15 05:43.
 */
@Singleton
public class PrestoQueryService extends AbstractQueryService {
    private final PrestoConfig prestoConfig;

    @Inject
    public PrestoQueryService(QueryMetadataStore database, PrestoConfig prestoConfig, QueryExecutor queryExecutor) {
        super(queryExecutor, database);
        this.prestoConfig = prestoConfig;
    }

    @Override
    protected String buildQuery(String project, Statement statement) {

        StringBuilder builder = new StringBuilder(project);
        // TODO: does cold storage supports schemas?
        new QueryFormatter(builder, node -> {
            QualifiedName prefix = node.getName().getPrefix().orElse(new QualifiedName(prestoConfig.getColdStorageConnector()));
            return prefix.getSuffix() + "." + project + "." + node.getName().getSuffix();
        }).process(statement, 0);

        return builder.toString();
    }

}
