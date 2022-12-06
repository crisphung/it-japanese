package quan.hust.itjapanese.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuditable is a Querydsl query type for Auditable
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAuditable extends EntityPathBase<Auditable> {

    private static final long serialVersionUID = 942774266L;

    public static final QAuditable auditable = new QAuditable("auditable");

    public final QInitializationInfo _super = new QInitializationInfo(this);

    //inherited
    public final DateTimePath<java.sql.Timestamp> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("lastModifiedDate", java.sql.Timestamp.class);

    public final StringPath updatedBy = createString("updatedBy");

    public QAuditable(String variable) {
        super(Auditable.class, forVariable(variable));
    }

    public QAuditable(Path<? extends Auditable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditable(PathMetadata metadata) {
        super(Auditable.class, metadata);
    }

}

