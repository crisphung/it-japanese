package quan.hust.itjapanese.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = -1704388732L;

    public static final QBook book = new QBook("book");

    public final StringPath author = createString("author");

    public final StringPath category = createString("category");

    public final NumberPath<Integer> Id = createNumber("Id", Integer.class);

    public final StringPath level = createString("level");

    public final SetPath<User, QUser> likes = this.<User, QUser>createSet("likes", User.class, QUser.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final NumberPath<Integer> ranking = createNumber("ranking", Integer.class);

    public QBook(String variable) {
        super(Book.class, forVariable(variable));
    }

    public QBook(Path<? extends Book> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBook(PathMetadata metadata) {
        super(Book.class, metadata);
    }

}

