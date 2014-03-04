package com.github.springular.server.component.employee.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSalaryBE is a Querydsl query type for SalaryBE
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalaryBE extends EntityPathBase<SalaryBE> {

    private static final long serialVersionUID = 935247001L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalaryBE salaryBE = new QSalaryBE("salaryBE");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final QEmployeeBE employee;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QSalaryBE(String variable) {
        this(SalaryBE.class, forVariable(variable), INITS);
    }

    public QSalaryBE(Path<? extends SalaryBE> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalaryBE(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalaryBE(PathMetadata<?> metadata, PathInits inits) {
        this(SalaryBE.class, metadata, inits);
    }

    public QSalaryBE(Class<? extends SalaryBE> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new QEmployeeBE(forProperty("employee")) : null;
    }

}

