package quan.hust.itjapanese.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInitializationInfo is a Querydsl query type for InitializationInfo
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QInitializationInfo extends EntityPathBase<InitializationInfo> {

    private static final long serialVersionUID = 508703433L;

    public static final QInitializationInfo initializationInfo = new QInitializationInfo("initializationInfo");

    public final DateTimePath<java.sql.Timestamp> createdAt = createDateTime("createdAt", java.sql.Timestamp.class);

    public final StringPath createdBy = createString("createdBy");

    public QInitializationInfo(String variable) {
        super(InitializationInfo.class, forVariable(variable));
    }

    public QInitializationInfo(Path<? extends InitializationInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInitializationInfo(PathMetadata metadata) {
        super(InitializationInfo.class, metadata);
    }

}

